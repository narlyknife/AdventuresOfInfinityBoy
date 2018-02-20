package main;

import java.awt.Toolkit;
import java.util.ArrayList;

public class Init {

	// Variable holding different Frames Per Second

	static ArrayList<Integer> fps = new ArrayList<Integer>();
	final int PLATFORM_COUNT;
	final int SCREEN_RES_X;
	final int SCREEN_RES_Y;
	final String font = "Arial";
	final static int[] GROUND_SIZE = {1920, 150};
	static float scaleIndex = 1;
	
	Init() {
		// The Main threads FPS
		setFps(0, 60);
		
		// Variable for maximum amount of platforms that will appear on screen
		PLATFORM_COUNT = 10;
		
		// Screen Resolutions in X and Y format
		SCREEN_RES_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		SCREEN_RES_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		scaleIndex = (float) (getResX() / getGroundSize(0));
	}
	
	public void setFps(int position, int value) {
		fps.add(position, (int) (1000 / value));
	}
	
	public static int getFps(int position) {
		return fps.get(position);
	}
	
	public int getResX() {
		return SCREEN_RES_X;
	}
	
	public int getResY() {
		return SCREEN_RES_Y;
	}
	
	public static int getGroundSize(int element) {
		return GROUND_SIZE[element];
	}
	
	public static float getScaleIndex() {
		return scaleIndex;
	}
	
	public String getOurFont() {
		return font;
	}
}