package jpanels.SubPanels;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Init;

public class SplashScreen extends JPanel{
	
	public SplashScreen() {
		URL url = SplashScreen.class.getResource("/Pictures/Splashscreen.gif");
		ImageIcon imageIcon = new ImageIcon(url);
		JLabel label = new JLabel(imageIcon);
		
		this.setLayout(null);
		label.setLocation(0, 0);
		label.setSize(Init.SCREEN_RES_X, Init.SCREEN_RES_Y);
		this.add(label);
	}
}
