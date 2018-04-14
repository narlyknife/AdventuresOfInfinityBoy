package jpanels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import engine.CharacterTimer;
import engine.Engine;
import engine.KeyHandler;
import gui.Character;
import gui.GroundBlocks;
import gui.Obstacle;
import gui.Platform;
import gui.PlatformCollision;
import gui.PlatformPath;
import gui.gameBackground;
import main.Init;

public class GamePanel extends JPanel implements ActionListener{

	// Getting values from "init" file
	private static int resX = Init.getResX();
	private static int resY = Init.getResY();
	
	private static float scaleX = Init.getScaleIndexX();
	private static float scaleY = Init.getScaleIndexY();
	
	// Keyhandler
	private KeyHandler keyHandler = new KeyHandler();
	
	// CharacterTimer
	private ActionListener charActLis = new CharacterTimer();
	
	// Used for for loops that instantiates unique values for platforms
	private static int MAX_PLATFORMS = Init.getPlatformAmount(); 
	
	// Setting game animation movements
	private static final int MOVEMENT_SPEED = (int) (Init.getCharacterMovement() * scaleX);
	private static int currentSpeed = 0;
	
	// Setting timer object with preferred FPS
	private Timer timer = new Timer(Init.getFps(0), this);
	private Timer charTimer = new Timer(Init.getFps(1), charActLis);
	
	// Image for the character
	private static Image imgChar = Engine.getImage("Character1.png");
	
	// Image for the background
	private static Image imgBack = Engine.getImage("gameplayBackground.png");

	// Images for the ground blocks
	private static Image[] imgGround = {Engine.getScaledImage(Engine.getImage("Ground1.png"), Init.getGroundSize(0), Init.getGroundSize(1)), 
										Engine.getScaledImage(Engine.getImage("Ground2.png"), Init.getGroundSize(0), Init.getGroundSize(1)), 
										Engine.getScaledImage(Engine.getImage("Ground3.png"), Init.getGroundSize(0), Init.getGroundSize(1)), 
										Engine.getScaledImage(Engine.getImage("Ground4.png"), Init.getGroundSize(0), Init.getGroundSize(1))
	};
	
	// Images for the platform blocks
	private static Image[] imgPlatform = {	Engine.getScaledImage(Engine.getImage("platform1.png"), Init.getPlatformSize(0), Init.getPlatformSize(1)), 
											Engine.getScaledImage(Engine.getImage("platform2.png"), Init.getPlatformSize(0), Init.getPlatformSize(1)),
											Engine.getScaledImage(Engine.getImage("platform3.png"), Init.getPlatformSize(0), Init.getPlatformSize(1)),
											Engine.getScaledImage(Engine.getImage("platform4.png"), Init.getPlatformSize(0), Init.getPlatformSize(1))
	};
	
	// Images for the obstacles
	private static Image[] imgObstacle = {	Engine.getScaledImage(Engine.getImage("obstacle1.png"), Init.getObstacleSize(0), Init.getObstacleSize(1)), 
											Engine.getScaledImage(Engine.getImage("obstacle2.png"), Init.getObstacleSize(0), Init.getObstacleSize(1)),
											Engine.getScaledImage(Engine.getImage("obstacle3.png"), Init.getObstacleSize(0), Init.getObstacleSize(1)),
											Engine.getScaledImage(Engine.getImage("obstacle4.png"), Init.getObstacleSize(0), Init.getObstacleSize(1))
	};
	
	// Images for the obstacles Large
	private static Image[] imgObstacleLarge = {	Engine.getScaledImage(Engine.getImage("obstacle1.png"), Init.getObstacleLargeSize(0), Init.getObstacleLargeSize(1)), 
												Engine.getScaledImage(Engine.getImage("obstacle2.png"), Init.getObstacleLargeSize(0), Init.getObstacleLargeSize(1)),
												Engine.getScaledImage(Engine.getImage("obstacle3.png"), Init.getObstacleLargeSize(0), Init.getObstacleLargeSize(1)),
												Engine.getScaledImage(Engine.getImage("obstacle4.png"), Init.getObstacleLargeSize(0), Init.getObstacleLargeSize(1))
	};
	
	// Creating objects
	public static Character character = new Character();
	
	public static gameBackground[] background = {new gameBackground(), new gameBackground()};
	
	public static GroundBlocks[] ground = {new GroundBlocks(), new GroundBlocks(), new GroundBlocks()};

	public static Platform[] platform = {new Platform(), new Platform(), new Platform()};
	
	public static Obstacle[] obstacle = {	new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(),
											new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(),
											new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle()
											
	};
	
	public static JPanel[] platformPath = {	new PlatformPath(), new PlatformPath(), new PlatformPath(),
											new PlatformPath(), new PlatformPath(), new PlatformPath()
	};
	public static JPanel[] platformCollision = {new PlatformCollision(), new PlatformCollision(), new PlatformCollision()};
	
	//############//
	// Background //
	
	// Creating background dimensions
	private final static int BACKGROUND_HEIGHT = gameBackground.getGameBackgroundHeight();
	private final static int BACKGROUND_WIDTH = gameBackground.getGameBackgroundWidth();
	
	// Creating background positions
	private static int[] backX = {0, BACKGROUND_WIDTH};
	
	//########//
	// Ground //
	
	// Creating block dimensions
	private final static int GROUND_HEIGHT = GroundBlocks.getGroundHeight();
	private final static int GROUND_WIDTH = GroundBlocks.getGroundWidth();
	
	// Creating block positions
	private static int[] groundX = {0, GROUND_WIDTH, GROUND_WIDTH * 2};
	private static int groundY = resY - GROUND_HEIGHT;
	
	// Current platform in use
	public static int currentGround = 0;
	
	//###########//
	// Platforms //
	private final static int PLATFORM_HEIGHT = Platform.getPlatformHeight();
	public final static int PLATFORM_WIDTH = Platform.getPlatformWidth();
	
	public final static int PLATFORM_PATH_HEIGHT = PlatformPath.getPlatformPathHeight();
	private final static int PLATFORM_PATH_WIDTH = PlatformPath.getPlatformPathWidth();
	
	public final static int PLATFORM_COLLISION_HEIGHT = PlatformCollision.getplatformCollisionHeight();
	private final static int PLATFORM_COLLISION_WIDTH = PlatformCollision.getplatformCollisionWidth();
	
	// Used for storing unique coordinates for obstacles
	private static int[] platformX = new int[MAX_PLATFORMS];
	private static int[] platformY = new int[MAX_PLATFORMS];

	// Settings coordinate system for placement of platforms
	private static int platformYOffset = groundY;
	private static int platformYIncrease = (int) (135 * scaleY);
	private static int lastChoosenYPoint = 3;
	private static boolean firstTimeSpawn = true;
	
	// Current platform in use
	public static int currentPlatform = 0;
	public static int lastPlatform = 2;
	
	//###########//
	// Obstacles //
	private final static int OBSTACLE_HEIGHT = (int) (Obstacle.getObstacleHeight() * scaleY);
	private final static int OBSTACLE_WIDTH = (int) (Obstacle.getObstacleWidth() * scaleX);
	
	private final static int OBSTACLE_LARGE_HEIGHT = (int) (Obstacle.getObstacleLargeHeight() * scaleY);
	private final static int OBSTACLE_LARGE_WIDTH = (int) (Obstacle.getObstacleLargeWidth() * scaleX);
	
	// Divide each platform into 6 pieces
	private final static int OBSTACLE_INCREASE = PLATFORM_WIDTH / 6;
	
	//###########//
	// Character //
	private final static int CHARACTER_HEIGHT = character.getCharacterHeight();
	private final static int CHARACTER_WIDTH = character.getCharacterWidth();

	//#############//
	// Constructor //
	public GamePanel() {
		System.out.println("NOTE: A new MainThread has been initiated");
		// Allowing for a XY precise placement, beneficial for a JPanel with the purpose of multiple object placements.
		this.setLayout(null);
		
		// Adding events to the key bindings
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "showPauseMenu");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("PAUSE"), "showPauseMenu");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "jump");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "drop");
		this.getActionMap().put("showPauseMenu", keyHandler.getPauseAction());
		this.getActionMap().put("jump", keyHandler.getJumpAction());
		this.getActionMap().put("drop", keyHandler.getDropAction());
		
		// Settings ground background color to transparent
		for(int i = 0; i < ground.length; i++) {
			ground[i].setBackground(new Color(0, 0, 0, 0));
		}
		
		// Setting platform background to transparent
		for(int i = 0; i < platform.length; i++) {
			platform[i].setBackground(new Color(0, 0, 0, 0));
		}
		
		// Adding Background
		for(int i = 0; i < background.length; i++) {
			this.add(background[i]);
		}
		
		// Adding Character
		this.add(character, 0);
		
		// Adding obstacles
		for(int i = 0; i < obstacle.length; i++) {
			this.add(obstacle[i], 0);
		}
		
		// Adding Ground
		for(int i = 0; i < ground.length; i++) {
			this.add(ground[i], 0);
		}
		
		// Adding Platforms
		for(int i = 0; i < platform.length; i++) {
			this.add(platform[i], 0);
			this.add(platformPath[i], 0);
			this.add(platformCollision[i], 0);
		}
		
		for(int i = platform.length; i < platformPath.length; i++) {
			this.add(platformPath[i], 0);
		}
		
		timer.start();
		charTimer.start();
	}



	//################//
	// ActionListener //
	public void actionPerformed(ActionEvent arg0) {
		//changing current platform in use
		for(int i = 0; i < platform.length; i++) {
			if((platform[i].getX() == character.getX())) {
				currentPlatform = i;
				
				if(i == 0) lastPlatform = 2;
				else lastPlatform = i - 1;
			}
		}
		
		//changing current ground in use
		for(int i = 0; i < ground.length; i++) {
			if(ground[i].getX() == character.getX()) {
				currentGround = i;
			}
		}
		
		for(int i = 0; i < background.length; i++) {
			// Respawning background
			if(background[i].getX() <= -BACKGROUND_WIDTH) {
				if(i == 0) background[0].setLocation(background[1].getX() + BACKGROUND_WIDTH, 0);
				if(i == 1) background[1].setLocation(background[0].getX() + BACKGROUND_WIDTH, 0);;
			}
			
			// Animating background
			background[i].setLocation(background[i].getX() - (currentSpeed / 2), 0);
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
		
		for (int i = 0; i < platform.length; i++) {
			// Resetting platform block
			if ((platformX[i] + PLATFORM_WIDTH) <= -10) {
				platformX[i] = newCoord(0, i);
				platformY[i] = newCoord(1, i);
				platform[i].setPlatformImage(Engine.pickRandomImage(imgPlatform));
				
				resetHarLevelObstacles(i);
			}
			
			// Animating platform block
			platform[i].setLocation(platformX[i] -= currentSpeed, platformY[i]);
			platformPath[i].setLocation(platformX[i], platformY[i] - PLATFORM_PATH_HEIGHT);
			platformPath[i + platform.length].setLocation(platformX[i - platform.length + platform.length], platformY[i - platform.length + platform.length] + PLATFORM_HEIGHT);
			platformCollision[i].setLocation(platformX[i], platformY[i] + (PLATFORM_COLLISION_HEIGHT * 2));
		}
		
		for(int i = 0; i < obstacle.length; i++) {
			obstacle[i].setLocation(obstacle[i].getX() - currentSpeed, obstacle[i].getY());
		}
	}
	
	// Method for reseting a obstacle group - Hard Mode
	public static void resetHarLevelObstacles(int platformIndex) {
		// Resetting obstacle blocks
		int selectedXPart = 0;
		int x;
		int y;
		
		for(int j = 6 * platformIndex; j < (6 * (platformIndex + 1) - 1); j++) {
			int randomOffset = (int) (Math.random() * OBSTACLE_INCREASE);
			
			// Next "third part section" of the platform 
			if(j % 2 == 0) selectedXPart += 2;
			
			if(selectedXPart != 6) x = platformX[platformIndex] + (OBSTACLE_INCREASE * (selectedXPart - 1)) + randomOffset;
			else x = platformX[platformIndex] + (OBSTACLE_INCREASE * (selectedXPart - 1)) + randomOffset - OBSTACLE_WIDTH;
			
			// Y CALCULATION //
			
			if(j % 2 == 0) {
				int temp = (int) (Math.random() * 10);
				if(temp <= 5) y = ground[platformIndex].getY() - Init.getObstacleGroundOffset();
				else y = platformY[platformIndex] + Init.getObstaclePlatformOffset();
			}
			else y = platformY[platformIndex] - Init.getObstacleLargePlatformOffset();
			obstacle[j].setLocation(x, y);
		}
	}

	// Method for setting the starting positions of all obstacles - Hard Mode
	public static void setHardLevelObstacles() {
		// Setting values for obstacles
		int selectedPlatform = 0;
		int selectedXPart = 0;
		for(int i = 0; i < obstacle.length; i++) {
			if(i % 2 == 0) {
				obstacle[i].setSize(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
				obstacle[i].setObstacleImage(Engine.pickRandomImage(imgObstacle));
			}
			else {
				obstacle[i].setSize(OBSTACLE_LARGE_WIDTH, OBSTACLE_LARGE_HEIGHT);
				obstacle[i].setObstacleImage(Engine.pickRandomImage(imgObstacleLarge));
			}

			int x;
			int y;
			int randomOffset = (int) (Math.random() * OBSTACLE_INCREASE);
				
			// X CALCULATION //
			
			// Place on second platform
			if((float) i / 6 == 1) {
				selectedPlatform++;
				selectedXPart = 0;
			}
			// Place on third and last platform
			if((float) i / 6 == 2) {
				selectedPlatform++;
				selectedXPart = 0;
			}
			// Next "third part section" of the platform 
			if(i % 2 == 0) selectedXPart += 2;
			
			if(selectedXPart != 6) x = platform[selectedPlatform].getX() + (OBSTACLE_INCREASE * (selectedXPart - 1)) + randomOffset;
			else x = platform[selectedPlatform].getX() + (OBSTACLE_INCREASE * (selectedXPart - 1)) + randomOffset - OBSTACLE_LARGE_WIDTH;
			
			// Y CALCULATION //
			
			if(i % 2 == 0) {
				int temp = (int) (Math.random() * 10);
				if(temp <= 5) y = ground[selectedPlatform].getY() - Init.getObstacleGroundOffset();
				else y = platform[selectedPlatform].getY() + Init.getObstaclePlatformOffset();
			}
			else y = platform[selectedPlatform].getY() - Init.getObstacleLargePlatformOffset();
			
			obstacle[i].setLocation(x, y);
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
				pos = platformYOffset - (platformYIncrease + PLATFORM_HEIGHT);
				lastChoosenYPoint = 1;
			}
			else if(foo >= 33 && foo < 67 && (lastChoosenYPoint != 2)) {
				pos = platformYOffset - (platformYIncrease * 2 + PLATFORM_HEIGHT * 2);
				lastChoosenYPoint = 2;
			}
			else if(foo >= 67 && foo <= 100 && (lastChoosenYPoint != 3)){
				pos = platformYOffset - (platformYIncrease * 3 + PLATFORM_HEIGHT * 3);
				lastChoosenYPoint = 3;
			}
			
			if(pos == 0) return newCoord(1, 0);
			
		break;
		}
		
		return pos;
	}
	
	// Starting new Object
	public static void startMusic() {
		int random = (int) (10 * Math.random());
		
		if(Init.settingsData[0] == 1) {
			if(random <= 5) Engine.playAudio("easy1.wav");
			else Engine.playAudio("easy2.wav");
		} else if(Init.settingsData[0] == 2) {
			if(random <= 5) Engine.playAudio("normal1.wav");
			else Engine.playAudio("normal2.wav");
		}
		else {
			if(random <= 5) Engine.playAudio("hard3.wav");
			else Engine.playAudio("hard3.wav");
		}
	}
	
	// Pausing & Resuming
	public static void resumeGame() {
		currentSpeed = MOVEMENT_SPEED;
	}
	
	public static void pauseGame() {
		currentSpeed = 0;
	}
	
	public static void reset() {
		groundX[0] = 0;
		groundX[1] = GROUND_WIDTH;
		groundX[2] = GROUND_WIDTH * 2;
		firstTimeSpawn = true;
		currentPlatform = 0;
		
		// Settings background size, location and image
		for(int i = 0; i < background.length; i++) {
			background[i].setLocation(backX[i], 0);
			background[i].setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
			background[i].setGameBackgroundImage(imgBack);
		}
		
		// Setting ground block size, starting location and image
		for(int i = 0; i < ground.length; i++) {
			ground[i].setLocation(groundX[i], resY - GROUND_HEIGHT);
			ground[i].setSize(GROUND_WIDTH, GROUND_HEIGHT);
			ground[i].setGroundImage(Engine.pickRandomImage(imgGround));
		}
		
		// Setting start values for platforms
		for (int i = 0; i < platform.length; i++) {
			if(i == platform.length/2) firstTimeSpawn = false;
			
			platformX[i] = newCoord(0, i);
			platformY[i] = newCoord(1, i);
			
			platform[i].setLocation(platformX[i], platformY[i]);
			platform[i].setSize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
			platform[i].setPlatformImage(Engine.pickRandomImage(imgPlatform));
			
			platformPath[i].setLocation(platformX[i], platformY[i] - PLATFORM_PATH_HEIGHT);
			platformPath[i].setSize(PLATFORM_PATH_WIDTH, PLATFORM_PATH_HEIGHT);
			
			platformCollision[i].setLocation(platformX[i], platformY[i] + (PLATFORM_COLLISION_HEIGHT * 2));
			platformCollision[i].setSize(PLATFORM_COLLISION_WIDTH, PLATFORM_COLLISION_HEIGHT);
		}
		
		for(int i = platform.length; i < platformPath.length; i++) {
			platformPath[i].setLocation(platformX[i - platform.length], platformY[i - platform.length] + PLATFORM_PATH_HEIGHT);
			platformPath[i].setSize(PLATFORM_PATH_WIDTH, PLATFORM_PATH_HEIGHT);
		}
		
		setHardLevelObstacles();
		CharacterTimer.fullReset();
		
		// Setting start values for character
		character.setLocation((int) (resX * 0.15), resY - (GroundBlocks.getGroundHeight() + Init.getCharacterGroundOffset()));
		character.setSize(CHARACTER_WIDTH, CHARACTER_HEIGHT);
		character.setCharacterImage(imgChar);
	}
}