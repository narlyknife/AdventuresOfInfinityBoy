package jpanels;

import javax.swing.JPanel;

import main.Main;

public class GamePanel extends JPanel{

	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	GroundBlocks[] ground = new GroundBlocks[5];
	
	int groundWidth = (int) (resX * 0.25) + 100; // 1080 * 0.25 + 100 = 580
	int groundHeight = (int) (resY*0.25);
	
	public GamePanel() {
		System.out.println("New Gameobject Created");
			
		
	}

}
