package jpanels.SubPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.MainActionListener;
import main.Init;
import main.Main;

public class Pause extends JPanel{
	// Changes to be made
	//
	// - Fix buttons so they do something (Half fixed)
	//
	
	
		// Getting values from "init" file
		static int resX = Init.getResX();
		static int resY = Init.getResY();
		static Font font = Init.getFont();
		static double scale = Init.getScaleIndexX();
		
		JLabel title = new JLabel("Pause");
		JButton[] buttons = {new JButton("Main menu"), new JButton("Retry"), new JButton("Continue")};
		
	public Pause() {

		System.out.println("Pause Object created");
		
		// Title
		title.setFont(font);
		
		// Positioning and alignment
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		title.setAlignmentX(CENTER_ALIGNMENT);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setAlignmentX(CENTER_ALIGNMENT);
		}
		
		/////////////
		// Dimensions
		Dimension min = new Dimension(40, (int) (10 * scale));		Dimension pref = new Dimension(40, (int) (20 * scale));		Dimension max = new Dimension(40, (int) (30 * scale));
		Dimension maxAbove = new Dimension(40, (int) (300 * scale));Dimension minAbove = new Dimension(40, (int) (350 * scale));	Dimension prefAbove = new Dimension(40, (int) (400 * scale));
		
		// Adding
		add(new Box.Filler(minAbove, prefAbove, maxAbove));
		for (int i = 0; i < buttons.length; i++) {
			add(buttons[i], 1);
			add(new Box.Filler(min, pref, max), 1);
		}
		add(title, 1);
		
		// Applying custom action listener
		MainActionListener.addButton(buttons[0], "return");
		MainActionListener.addButton(buttons[1], "startOver");
		MainActionListener.addButton(buttons[2], "contine");
		
		for(int i = 0; i < buttons.length; i++) buttons[i].addActionListener(Main.actionListener);

		this.setBackground(Color.black);
	}

}
