package pui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import gui.GroundBlocks;
import gui.Obstacles;
import main.Init;
import main.Main;

public class gameGeneralThread extends Thread implements ActionListener{
	
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();

	// Images for the ground blocks
	private static Image img1 = new ImageIcon(GroundBlocks.class.getResource("/Pictures/Ground1.png")).getImage();
	private static Image img2 = new ImageIcon(GroundBlocks.class.getResource("/Pictures/Ground2.png")).getImage();
	private static Image img3 = new ImageIcon(GroundBlocks.class.getResource("/Pictures/Ground3.png")).getImage();
	private static Image img4 = new ImageIcon(GroundBlocks.class.getResource("/Pictures/Ground4.png")).getImage();
	
	// Images for the obstacle blocks
	private static Image img5 = new ImageIcon(Obstacles.class.getResource("/Pictures/Ground1.png")).getImage();
	private static Image img6 = new ImageIcon(Obstacles.class.getResource("/Pictures/Ground2.png")).getImage();
	private static Image img7 = new ImageIcon(Obstacles.class.getResource("/Pictures/Ground3.png")).getImage();
	private static Image img8 = new ImageIcon(Obstacles.class.getResource("/Pictures/Ground4.png")).getImage();
	
	// Creating objects
	public static GroundBlocks[] ground = {new GroundBlocks(), new GroundBlocks(), new GroundBlocks()};
	public static Obstacles[] obstacle = {new Obstacles(newCoord(0, true), newCoord(1, true)), new Obstacles(newCoord(0, true), newCoord(1, true)), new Obstacles(newCoord(0, true), newCoord(1, true)), new Obstacles(newCoord(0, true), newCoord(1, true)), new Obstacles(newCoord(0, true), newCoord(1, true))};
	
	// Creating block dimensions
	final int GROUND_HEIGHT = resY - GroundBlocks.getGroundHeight();
	final int GROUND_WIDTH = ground[1].getGroundWidth();
	int[] groundX = {0, GROUND_WIDTH, GROUND_WIDTH*2};
	
	final int OBSTACLE_HEIGHT = obstacle[0].getHeight();
	final int OBSTACLE_WIDTH = obstacle[0].getWidth();
		
	// Setting game animation movement
	final int MOVEMENT_SPEED = 7;
	
	// Setting timer object with preferred FPS
	Timer time = new Timer(Init.getFps(0), this);
	
	public void run() {
		System.out.println("A new MainThread has been initiated");
		time.start();
		
		// Setting ground block size, starting location and image
		for(int i = 0; i < ground.length; i++) {
			ground[i].setLocation(groundX[i], GROUND_HEIGHT);
			ground[i].setSize(GROUND_WIDTH, GROUND_HEIGHT);
			ground[i].setGroundImage(pickRandomGroundImage());
		}
		
		// Setting background image for obstacles
		for (int i = 0; i < obstacle.length; i++) {
			int posX = obstacle[i].getPosX();
			int posY = obstacle[i].getPosY();
			obstacle[i].setLocation(posX, posY);
			obstacle[i].setSize(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
			obstacle[i].setObstacleImage(pickRandomObstacleImage());
//			System.out.println(obstacle[i].getLocation());
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int i = 0; i < ground.length; i++) {
			// Respawning ground block
			if(groundX[i] <= -resX) {
				groundX[i] = GROUND_WIDTH*2 - MOVEMENT_SPEED;
				ground[i].setGroundImage(pickRandomGroundImage());
			}
			
			// Animating ground block
			ground[i].setLocation(groundX[i] -= MOVEMENT_SPEED, GROUND_HEIGHT);
		}
		
		for (int i = 0; i < obstacle.length; i++) {

			// Resetting obstacle block
			if (obstacle[i].getPosX() + OBSTACLE_WIDTH <= -10) {
				obstacle[i].setPosX(newCoord(0,false));
				obstacle[i].setPosY(newCoord(1,false));
				obstacle[i].setLocation(obstacle[i].getPosX(), obstacle[i].getPosY());
			}
			
			// Animating obstacle block
			obstacle[i].setPosX(obstacle[i].getPosX() - MOVEMENT_SPEED);
			obstacle[i].setLocation(obstacle[i].getPosX(), obstacle[i].getPosY());
//			if (i == 0) {
//				System.out.println(obstacle[i].getLocation());
//			}
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
	public static int newCoord(int axis, boolean first) {
		int pos = 0;
		switch(axis) {
		// X
		case 0:
			if (first) {
				pos = (int) (resX*randomNum()) + resX;
				System.out.println(pos + "");
				return pos;
			}
			else {
				pos =	(int) (resX*randomNum()) + resX;
				return pos;
			}
		// Y	
		case 1:
			pos = (int) (resY*randomNum());
			return pos;
		}
		System.out.println("Failed to create new coordinates for obstacle!");
		return pos;
	}
	
	// Generate multiplier for obstacle coordinate randomization
	public static double randomNum() {
		double random = Math.random();

		if (random > 0.75 || random < 0.1) {
			randomNum();
		}
		return random;
	}
}
