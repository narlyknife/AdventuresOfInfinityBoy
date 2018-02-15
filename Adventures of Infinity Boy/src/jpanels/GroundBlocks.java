package jpanels;

import main.Main;

public class GroundBlocks {
	
	// Declarations
		static int resX = Main._init.getResX();
		static int resY = Main._init.getResY();
		static String font = Main._init.getOurFont();
		
		public GroundBlocks() {
			int groundWidth = (int) (resX * 0.25) + 100; // 1080 * 0.25 + 100 = 580
			int groundHeight = (int) (resY*0.25);
			System.out.println("GroundBlock created");
					
		}
}
