import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jpanels.*;

public class Main {
	public static final JFrame _frame = new JFrame();
	public static final Init _init = new Init();
	static Map<String, JPanel> panelMap = new HashMap<String, JPanel>();
	
	public static void main(String[] args) {
		panelMap.put("credits", new Credits());
		
		setFullscreen();
		_frame.setVisible(true);
	}

	// Setting "_frame" to fullscreen.
	private static void setFullscreen() {
		_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		_frame.setUndecorated(true);
	}
	
}