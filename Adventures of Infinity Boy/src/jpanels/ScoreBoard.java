package jpanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.MainActionListener;
import main.Main;

public class ScoreBoard extends JPanel{
	// CHANGES TO BE MADE
	//	
	//	 - The scores need to be taken from a file. Fix plz.
	//
	
	// The structure is built on JPanels on top of JPanels.
	// Visual representation of structure from top to bottom:
	//  - Score panel
	//  - Filler panel
	//  - Mid panel
	//  - JFrame (base)
	
	//////////////////////////////////
	// Getting values from "init" file
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	static double scale = Main._init.getScaleIndexX();
	
	JLabel header =  new JLabel("Scoreboard");
	private JLabel[] scores = new JLabel[10];
	JButton[] buttons = {new JButton("Back")};
	
	//////////////
	// Constructor
	public ScoreBoard() {
		
		// Accessing scores and then putting them into the JLabel array for later use
		setScores();
		
		/////////////
		// Dimensions
		Dimension min = new Dimension(40, (int) (10 * scale));		Dimension pref = new Dimension(40, (int) (20 * scale));		Dimension max = new Dimension(40, (int) (30 * scale));
		Dimension minHalf = new Dimension((int) (resX * 0.2), (int) (300 * scale));	Dimension prefHalf = new Dimension((int) (resX * 0.2) + 10, (int) (300 * scale));	Dimension maxHalf = new Dimension((int) (resX * 0.2) + 15, (int) (300 * scale));
		Dimension minSide = new Dimension((int) (resX * 0.33), resY);		Dimension prefSide = new Dimension((int) ((resX * 0.33) + 25), resY); 	Dimension maxSide = new Dimension((int) ((resX * 0.33) + 50), resY);
		Dimension minMiddle = new Dimension((int) (resX * 0.33), (int) (60 * scale));
		
		//////////////
		// Score panel
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
//		scorePanel.setBackground(Color.darkGray);
		
		// Adding
		for (int i = 0; i < scores.length; i++) {
			scorePanel.add(scores[i]);
			scorePanel.add(new Box.Filler(min, pref, max));
		}
		
		// Alignment
		for (int i = 0; i < scores.length; i++) {
			scores[i].setAlignmentX(LEFT_ALIGNMENT);
		}
		
		///////////////
		// Filler Panel
		JPanel fillerPanel = new JPanel();
		fillerPanel.setLayout(new BoxLayout(fillerPanel, BoxLayout.X_AXIS));
//		fillerPanel.setBackground(Color.orange);
		fillerPanel.add(scorePanel);
		fillerPanel.add(new Box.Filler(minHalf, prefHalf, maxHalf));
		
		
		////////////
		// Mid panel
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
//		midPanel.setBackground(Color.gray);
		// Positioning and font
		header.setAlignmentX(CENTER_ALIGNMENT);
		header.setFont(new Font(font, Font.BOLD, 50));		
		buttons[0].setAlignmentX(CENTER_ALIGNMENT);
		
		// Adding		
		midPanel.add(new Box.Filler(min, pref, max));
		
		midPanel.add(header);
		
		midPanel.add(new Box.Filler(minMiddle, minMiddle, minMiddle));
		
		midPanel.add(fillerPanel);

		midPanel.add(new Box.Filler(min, pref, max));
		midPanel.add(new Box.Filler(min, pref, max));
		
		midPanel.add(buttons[0]);
		
		// Main panel
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new Box.Filler(minSide, prefSide, maxSide ));
		add(midPanel);
		add(new Box.Filler(minSide, prefSide, maxSide ));
//		this.setBackground(Color.cyan);
		
		
		////////////////////////////
		// Add custom actionListener
		MainActionListener.addButton(buttons[0], "mainMenu");
		buttons[0].addActionListener(Main.actionListener);
	}
	
	// Goes trough all scores in the array and if there is no score it fills the spot with "N/A"
	public void setScores() {
		int length = scores.length;
		
		for (int i = 0; i < length; i++) {
				scores[i] = new JLabel((i + 1) + ". N/A");
		}
	}
}
