package jpanels.SubPanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jpanels.Settings;
import main.Init;

public class DifficultySettings extends JPanel{

	private static Image[] dif1 = {
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif1def.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif1hov.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif1pre.png")).getImage()
	};
	
	private static Image[] dif2 = {
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif2def.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif2hov.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif2pre.png")).getImage()
	};
	
	private static Image[] dif3 = {
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif3def.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif3hov.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif3pre.png")).getImage()
	};
	
	public DifficultySettings() {
		this.setBackground(Color.red);
	}
	
	private float scaleX = Init.getScaleIndexX();
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int spacingX = (int) ((Settings.getSettingsPanelW() - (3 * (Init.SETTINGS_DIF_SIZE[0] * scaleX))) / 4);
		int spacingY = (int) (((Settings.getSettingsPanelH() - (Init.SETTINGS_DIF_SIZE[1] * ((float)Settings.getSettingsPanelH()/530))) / 2));	// No touching
		int difWidth = (int) (Init.SETTINGS_DIF_SIZE[0] * scaleX);
		int difHeight = (int) ((Settings.getSettingsPanelH() - (2 * spacingY)));
		
		g.drawImage(dif1[0], spacingX, spacingY, difWidth, difHeight, this);
		g.drawImage(dif2[0], spacingX * 2 + (int) (Init.SETTINGS_DIF_SIZE[0] * scaleX), spacingY, difWidth, difHeight, this);
		g.drawImage(dif3[0], spacingX * 3 + (int) (Init.SETTINGS_DIF_SIZE[0] * scaleX) * 2, spacingY, difWidth, difHeight, this);
	}

}
