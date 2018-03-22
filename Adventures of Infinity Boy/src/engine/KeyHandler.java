package engine;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import jpanels.GamePanel;
import main.Main;
import pui.gameGeneralThread;

public class KeyHandler{
	
	Action pauseAction, jumpAction;
	boolean gamePaused = false;

	// The action to perform the steps of a pause. (Game movement set to 0, display/hide pause menu)
	public KeyHandler(){
		pauseAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				if(!gamePaused) {
					System.out.println("game has been paused");
					Main.showSubPanel(Main.getSubPanel("pause"), true);
					gameGeneralThread.pauseGame();
				} else {
					System.out.println("game has been resumed");
					Main.showSubPanel(Main.getSubPanel("pause"), false);
					gameGeneralThread.resumeGame();
				}
					
				gamePaused = !gamePaused;
			}
		};
		
		jumpAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				if(gameGeneralThread.jumping() == false) {
					 gameGeneralThread.enableJump();
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
	
	
}
