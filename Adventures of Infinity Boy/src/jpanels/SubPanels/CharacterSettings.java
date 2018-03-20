package jpanels.SubPanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Init;

public class CharacterSettings extends JPanel{
	
	private static Image[] char1 = {
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/character1def.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/character1hov.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/character1pre.png")).getImage()
	};
	
	private static Image[] char2 = {
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/character2def.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/character2hov.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/character2pre.png")).getImage()
	};
	
	private static Image[] char3 = {
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/character3def.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/character3hov.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/character3pre.png")).getImage()
	};
	
	public CharacterSettings() {
		this.setBackground(Color.green);
	}
	
	private int scale = (int) Init.getScaleIndex();
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(char1[0], 15 * scale, 15 * scale, this);	// Change this to fit better in all sizes, no exact values -.-
	}
}
