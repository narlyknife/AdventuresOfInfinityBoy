package jpanels.SubPanels;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Init;

public class SplashScreen extends JPanel{
	
	public SplashScreen() {
		ImageIcon imageIcon = new ImageIcon(SplashScreen.class.getResource("/Pictures/splashscreen.gif"));
		JLabel label = new JLabel(imageIcon);
		
		this.setLayout(null);
		label.setLocation(0, 0);
		label.setSize(Init.SCREEN_RES_X, Init.SCREEN_RES_Y);
		this.add(label);
	}
}