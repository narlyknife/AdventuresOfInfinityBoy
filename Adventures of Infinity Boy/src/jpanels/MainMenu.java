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

public class MainMenu extends JPanel{
	// Changes to be made
	// 
	//	 - N/A
	//
	
	// Getting and setting values from intit
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	static double scale = Main._init.getScaleIndex();
	
	public MainMenu() {
		
		// Declaration of the array containing all buttons
		JButton[] buttons = {new JButton("Start Game"), new JButton("Settings"), new JButton("Scoreboard"), new JButton("Credits"), new JButton("Quit Game")};
		JLabel title = new JLabel("Infinity Squirrel");
		
		// Aligning everything
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for(int i = 0; i < buttons.length; i++) buttons[i].setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font(font, Font.BOLD, 50));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		/////////////
		// Dimensions
		Dimension min = new Dimension(40, (int) (10 * scale));		Dimension pref = new Dimension(40, (int) (20 * scale));		Dimension max = new Dimension(40, (int) (30 * scale));
		Dimension maxAbove = new Dimension(40, (int) (200 * scale));Dimension minAbove = new Dimension(40, (int) (250 * scale));	Dimension prefAbove = new Dimension(40, (int) (300 * scale));
		
		// Adding components and spacing between them
		add(new Box.Filler(minAbove, prefAbove, maxAbove));
		add(new Box.Filler(min, pref, max));
		add(title);
		add(new Box.Filler(minAbove, prefAbove, maxAbove));
		add(new Box.Filler(min, pref, max));
		add(new Box.Filler(min, pref, max));
		
		for (int i = 0; i < buttons.length; i++) {
			// Adding more space between "quit game" button and the rest so that quit game is separated.
			if (i == buttons.length - 1 ) {
				add(new Box.Filler(min, pref, max));
				add(new Box.Filler(min, pref, max));
				add(buttons[i]);
			}
			else {
				add(buttons[i]);
				add(new Box.Filler(min, pref, max));
			}
		}
		
		
		// Adding custom ActionListener
		MainActionListener.addButton(buttons[0], "gamepanel");
		MainActionListener.addButton(buttons[1], "settings");
		MainActionListener.addButton(buttons[2], "scoreboard");
		MainActionListener.addButton(buttons[3], "credits");
		MainActionListener.addButton(buttons[4], "quitgame");
		
		for(int i = 0; i < buttons.length; i++) buttons[i].addActionListener(Main.actionListener);
		
	}
}
