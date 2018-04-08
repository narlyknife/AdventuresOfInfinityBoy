package jpanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import engine.Engine;
import main.Init;

public class Credits extends JPanel {
	
	// Getting values from "init" file
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	static Font font = Init.getFont2();
	static Font fontItalic = Init.getFont3();
	static Color textColor = Init.getTextColor();
	
	static float scaleX = Init.getScaleIndexX();
	static float scaleY = Init.getScaleIndexY();
	
	// Each title gets their own Array of labels. The first element is used for a title or description of what the people under it did.
	// Programmers
	JLabel labelProg = new JLabel();
	JLabel[] programmers = {			new JLabel("Herman Eriksson", SwingConstants.CENTER), 		new JLabel("Folke Johansson", SwingConstants.CENTER)};
	
	// Music producers
	JLabel labelProd = new JLabel();
	JLabel[] musicProducersArtist = {	new JLabel("Arzee", SwingConstants.CENTER), 				new JLabel("Doze", SwingConstants.CENTER), 				new JLabel("Clever Girl", SwingConstants.CENTER),			new JLabel("James Stikå", SwingConstants.CENTER)};
	JLabel[] musicProducersReal = {		new JLabel("\"Alex Gjersing\"", SwingConstants.CENTER), 		new JLabel("\"Niels Beese\"", SwingConstants.CENTER), 	new JLabel("\"Ryan Kennelley\"", SwingConstants.CENTER),	new JLabel("\"James Stikå\"", SwingConstants.CENTER)};
	
	// Graphical Artists
	JLabel labelGrap = new JLabel();
	JLabel[] graphicalArtist = {		new JLabel("Folke Johansson", SwingConstants.CENTER)};
	
	// Special Thanks
	JLabel labelSpec = new JLabel();
	JLabel[] specialThanksCompany = {	new JLabel("Eagle Nation Records", SwingConstants.CENTER), 	new JLabel("Fallacy Records", SwingConstants.CENTER)};
	JLabel[] specialThanksName = {		new JLabel("\"James Ford\"", SwingConstants.CENTER),		new JLabel("\"Tevin Jones\"", SwingConstants.CENTER)};
	
	JLabel title = new JLabel();
	JLabel button = new JLabel();
	
	private static ImageIcon[] imgTitle = Engine.getScaledImageicon("titleCredits", Init.TITLE_SIZE[0] * scaleX, Init.TITLE_SIZE[1] * scaleY);
	private static ImageIcon[] imgButton = Engine.getScaledImageicon("buttonBack", Init.BUTTON_SIZE_1[0] * scaleX, Init.BUTTON_SIZE_1[1] * scaleY);
	
	private static ImageIcon[] imgProg = Engine.getScaledImageicon("titleCreditsProg", Init.TITLE_SIZE_SMALL[0] * scaleX, Init.TITLE_SIZE_SMALL[1] * scaleY);
	private static ImageIcon[] imgProd = Engine.getScaledImageicon("titleCreditsProd", Init.TITLE_SIZE_SMALL[0] * scaleX, Init.TITLE_SIZE_SMALL[1] * scaleY);
	private static ImageIcon[] imgGrap = Engine.getScaledImageicon("titleCreditsGrap", Init.TITLE_SIZE_SMALL[0] * scaleX, Init.TITLE_SIZE_SMALL[1] * scaleY);
	private static ImageIcon[] imgSpec = Engine.getScaledImageicon("titleCreditsSpec", Init.TITLE_SIZE_SMALL[0] * scaleX, Init.TITLE_SIZE_SMALL[1] * scaleY);
	private Image img;
	
	public Credits() {
		img = new ImageIcon(MainMenu.class.getResource("/resources/settingsBackground.png")).getImage();
		
		this.setLayout(null);
		
		title.setSize((int) (Init.TITLE_SIZE[0] * scaleX), (int) (Init.TITLE_SIZE[1] * scaleY));
		title.setLocation((resX - title.getWidth()) / 2, 0);
		title.setIcon(imgTitle[0]);
		
		button.setSize((int) (Init.BUTTON_SIZE_1[0] * scaleX), (int) (Init.BUTTON_SIZE_1[1] * scaleY));
		button.setLocation((resX - button.getWidth()) / 2, (int) (990 * scaleY));
		button.setIcon(imgButton[0]);
		
		Engine.animateLabel(button, imgButton, "mainmenu");
		
		int xOffset = (int) (500 * scaleX);
		int ySpacing = (int) (200 * scaleY);
		int yOffset = (int) (100 * scaleY);
		
		int titleSizeX = (int) (Init.TITLE_SIZE_SMALL[0] * scaleX);
		int titleSizeY = (int) (Init.TITLE_SIZE_SMALL[1] * scaleY);
		
		int textSizeX = (int) (Init.CREDITS_TEXT_SIZE[0] * scaleX);
		int textSizeY = (int) (Init.CREDITS_TEXT_SIZE[1] * scaleY);
		
		// Programmers Section
		labelProg.setSize(titleSizeX, titleSizeY);
		labelProg.setLocation((resX - labelProg.getWidth()) / 2 - xOffset, 2 * ySpacing + yOffset);
		labelProg.setIcon(imgProg[0]);
		for(int i = 0; i < programmers.length; i++) {
			if(i == 0) programmers[i].setLocation(labelProg.getX(), labelProg.getY() + labelProg.getHeight());
			else programmers[i].setLocation(labelProg.getX(), programmers[i-1].getY() + programmers[i-1].getHeight());
			
			programmers[i].setSize(textSizeX, textSizeY);
			programmers[i].setFont(font);
			programmers[i].setForeground(textColor);
			
			this.add(programmers[i]);
		}
		
		// Music Producers Section
		labelProd.setSize(titleSizeX, titleSizeY);
		labelProd.setLocation((resX - labelProd.getWidth()) / 2, ySpacing + yOffset);
		labelProd.setIcon(imgProd[0]);
		for(int i = 0; i < musicProducersArtist.length; i++) {
			if(i == 0) musicProducersArtist[i].setLocation(labelProd.getX(), labelProd.getY() + labelProd.getHeight());
			else musicProducersArtist[i].setLocation(musicProducersReal[i-1].getX(), musicProducersReal[i-1].getY() + musicProducersReal[i-1].getHeight());
			
			musicProducersArtist[i].setSize(textSizeX, textSizeY);
			musicProducersArtist[i].setFont(font);
			musicProducersArtist[i].setForeground(textColor);
			
			musicProducersReal[i].setLocation(labelProd.getX(), musicProducersArtist[i].getY() + musicProducersArtist[i].getHeight());
			musicProducersReal[i].setSize(textSizeX, textSizeY);
			musicProducersReal[i].setFont(fontItalic);
			musicProducersReal[i].setForeground(textColor);
			
			this.add(musicProducersReal[i]);
			this.add(musicProducersArtist[i]);
		}
		
		// Graphic Artist Section
		labelGrap.setSize(titleSizeX, titleSizeY);
		labelGrap.setLocation((resX - labelGrap.getWidth()) / 2 + xOffset, 2 * ySpacing + yOffset);
		labelGrap.setIcon(imgGrap[0]);
		for(int i = 0; i < graphicalArtist.length; i++) {
			if(i == 0) graphicalArtist[i].setLocation(labelGrap.getX(), labelGrap.getY() + labelGrap.getHeight());
			else graphicalArtist[i].setLocation(labelGrap.getX(), graphicalArtist[i-1].getY() + graphicalArtist[i-1].getHeight());
			
			graphicalArtist[i].setSize(textSizeX, textSizeY);
			graphicalArtist[i].setFont(font);
			graphicalArtist[i].setForeground(textColor);
			
			this.add(graphicalArtist[i]);
		}
		
		// Special Thanks Section
		labelSpec.setSize(titleSizeX, titleSizeY);
		labelSpec.setLocation((resX - labelSpec.getWidth()) / 2, 3 * ySpacing + yOffset);
		labelSpec.setIcon(imgSpec[0]);
		for(int i = 0; i < specialThanksCompany.length; i++) {
			if(i == 0) specialThanksCompany[i].setLocation(labelSpec.getX(), labelSpec.getY() + labelSpec.getHeight());
			else specialThanksCompany[i].setLocation(specialThanksName[i-1].getX(), specialThanksName[i-1].getY() + specialThanksName[i-1].getHeight());
			
			specialThanksCompany[i].setSize(textSizeX, textSizeY);
			specialThanksCompany[i].setFont(font);
			specialThanksCompany[i].setForeground(textColor);
			
			specialThanksName[i].setLocation(labelSpec.getX(), specialThanksCompany[i].getY() + specialThanksCompany[i].getHeight());
			specialThanksName[i].setSize(textSizeX, textSizeY);
			specialThanksName[i].setFont(fontItalic);
			specialThanksName[i].setForeground(textColor);
			
			this.add(specialThanksName[i]);
			this.add(specialThanksCompany[i]);
		}
		
		this.add(title);
		this.add(labelProg);
		this.add(labelProd);
		this.add(labelGrap);
		this.add(labelSpec);
		this.add(button);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0 , 0, getWidth(), getHeight(), this);
	}
}