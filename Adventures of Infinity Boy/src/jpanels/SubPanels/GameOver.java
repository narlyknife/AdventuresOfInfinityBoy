package jpanels.SubPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Engine;
import engine.MainActionListener;
import main.Init;
import main.Main;

public class GameOver extends JPanel{
	
	// Getting values from "Init" class
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	static float scaleX = Init.getScaleIndexX();
	static float scaleY = Init.getScaleIndexY();	
	
	private Image img;
	
	private static ImageIcon[] imgTitle = Engine.getScaledImageicon("titleGameover", Init.TITLE_SIZE[0] * scaleX, Init.TITLE_SIZE[1] * scaleY);
	private static ImageIcon[] imgRetry = Engine.getScaledImageicon("buttonRetry", Init.BUTTON_SET_SIZE[0] * scaleX, Init.BUTTON_SET_SIZE[1] * scaleY);
	private static ImageIcon[] imgMainmenu = Engine.getScaledImageicon("buttonMainmenu", Init.BUTTON_SET_SIZE[0] * scaleX, Init.BUTTON_SET_SIZE[1] * scaleY);
	
	JLabel title = new JLabel();
	JLabel[] buttons = {new JLabel(), new JLabel()};
		
	public GameOver() {
		img = Engine.getImage("settingsBackground.png");
		
		this.setLayout(null);
		
		title.setIcon(imgTitle[0]);
		title.setSize((int) (Init.TITLE_SIZE[0] * scaleX), (int) (Init.TITLE_SIZE[1] * scaleY));
		title.setLocation((resX - title.getWidth()) / 2, 0);
		
		buttons[0].setIcon(imgRetry[0]);
		buttons[1].setIcon(imgMainmenu[0]);
		
		Engine.animateLabel(buttons[0], imgRetry, "retry");
		Engine.animateLabel(buttons[1], imgMainmenu, "mainmenu");
		
		int yOffset = (int) (400 * scaleY);
		int yIncrease = (int) (200 * scaleY);
		
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setSize((int) (Init.BUTTON_SET_SIZE[0] * scaleX), (int) (Init.BUTTON_SET_SIZE[1] * scaleY));
			buttons[i].setLocation((resX - buttons[i].getWidth()) / 2, yOffset + (i * yIncrease));
			this.add(buttons[i]);
		}
		
		this.add(title);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0 , 0, getWidth(), getHeight(), this);
	}
}
