package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	// attributes
	private static final long 	serialVersionUID 	= 1L;
	public  static final int 	WIDTH 				= 320;				// Screen WIDTH
	public  static final int 	HEIGHT 				= WIDTH/16 * 9;		// Screen HEIGHT
	public  static final int 	SCALE				= 2;				// Width - Height scaling
	public  final String 		TITLE 				= "Frogger";		// JFrame title
	
	private boolean	running = false;	// variable to check whether the game is running
	private Thread thread;				// Game thread
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
	private Player frog;
	
	
	// methods
	public  void init(){
				
		BufferedImageLoader loader = new BufferedImageLoader();
				
		try{	
			spriteSheet = loader.loadImage("/sprite_sheet.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		
		frog = new Player(200, 200, this);
	}
	private synchronized void start(){
		// Method to initialize the game Thread
		// only works if the game is not running
		if(running)
			return;
		
		running = true;
		thread 	= new Thread(this);
		thread.start();
	}	
	private synchronized void stop(){
		// Method to stop the game thread and kill the game
		// only works if the game is running
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}	
	public  void run(){
		init();
		long 			lastTime 		= System.nanoTime();			// gets the current time
		final double 	amountOfTicks 	= 60.0; 						// 60 FPS
		double 			ns 				= 1000000000 / amountOfTicks;	// nanoseconds
		double 			delta 			= 0;							// difference in time
		int 			updates 		= 0;							//
		int				frames 			= 0;							// FPS variable
		long 			timer			= System.currentTimeMillis();	// game timer
		
		while (running){
			long now = System.nanoTime();			// gets current loop time
			delta += (now - lastTime) / ns;			// assigns delta
			lastTime = now;							// updates lastTime for the loop
			if (delta >= 1){
				tick();								// game tick has happened
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}	
	private void tick(){
		frog.tick();
	}	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy(); // Buffer handling
		
		if (bs == null){
			createBufferStrategy(3); // Triple buffering
			return;			
		}
		
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		frog.render(g);
		/////////////////////////////////
		g.dispose();
		bs.show();
	}
	public  void keyPressed(KeyEvent event){
		int key = event.getKeyCode();
		
		switch (key){
			case KeyEvent.VK_RIGHT:
				frog.setPosX(frog.getPosX() + 5);
				System.out.println("right pressed");
				break;
			case KeyEvent.VK_LEFT:
				frog.setPosX(frog.getPosX() - 5);
				break;
			case KeyEvent.VK_DOWN:
				frog.setPosY(frog.getPosY() + 5);
				break;
			case KeyEvent.VK_UP:
				frog.setPosY(frog.getPosY() - 5);
				break;		
		}
	}
	public  void keyReleased(KeyEvent event){
				
	}	
	
	
	// main
	public static void main(String[] args) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}

	// getters and setters
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
}