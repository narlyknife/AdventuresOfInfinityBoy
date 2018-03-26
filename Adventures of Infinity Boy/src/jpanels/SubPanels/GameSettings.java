package jpanels.SubPanels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Engine;
import main.Init;

public class GameSettings extends JPanel{
	
	static Image checkTrue = new ImageIcon(GameSettings.class.getResource("/Pictures/checktrue.png")).getImage();
	static Image checkFalse = new ImageIcon(GameSettings.class.getResource("/Pictures/checkfalse.png")).getImage();
	static Image textMusic = new ImageIcon(GameSettings.class.getResource("/Pictures/textPlaceholder.png")).getImage();
	static Image textSplashscreen = new ImageIcon(GameSettings.class.getResource("/Pictures/textPlaceholder.png")).getImage();
	
	static ImageIcon checkTrueScaled;
	static ImageIcon checkFalseScaled;
	static ImageIcon textMusicScaled;
	static ImageIcon textSplashscreenScaled;
		
	static JLabel toggleMusic = new JLabel();
	static JLabel toggleSplashscreen = new JLabel();
	static JLabel labelTextMusic = new JLabel();
	static JLabel labelTextSplashscreen = new JLabel();
	
	static int row1X = 0;
	static int row1Y = 0;
	static int row2X = 0;
	static int row2Y = 0;
	
	boolean musicState = true;
	boolean splashscreenState = true;
	
	public GameSettings() {
		this.setBackground(new Color(0 ,0 ,0));
		
		this.setLayout(null);
		
		toggleMusic.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(musicState) {
					toggleMusic.setIcon(checkFalseScaled);
					Init.settingsData[3] = 0;
				}
				else {
					toggleMusic.setIcon(checkTrueScaled);
					Init.settingsData[3] = 1;
				}
				
				musicState = !musicState;
			}
		});
		
		toggleSplashscreen.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(splashscreenState) {
					toggleSplashscreen.setIcon(checkFalseScaled);
					Init.settingsData[2] = 0;
				}
				else {
					toggleSplashscreen.setIcon(checkTrueScaled);
					Init.settingsData[2] = 1;
				}
				
				splashscreenState = !splashscreenState;
			}
		});
		
		this.add(toggleMusic);
		this.add(toggleSplashscreen);
		this.add(labelTextMusic);
		this.add(labelTextSplashscreen);
	}
	
	public static void setLocations(float panelH, float panelW) {
		row1Y = (int) ((panelH - (Init.SETTINGS_CHECKBOX_SIZE[1] * 2)) / 3);
		row1X = (int) (panelW / 6);
		row2Y = (int) ((row1Y * 2) + Init.SETTINGS_TEXT_MUSIC_SIZE[1]);
		row2X = (int) (row1X * 2 + Init.SETTINGS_TEXT_MUSIC_SIZE[0]);
		
		float scaleX = panelW/1327;
		float scaleY = panelH/530;
		
		checkTrueScaled = new ImageIcon(Engine.getScaledImage(checkTrue, (int) (Init.SETTINGS_CHECKBOX_SIZE[0] * scaleX), (int) (Init.SETTINGS_CHECKBOX_SIZE[1] * scaleY)));
		checkFalseScaled = new ImageIcon(Engine.getScaledImage(checkFalse, (int) (Init.SETTINGS_CHECKBOX_SIZE[0] * scaleX), (int) (Init.SETTINGS_CHECKBOX_SIZE[1] * scaleY)));
		textMusicScaled = new ImageIcon(Engine.getScaledImage(textMusic, (int) (Init.SETTINGS_TEXT_MUSIC_SIZE[0] * scaleX), (int) (Init.SETTINGS_TEXT_MUSIC_SIZE[1] * scaleY)));
		textSplashscreenScaled = new ImageIcon(Engine.getScaledImage(textSplashscreen, (int) (Init.SETTINGS_TEXT_SPLASHSCREEN_SIZE[0] * scaleX), (int) (Init.SETTINGS_TEXT_SPLASHSCREEN_SIZE[1] * scaleY)));
		
		if(Init.settingsData[3] == 1) toggleMusic.setIcon(checkTrueScaled);
		else toggleMusic.setIcon(checkFalseScaled);
		
		if(Init.settingsData[2] == 1) toggleSplashscreen.setIcon(checkTrueScaled);
		else toggleSplashscreen.setIcon(checkFalseScaled);
		
		labelTextMusic.setIcon(textMusicScaled);
		labelTextSplashscreen.setIcon(textSplashscreenScaled);
		
		toggleMusic.setSize((int) (Init.SETTINGS_CHECKBOX_SIZE[0] * scaleX),(int) (Init.SETTINGS_CHECKBOX_SIZE[1] * scaleY));
		toggleSplashscreen.setSize((int) (Init.SETTINGS_CHECKBOX_SIZE[0] * scaleX),(int) (Init.SETTINGS_CHECKBOX_SIZE[1] * scaleY));
		labelTextMusic.setSize((int) (Init.SETTINGS_TEXT_MUSIC_SIZE[0] * scaleX),(int) (Init.SETTINGS_TEXT_MUSIC_SIZE[1] * scaleY));
		labelTextSplashscreen.setSize((int) (Init.SETTINGS_TEXT_SPLASHSCREEN_SIZE[0] * scaleX),(int) (Init.SETTINGS_TEXT_SPLASHSCREEN_SIZE[1] * scaleY));
		
		labelTextMusic.setLocation(row1X, row1Y);
		toggleMusic.setLocation(row2X, row1Y);
		labelTextSplashscreen.setLocation(row1X, row2Y);
		toggleSplashscreen.setLocation(row2X, row2Y);
	}
}
