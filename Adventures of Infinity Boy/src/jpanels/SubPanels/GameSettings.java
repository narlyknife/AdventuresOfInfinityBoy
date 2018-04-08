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
import jpanels.Settings;

public class GameSettings extends JPanel{
	
	static Image checkTrue = new ImageIcon(GameSettings.class.getResource("/resources/checktrue.png")).getImage();
	static Image checkFalse = new ImageIcon(GameSettings.class.getResource("/resources/checkfalse.png")).getImage();
	static Image textMusic = new ImageIcon(GameSettings.class.getResource("/resources/textMusic.png")).getImage();
	static Image textSplashscreen = new ImageIcon(GameSettings.class.getResource("/resources/textSplashscreen.png")).getImage();
	
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
	
	static float scaleX = Init.getScaleIndexX();
	static float scaleY = Init.getScaleIndexY();
	
	boolean musicState = true;
	boolean splashscreenState = true;
	
	public GameSettings() {
		this.setBackground(new Color(0 ,0 ,0));
		
		this.setLayout(null);
		
		row1Y = (int) (125 * scaleY);
		row1X = (int) (200 * scaleX);
		row2Y = (int) (325 * scaleY);
		row2X = (int) (1000 * scaleX);
		
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
		
		toggleMusic.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(musicState) {
					toggleMusic.setIcon(checkFalseScaled);
					Settings.dataMusic = 0;
				}
				else {
					toggleMusic.setIcon(checkTrueScaled);
					Settings.dataMusic = 1;
				}
				
				musicState = !musicState;
			}
		});
		
		toggleSplashscreen.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(splashscreenState) {
					toggleSplashscreen.setIcon(checkFalseScaled);
					Settings.dataSplash = 0;
				}
				else {
					toggleSplashscreen.setIcon(checkTrueScaled);
					Settings.dataSplash = 1;
				}
				
				splashscreenState = !splashscreenState;
			}
		});
		
		this.add(toggleMusic);
		this.add(toggleSplashscreen);
		this.add(labelTextMusic);
		this.add(labelTextSplashscreen);
	}
}
