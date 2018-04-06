package engine;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import jpanels.GamePanel;
import main.Main;

public class KeyHandler{
	
	Action pauseAction, jumpAction, dropAction;
	static boolean gamePaused = false;

	// The action to perform the steps of a pause. (Game movement set to 0, display/hide pause menu)
	public KeyHandler(){
		pauseAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				if(!gamePaused) {
					Engine.stopAudio();
					System.out.println("NOTE: Game has been paused");
					Main.showSubPanel(Main.getSubPanel("pause"), true);
					GamePanel.pauseGame();
				} else {
					Engine.resumeAudio();
					System.out.println("Note: Game has been resumed");
					Main.showSubPanel(Main.getSubPanel("pause"), false);
					GamePanel.resumeGame();
				}
					
				gamePaused = !gamePaused;
			}
		};
		
		jumpAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if(CharacterTimer.getJumping() == false || CharacterTimer.getOnTopPlat() == true) {
					CharacterTimer.jump();
				}
			}
		};
		
		dropAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if(CharacterTimer.getOnBotPlat()) {
					CharacterTimer.drop();
				}
			}
		};
	}
	
	public Action getPauseAction() {
		return pauseAction;
	}
	
	public Action getJumpAction() {
		return jumpAction;
	}
	
	public Action getDropAction() {
		return dropAction;
	}
}
