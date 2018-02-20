package pui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import gui.GroundBlocks;
import jpanels.GamePanel;
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
	
	// Creating 3 ground block objects
	public static GroundBlocks[] ground = {new GroundBlocks(), new GroundBlocks(), new GroundBlocks()};
	
	// Creating block dimensions
	final int GROUND_HEIGHT = resY - GroundBlocks.getGroundHeight();
	final int GROUND_WIDTH = ground[1].getGroundWidth();
	int[] groundX = {0, GROUND_WIDTH, GROUND_WIDTH*2};
	
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
			ground[i].setGroundImage(pickRandomImage());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int i = 0; i < ground.length; i++) {
			// Respawning ground block
			if(groundX[i] <= -resX) {
				groundX[i] = GROUND_WIDTH*2 - MOVEMENT_SPEED;
				ground[i].setGroundImage(pickRandomImage());
			}
			
			// Animating ground block
			ground[i].setLocation(groundX[i] -= MOVEMENT_SPEED, GROUND_HEIGHT);
		}
	}
	
	// Choose a random image for a ground block
	public static Image pickRandomImage() {
		int i = (int) (Math.random() * 10);
		
		if(i >= 0 && i < 2) return img1;
		else if(i >= 2 && i < 5) return img2;
		else if(i >= 5 && i < 7) return img3;
		else return img4;
	}
}
