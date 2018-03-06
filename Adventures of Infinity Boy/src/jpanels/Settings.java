package jpanels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.MainActionListener;
import main.Main;

public class Settings extends JPanel{
	// Changes to be made
	// 
	//	 - Setup the sub panels containing the settings and all the methods for it
	//
	//   - Set action listener for all the buttons
	
	// Structure is built on JPanels on top of JPanels
	// Visual representation of structure from top to bottom
	//  - Mid panel
	//    > title
	//    > button panel (for changing the settings panel to show game, difficulty or character settings)
	//    > settings panel (area for sub panels to show up)
	//    > bottom button panel (cancel and save)
	//
	//  - Main panel (base)
	
	// Getting values from "init" file
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	JLabel title;
	JButton[] buttons = {new JButton("Game Settings"), new JButton("Difficulty Settings"), new JButton("Character Settings"), new JButton("Cancel"), new JButton("Save")};
	
	
	public Settings() {
		
		/////////////
		// Dimensions
		Dimension minMiddle = new Dimension(40, 500);					Dimension prefMiddle = new Dimension(40, 550);							Dimension maxMiddle = new Dimension(40, 600);
		Dimension minSizeAbove = new Dimension(40,200);					Dimension prefSizeAbove = new Dimension(40, 250);						Dimension maxSizeAbove = new Dimension(40, 300);
		Dimension minBottom = new Dimension(40,100); 					Dimension prefBottom = new Dimension(40, 150); 							Dimension maxBottom = new Dimension(40,200);
		Dimension minSide = new Dimension((int) (resX * 0.33), resY);	Dimension prefSide = new Dimension((int) ((resX * 0.33) + 200), resY); 	Dimension maxSide = new Dimension((int) ((resX * 0.33) + 400), resY);
		
		// Title
		title = new JLabel("Settings");
		
		title.setFont(new Font(font, Font.BOLD, 50));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		// ButtonPanel
		JPanel buttonPanel = new JPanel(new GridLayout(1,0,40,0));
		buttonPanel.add(buttons[0]);	buttonPanel.add(buttons[1]);	buttonPanel.add(buttons[2]);
		
		// Settings panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
		settingsPanel.add(new Box.Filler(minMiddle, prefMiddle, maxMiddle));
		
		// Bottom buttonPanel
		JPanel bottomButtonPanel = new JPanel(new GridLayout(1,0,40,0));
		bottomButtonPanel.add(buttons[3]);	bottomButtonPanel.add(buttons[4]);
		
		// Middle panel
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		
		midPanel.add(new Box.Filler(minSizeAbove, prefSizeAbove, maxSizeAbove));
		
		midPanel.add(title);
		
		midPanel.add(Box.createRigidArea(new Dimension(10, 50)));
		
		midPanel.add(buttonPanel);

		midPanel.add(settingsPanel);
		
		midPanel.add(bottomButtonPanel);
		
		midPanel.add(new Box.Filler(minBottom, prefBottom, maxBottom));
		
		// Main panel
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(new Box.Filler(minSide, prefSide, maxSide )); 
		this.add(midPanel); 
		this.add(new Box.Filler(minSide, prefSide, maxSide ));
		
		// Applying action Listener
		MainActionListener.addButton(buttons[3], "cancel");
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(Main.actionListener);
		}
	}
}
