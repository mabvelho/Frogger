package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	Game game;
	
	public KeyInput(Game game){
		this.game = game;
	}
	
	public void keyPressed(KeyEvent event){
		game.keyPressed(event);
	}
	public void keyReleased(KeyEvent event){
		game.keyReleased(event);
	}	
}
