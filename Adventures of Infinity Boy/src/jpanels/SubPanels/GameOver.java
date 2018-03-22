package jpanels.SubPanels;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.MainActionListener;
import main.Main;

public class GameOver extends JPanel {
	
	// Getting values from "init" file
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	static double scale = Main._init.getScaleIndexX();
	
	JLabel title;
	JButton[] buttons = {new JButton("Retry"), new JButton("Main menu")};
	
	public GameOver() {
		
		// Declarations
		title = new JLabel("Game Over!");
		
		title.setFont(new Font(font, Font.PLAIN, 40));
		
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
		add(title);
		for (int i = 0; i < buttons.length; i++) {
			add(new Box.Filler(min, pref, max));
			add(buttons[i]);
		}
		
		// Adding custom action listener
		MainActionListener.addButton(buttons[0], "retry");
		MainActionListener.addButton(buttons[1], "back");
		
		for(int i = 0; i < buttons.length; i++) buttons[i].addActionListener(Main.actionListener);
	}
}