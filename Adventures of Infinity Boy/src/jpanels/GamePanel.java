package jpanels;

import javax.swing.JComponent;
import javax.swing.JPanel;

import javax.swing.KeyStroke;

import gui.GroundBlocks;
import gui.Obstacles;
import pui.*;

import engine.KeyHandler;
import main.Main;
import pui.gameGeneralThread;

public class GamePanel extends JPanel{
	
	// Getting values from "init" file
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	KeyHandler keyHandler = new KeyHandler();
	
	// Creating a new thread for the processing of world movement.
	static gameGeneralThread mainThread = new gameGeneralThread();
	
	@SuppressWarnings("static-access")
	public GamePanel() {
		System.out.println("New Gameobject Created");
		 
		// Allowing for a XY precise placement, beneficial for a JPanel with the purpose of multiple object placements.
		this.setLayout(null);
		
		// Adding events to the key bindings
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "showPauseMenu");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("PAUSE"), "showPauseMenu");
		this.getActionMap().put("showPauseMenu", keyHandler.getPauseAction());
		
		for(int i = 0; i < mainThread.obstacle.length; i++) this.add(mainThread.obstacle[i]);
		
		this.add(mainThread.character);
	}
	
	// Starting new thread
	public static void startMainThread() {
		mainThread.start();
	}
}
