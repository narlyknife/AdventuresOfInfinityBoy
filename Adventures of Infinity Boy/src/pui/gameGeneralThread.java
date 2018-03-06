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
	
	public static Obstacles[] obstacle = {new Obstacles(), new Obstacles(), new Obstacles(),
										  new Obstacles(), new Obstacles(), new Obstacles(),
										  new Obstacles(), new Obstacles(), new Obstacles(),
										  new Obstacles(), new Obstacles(), new Obstacles(),
										  new Obstacles(), new Obstacles(), new Obstacles(),
										  new Obstacles(), new Obstacles(), new Obstacles(),};
	
	// Creating block dimensions
	final static int GROUND_HEIGHT = GroundBlocks.getGroundHeight();
	final int GROUND_WIDTH = ground[1].getGroundWidth();
	int[] groundX = {0, GROUND_WIDTH, GROUND_WIDTH*2};
	
	final int OBSTACLE_HEIGHT = obstacle[0].getObstacleHeight();
	final int OBSTACLE_WIDTH = obstacle[0].getObstacleWidth();
	
	// Used for storing unique coordinates for obstacles
	int[] obstacleX = new int[MAX_OBSTACLES];
	int[] obstacleY = new int[MAX_OBSTACLES];
	
	// Setting coordinate values into shorter names for easier use in ex. collision handling
	int[] oTop = new int[MAX_OBSTACLES];
	int[] oBot = new int[MAX_OBSTACLES];
	int[] oX = new int[MAX_OBSTACLES];
	
	final int CHARACTER_HEIGHT = character.getCharacterHeight();
	final int CHARACTER_WIDTH = character.getCharacterWidth();
	
	// Setting coordinate values into shorter names for easier use in ex. collision handling
	int cTop;
	int cBot;
	int cX;
	
	// Settings coordinate system for placement of obstacles
	int[][] obstacleCoordSystemX = new int[4][3];
	int[][] obstacleCoordSystemY = new int[4][3];
	
	// Setting game animation movement
	// Double increase = -1.0001;
	static final int MOVEMENT_SPEED = (int) (Init.getCharacterMovement() * Init.getScaleIndex());
	static int currentSpeed = MOVEMENT_SPEED;
	
	// Setting timer object with preferred FPS
	public Timer time = new Timer(Init.getFps(0), this);
	
	
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
		
		System.out.println("\nCoordinate System for obstacle placement");
		for(int i = 0; i < groundX.length; i++) {
			System.out.println("Groundblock: " + i);
			
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 4; k++) {
					obstacleCoordSystemX[k][j] = groundX[i] + (int) (96 * Init.getScaleIndex() + ((OBSTACLE_WIDTH + 96) * Init.getScaleIndex() * j));
					obstacleCoordSystemY[k][j] = (int) ((resY - GROUND_HEIGHT) - ((CHARACTER_HEIGHT * 1.5 + OBSTACLE_HEIGHT) * k));
					
					System.out.println("I: " + i + "	J: " + j + "	K: " + k + "	X and Y: " + obstacleCoordSystemX[k][j] + " " + obstacleCoordSystemY[k][j]);
				}
				System.out.println("\n");
			}
		}
			
		// Setting start values for obstacles
		for (int i = 0; i < obstacle.length; i++) {
			obstacleX[i] = newCoord(0);
			obstacleY[i] = newCoord(1);
			obstacle[i].setLocation(obstacleX[i], obstacleY[i]);
			obstacle[i].setSize(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
			obstacle[i].setObstacleImage(pickRandomObstacleImage());
			
			oTop[i] = obstacle[i].getY();
			oBot[i] = oTop[i] + OBSTACLE_HEIGHT;
			oX[i] = obstacle[i].getX();
		}
		
		// Setting start values for character
		character.setLocation((int) (resX * 0.15), resY - (GroundBlocks.getGroundHeight() + CHARACTER_HEIGHT));
		character.setSize(CHARACTER_WIDTH, CHARACTER_HEIGHT);
		character.setCharacterImage(img9);
		cTop = character.getY();
		cBot = cTop + CHARACTER_HEIGHT;
		cX = character.getX();
	}

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
			if (oX[i] <= (cX + CHARACTER_WIDTH) && ((oTop[i] >= cTop && oTop[i] <= cBot) || (oBot[i] >= cTop && oBot[i] <= cBot))) {
				currentSpeed = 0;
			}
			
			// Animating obstacle block
			obstacle[i].setLocation(obstacleX[i] -= currentSpeed, obstacle[i].getY());
			oX[i] = obstacle[i].getX();
		}
	}
	
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
		switch(axis) {
		// X
		case 0:
			pos = (int) (resX *  Math.random() + resX);
			return pos;
		// Y	
		case 1:
			pos = (int) ((resY - GROUND_HEIGHT) - (randomYOffset() * Init.getScaleIndex()));
//			System.out.println("Y offset: " + randomYOffset());
			return pos;
		}
		System.out.println("Failed to create new coordinates for obstacle!");
		return pos;
	}
	
	// Generate multiplier for obstacle coordinate randomization 
	// (Change if you like Folke. You know height of character better than me.)
	public static int randomYOffset() {
		double random = Math.random() * 10;

		if(random >= 0 && random <= 3) return 50;
		else if(random > 3 && random <= 5) return 125;
		else if (random > 5 && random <= 7) return 180;
		else return 220;
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
