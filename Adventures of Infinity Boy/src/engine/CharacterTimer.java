package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jpanels.GamePanel;
import main.Init;
import main.Main;
import gui.Character;
import gui.GroundBlocks;

public class CharacterTimer implements ActionListener{

	//###########//
	// Character //
	
	private static Character character = GamePanel.character;
	
	private static double startingVel = 60 * Init.getScaleIndexY();				// Starting velocity of a jump
	private static double charAccel = 1;					// The acceleration when jumping
	private static double charGrav = 0.98;					// The affecting Gravity
	private static double charClock = 0;					// A clock to track how far in a jump is
	private static double charClockIncrease = 0.1;			// How much this clock increases
	private static double charMaxVel = startingVel * 1.2;	// The max velocity, used to regulate falling speeds
	private static double charVel = startingVel;			// The velocity of the character
	private static double charPointOfTurn = 6;				// The velocity in which the character will start to fall instead of increase
	
	private static boolean jumping = false;				// State - Is the character performing a jump
	private static boolean falling = false;				// State - Is the character performing a fall
	
	private static boolean onBotPlat = false; 			// Detect if character is currently on top of a platform
	private static boolean onTopPlat = false; 			// Detect if character is currently attached to the bottom of a platform
	
	public void actionPerformed(ActionEvent e) {
		// Checking if character collides with front of platform
		for(int i = 0; i < GamePanel.platform.length; i++) {
			if(Engine.intersects(character, GamePanel.platformCollision[i])) {
				gameOver();
			}
		}
		
		// Checking if character collides with an obstacle
		for(int i = 0; i < GamePanel.obstacle.length; i++) {
			if(Engine.intersects(character, GamePanel.obstacle[i])) {
				gameOver();
			}
		}

		// Checking if character is in contact with a platformPath
		boolean inContact = false;
		for(int i = 0; i < GamePanel.platformPath.length; i++) {
			if(Engine.intersects(character, GamePanel.platformPath[i])) {
				inContact = true;
				break;
			}
		}
		
		// Character falls of the edge of platform
		if(inContact == false && falling == false && (onTopPlat || onBotPlat)) {
			falling = true;
			resetPhys();
			charGrav = 1.005;
			charVel = charPointOfTurn;
			
			onBotPlat = false;
			onTopPlat = false;
		}
		
		// Jumping physics
		if (jumping) {
			charVel *= charAccel;
			charAccel *= charGrav;
			charClock += charClockIncrease;
			
			character.setLocation(character.getX(), (int) (character.getY() - (int) (charClock * charVel)));
			
			// collision with bottom of platform
			for(int i = GamePanel.platform.length; i < GamePanel.platformPath.length; i++) {
				if(Engine.intersects(character, GamePanel.platformPath[i])) {
					character.setLocation(character.getX(), GamePanel.platformPath[i].getY() - Init.getCharacterUnderPlatformOffset());
					jumping = false;
					falling = false;
					
					resetPhys();
					onBotPlat = true;
					onTopPlat = false;
				}
			}

			
			if(charVel <= charPointOfTurn) {
				jumping = false;
				falling = true;
				
				resetPhys();
				charVel = charPointOfTurn;
				charGrav = 1.005;
			}
		}
		
		// Falling Physics
		if(falling) {
			// Stop accelerating if max speed is reached
			if(!(charVel >= charMaxVel)) {
				charVel *= charAccel;
				charAccel *= charGrav;
				charClock += charClockIncrease;
			}
			
			int nextY = (int) (character.getY() + (int) (charClock * charVel));
			
			// Collision with ground, future position detecting due to preventing impact Y coordinate to not be inside the ground
			if(nextY >= GamePanel.ground[GamePanel.currentGround].getY() - character.getHeight()) {
				character.setLocation(character.getX(), GamePanel.ground[GamePanel.currentGround].getY() - Init.getCharacterGroundOffset());
				
				resetPhys();
				falling = false;
			}
			else {
				character.setLocation(character.getX(), nextY);
				
				// Collision with top of current platform
				for(int i = 0; i < GamePanel.platform.length; i++) {
					if(Engine.intersects(character, GamePanel.platformPath[i])) {
						character.setLocation(character.getX(), GamePanel.platformPath[i].getY() - Init.getCharacterPlatformOffset());
						falling = false;
						
						resetPhys();
						onTopPlat = true;
					}
				}
			}
		}
	}
	
	public static void jump() {
		if(jumping == false && falling == false) {
			resetPhys();
			if(onTopPlat) character.setLocation(character.getX(), GamePanel.platformPath[GamePanel.currentPlatform].getY() - character.getHeight());
			onTopPlat = false;
		
			jumping = true;
		}
	}
	
	public static void drop() {
		falling = true;
		resetPhys();
		charGrav = 1.005;
		charVel = charPointOfTurn;
		
		onBotPlat = false;
		onTopPlat = false;
	}
	
	public static void resetPhys() {
		charGrav = 0.98;
		charAccel = 1;
		charClock = 0;
		charVel = startingVel;
	}
	
	public static void fullReset() {
		character = GamePanel.character;
		
		startingVel = 60 * Init.getScaleIndexY();
		charAccel = 1;					
		charGrav = 0.98;					
		charClock = 0;					
		charClockIncrease = 0.1;			
		charMaxVel = startingVel * 1.2;	
		charVel = startingVel;			
		charPointOfTurn = 4;				
		
		jumping = false;				
		falling = false;				
		
		onBotPlat = false; 			
		onTopPlat = false;
	}
	
	public static void gameOver(){
		Engine.stopAudio();
		Main.showSubPanel(Main.getSubPanel("gameover"), true);
		GamePanel.pauseGame();
	}
	
	public static void setFalling(boolean bool) {
		falling = bool;
	}
	
	public static boolean getFalling() {
		return falling;
	}
	
	public static boolean getJumping() {
		return jumping;
	}
	
	public static void setOnTopPlat(boolean bool) {
		onTopPlat = bool;
	}
	
	public static boolean getOnTopPlat() {
		return onTopPlat;
	}
	
	public static void setOnBotPlat(boolean bool) {
		onBotPlat = bool;
	}
	
	public static boolean getOnBotPlat() {
		return onBotPlat;
	}
}
