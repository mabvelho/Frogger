package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
	// attributes
	private double posX; 			// x coordinate
	private double posY; 			// y coordinate
	private BufferedImage player; 	// buffers the player's image
	
	// methods
	public Player(double posX, double posY, Game game){
		this.posX = posX;
		this.posY = posY;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		player = ss.grabImage(1, 1, 32, 32);
	}	
	public void tick(){
		
	}
	public void render(Graphics g){
		g.drawImage(player, (int)posX, (int)posY, null);
	}
	
	// getters and setters
	public double getPosX(){
		return posX;
	}
	public double getPosY(){
		return posX;
	}
	public void setPosX(double posY){
		this.posX = posX;
	}
	public void setPosY(double posY){
		this.posY = posY;
	}


}
