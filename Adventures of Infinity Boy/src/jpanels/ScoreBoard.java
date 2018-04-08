package jpanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import engine.Engine;
import main.Init;

public class ScoreBoard extends JPanel{	
	
	// Getting values from "init" file
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	static float scaleX = Init.getScaleIndexX();
	static float scaleY = Init.getScaleIndexY();
	
	private static ImageIcon[] imgTitle = Engine.getScaledImageicon("titleScoreboard", Init.TITLE_SIZE[0] * scaleX, Init.TITLE_SIZE[1] * scaleY);
	private static ImageIcon[] imgBack = Engine.getScaledImageicon("buttonBack", Init.BUTTON_SET_SIZE[0] * scaleX, Init.BUTTON_SET_SIZE[1] * scaleY);
	
	private JLabel header =  new JLabel();
	private JLabel button = new JLabel();
	private JLabel[] scores = new JLabel[10];
	
	private Image img;
	
	public ScoreBoard() {
		img = new ImageIcon(MainMenu.class.getResource("/resources/settingsBackground.png")).getImage();
		
		this.setLayout(null);
		
		// Accessing scores and then putting them into the JLabel array for later use
		setScores();
		
		header.setSize((int) (Init.TITLE_SIZE[0] * scaleX), (int) (Init.TITLE_SIZE[1] * scaleY));
		header.setLocation((resX - header.getWidth()) / 2, 0);
		header.setIcon(imgTitle[0]);
		
		button.setSize((int) (Init.BUTTON_SET_SIZE[0] * scaleX), (int) (Init.BUTTON_SET_SIZE[1] * scaleY));
		button.setLocation((int) ((resX - button.getWidth()) / 2), (int) (930 * scaleY));
		button.setIcon(imgBack[0]);
		
		Engine.animateLabel(button, imgBack, "mainmenu");
		
		this.add(header);
		this.add(button);
		
		// Adding
		for (int i = 0; i < scores.length; i++) {
			this.add(scores[i]);
		}
	}
	
	// Goes trough all scores in the array and if there is no score it fills the spot with "N/A"
	public void setScores() {
		Dimension dim = new Dimension((int) (Init.SCOREBOARD_SIZE[0] * scaleX), (int) (Init.SCOREBOARD_SIZE[1] * scaleY));
		int yOffset = 320;
		
		for (int i = 0; i < scores.length; i++) {
			scores[i] = new JLabel("N/A in BETA", SwingConstants.CENTER);
			
			scores[i].setFont(Init.getFont());
			scores[i].setForeground(Init.getTextColor());

			scores[i].setSize(dim);
			scores[i].setLocation((int) ((resX - scores[i].getWidth()) / 2), (int) ((yOffset + 60 * i) * scaleY));
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0 , 0, getWidth(), getHeight(), this);
	}
}
