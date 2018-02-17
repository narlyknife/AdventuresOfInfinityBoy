package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

import jpanels.GamePanel;
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
		if(e.getSource() == buttonMap.get("back") || e.getSource() == buttonMap.get("cancel") || e.getSource() == buttonMap.get("mainMenu")) {
			Main.setPanel("mainmenu");
		}
	}
	
	// Add a button to the register with a key to associate it with
	public static void addButton(JButton button, String name) {
		buttonMap.put(name, button);
	}
	
}
