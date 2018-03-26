package pui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collector.Characteristics;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import gui.GroundBlocks;
import gui.Obstacles;
import gui.Character;
import main.Init;
import main.Main;

public class gameGeneralThread extends Thread implements ActionListener{
	
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static int MAX_OBSTACLES = Main._init.getObstacleAmount(); // Used for for loops that instantiates unique values for obstacles
	static int a = Init.getGravity();
	static int vZero = Init.getVZero();

	// Images for the ground blocks
	private static Image img1 = new ImageIcon(GroundBlocks.class.getResource("/Pictures/Ground1.png")).getImage();
	private static Image img2 = new ImageIcon(GroundBlocks.class.getResource("/Pictures/Ground2.png")).getImage();
	private static Image img3 = new ImageIcon(GroundBlocks.class.getResource("/Pictures/Ground3.png")).getImage();
	private static Image img4 = new ImageIcon(GroundBlocks.class.getResource("/Pictures/Ground4.png")).getImage();
	
	// Images for the obstacle blocks
	private static Image img5 = new ImageIcon(Obstacles.class.getResource("/Pictures/Obstacle1.png")).getImage();
	private static Image img6 = new ImageIcon(Obstacles.class.getResource("/Pictures/Obstacle2.png")).getImage();
	private static Image img7 = new ImageIcon(Obstacles.class.getResource("/Pictures/Obstacle3.png")).getImage();
	private static Image img8 = new ImageIcon(Obstacles.class.getResource("/Pictures/Obstacle4.png")).getImage();
	
	// Image for the character
	private static Image img9 = new ImageIcon(Character.class.getResource("/Pictures/Character1.png")).getImage();
	
	// Creating objects
	public static GroundBlocks[] ground = {new GroundBlocks(), new GroundBlocks(), new GroundBlocks()};

	public static Character character = new Character();
	
	public static Obstacles[] obstacle = {new Obstacles(), new Obstacles(), new Obstacles(), new Obstacles(),
										  new Obstacles(), new Obstacles(), new Obstacles(), new Obstacles()};
	
	//#######
	// Ground
	
	// Creating block dimensions
	final static int GROUND_HEIGHT = GroundBlocks.getGroundHeight();
	final static int GROUND_WIDTH = ground[1].getGroundWidth();
	static int[] groundX = {0, GROUND_WIDTH, GROUND_WIDTH*2};
	int gY = resY - GROUND_HEIGHT;
	
	//##########
	// Obstacles
	final int OBSTACLE_HEIGHT = obstacle[0].getObstacleHeight();
	final int OBSTACLE_WIDTH = obstacle[0].getObstacleWidth();
	
	// Used for storing unique coordinates for obstacles
	int[] obstacleX = new int[MAX_OBSTACLES];
	int[] obstacleY = new int[MAX_OBSTACLES];
	
	// Setting coordinate values into shorter names for easier use in ex. collision handling
	int[] oTop = new int[MAX_OBSTACLES];
	int[] oBot = new int[MAX_OBSTACLES];
	int[] oX = new int[MAX_OBSTACLES];
	
	// Settings coordinate system for placement of obstacles
	static int[][] obstacleCoordSystemX = new int[4][3];
	static int[][] obstacleCoordSystemY = new int[4][3];
	static int amountSpawnedOnRow = 0;
	static int currentColumn = 0;
	static int choosenYPointAmount = 0;
	static int lastChoosenYPoint = 0;
	static boolean firstTimeSpawn = true;
	
	//##########
	// Character
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
	int cBot; 		// Character y value of bottom edge
	int cX;			// Character x value (constant)
	int cOrigin;	// Point of origin for character (Y). (resY - GROUND_HEIGHT - CHARACTER_HEIGHT)
	static int cTempY;
	

	
	// Setting game animation movement
	// Double increase = -1.0001;
	static final int MOVEMENT_SPEED = (int) (Init.getCharacterMovement() * Init.getScaleIndex());
	static int currentSpeed = MOVEMENT_SPEED;
	
	// Setting timer object with preferred FPS
	public Timer time = new Timer(Init.getFps(0), this);
	
	/////////////////
	// Initial values
	public void run() {
		System.out.println("A new MainThread has been initiated");
		time.start();
		
		for(int i = 0; i < gameGeneralThread.ground.length; i++) Main.getPanel("gamepanel").add(gameGeneralThread.ground[i]);
		
		// Setting ground block size, starting location and image
		for(int i = 0; i < ground.length; i++) {
			ground[i].setLocation(groundX[i], resY - GROUND_HEIGHT);
			ground[i].setSize(GROUND_WIDTH, GROUND_HEIGHT);
			ground[i].setGroundImage(pickRandomGroundImage());
		}
		
		// Creating a coordinate system for all the obstacles possible placements
		// Further data in console
//		System.out.println("\nCoordinate System for obstacle placement");
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 4; k++) {
					obstacleCoordSystemX[k][j] = (int) (700 * Init.getScaleIndex() + ((OBSTACLE_WIDTH + 700) * Init.getScaleIndex() * j));
					obstacleCoordSystemY[k][j] = (int) ((resY - GROUND_HEIGHT) - ((CHARACTER_HEIGHT * 0.9 + OBSTACLE_HEIGHT) * (k + 1)));
					
//					System.out.println("J: " + j + "	K: " + k + "	X and Y: " + obstacleCoordSystemX[k][j] + " " + obstacleCoordSystemY[k][j]);
				}
//				System.out.println("\n");
		}
			
		// Setting start values for obstacles
		for (int i = 0; i < obstacle.length; i++) {
			if(i == obstacle.length/2) firstTimeSpawn = false;
			obstacleX[i] = newCoord(0);
			obstacleY[i] = newCoord(1);
			obstacle[i].setLocation(obstacleX[i], obstacleY[i]);
			obstacle[i].setSize(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
			obstacle[i].setObstacleImage(pickRandomObstacleImage());
			
			oTop[i] = obstacle[i].getY() + 2;
			oBot[i] = oTop[i] + OBSTACLE_HEIGHT - 4;
			oX[i] = obstacle[i].getX();
		}
		
		// Setting start values for character
		character.setLocation((int) (resX * 0.15), resY - (GroundBlocks.getGroundHeight() + CHARACTER_HEIGHT));
		character.setSize(CHARACTER_WIDTH, CHARACTER_HEIGHT);
		character.setCharacterImage(img9);
		cTop = character.getY();
		cBot = cTop + CHARACTER_HEIGHT;
		cX = character.getX();
		cOrigin = resY - (GroundBlocks.getGroundHeight() + CHARACTER_HEIGHT);
		cTempY = cOrigin;
	}

	/////////////////
	// ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
	for(int i = 0; i < ground.length; i++) {
			// Respawning ground block
			if(groundX[i] <= -resX) {
				groundX[i] = GROUND_WIDTH * 2 - currentSpeed;
				ground[i].setGroundImage(pickRandomGroundImage());
			}
			
			// Animating ground block
			ground[i].setLocation(groundX[i] -= currentSpeed, resY - GROUND_HEIGHT);
		}
		
		
		for (int i = 0; i < obstacle.length; i++) {
			// Resetting obstacle block
			if ((obstacleX[i] + OBSTACLE_WIDTH) <= -10) {
				obstacleX[i] = newCoord(0);
				obstacleY[i] = newCoord(1);
				oTop[i] = obstacle[i].getY();
				oBot[i] = oTop[i] + OBSTACLE_HEIGHT;
				obstacle[i].setObstacleImage(pickRandomObstacleImage());
				
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
			obstacle[i].setLocation(obstacleX[i] -= currentSpeed, obstacle[i].getY());
			oX[i] = obstacle[i].getX();
		}
			/////////////////////////////////////////////////////
			// Animation and collision handling for the character
			if (jumping) {
				
				y = (int) ((vZero * cClock) - ((a*Math.pow(cClock, 2))/2)); // y=V*t*(at^2)/2
				
				if (lastY - y < 0  ) {
					direction = 2; // up
				}
				else if (lastY - y > 0) {
					direction = 0; // down
				}
				else {
					direction = 1; // still
				}				

				character.setLocation(cX, cTempY - y);
				cTop = character.getY();
				cBot = cTop + CHARACTER_HEIGHT;
				
				for (int i = 0; i < obstacle.length; i++) {
					if ((cBot - oTop[i] <= 7 && cBot - oTop[i] >= -7) && ((cX >= oX[i] && cX <= oX[i] + OBSTACLE_WIDTH) || (cX + CHARACTER_WIDTH >= oX[i] && cX + CHARACTER_WIDTH <= oX[i] + OBSTACLE_WIDTH)) && direction == 0) {
						if (!onTop) {
							jIncrease = 0;
							onTop = true;
							System.out.println();
						}
					}
				}
				
				cClock += jIncrease;
				lastY = y;
				System.out.print(y + ", ");
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
	
	//////////
	// Methods
	
	// Choose a random image for a ground block
	public static Image pickRandomGroundImage() {
		int i = (int) (Math.random() * 10);
		
		if(i >= 0 && i < 2) return img1;
		else if(i >= 2 && i < 5) return img2;
		else if(i >= 5 && i < 7) return img3;
		else return img4;
	}
	
	// Choose a random image for a obstacle block
	public static Image pickRandomObstacleImage() {
		int i = (int) (Math.random() * 10);
		
		if(i >= 0 && i < 2) return img5;
		else if(i >= 2 && i < 5) return img6;
		else if(i >= 5 && i < 7) return img7;
		else return img8;
	}

	// Method for getting new coordinates for obstacles
	public static int newCoord(int axis) {

		int pos = 0;
		int selectedGround = 0;
		
		// What ground object is the farthest away?
		for(int i = 0; i < groundX.length; i++) {
			if(groundX[i] > groundX[selectedGround]) selectedGround = i;
		}
		
		switch(axis) {
		
		// X
		case 0:
			// If the row has not been filled yet, spawn obstacle in random column
			if(amountSpawnedOnRow >= 0 && amountSpawnedOnRow <= 2) {
				
				// set X value of a obstacle
				if(firstTimeSpawn) pos = groundX[selectedGround - 1] + obstacleCoordSystemX[0][currentColumn];
				else pos = groundX[selectedGround] + obstacleCoordSystemX[0][currentColumn];
			}
			else {
				amountSpawnedOnRow = 0;
				
				if(!(currentColumn >= 2)) currentColumn++;
				else currentColumn = 0;
				
				// Pick X value of current column
				if(firstTimeSpawn) pos = groundX[selectedGround - 1] + obstacleCoordSystemX[0][currentColumn];
				else pos = groundX[selectedGround] + obstacleCoordSystemX[0][currentColumn];
			}
			
//			System.out.println("posX: " + pos);
		break;
		
		// Y	
		case 1:
			if(choosenYPointAmount == 0) {
				int foo = (int) (100 * Math.random());
				
				if(foo >= 0 && foo < 47) pos = obstacleCoordSystemY[0][0];
				else if(foo >= 48 && foo < 95) pos = obstacleCoordSystemY[1][0];
				else if(foo >= 96 && foo < 100) pos = obstacleCoordSystemY[2][0];
				else pos = obstacleCoordSystemY[3][0];
				
				choosenYPointAmount++;
				
				lastChoosenYPoint = pos;
			}
			else {
				int foo = (int) (100 * Math.random());
				
				if(foo >= 0 && foo < 47) pos = obstacleCoordSystemY[0][0];
				else if(foo >= 48 && foo < 95) pos = obstacleCoordSystemY[1][0];
				else if(foo >= 96 && foo < 100) pos = obstacleCoordSystemY[2][0];
				else pos = obstacleCoordSystemY[3][0];
				
				if(pos == lastChoosenYPoint) return newCoord(1);
				choosenYPointAmount = 0;
			}
			
//			System.out.println("posY: " + pos + "\n");
		break;
		}
		
		amountSpawnedOnRow++;
		return pos;
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
	
	// Pause game
	public static void pauseGame() {
		currentSpeed = 0;
	}
	
	// Resume game
	public static void resumeGame() {
		currentSpeed = MOVEMENT_SPEED;
	}
}
