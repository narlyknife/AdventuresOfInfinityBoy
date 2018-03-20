package main;

import java.awt.Toolkit;
import java.util.ArrayList;

public class Init {

	// Variable holding different Frames Per Second

	static ArrayList<Integer> fps = new ArrayList<Integer>();
	final int PLATFORM_COUNT;
	public static int SCREEN_RES_X;
	public static int SCREEN_RES_Y;
	final static String font = "Arial";
	final int DEFAULT_X_VALUE = 1920;
	final static int[] GROUND_SIZE = {1920, 150};
	final static int[] OBSTACLE_SIZE = {600, 30};
	final static int[] CHARACTER_SIZE = {150, 75};
	final static int OBSTACLE_AMOUNT = 12;
	static int characterMovement = 6;
	static float scaleIndex = 1;
	static int splashScreenTime = 5000;	// Milliseconds
	
	// World selection (1-3)
	// Character selection (1-3)
	// Toggle splash screen (0-1)
	// Toggle music (0-1) 
	// 0 = false, 1 = true
	public static int[] settingsData = {1, 1, 1, 1};
	final static String SETTINGS_PATH = "src/Settings/settings.txt";
	
	Init() {
		// The Main threads FPS
		setFps(0, 100);

		
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
	
	public static int getResX() {
		return SCREEN_RES_X;
	}
	
	public static int getResY() {
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
	
	public static int getCharacterSize(int element) {
		return CHARACTER_SIZE[element];
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
	
	public static String getOurFont() {
		return font;
	}
}