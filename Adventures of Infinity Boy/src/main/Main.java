package main;

import javax.swing.JFrame;

public class Main {
	public static final JFrame _frame = new JFrame();
	public static final Init _init = new Init();
	
	public static void main(String[] args) {
		setFullscreen();
		
		
		
		_frame.setVisible(true);
	}

	// Setting "_frame" to fullscreen.
	private static void setFullscreen() {
		_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		_frame.setUndecorated(true);
	}
	
}