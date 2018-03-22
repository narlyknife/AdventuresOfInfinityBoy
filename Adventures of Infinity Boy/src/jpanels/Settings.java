package jpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.MainActionListener;
import jpanels.SubPanels.CharacterSettings;
import jpanels.SubPanels.DifficultySettings;
import jpanels.SubPanels.GameOver;
import jpanels.SubPanels.GameSettings;
import main.Init;
import main.Main;

public class Settings extends JPanel{
	// Changes to be made
	//
	//	 - Clean up the code.
	// 
	//	 - Setup the sub panels containing the settings and all the methods for it
	//
	
	
	// Declarations
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	static String font = Init.getOurFont();
	
	JLabel title;
	static JPanel settingsPanel;
	JButton[] buttons = {new JButton("Game Settings"), new JButton("Difficulty Settings"), new JButton("Character Settings"), new JButton("Cancel"), new JButton("Save")};
	
	
	public Settings() {
		
		// Declarations
		title = new JLabel("Settings");
		
		title.setFont(new Font(font, Font.BOLD, 50));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		/////////////
		// Dimensions
		
		// Currently used in the settings panel used for showing sub panels. Placeholder really...
    Dimension minMiddle = new Dimension(40, (int) (500 * Init.getScaleIndexY()));		Dimension prefMiddle = new Dimension(40, (int) (550 * Init.getScaleIndexY()));			Dimension maxMiddle = new Dimension(40, (int) (600 * Init.getScaleIndexY()));
		
		// Spacing above header/title
		Dimension minAbove = new Dimension(40,(int) (100 * Init.getScaleIndexY()));		Dimension prefAbove = new Dimension(40, (int) (150 * Init.getScaleIndexY()));		Dimension maxAbove = new Dimension(40, (int) (200 * Init.getScaleIndexY()));
		
		// Creates a filler to make the bottom buttons look normal sized.
		Dimension minBottom = new Dimension(40,100); 				Dimension prefBottom = new Dimension(40, 150); 							Dimension maxBottom = new Dimension(40,200);
		
		// Contains dimensions for the height of the screen and 1/10th of its width. Used to center the content on the panel
		Dimension minSide = new Dimension((int) (resX * 0.05 * Init.getScaleIndexX()), (int) (resY * Init.getScaleIndexY()));		Dimension prefSide = new Dimension((int) ((resX * 0.05 * Init.getScaleIndexX()) + 200), (int) (resY * Init.getScaleIndexY())); Dimension maxSide = new Dimension((int) ((resX * 0.05 * Init.getScaleIndexX()) + 400), (int) (resY * Init.getScaleIndexY()));
		
		// ButtonPanel
		JPanel buttonPanel = new JPanel(new GridLayout(1,0,40,0));
		buttonPanel.add(buttons[0]);	buttonPanel.add(buttons[1]);	buttonPanel.add(buttons[2]);
		
		// Bottom buttonPanel
		JPanel bottomButtonPanel = new JPanel(new GridLayout(1,0,40,0));
		bottomButtonPanel.add(buttons[3]);	bottomButtonPanel.add(buttons[4]);
		
		// Settings panel
		settingsPanel = new JPanel();
		settingsPanel.add(new Box.Filler(minMiddle, prefMiddle, maxMiddle));
		settingsPanel.setLayout(new BorderLayout());
		settingsPanel.add(Main.getSubPanel("gamesettings"), 0);
		settingsPanel.add(Main.getSubPanel("difficultysettings"), 0);
		settingsPanel.add(Main.getSubPanel("charactersettings"), 0);
		
		// Middle panel
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		
		midPanel.add(new Box.Filler(minAbove, prefAbove, maxAbove));
		
		midPanel.add(title);
		
		midPanel.add(Box.createRigidArea(new Dimension(10, 50)));

		midPanel.add(buttonPanel);
		
		midPanel.add(Box.createRigidArea(new Dimension(10, 50)));
		
		settingsPanel.setLayout(new BorderLayout());
		
		Main.subPanelMap.put("charactersettings", new CharacterSettings());	
		Main.subPanelMap.put("difficultysettings", new DifficultySettings());	
		Main.subPanelMap.put("gamesettings", new GameSettings());
		
		settingsPanel.add(Main.getSubPanel("gamesettings"), 0);
		settingsPanel.add(Main.getSubPanel("difficultysettings"), 0);
		settingsPanel.add(Main.getSubPanel("charactersettings"), 0);

		midPanel.add(settingsPanel);
		
		midPanel.add(Box.createRigidArea(new Dimension(10, 50)));
		
		midPanel.add(bottomButtonPanel);
		
		midPanel.add(new Box.Filler(minBottom, prefBottom, maxBottom));
		
		// Main panel
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(new Box.Filler(minSide, prefSide, maxSide )); 
		this.add(midPanel); 
		this.add(new Box.Filler(minSide, prefSide, maxSide ));
		
		// Applying action Listener

		MainActionListener.addButton(buttons[0], "gamesettings");
		MainActionListener.addButton(buttons[1], "difficultysettings");
		MainActionListener.addButton(buttons[2], "charactersettings");
		MainActionListener.addButton(buttons[3], "cancel");
		MainActionListener.addButton(buttons[4], "save");
		
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(Main.actionListener);
		}
	}
	
	public static void changeSettingPanel(String name) {
		Main.getSubPanel(name).setVisible(true);
		if(!(name.equals("gamesettings"))) Main.getSubPanel("gamesettings").setVisible(false);
		if(!(name.equals("difficultysettings"))) Main.getSubPanel("difficultysettings").setVisible(false);
		if(!(name.equals("charactersettings"))) Main.getSubPanel("charactersettings").setVisible(false);
	}
	
	public static int getSettingsPanelW() {
		return settingsPanel.getWidth();
	}
	
	public static int getSettingsPanelH() {
		return settingsPanel.getHeight();
	}
}
