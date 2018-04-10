package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Init {

	// Variable holding different Frames Per Second

	static ArrayList<Integer> fps = new ArrayList<Integer>();
	public static int SCREEN_RES_X;
	public static int SCREEN_RES_Y;
	static Font font1;
	static Font font2;
	static Font font3;
	final static Color TEXT_COLOR = new Color(255, 255, 255);
	final int DEFAULT_X_VALUE = 1920;
	final int DEFAULT_Y_VALUE = 1080;
	final static int[] GAME_BACKGROUND_SIZE = {2000, 1080};
	final static int[] GROUND_SIZE = {1920, 150};
	final static int[] PLATFORM_SIZE = {1920, 50};
	final static int[] OBSTACLE_SIZE = {50, 50};
	final static int[] OBSTACLE_LARGE_SIZE = {75, 100};
	final static int[] CHARACTER_SIZE = {150, 75};
	public final static int[] SETTINGS_CHAR_SIZE = {340, 460};
	public final static int[] SETTINGS_DIF_SIZE = {400, 300};
	public final static int[] SETTINGS_TEXT_MUSIC_SIZE = {450, 150};
	public final static int[] SETTINGS_TEXT_SPLASHSCREEN_SIZE = {450, 150};
	public final static int[] SETTINGS_CHECKBOX_SIZE = {150, 150};
	public final static int[] LOGO_SIZE = {1511, 409};
	public final static int[] TITLE_SIZE = {800, 300};
	public final static int[] TITLE_SIZE_SMALL = {300, 100};
	public final static int[] SCOREBOARD_SIZE = {600, 50};
	public final static int[] CREDITS_TEXT_SIZE = {300, 35};
	public final static int[] BUTTON_SIZE_1 = {200, 75};
	public final static int[] BUTTON_SIZE_2 = {250, 75};
	public final static int[] BUTTON_SET_SIZE = {360, 135};
	final static int PLATFORM_AMOUNT = 3;
	static int characterMovement = 6;
	static float scaleIndexX = 1;
	static float scaleIndexY = 1;
	static int a = 35;
	static int vZero = 140;
	static int splashScreenTime = 5000;	// Milliseconds
	
	// Font on Text - Hombre
	
	// Difficulty selection (1-3)
	// Character selection (1-3)
	// Toggle splash screen (0-1)
	// Toggle music (0-1) 
	// 0 = false, 1 = true
	public static int[] settingsData = {1, 1, 1, 1, 34274566};
	public final static String SETTINGS_PATH = "Settings.txt";
	
	Init() {
		// The Main threads FPS
		setFps(0, 100);
		setFps(1, 50);
		
		// Screen Resolutions in X and Y format
		SCREEN_RES_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		SCREEN_RES_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		scaleIndexX = (float) SCREEN_RES_X / DEFAULT_X_VALUE;
		scaleIndexY = (float) SCREEN_RES_Y / DEFAULT_Y_VALUE;
		
		font1 = new Font("SANS_SERIF", Font.BOLD, (int) (40 * scaleIndexX));
		font2 = new Font("SANS_SERIF", Font.PLAIN, (int) (30 * scaleIndexX));
		font3 =  new Font("SANS_SERIF", Font.ITALIC, (int) (20 * scaleIndexX));
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
	
	public static int getGameBackgroundSize(int element) {
		return GAME_BACKGROUND_SIZE[element];
	}
	
	public static int getGroundSize(int element) {
		return GROUND_SIZE[element];
	}
	
	public static int getObstacleSize(int element) {
		return OBSTACLE_SIZE[element];
	}
	
	public static int getObstacleLargeSize(int element) {
		return OBSTACLE_LARGE_SIZE[element];
	}
	
	public static int getPlatformSize(int element) {
		return PLATFORM_SIZE[element];
	}
	
	public static int getPlatformAmount() {
		return PLATFORM_AMOUNT;
	}
	
	public static int getCharacterSize(int element) {
		return CHARACTER_SIZE[element];
	}
	
	public static int getGravity() {
		return a;
	}
	
	public static int getVZero() {
		return vZero;
	}
	
	public static int getCharacterMovement() {
		return characterMovement;
	}
	
	public static void setCharacterMovement(int speed) {
		characterMovement = speed;
	}
	
	public static float getScaleIndexX() {
		return scaleIndexX;
	}
	
	public static float getScaleIndexY() {
		return scaleIndexY;
	}
	
	public static Font getFont() {
		return font1;
	}
	
	public static Font getFont2() {
		return font2;
	}
	
	public static Font getFont3() {
		return font3;
	}
	
	public static Color getTextColor() {
		return TEXT_COLOR;
	}
}