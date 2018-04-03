package jpanels;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import engine.Engine;
import engine.KeyHandler;
import gui.Character;
import gui.GroundBlocks;
import gui.Platform;
import gui.PlatformCollision;
import gui.PlatformPath;
import main.Init;

public class GamePanel extends JPanel implements ActionListener{
	
	// Getting values from "init" file
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	static float scaleX = Init.getScaleIndexX();
	static float scaleY = Init.getScaleIndexY();
	
	// Keyhandler
	KeyHandler keyHandler = new KeyHandler();
	
	// Used for for loops that instantiates unique values for obstacles
	static int MAX_OBSTACLES = Init.getPlatformAmount(); 
	
	// Physic variables
	static int a = Init.getGravity();
	static int vZero = Init.getVZero();
	
	// Setting game animation movements
	static final int MOVEMENT_SPEED = (int) (Init.getCharacterMovement() * scaleX);
	static int currentSpeed = 0;
	
	// Setting timer object with preferred FPS
	public Timer timer = new Timer(Init.getFps(0), this);
	
	// Image for the character
	private static Image imgChar = Engine.getImage("Character1.png");

	// Images for the ground blocks
	private static Image[] imgGround = {Engine.getScaledImage(Engine.getImage("Ground1.png"), Init.getGroundSize(0), Init.getGroundSize(1)), 
										Engine.getScaledImage(Engine.getImage("Ground2.png"), Init.getGroundSize(0), Init.getGroundSize(1)), 
										Engine.getScaledImage(Engine.getImage("Ground3.png"), Init.getGroundSize(0), Init.getGroundSize(1)), 
										Engine.getScaledImage(Engine.getImage("Ground4.png"), Init.getGroundSize(0), Init.getGroundSize(1))
	};
	
	// Images for the obstacle blocks
	private static Image[] imgPlatform = {	Engine.getScaledImage(Engine.getImage("Obstacle1.png"), Init.getGroundSize(0), Init.getGroundSize(1)), 
											Engine.getScaledImage(Engine.getImage("Obstacle2.png"), Init.getGroundSize(0), Init.getGroundSize(1)),
											Engine.getScaledImage(Engine.getImage("Obstacle3.png"), Init.getGroundSize(0), Init.getGroundSize(1)),
											Engine.getScaledImage(Engine.getImage("Obstacle4.png"), Init.getGroundSize(0), Init.getGroundSize(1))
	};
	
	// Creating objects
	public static Character character = new Character();
	
	public static GroundBlocks[] ground = {new GroundBlocks(), new GroundBlocks(), new GroundBlocks()};

	public static Platform[] platform = {new Platform(), new Platform(), new Platform()};
	public static JPanel[] platformPath = {	new PlatformPath(), new PlatformPath(), new PlatformPath(),
											new PlatformPath(), new PlatformPath(), new PlatformPath()
	};
	public static JPanel[] platformCollision = {new PlatformCollision(), new PlatformCollision(), new PlatformCollision()};
	
	//########//
	// Ground //
	
	// Creating block dimensions
	final static int GROUND_HEIGHT = GroundBlocks.getGroundHeight();
	final static int GROUND_WIDTH = GroundBlocks.getGroundWidth();
	// Creating block positions
	static int[] groundX = {0, GROUND_WIDTH, GROUND_WIDTH * 2};
	static int groundY = resY - GROUND_HEIGHT;
	
	//###########//
	// Platforms //
	final static int PLATFORM_HEIGHT = Platform.getPlatformHeight();
	final static int PLATFORM_WIDTH = Platform.getPlatformWidth();
	
	final static int PLATFORM_PATH_HEIGHT = PlatformPath.getPlatformPathHeight();
	final static int PLATFORM_PATH_WIDTH = PlatformPath.getPlatformPathWidth();
	
	final static int PLATFORM_COLLISION_HEIGHT = PlatformCollision.getplatformCollisionHeight();
	final static int PLATFORM_COLLISION_WIDTH = PlatformCollision.getplatformCollisionWidth();
	
	// Used for storing unique coordinates for obstacles
	int[] platformX = new int[MAX_OBSTACLES];
	int[] platformY = new int[MAX_OBSTACLES];
	
	// Setting coordinate values into shorter names for easier use in ex. collision handling
	int[] oTop = new int[MAX_OBSTACLES];
	int[] oBot = new int[MAX_OBSTACLES];
	int[] oX = new int[MAX_OBSTACLES];
	
	// Settings coordinate system for placement of platforms
	static int platformYOffset = groundY;
	static int platformYIncrease = (int) (150 * scaleY);
	static int lastChoosenYPoint = 4;
	static boolean firstTimeSpawn = true;
	
	// Current platform in use
	static int currentPlatform = 0;
	
	//###########//
	// Character //
	final int CHARACTER_HEIGHT = character.getCharacterHeight();
	final int CHARACTER_WIDTH = character.getCharacterWidth();
	static boolean jumping = false;
	static double cClock = 0;
	static double jIncrease = 0.1;
	int direction = 1; // 0 = up, 1 = still, 2 = down
	int lastY = 0;
	static int y = 0;
	static boolean onTop = false;
	static int temp = 0;
	
	// Setting coordinate values into shorter names for easier use in ex. collision handling
	static int cTop;		// Character y value of top edge
	int cBot; 				// Character y value of bottom edge
	int cX;					// Character x value (constant)
	int cOrigin;			// Point of origin for character (Y). (resY - GROUND_HEIGHT - CHARACTER_HEIGHT)
	static int cTempY;
	static boolean onBotPlat = false; // Detect if character is currently on top of a platform
	static boolean onTopPlat = false; // Detect if character is currently attached to the bottom of a platform



	//#############//
	// Constructor //
	public GamePanel() {
		System.out.println("NOTE: A new MainThread has been initiated");
		// Allowing for a XY precise placement, beneficial for a JPanel with the purpose of multiple object placements.
		this.setLayout(null);
		timer.start();
		
		// Adding events to the key bindings
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "showPauseMenu");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("PAUSE"), "showPauseMenu");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "jump");
		this.getActionMap().put("showPauseMenu", keyHandler.getPauseAction());
		this.getActionMap().put("jump", keyHandler.getJumpAction());
		
		// Setting start values for obstacles
		for (int i = 0; i < platform.length; i++) {
			if(i == platform.length/2) firstTimeSpawn = false;
			
			platformX[i] = newCoord(0, i);
			platformY[i] = newCoord(1, i);
			
			platform[i].setLocation(platformX[i], platformY[i]);
			platform[i].setSize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
			platform[i].setObstacleImage(Engine.pickRandomImage(imgPlatform));
			
			platformPath[i].setLocation(platformX[i], platformY[i] - PLATFORM_PATH_HEIGHT);
			platformPath[i].setSize(PLATFORM_PATH_WIDTH, PLATFORM_PATH_HEIGHT);
			
			platformCollision[i].setLocation(platformX[i] - PLATFORM_COLLISION_WIDTH, platformY[i]);
			platformCollision[i].setSize(PLATFORM_COLLISION_WIDTH, PLATFORM_COLLISION_HEIGHT);
			
			oTop[i] = platform[i].getY() + 2;
			oBot[i] = oTop[i] + PLATFORM_HEIGHT - 4;
			oX[i] = platform[i].getX();
		}
		
		for(int i = platform.length; i < platformPath.length; i++) {
			platformPath[i].setLocation(platformX[i - platform.length], platformY[i - platform.length] + PLATFORM_PATH_HEIGHT);
			platformPath[i].setSize(PLATFORM_PATH_WIDTH, PLATFORM_PATH_HEIGHT);
		}
		
		// Setting start values for character
		character.setLocation((int) (resX * 0.15), resY - (GroundBlocks.getGroundHeight() + CHARACTER_HEIGHT));
		character.setSize(CHARACTER_WIDTH, CHARACTER_HEIGHT);
		character.setCharacterImage(imgChar);
		cTop = character.getY();
		cBot = cTop + CHARACTER_HEIGHT;
		cX = character.getX();
		cOrigin = resY - (GroundBlocks.getGroundHeight() + CHARACTER_HEIGHT);
		cTempY = cOrigin;
		
		// Setting ground block size, starting location and image
		for(int i = 0; i < ground.length; i++) {
			ground[i].setLocation(groundX[i], resY - GROUND_HEIGHT);
			ground[i].setSize(GROUND_WIDTH, GROUND_HEIGHT);
			ground[i].setGroundImage(Engine.pickRandomImage(imgGround));
		}
		
		// Adding Ground
		for(int i = 0; i < ground.length; i++) this.add(ground[i]);
		
		// Adding Platforms
		for(int i = 0; i < platform.length; i++) {
			this.add(platform[i]);
			this.add(platformPath[i]);
			this.add(platformCollision[i]);
		}
		
		for(int i = platform.length; i < platformPath.length; i++) {
			this.add(platformPath[i]);
		}
		
		// Adding Character
		this.add(character);
	}



	//################//
	// ActionListener //
	public void actionPerformed(ActionEvent arg0) {
		//changing current platform in use
		for(int i = 0; i < platform.length; i++) {
			if((platform[i].getX() == character.getX())) {
				currentPlatform = i;
			}
		}
		
		// Character falls off obstacle
		if (Engine.intersects(character, platformPath[currentPlatform]) == false) {
			jIncrease = 0.1;
			onTopPlat = false;
			onBotPlat = false;
		}
		
		for(int i = 0; i < ground.length; i++) {
			// Respawning ground block
			if(groundX[i] <= -resX) {
				groundX[i] = GROUND_WIDTH * 2 - currentSpeed;
				ground[i].setGroundImage(Engine.pickRandomImage(imgGround));
			}
			
			// Animating ground block
			ground[i].setLocation(groundX[i] -= currentSpeed, resY - GROUND_HEIGHT);
		}
		
		// Collision with bottom of platform
		for(int i = platform.length; i < platformPath.length; i++) {
			if(Engine.intersects(character, platformPath[i]) && !onBotPlat) {
				onBotPlat = true;
				temp = y;
			}
		}
		
		// Collision with top of platform
		for(int i = 0; i < platform.length; i++) {
			if(Engine.intersects(character, platformPath[i]) && !onTopPlat) {
				onTopPlat = true;
				jIncrease = 0;
			}
		}
		
		// Collision with collision part of platform.. Punishable by Death!
		for(int i = 0; i < platform.length; i++) {
			if(Engine.intersects(character, platformCollision[i]) && !onTopPlat) {
				System.out.println("NOTE: Player collided with platform front");
				System.exit(0);
			}
		}
		
		for (int i = 0; i < platform.length; i++) {
			// Resetting platform block
			if ((platformX[i] + PLATFORM_WIDTH) <= -10) {
				platformX[i] = newCoord(0, i);
				platformY[i] = newCoord(1, i);
				oTop[i] = platform[i].getY();
				oBot[i] = oTop[i] + PLATFORM_HEIGHT;
				platform[i].setObstacleImage(Engine.pickRandomImage(imgPlatform));
			}
			
			// Animating platform block
			platform[i].setLocation(platformX[i] -= currentSpeed, platformY[i]);
			platformPath[i].setLocation(platformX[i], platformY[i] - PLATFORM_PATH_HEIGHT);
			platformPath[i + platform.length].setLocation(platformX[i - platform.length + platform.length], platformY[i - platform.length + platform.length] + PLATFORM_HEIGHT);
			platformCollision[i].setLocation(platformX[i] - PLATFORM_COLLISION_WIDTH, platformY[i]);
			oX[i] = platform[i].getX();
		}
		
		// Jumping physics
		if (jumping) {
			y = (int) ((vZero * cClock) - ((a*Math.pow(cClock, 2))/2)); // y=V*t*(at^2)/2
			
			if (lastY - y < 0) direction = 2; 		// up
			else if (lastY - y > 0) direction = 0; 	// down
			else direction = 1; 					// still

			if (onBotPlat) {
				if (y == temp) {
					jIncrease = 0;
				}
				else {
					jIncrease = 0.1;
				}
				y = temp;
				cClock += jIncrease;
				lastY = y;
			}
			else {				
				character.setLocation(cX, cTempY - y);
				cTop = character.getY();				
				cClock += jIncrease;
				lastY = y;
			}
		}
		
		// Character stays on ground
		if ((cTempY - y) >= cOrigin && jumping) {
			character.setLocation(cX, cOrigin);
			cClock = 0;
			y = 0;
			cTop = cOrigin;
			cTempY = cTop;
			jumping = false;
			temp = 0;
		}
	}

	// Method for getting new coordinates for obstacles
	public static int newCoord(int axis, int index) {
		int pos = 0;
		int selectedGround = 0;
		
		// What ground object is the farthest away?
		for(int i = 0; i < groundX.length; i++) {
			if(groundX[i] > groundX[selectedGround]) selectedGround = i;
		}
		
		switch(axis) {
		// X
		case 0:
			if(firstTimeSpawn) {
				pos = resX;
				firstTimeSpawn = false;
			}
			else {
				if(index - 1 >= 0) pos = platform[index - 1].getX() + PLATFORM_WIDTH;
				else {
					pos = platform[platform.length - 1].getX() + PLATFORM_WIDTH;
				}
			}
		break;
		
		// Y	
		case 1:
			int foo = (int) (100 * Math.random());
			
			if(foo >= 0 && foo < 33 && (lastChoosenYPoint != 1)) {
				pos = platformYOffset - (platformYIncrease + Init.getPlatformSize(1));
				lastChoosenYPoint = 1;
			}
			else if(foo >= 33 && foo < 67 && (lastChoosenYPoint != 2)) {
				pos = platformYOffset - (platformYIncrease * 2 + Init.getPlatformSize(1) * 2);
				lastChoosenYPoint = 2;
			}
			else if(foo >= 67 && foo <= 100 && (lastChoosenYPoint != 3)){
				pos = platformYOffset - (platformYIncrease * 3 + Init.getPlatformSize(1) * 3);
				lastChoosenYPoint = 3;
			}
			
			if(pos == 0) return newCoord(1, 0);
		break;
		}
		
		return pos;
	}
	
	// Starting new Object
	public static void startMainThread() {
		int random = (int) (10 * Math.random());
		
//		if(Init.settingsData[0] == 1) {
//			if(random <= 5) Engine.playAudio("easy1.wav");
//			else Engine.playAudio("easy2.wav");
//		} else if(Init.settingsData[0] == 2) {
//			if(random <= 5) Engine.playAudio("normal1.wav");
//			else Engine.playAudio("normal2.wav");
//		}
//		else {
//			if(random <= 5) Engine.playAudio("hard3.wav");
//			else Engine.playAudio("hard3.wav");
//		}
	}

	// Jump
	public static boolean jumping() {
		return jumping;
	}

	public static void jump() {
		if(onTopPlat) {
			character.setLocation(character.getX(), platform[currentPlatform].getY() - character.getHeight());
		};
		
		cClock = 0.1;
		jIncrease = 0.1;
		y = 1;
		cTempY = character.getY();
		onTopPlat = false;
		onBotPlat = false;
		temp = 0;
		jumping = true;
	}
	
	public static boolean onPlat() {
		return onTopPlat;
	}
	
	// Pausing & Resuming
	public static void resumeGame() {
		currentSpeed = MOVEMENT_SPEED;
	}
	
	public static void pauseGame() {
		currentSpeed = 0;
	}
}
