package jpanels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.MainActionListener;
import main.Main;

public class Credits extends JPanel {
	// CHANGES TO BE MADE
	//
	//	 - Polish up the code a little bit. 
	//
	//	 - Introduce Hash maps to make headlines and people more smoothly?
	//
	
	
	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	JLabel mainHeader = new JLabel("Credits");
	JLabel[] headers = {new JLabel("Programmers")};
	JLabel[] programmers = {new JLabel("Herman Eriksson"), new JLabel("Folke Johansson")};
	JButton[] buttons = {new JButton("Back")};
	public Credits() {
		

		///////////////
		// Setting font
		mainHeader.setFont(new Font(font, Font.BOLD, 50));
		
		// Headers
		for (int i = 0; i < headers.length; i++) {
			headers[i].setFont(new Font(font, Font.PLAIN, 30));
		}
		
		// Programmers
		for (int i = 0; i < programmers.length; i++) {
			programmers[i].setFont(new Font(font, Font.PLAIN, 18));
		}

		///////////////////////////
		//Positioning and alignment
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		mainHeader.setAlignmentX(CENTER_ALIGNMENT);
		
		// Headers
		for (int i = 0; i < headers.length; i++) {
			headers[i].setAlignmentX(CENTER_ALIGNMENT);
		}
		
		// Programmers
		for (int i = 0; i < programmers.length; i++) {
			programmers[i].setAlignmentX(CENTER_ALIGNMENT);
		}
		
		// Buttons
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setAlignmentX(CENTER_ALIGNMENT);
		}
		
		/////////
		// Adding
		Dimension minSize = new Dimension(40,10);		Dimension prefSize = new Dimension(40, 20);		Dimension maxSize = new Dimension(40, 30);
		Dimension minSizeAbove = new Dimension(40,200);		Dimension prefSizeAbove = new Dimension(40, 250);		Dimension maxSizeAbove = new Dimension(40, 300);
		add(new Box.Filler(minSizeAbove, prefSizeAbove, maxSizeAbove));
		add(mainHeader);
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(headers[0]); 		// Title
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(programmers[0]);	// Person
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(programmers[1]);	//Person
		Dimension minSizeBelow = new Dimension(100, 100);
		Dimension prefSizeBelow = new Dimension(100, 150);
		Dimension maxSizeBelow = new Dimension(100, 200);
		add(new Box.Filler(minSizeBelow, prefSizeBelow, maxSizeBelow));
		add(buttons[0]);
		
		////////////////////////////////
		// Setting custom actionListener
		MainActionListener.addButton(buttons[0], "back");
		buttons[0].addActionListener(Main.actionListener);
		
	}
	
}