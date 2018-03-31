package jpanels;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Engine;
import main.Init;

public class MainMenu extends JPanel{
	// Changes to be made
	// 
	//	 - N/A
	//
	
	// Getting and setting values from init
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	private Image img;
	
	private static float scaleX = Init.getScaleIndexX();
	private static float scaleY = Init.getScaleIndexY();
	
	private static ImageIcon[] imgStart = Engine.getScaledImageicon("buttonStart", Init.BUTTON_SIZE_1[0] * scaleX, Init.BUTTON_SIZE_1[1] * scaleY);
	private static ImageIcon[] imgSettings = Engine.getScaledImageicon("buttonSettings", Init.BUTTON_SIZE_1[0] * scaleX, Init.BUTTON_SIZE_1[1] * scaleY);
	private static ImageIcon[] imgScoreboard = Engine.getScaledImageicon("buttonScoreboard", Init.BUTTON_SIZE_2[0] * scaleX, Init.BUTTON_SIZE_2[1] * scaleY);
	private static ImageIcon[] imgCredits = Engine.getScaledImageicon("buttonCredits", Init.BUTTON_SIZE_1[0] * scaleX, Init.BUTTON_SIZE_1[1] * scaleY);
	private static ImageIcon[] imgQuit = Engine.getScaledImageicon("buttonQuit", Init.BUTTON_SIZE_2[0] * scaleX, Init.BUTTON_SIZE_2[1] * scaleY);
	
	private static JLabel[] buttons = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()};
	private static JLabel title = new JLabel();
	
	public MainMenu() {
		this.setLayout(null);
		
		img = Engine.getImage("mainBackground.png");
		
		// Settings Icons
		title.setIcon(new ImageIcon(Engine.getScaledImage(Engine.getImage("titleLogo.png"), Init.LOGO_SIZE[0], Init.LOGO_SIZE[1])));
		
		buttons[0].setIcon(imgStart[0]);
		buttons[1].setIcon(imgSettings[0]);
		buttons[2].setIcon(imgScoreboard[0]);
		buttons[3].setIcon(imgCredits[0]);
		buttons[4].setIcon(imgQuit[0]);
		
		Engine.animateLabel(buttons[0], imgStart, "gamepanel");
		Engine.animateLabel(buttons[1], imgSettings, "settings");
		Engine.animateLabel(buttons[2], imgScoreboard, "scoreboard");
		Engine.animateLabel(buttons[3], imgCredits, "credits");
		Engine.animateLabel(buttons[4], imgQuit, "quit");
		
		Dimension dim1 = new Dimension((int) (Init.BUTTON_SIZE_1[0] * scaleX), (int) (Init.BUTTON_SIZE_1[1] * scaleY));
		Dimension dim2 = new Dimension((int) (Init.BUTTON_SIZE_2[0] * scaleX), (int) (Init.BUTTON_SIZE_2[1] * scaleY));
		for (int i = 0; i < buttons.length; i++) {
			if(i == 2 || i == 4) buttons[i].setSize(dim2);
			else buttons[i].setSize(dim1);
			
			buttons[i].setLocation((Init.SCREEN_RES_X - buttons[i].getWidth()) / 2, (int) (480 * scaleY) + i * (int) (80 * scaleY));
			
			if(i == 4) buttons[4].setLocation(buttons[4].getX(), buttons[4].getY() + (int) (60 * scaleY));
			this.add(buttons[i]);
		}
		
		title.setSize((int) (Init.LOGO_SIZE[0] * scaleX), (int) (Init.LOGO_SIZE[1] * scaleY));
		title.setLocation((Init.SCREEN_RES_X - title.getWidth()) / 2, (int) (120 * scaleY));
		this.add(title);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0 , 0, getWidth(), getHeight(), this);
	}
}
