package jpanels;

import java.awt.Graphics;

import javax.swing.JPanel;
import gui.*;
import pui.*;

import main.Main;

public class GamePanel extends JPanel{
	
	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	// Creating a new thread for the processing of world movement.
	static gameGeneralThread mainThread = new gameGeneralThread();
	
	public GamePanel() {
		System.out.println("New Gameobject Created");
		
		// Allowing for a XY precise placement, beneficial for a JPanel with the purpose of multiple object placements.
		this.setLayout(null);
		
		// Setting the grounds size, placement and z position on the gamePanel.
		for(int i = 0; i < mainThread.ground.length; i++) this.add(mainThread.ground[i]);
	}
	
	// Starting new thread
	public static void startMainThread() {
		mainThread.start();
	}
}
