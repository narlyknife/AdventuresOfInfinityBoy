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
import gui.Obstacles;
import main.Init;
import main.Main;

public class GamePanel extends JPanel implements ActionListener{
	
	// Getting values from "init" file
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	static float scaleX = Init.getScaleIndexX();
	static float scaleY = Init.getScaleIndexY();
	
	// Keyhandler
	KeyHandler keyHandler = new KeyHandler();
	
	// Used for for loops that instantiates unique values for obstacles
	static int MAX_OBSTACLES = Init.getObstacleAmount(); 
	
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
	private static Image[] imgObstacle = {	Engine.getScaledImage(Engine.getImage("Obstacle1.png"), Init.getGroundSize(0), Init.getGroundSize(1)), 
											Engine.getScaledImage(Engine.getImage("Obstacle2.png"), Init.getGroundSize(0), Init.getGroundSize(1)),
											Engine.getScaledImage(Engine.getImage("Obstacle3.png"), Init.getGroundSize(0), Init.getGroundSize(1)),
											Engine.getScaledImage(Engine.getImage("Obstacle4.png"), Init.getGroundSize(0), Init.getGroundSize(1))
	};
	
	// Creating objects
	public static Character character = new Character();
	
	public static GroundBlocks[] ground = {new GroundBlocks(), new GroundBlocks(), new GroundBlocks()};

	public static Obstacles[] obstacle = {new Obstacles(), new Obstacles(), new Obstacles()};
	
	//########//
	// Ground //
	
	// Creating block dimensions
	final static int GROUND_HEIGHT = GroundBlocks.getGroundHeight();
	final static int GROUND_WIDTH = GroundBlocks.getGroundWidth();
	// Creating block positions
	static int[] groundX = {0, GROUND_WIDTH, GROUND_WIDTH * 2};
	static int groundY = resY - GROUND_HEIGHT;
	
	//###########//
	// Obstacles //
	final static int OBSTACLE_HEIGHT = Obstacles.getObstacleHeight();
	final static int OBSTACLE_WIDTH = Obstacles.getObstacleWidth();
	
	// Used for storing unique coordinates for obstacles
	int[] obstacleX = new int[MAX_OBSTACLES];
	int[] obstacleY = new int[MAX_OBSTACLES];
	
	// Setting coordinate values into shorter names for easier use in ex. collision handling
	int[] oTop = new int[MAX_OBSTACLES];
	int[] oBot = new int[MAX_OBSTACLES];
	int[] oX = new int[MAX_OBSTACLES];
	
	// Settings coordinate system for placement of obstacles
	static int obstacleYOffset = groundY;
	static int obstacleYIncrease = (int) (160 * scaleY);
	static int lastChoosenYPoint = 4;
	static boolean firstTimeSpawn = true;
	
	//###########//
	// Character //
	final int CHARACTER_HEIGHT = character.getCharacterHeight();
	final int CHARACTER_WIDTH = character.getCharacterWidth();
	static boolean jumping = false;
	static double cClock = 0;
	static double jIncrease = 0.1;
	int direction = 1; // 0 = up, 1 = still, 2 = down
	int lastY = 0, y = 0;
	static boolean onTop = false;
	
	// Setting coordinate values into shorter names for easier use in ex. collision handling
	static int cTop;		// Character y value of top edge
	int cBot; 				// Character y value of bottom edge
	int cX;					// Character x value (constant)
	int cOrigin;			// Point of origin for character (Y). (resY - GROUND_HEIGHT - CHARACTER_HEIGHT)
	static int cTempY;



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
		for (int i = 0; i < obstacle.length; i++) {
			if(i == obstacle.length/2) firstTimeSpawn = false;
			obstacleX[i] = newCoord(0, i);
			obstacleY[i] = newCoord(1, i);
			obstacle[i].setLocation(obstacleX[i], obstacleY[i]);
			obstacle[i].setSize(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
			obstacle[i].setObstacleImage(Engine.pickRandomImage(imgObstacle));
			
			oTop[i] = obstacle[i].getY() + 2;
			oBot[i] = oTop[i] + OBSTACLE_HEIGHT - 4;
			oX[i] = obstacle[i].getX();
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
		
		// Adding Obstacles
		for(int i = 0; i < obstacle.length; i++) this.add(obstacle[i]);
		
		// Adding Character
		this.add(character);
	}



	//################//
	// ActionListener //
	public void actionPerformed(ActionEvent arg0) {
		for(int i = 0; i < ground.length; i++) {
			// Respawning ground block
			if(groundX[i] <= -resX) {
				groundX[i] = GROUND_WIDTH * 2 - currentSpeed;
				ground[i].setGroundImage(Engine.pickRandomImage(imgGround));
			}
			
			// Animating ground block
			ground[i].setLocation(groundX[i] -= currentSpeed, resY - GROUND_HEIGHT);
		}
		
		for (int i = 0; i < obstacle.length; i++) {
			// Resetting obstacle block
			if ((obstacleX[i] + OBSTACLE_WIDTH) <= -10) {
				obstacleX[i] = newCoord(0, i);
				obstacleY[i] = newCoord(1, i);
				oTop[i] = obstacle[i].getY();
				oBot[i] = oTop[i] + OBSTACLE_HEIGHT;
				obstacle[i].setObstacleImage(Engine.pickRandomImage(imgObstacle));
			}
			
			// Collision handling between obstacles and character. Needs expansion for later use if it is
			// to be used for the character to stand on top of the obstacles.
			//
			// Works by checking the front corners of the obstacle and seeing if they are within the front
			// corners of the character.
			if ((oX[i] - (cX + CHARACTER_WIDTH) <= (4) && (oX[i] - (cX + CHARACTER_WIDTH) <= ((4) * -1)) && (oX[i] > cX + CHARACTER_WIDTH/2)) && ((oTop[i] >= cTop && oTop[i] <= cBot) || (oBot[i] >= cTop && oBot[i] <= cBot))) {
				currentSpeed = 0;
				jumping = false;
				System.out.println("Character collided frontally!");
				System.exit(0);
			}

			
			// Animating obstacle block
			obstacle[i].setLocation(obstacleX[i] -= currentSpeed, obstacleY[i]);
			oX[i] = obstacle[i].getX();
		}
		
		// Jumping physics
		if (jumping) {
			y = (int) ((vZero * cClock) - ((a*Math.pow(cClock, 2))/2)); // y=V*t*(at^2)/2
			
			if (lastY - y < 0) direction = 2; 		// up
			else if (lastY - y > 0) direction = 0; 	// down
			else direction = 1; 					// still
						
			character.setLocation(cX, cTempY - y);
			cTop = character.getY();
			cBot = cTop + CHARACTER_HEIGHT;
			
			for (int i = 0; i < obstacle.length; i++) {
				if ((cBot - oTop[i] <= 7 && cBot - oTop[i] >= -7) && ((cX >= oX[i] && cX <= oX[i] + OBSTACLE_WIDTH) || (cX + CHARACTER_WIDTH >= oX[i] && cX + CHARACTER_WIDTH <= oX[i] + OBSTACLE_WIDTH)) && direction == 0) {
					if (!onTop) {
						jIncrease = 0;
						onTop = true;
					}
				}
			}
			
			cClock += jIncrease;
			lastY = y;
		}
		
		// Character stays on ground
		if ((cTempY - y) >= cOrigin && jumping) {
			character.setLocation(cX, cOrigin);
			cClock = 0;
			y = 0;
			cTop = cOrigin;
			cTempY = cTop;
			jumping = false;
			onTop = false;
		}
		
		// Character falls off obstacle
		if (!jumping || jIncrease == 0) {
			for (int i = 0; i < obstacle.length; i++) {
				if (cX > oX[i] + OBSTACLE_WIDTH) {
					jIncrease = 0.1;
					onTop = false;
				}
			}
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
				if(index - 1 >= 0) pos = obstacle[index - 1].getX() + OBSTACLE_WIDTH;
				else {
					pos = obstacle[obstacle.length - 1].getX() + OBSTACLE_WIDTH;
				}
			}
		break;
		
		// Y	
		case 1:
			int foo = (int) (100 * Math.random());
			
			if(foo >= 0 && foo < 33 && (lastChoosenYPoint != 1)) {
				pos = obstacleYOffset - (obstacleYIncrease + Init.getObstacleSize(1));
				lastChoosenYPoint = 1;
			}
			else if(foo >= 33 && foo < 67 && (lastChoosenYPoint != 2)) {
				pos = obstacleYOffset - (obstacleYIncrease * 2 + Init.getObstacleSize(1) * 2);
				lastChoosenYPoint = 2;
			}
			else if(foo >= 67 && foo <= 100 && (lastChoosenYPoint != 3)){
				pos = obstacleYOffset - (obstacleYIncrease * 3 + Init.getObstacleSize(1) * 3);
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

	// Jump
	public static boolean jumping() {
		return jumping;
	}

	public static void enableJump() {
		jumping = true;
		cClock = 0.1;
		jIncrease = 0.1;
		cTempY = cTop;
		onTop = false;
	}

	public static boolean onTop() {
		return onTop;
	}
	
	public static void onTopJump() {
		cTempY = cTop;
		cClock = 0;
		jIncrease = 0.1;
		onTop = false;

	}
	
	// Pausing & Resuming
	public static void resumeGame() {
		currentSpeed = MOVEMENT_SPEED;
	}
	
	public static void pauseGame() {
		currentSpeed = 0;
	}
}
