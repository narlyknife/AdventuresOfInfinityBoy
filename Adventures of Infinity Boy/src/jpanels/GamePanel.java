package jpanels;

import java.awt.Graphics;

import javax.swing.JPanel;
import gui.*;

import main.Main;

public class GamePanel extends JPanel{

	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	//Creating objects for the ground that will be cycled through and reused.
	GroundBlocks ground = new GroundBlocks();
	
	public GamePanel() {
		System.out.println("New Gameobject Created");
		
		// Allowing for a XY precise placement, beneficial for a JPanel with the purpose of multiple object placements.
		this.setLayout(null);
		
		// Setting the grounds size, placement and z position on the gamepanel.
		this.add(ground);
		ground.setLocation(0, resY - ground.getGroundHeight());
		ground.setSize(ground.getGroundWidth(), ground.getGroundHeight());
	}
}
