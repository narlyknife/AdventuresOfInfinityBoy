package jpanels.SubPanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.print.attribute.standard.PrinterLocation;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jpanels.Settings;
import main.Init;
import main.Main;

public class CharacterSettings extends JPanel{
	
	private static Image[] char1 = {
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character1def.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character1hov.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character1pre.png")).getImage()
	};
	
	private static Image[] char2 = {
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character2def.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character2hov.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character2pre.png")).getImage()
	};
	
	private static Image[] char3 = {
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character3def.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character3hov.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character3pre.png")).getImage()
	};
	
	public CharacterSettings() {
		this.setBackground(Color.green);
	}
	
	private float scaleX = Init.getScaleIndexX();
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int spacingX = (int) ((Settings.getSettingsPanelW() - (3 * (Init.SETTINGS_CHAR_SIZE[0] * scaleX))) / 4);
		int spacingY = (int) (((Settings.getSettingsPanelH() - (Init.SETTINGS_CHAR_SIZE[1] * ((float)Settings.getSettingsPanelH()/530))) / 2));	// No touching
		int charWidth = (int) (Init.SETTINGS_CHAR_SIZE[0] * scaleX);
		int charHeight = (int) ((Settings.getSettingsPanelH() - (2 * spacingY)));
		
		g.drawImage(char1[0], spacingX, spacingY, charWidth, charHeight, this);
		g.drawImage(char2[0], spacingX * 2 + (int) (Init.SETTINGS_CHAR_SIZE[0] * scaleX), spacingY, charWidth, charHeight, this);
		g.drawImage(char3[0], spacingX * 3 + (int) (Init.SETTINGS_CHAR_SIZE[0] * scaleX) * 2, spacingY, charWidth, charHeight, this);
	}
}
