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
		Dimension minSize = new Dimension(40,10);		Dimension prefSize = new Dimension(40, 20);			Dimension maxSize = new Dimension(40, 30);
		Dimension minSizeAbove = new Dimension(40,300);	Dimension prefSizeAbove = new Dimension(40, 350);	Dimension maxSizeAbove = new Dimension(40, 400);

		// Adding
		add(new Box.Filler(minSizeAbove, prefSizeAbove, maxSizeAbove));
		add(title);
		for (int i = 0; i < buttons.length; i++) {
			add(new Box.Filler(minSize, prefSize, maxSize));
			add(buttons[i]);
		}
		
		// Adding custom action listener
		MainActionListener.addButton(buttons[0], "retry");
		MainActionListener.addButton(buttons[1], "back");
		
		for(int i = 0; i < buttons.length; i++) buttons[i].addActionListener(Main.actionListener);
	}
}