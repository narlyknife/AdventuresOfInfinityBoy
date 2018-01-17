package jpanels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

public class Settings extends JPanel implements ActionListener{
	
	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	JLabel title;
	JButton game, difficulty, character, save, cancel;
	
	public Settings() {
		
		// Declarations
		title = new JLabel("Settings");
		game = new JButton("Game Settings");
		difficulty = new JButton("Difficulty Settings");
		character = new JButton("Character Settings");
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		
		title.setFont(new Font(font, Font.PLAIN, 40));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		// ButtonPanel
		JPanel buttonPanel = new JPanel(new GridLayout(1,0,40,0));
		buttonPanel.add(game);	buttonPanel.add(difficulty);	buttonPanel.add(character);
		
		// Bottom buttonPanel
		JPanel bottomButtonPanel = new JPanel(new GridLayout(1,0,40,0));
		bottomButtonPanel.add(cancel);	bottomButtonPanel.add(save);
		
		// Settings panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
		Dimension minMiddle = new Dimension(40, 500);		Dimension prefMiddle = new Dimension(40, 550);		Dimension maxMiddle = new Dimension(40, 600);
		settingsPanel.add(new Box.Filler(minMiddle, prefMiddle, maxMiddle));
		
		// Middle pane
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		
		Dimension minSizeAbove = new Dimension(40,200);		Dimension prefSizeAbove = new Dimension(40, 250);		Dimension maxSizeAbove = new Dimension(40, 300);
		midPanel.add(new Box.Filler(minSizeAbove, prefSizeAbove, maxSizeAbove));
		
		midPanel.add(title);
		
		midPanel.add(Box.createRigidArea(new Dimension(10, 50)));
		
		midPanel.add(buttonPanel);

		midPanel.add(settingsPanel);
		
		midPanel.add(bottomButtonPanel);
		
		Dimension minBottom = new Dimension(40,100); Dimension prefBottom = new Dimension(40, 150); Dimension maxBottom = new Dimension(40,200);
		midPanel.add(new Box.Filler(minBottom, prefBottom, maxBottom));
		
		// Main panel
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		Dimension minSide = new Dimension((int) (resX * 0.33), resY);		Dimension prefSide = new Dimension((int) ((resX * 0.33) + 200), resY); Dimension maxSide = new Dimension((int) ((resX * 0.33) + 400), resY);
		add(new Box.Filler(minSide, prefSide, maxSide )); add(midPanel); add(new Box.Filler(minSide, prefSide, maxSide ));
		
		// Applying action Listener
		game.addActionListener(this);
		difficulty.addActionListener(this);
		character.addActionListener(this);
		save.addActionListener(this);
		cancel.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == game) {
			
		}
		if (e.getSource() == difficulty) {
			
		}
		if (e.getSource() == character) {
			
		}
		if (e.getSource() == save) {
			
		}
		if (e.getSource() == cancel) {
			
		}
	}
}
