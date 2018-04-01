package jpanels;

import javax.swing.JComponent;
import javax.swing.JPanel;

import javax.swing.KeyStroke;

import gui.GroundBlocks;
import gui.Obstacles;
import pui.*;
import engine.Engine;
import engine.KeyHandler;
import main.Init;
import main.Main;
import pui.gameGeneralThread;

public class GamePanel extends JPanel{
	
	// Getting values from "init" file
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	KeyHandler keyHandler = new KeyHandler();
	
	// Creating a new thread for the processing of world movement.
	static gameGeneralThread mainThread = new gameGeneralThread();
	
	@SuppressWarnings("static-access")
	public GamePanel() {
		// Allowing for a XY precise placement, beneficial for a JPanel with the purpose of multiple object placements.
		this.setLayout(null);
		
		// Adding events to the key bindings
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "showPauseMenu");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("PAUSE"), "showPauseMenu");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "jump");
		this.getActionMap().put("showPauseMenu", keyHandler.getPauseAction());
		this.getActionMap().put("jump", keyHandler.getJumpAction());
		
		for(int i = 0; i < mainThread.obstacle.length; i++) this.add(mainThread.obstacle[i]);
		
		this.add(mainThread.character);
	}
	
	// Starting new thread
	public static void startMainThread() {
		int random = (int) (10 * Math.random());
		
		if(Init.settingsData[0] == 1) {
			if(random <= 5) Engine.playAudio("easy1.wav");
			else Engine.playAudio("easy2.wav");
		} else if(Init.settingsData[0] == 2) {
			if(random <= 5) Engine.playAudio("normal1.wav");
			else Engine.playAudio("normal2.wav");
		}
		else {
			if(random <= 5) Engine.playAudio("hard3.wav");
			else Engine.playAudio("hard3.wav");
		}
		
		gameGeneralThread mainThread = new gameGeneralThread();
		mainThread.start();
	}
}
