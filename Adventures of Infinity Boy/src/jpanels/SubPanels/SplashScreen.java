package jpanels.SubPanels;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SplashScreen extends JPanel{
	
	Image image;
	
	public SplashScreen() {
		image = new ImageIcon(SplashScreen.class.getResource("/Pictures/Splashscreen.gif")).getImage();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

}
