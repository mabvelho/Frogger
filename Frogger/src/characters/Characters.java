package characters;

public class Characters {
	// Generic class for all characters in Frogger
	private float startPosX;		// 		- Starting position, X coordinate used to
									// place the character on the screen
	private float startPosY;		// 		- Starting position, Y coordinate
	private	float currentPosX;		// 		Current X position for the character
	private boolean collision;		// 		- For frog-vehicle collision and frog lack
									// of collision 
	private float speedX;			// 		- All characters have speed, while
									// the frog has a vertical movement
	int priority = 0;					//		- Character's priority on the screen layers
									// vehicle = 1 > frog = 2 > log = 3
	
	
	public float getSpeedX(){
		return speedX;
	}
	public boolean getCollision(){
		return this.collision;
	}
	public float getPositionX(){
		return speedX;
	}	
}

