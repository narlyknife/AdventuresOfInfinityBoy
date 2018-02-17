package main;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.MainActionListener;
import jpanels.Credits;
import jpanels.GamePanel;
import jpanels.MainMenu;
import jpanels.ScoreBoard;
import jpanels.Settings;
import jpanels.SubPanels.CharacterSettings;
import jpanels.SubPanels.DifficultySettings;
import jpanels.SubPanels.GameOver;
import jpanels.SubPanels.GameSettings;
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
	
	public static void main(String[] args) {

		// Creating a map with all the panel objects and their preferred key names, because.. you know objects have genders to... i guess?
		panelMap.put("credits", new Credits());							panelMap.put("gamepanel", new GamePanel());			panelMap.put("mainmenu", new MainMenu());	
		panelMap.put("scoreboard", new ScoreBoard());					panelMap.put("settings", new Settings());			

		// Creating another map for all the sub-panels, #subpanelLivesMatter
		subPanelMap.put("charactersettings", new CharacterSettings());	subPanelMap.put("difficultysettings", new DifficultySettings());	
		subPanelMap.put("gameover", new GameOver());					subPanelMap.put("gamesettings", new GameSettings());				
		subPanelMap.put("pause", new Pause());							subPanelMap.put("splashscreen", new SplashScreen());
		
		// Switching displaying panel to the main menu
		_frame.setContentPane(mainPanel);
		setPanel("mainmenu");
		
		setFullscreen();
		_frame.setVisible(true);
	}

	// Setting "_frame" to fullscreen.
	private static void setFullscreen() {
		_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		_frame.setUndecorated(true);
	}
	
	public static void setPanel(String panel) {
		mainPanel.add(panelMap.get(panel), panel);
		cardlayout.show(mainPanel, panel);
	}
	
	// Getting a scaled index to reference how large if any the scaling of the ground object will be.
	// A resolution of 2x will result in a scaleIndex of 2.
	

}