package main;

import java.awt.Toolkit;
import java.util.ArrayList;

public class Init {

	
	// Variable holding different Frames Per Second

	ArrayList<Integer> fps = new ArrayList<Integer>();
	final int PLATFORM_COUNT;
	final int SCREEN_RES_X;
	final int SCREEN_RES_Y;
	
	Init() {
		// The Main threads FPS
		fps.add(60);
		
		// Variable for maximum amount of platforms that will appear on screen
		PLATFORM_COUNT = 10;
		
		// Screen Resolutions in X and Y format
		SCREEN_RES_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		SCREEN_RES_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}
	
	public void setFps(int position, int value) {
		fps.add(position, value);
	}
	
	public int getFps(int position) {
		return fps.get(position);
	}
	
	public int getResX() {
		return SCREEN_RES_X;
	}
	
	public int getResY() {
		return SCREEN_RES_Y;
	}
}
}