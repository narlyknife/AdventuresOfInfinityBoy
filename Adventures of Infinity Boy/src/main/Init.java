package main;

import java.awt.Toolkit;
import java.util.ArrayList;

public class Init {

	// Variable holding different Frames Per Second

	static ArrayList<Integer> fps = new ArrayList<Integer>();
	final int PLATFORM_COUNT;
	public final int SCREEN_RES_X;
	public final int SCREEN_RES_Y;
	final String font = "Arial";
	final int DEFAULT_X_VALUE = 1920;
	final static int[] GROUND_SIZE = {1920, 150};
	final static int[] OBSTACLE_SIZE = {200, 15};
	final static int OBSTACLE_AMOUNT = 5;
	static int characterMovement = 7;
	static float scaleIndex = 1;
	
	Init() {
		// The Main threads FPS
		setFps(0, 60);
		
		// Variable for maximum amount of platforms that will appear on screen
		PLATFORM_COUNT = 10;
		
		// Screen Resolutions in X and Y format
		SCREEN_RES_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		SCREEN_RES_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		scaleIndex = (float) SCREEN_RES_X / DEFAULT_X_VALUE;
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
	
	public static int getObstacleSize(int element) {
		return OBSTACLE_SIZE[element];
	}
	
	public static int getObstacleAmount() {
		return OBSTACLE_AMOUNT;
	}
	
	public static int getCharacterMovement() {
		return characterMovement;
	}
	
	public static void setCharacterMovement(int speed) {
		characterMovement = speed;
	}
	
	public static float getScaleIndex() {
		return scaleIndex;
	}
	
	public String getOurFont() {
		return font;
	}
}