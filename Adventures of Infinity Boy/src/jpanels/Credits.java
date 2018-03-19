package jpanels;

import java.awt.Dimension;
import java.awt.Font;

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
	//	- N/A
	//
	
	// Getting values from "init" file
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	static double scale = Main._init.getScaleIndex();
	
	JLabel mainHeader = new JLabel("Credits");
	
	// Each title gets their own Array of labels. The first element is used for a title or description of what the people under it did.
	JLabel[] programmers = {new JLabel("Programmers"), new JLabel("Herman Eriksson"), new JLabel("Folke Johansson")};
	JButton[] buttons = {new JButton("Back")};
	
	public Credits() {
		
		///////////////
		// Setting font
		mainHeader.setFont(new Font(font, Font.BOLD, 50));
		programmers = setFontAndAlign(programmers);
		
		////////////////////////////////////
		// Layout, positioning and alignment
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		mainHeader.setAlignmentX(CENTER_ALIGNMENT);
				
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setAlignmentX(CENTER_ALIGNMENT);
		}
		
		//////////////////////////////////////////
		// Setting new dimensions for filler boxes
		Dimension min = new Dimension(40, (int) (10 * scale));		Dimension pref = new Dimension(40, (int) (20 * scale));		 Dimension max = new Dimension(40, (int) (30 * scale));
		Dimension minAbove = new Dimension(40, (int) (150 * scale));Dimension prefAbove = new Dimension(40, (int) (200 * scale));Dimension maxAbove = new Dimension(40, (int) (250 * scale));		
		
		/////////
		// Adding
		add(new Box.Filler(minAbove, prefAbove, maxAbove));
		add(mainHeader);
		add(new Box.Filler(min, pref, max));
		add(new Box.Filler(min, pref, max));
		
		for (int i = 0; i < programmers.length; i++) {
			add(programmers[i]);
			add(new Box.Filler(min, pref, max));
		}
	
		add(new Box.Filler(minAbove, prefAbove, maxAbove));
		add(buttons[0]);
		
		////////////////////////////////
		// Setting custom actionListener
		MainActionListener.addButton(buttons[0], "back");
		buttons[0].addActionListener(Main.actionListener);
		
	}
	//////////
	// Methods
	public JLabel[] setFontAndAlign(JLabel[] array) {
		JLabel[] data = array;
		int length = data.length;
		
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				data[i].setFont(new Font(font, Font.BOLD, 30));
				data[i].setAlignmentX(CENTER_ALIGNMENT);
			}
			else {
				data[i].setFont(new Font(font, Font.PLAIN, 18));
				data[i].setAlignmentX(CENTER_ALIGNMENT);
			}
		}
		
		return data;
	}
}