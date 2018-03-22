package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

import jpanels.GamePanel;
import jpanels.Settings;
import main.Main;

public class MainActionListener implements ActionListener{
    
	// Button register
    public static Map<String, JButton> buttonMap = new HashMap<String, JButton>();
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonMap.get("gamepanel")) {
			Main.setPanel("gamepanel");
			GamePanel.startMainThread();
		}
		if(e.getSource() == buttonMap.get("settings")) {
			Main.setPanel("settings");
		}
		if(e.getSource() == buttonMap.get("scoreboard")) {
			Main.setPanel("scoreboard");
		}
		if(e.getSource() == buttonMap.get("credits")) {
			Main.setPanel("credits");
		}
		if(e.getSource() == buttonMap.get("quitgame")) {
			System.exit(0); // KILL IT WITH FIRE!
		}

		// The names of all the button must be unique, so if you want several buttons to lead to the same place, this is what you have to do:
		if(e.getSource() == buttonMap.get("back") || e.getSource() == buttonMap.get("cancel") || e.getSource() == buttonMap.get("mainMenu") || e.getSource() == buttonMap.get("return") || e.getSource() == buttonMap.get("save")) {
			Main.setPanel("mainmenu");
		}
		if(e.getSource() == buttonMap.get("retry") || e.getSource() == buttonMap.get("startOver")) {
			// Code
		}
		if(e.getSource() == buttonMap.get("continue")) {
			// Code
		}
		if(e.getSource() == buttonMap.get("gamesettings")) {
			Settings.changeSettingPanel("gamesettings");
			System.out.println("NOTE: Switched to game settings");
		}
		if(e.getSource() == buttonMap.get("difficultysettings")) {
			Settings.changeSettingPanel("difficultysettings");
			System.out.println("NOTE: Switched to difficulty settings");
		}
		if(e.getSource() == buttonMap.get("charactersettings")) {
			Settings.changeSettingPanel("charactersettings");
			System.out.println("NOTE: Switched to character settings");
		}
	}
	
	// Add a button to the register with a key to associate it with
	public static void addButton(JButton button, String name) {
		buttonMap.put(name, button);
	}
	
}
