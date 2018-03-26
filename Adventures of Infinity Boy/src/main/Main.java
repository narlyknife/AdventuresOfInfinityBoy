package main;

import java.awt.CardLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Engine;
import engine.MainActionListener;
import jpanels.Credits;
import jpanels.GamePanel;
import jpanels.MainMenu;
import jpanels.ScoreBoard;
import jpanels.Settings;
import jpanels.SubPanels.GameOver;
import jpanels.SubPanels.Pause;
import jpanels.SubPanels.SplashScreen;


public class Main {
	public static final JFrame _frame = new JFrame();
	public static final Init _init = new Init();

	public static Map<String, JPanel> panelMap = new HashMap<String, JPanel>();
	public static Map<String, JPanel> subPanelMap = new HashMap<String, JPanel>();
	public static MainActionListener actionListener = new MainActionListener();
	
	public static CardLayout cardlayout = new CardLayout();
	public static JPanel mainPanel = new JPanel(cardlayout);
	
	public static void main(String[] args){
		System.out.println("\t\t\t\t** PROGRAM CREATED BY WIELDING GIANT UF **");
		System.out.println("\t\tALL CODE, IMAGES, SOUND AND INCLUDED SOFTWARE IS PROPERTY OF WIELDING GIANT UF.");
		System.out.println("ANY ATTEMPT TO DISTRIBUTE, SELL OR COPY THE PROPERTY OF WIELDING GIANT UF WILL BE MET WITH LEGAL ACTIONS.\n\n\n");
		
		// --PREPARING AND STRUCTURING DATA--
		System.out.println("\t--PREPARING AND STRUCTURING DATA--\n");
		
		// Creating a map with all the panel objects and their preferred key names, because.. you know objects have genders to... i guess?
		panelMap.put("credits", new Credits());							panelMap.put("gamepanel", new GamePanel());			panelMap.put("mainmenu", new MainMenu());	
		panelMap.put("scoreboard", new ScoreBoard());					panelMap.put("settings", new Settings());			panelMap.put("splashscreen", new SplashScreen());
		
		// Creating another map for all the sub-panels, #subpanelLivesMatter
		subPanelMap.put("gameover", new GameOver());					subPanelMap.put("pause", new Pause());
		
		System.out.println("DONE: Creating and sorting JPanels");
		
		// Checking for existing settings file
		File f = new File(Init.SETTINGS_PATH);
		
		// If settings exists, read data
		if(f.exists() && !f.isDirectory()) {
			System.out.println("NOTE: Settings file detected \nNOTE: Reading data...");
			
			// Storing data from settings file into static array
			Engine.readTxtFile(Init.SETTINGS_PATH);
			System.out.println("DONE: Reading data");
		}
		// Else, create a new template settings file
		else {
			System.out.println("WARNING: Settings file not found \t\t\t#\nNOTE: Creating new settings file...");
			
			// Creating & writing data to text file
			Engine.writeTxtFile(Init.SETTINGS_PATH, Init.settingsData);
		}
		System.out.println("DONE: Primary Data structuring and loading");
		
		// --SETTING THE FRAME VISIBLE AND STARTING THE PROGRAM--
		System.out.println("\n\t--GUI INITIALIZATION & RENDERING FRAME--\n");
		
		// Switching displaying panel to the main menu
		_frame.setContentPane(mainPanel);
		showSplashScreen("splashscreen", _frame, _init.splashScreenTime);
		System.out.println("NOTE: Splashscreen displayed");
		
		setPanel("mainmenu");
		
		getPanel("gamepanel").add(getSubPanel("pause"), 0);
		getSubPanel("pause").setSize(Init.SCREEN_RES_X, Init.SCREEN_RES_Y);
		getSubPanel("pause").setLocation(0, 0);
		getSubPanel("pause").setVisible(false);
		
		getSubPanel("gamesettings").setSize(Init.SCREEN_RES_X, (int) (530 * Init.scaleIndexY));
		getSubPanel("difficultysettings").setSize(Init.SCREEN_RES_X, (int) (530 * Init.scaleIndexY));
		getSubPanel("charactersettings").setSize(Init.SCREEN_RES_X, (int) (530 * Init.scaleIndexY));
		
		System.out.println("DONE: Primary GUI configuration");
		
		System.out.println("DONE: Main initialization");
		System.out.println("\n\t\t--GAME RUNNING--\n");
	}

	// Setting "_frame" to fullscreen.
	private static void setFullscreen() {
		_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		_frame.setUndecorated(true);
	}
	
	// Show a splashscreen with selected panels before and after.
	private static void showSplashScreen(String splashscreen, JFrame frame, int time) {
		Engine.playAudio("Splashscreen.wav");
		
		setPanel(splashscreen);
		
		setFullscreen();
		frame.setVisible(true);
		
		try {
			Thread.sleep(time);
		} catch(InterruptedException e) {
			System.out.println("Failed to load splashscreen");
		}
	}
	
	// Set and show a specific panel
	public static void setPanel(String panel) {
		mainPanel.add(panelMap.get(panel), panel);
		cardlayout.show(mainPanel, panel);
	}
	
	public static JPanel getPanel(String name) {
		return panelMap.get(name);
	}
	
	public static JPanel getSubPanel(String name) {
		return subPanelMap.get(name);
	}
	
	// Show/Hide selected subpanel
	public static void showSubPanel(JPanel panel, boolean flag) {
		panel.setVisible(flag);
	}
}