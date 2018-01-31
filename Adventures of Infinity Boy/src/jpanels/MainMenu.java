package jpanels;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import engine.MainActionListener;
import main.Main;

public class MainMenu extends JPanel{
	// Changes to be made
	// 
	//	 - Clean the code!
	//
	
	public MainMenu() {
		
		// Declaration of the array containing all buttons
		JButton[] buttons = {new JButton("Start Game"), new JButton("Settings"), new JButton("Scoreboard"), new JButton("Credits"), new JButton("Quit Game")};
		
		// Aligning everything
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for(int i = 0; i < buttons.length; i++) buttons[i].setAlignmentX(CENTER_ALIGNMENT);
				
		// Adding components and adding spacing between them
		Dimension minSize = new Dimension(40,10);
		Dimension prefSize = new Dimension(40, 15);
		Dimension maxSize = new Dimension(40, 20);
		Dimension maxSizeAbove = new Dimension(40,600);
		add(new Box.Filler(minSize, prefSize, maxSizeAbove));
		add(buttons[0]);	
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(buttons[1]);	
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(buttons[2]);
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(buttons[3]);
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(buttons[4]);
		Dimension maxSizeBelow = new Dimension(40,200);
		add(new Box.Filler(minSize, prefSize, maxSizeBelow));
		
		MainActionListener.addButton(buttons[0], "gamepanel");
		MainActionListener.addButton(buttons[1], "settings");
		MainActionListener.addButton(buttons[2], "scoreboard");
		MainActionListener.addButton(buttons[3], "credits");
		MainActionListener.addButton(buttons[4], "quitgame");
		
		// Adding custom ActionListener
		for(int i = 0; i < buttons.length; i++) buttons[i].addActionListener(Main.actionListener);
		
	}
}
