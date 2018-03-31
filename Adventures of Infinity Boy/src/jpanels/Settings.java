package jpanels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Engine;
import jpanels.SubPanels.CharacterSettings;
import jpanels.SubPanels.DifficultySettings;
import jpanels.SubPanels.GameSettings;
import main.Init;
import main.Main;

public class Settings extends JPanel{
	
	// Declarations
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	static float scaleX = Init.getScaleIndexX();
	static float scaleY = Init.getScaleIndexY();
	
	public static int activePanel = 1;
	
	private Image img;
	
	public static ImageIcon[] imgGame = Engine.getScaledImageicon("buttonGameset", Init.BUTTON_SET_SIZE[0] * scaleX, Init.BUTTON_SET_SIZE[1] * scaleY);
	public static ImageIcon[] imgDifficulty = Engine.getScaledImageicon("buttonDiffset", Init.BUTTON_SET_SIZE[0] * scaleX, Init.BUTTON_SET_SIZE[1] * scaleY);
	public static ImageIcon[] imgCharacter = Engine.getScaledImageicon("buttonCharset", Init.BUTTON_SET_SIZE[0] * scaleX, Init.BUTTON_SET_SIZE[1] * scaleY);
	private static ImageIcon[] imgCancel = Engine.getScaledImageicon("buttonCancelset", Init.BUTTON_SET_SIZE[0] * scaleX, Init.BUTTON_SET_SIZE[1] * scaleY);
	private static ImageIcon[] imgSave = Engine.getScaledImageicon("buttonSaveset", Init.BUTTON_SET_SIZE[0] * scaleX, Init.BUTTON_SET_SIZE[1] * scaleY);
	
	public static JLabel[] buttons = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()};
	
	// Temp Data
	public static int dataDiff = Init.settingsData[0];
	public static int dataChar = Init.settingsData[1];
	public static int dataSplash = Init.settingsData[2];
	public static int dataMusic = Init.settingsData[3];
	
	public Settings() {
		this.setLayout(null);
		img = new ImageIcon(Settings.class.getResource("/Pictures/settingsBackground.png")).getImage();
		
		Main.subPanelMap.put("gamesettings", new GameSettings());
		Main.subPanelMap.put("charactersettings", new CharacterSettings());	
		Main.subPanelMap.put("difficultysettings", new DifficultySettings());
		
		JPanel gameSettings = Main.getSubPanel("gamesettings");
		JPanel characterSettings = Main.getSubPanel("charactersettings");
		JPanel difficultySettings = Main.getSubPanel("difficultysettings");
		
		buttons[0].setIcon(imgGame[0]);
		buttons[1].setIcon(imgDifficulty[0]);
		buttons[2].setIcon(imgCharacter[0]);
		buttons[3].setIcon(imgCancel[0]);
		buttons[4].setIcon(imgSave[0]);
		
		Engine.animateLabel(buttons[0], imgGame, "gamesettings");
		Engine.animateLabel(buttons[1], imgDifficulty, "difficultysettings");
		Engine.animateLabel(buttons[2], imgCharacter, "charactersettings");
		Engine.animateLabel(buttons[3], imgCancel, "mainmenu");
		Engine.animateLabel(buttons[4], imgSave, "save");
		
		Dimension dim1 = new Dimension((int) (Init.BUTTON_SET_SIZE[0] * scaleX), (int) (Init.BUTTON_SET_SIZE[1] * scaleY));
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setSize(dim1);
		}
		
		int xPosTop = (int) ((resX - (3 * buttons[0].getWidth())) / 4);
		int yPosTop = (int) (80 * scaleY);
		
		int xPosBottom = (int) ((resX - (2 * buttons[3].getWidth())) / 3);
		int yPosBottom = (int) (900 * scaleY);
		
		Point[] rowTop = {new Point(xPosTop, yPosTop), new Point(xPosTop * 2 + buttons[0].getWidth(), yPosTop), new Point(xPosTop * 3 + (buttons[0].getWidth() * 2), yPosTop)};
		Point[] rowBottom = {new Point(xPosBottom, yPosBottom), new Point(xPosBottom * 2 + buttons[3].getWidth(), yPosBottom)};
		
		for(int i = 0; i < rowTop.length; i++) {
			buttons[i].setLocation(rowTop[i]);
			System.out.println(i + " Set Location: " + rowTop[i].getLocation());
		}
		
		for(int i = rowTop.length; i < buttons.length; i++) {
			buttons[i].setLocation(rowBottom[i - rowTop.length]);
			System.out.println(i + " Set Location: " + rowBottom[i - rowTop.length].getLocation());
		}
		
		Dimension dim2 = new Dimension((int) (1400 * scaleX), (int) (600 * scaleY));
		gameSettings.setSize(dim2);
		characterSettings.setSize(dim2);
		difficultySettings.setSize(dim2);
		
		Point point = new Point((int) ((resX - dim2.getWidth()) / 2), (int) ((resY - dim2.getHeight()) / 2));
		gameSettings.setLocation(point);
		characterSettings.setLocation(point);
		difficultySettings.setLocation(point);
		
		this.add(gameSettings);
		this.add(characterSettings);
		this.add(difficultySettings);
		
		for(int i = 0; i < buttons.length; i++) this.add(buttons[i], 0);
	}
	
	public static void changeSettingPanel(String name) {
		Main.getSubPanel(name).setVisible(true);
		
		if(!(name.equals("gamesettings"))) Main.getSubPanel("gamesettings").setVisible(false);
		if(!(name.equals("difficultysettings"))) Main.getSubPanel("difficultysettings").setVisible(false);
		if(!(name.equals("charactersettings"))) Main.getSubPanel("charactersettings").setVisible(false);
	}
	
	public static void saveData() {
		Init.settingsData[0] = dataDiff;
		Init.settingsData[1] = dataChar;
		Init.settingsData[2] = dataSplash;
		Init.settingsData[3] = dataMusic;
		
		Engine.writeTxtFile(Init.SETTINGS_PATH, Init.settingsData);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0 , 0, getWidth(), getHeight(), this);
	}
}
