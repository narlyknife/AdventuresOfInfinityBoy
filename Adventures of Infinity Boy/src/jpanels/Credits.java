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
	// CHANGES TO BE MADE
	//
	//	- N/A
	//
	
	// Getting values from "init" file
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	static Font font = Init.getFont2();
	static Font fontItalic = Init.getFont3();
	
	static float scaleX = Init.getScaleIndexX();
	static float scaleY = Init.getScaleIndexY();
	
	// Each title gets their own Array of labels. The first element is used for a title or description of what the people under it did.
	// Programmers
	JLabel labelProg = new JLabel();
	JLabel[] programmers = {			new JLabel("Herman Eriksson", SwingConstants.CENTER), 		new JLabel("Folke Johansson", SwingConstants.CENTER)};
	
	// Music producers
	JLabel labelProd = new JLabel();
	JLabel[] musicProducersArtist = {	new JLabel("Arzee", SwingConstants.CENTER), 				new JLabel("Doze", SwingConstants.CENTER), 				new JLabel("Clever Girl", SwingConstants.CENTER)};
	JLabel[] musicProducersReal = {		new JLabel("\"Alex Gjersin\"", SwingConstants.CENTER), 		new JLabel("\"Niels Beese\"", SwingConstants.CENTER), 	new JLabel("\"Ryan Kennelley\"", SwingConstants.CENTER)};
	
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
	
	private static ImageIcon[] imgProg = Engine.getScaledImageicon("titleCreditsProg", Init.TITLE_SIZE_SMALL[0] * scaleX, Init.TITLE_SIZE_SMALL[1] * scaleY);
	private static ImageIcon[] imgProd = Engine.getScaledImageicon("titleCreditsProd", Init.TITLE_SIZE_SMALL[0] * scaleX, Init.TITLE_SIZE_SMALL[1] * scaleY);
	private static ImageIcon[] imgGrap = Engine.getScaledImageicon("titleCreditsGrap", Init.TITLE_SIZE_SMALL[0] * scaleX, Init.TITLE_SIZE_SMALL[1] * scaleY);
	private static ImageIcon[] imgSpec = Engine.getScaledImageicon("titleCreditsSpec", Init.TITLE_SIZE_SMALL[0] * scaleX, Init.TITLE_SIZE_SMALL[1] * scaleY);
	private Image img;
	
	public Credits() {
		img = new ImageIcon(MainMenu.class.getResource("/Pictures/settingsBackground.png")).getImage();
		
		this.setLayout(null);
		
		title.setSize((int) (Init.TITLE_SIZE[0] * scaleX), (int) (Init.TITLE_SIZE[1] * scaleY));
		title.setLocation((resX - title.getWidth()) / 2, 0);
		title.setIcon(new ImageIcon(Engine.getScaledImage(Engine.getImage("titleCredits.png"), Init.TITLE_SIZE[0], Init.TITLE_SIZE[1])));
		
		int xOffset = (int) (500 * scaleX);
		int ySpacing = (int) (200 * scaleY);
		int yOffset = (int) (100 * scaleY);
		int yOffsetText = (int) (12 * scaleY);
		
		labelProg.setSize((int) (Init.TITLE_SIZE_SMALL[0] * scaleX), (int) (Init.TITLE_SIZE_SMALL[1] * scaleY));
		labelProg.setLocation((resX - labelProg.getWidth()) / 2 - xOffset, 2 * ySpacing + yOffset);
		labelProg.setIcon(new ImageIcon(Engine.getScaledImage(Engine.getImage("titleCreditsProg.png"), Init.TITLE_SIZE_SMALL[0], Init.TITLE_SIZE_SMALL[1])));
		for(int i = 0; i < programmers.length; i++) {
			if(i == 0) programmers[i].setLocation(labelProg.getX(), labelProg.getY() + labelProg.getHeight() + yOffsetText);
			else programmers[i].setLocation(labelProg.getX(), programmers[i-1].getY() + programmers[i-1].getHeight() + yOffsetText);
			programmers[i].setSize(Init.CREDITS_TEXT_SIZE[0], Init.CREDITS_TEXT_SIZE[1]);
			programmers[i].setFont(font);
			programmers[i].setForeground(new Color(255, 255, 255));
			
			this.add(programmers[i]);
		}
		
		labelProd.setSize((int) (Init.TITLE_SIZE_SMALL[0] * scaleX), (int) (Init.TITLE_SIZE_SMALL[1] * scaleY));
		labelProd.setLocation((resX - labelProd.getWidth()) / 2, ySpacing + yOffset);
		labelProd.setIcon(new ImageIcon(Engine.getScaledImage(Engine.getImage("titleCreditsProd.png"), Init.TITLE_SIZE_SMALL[0], Init.TITLE_SIZE_SMALL[1])));
		for(int i = 0; i < musicProducersArtist.length; i++) {
			if(i == 0) musicProducersArtist[i].setLocation(labelProd.getX(), labelProd.getY() + labelProd.getHeight() + i * yOffsetText);
			else musicProducersArtist[i].setLocation(musicProducersReal[i-1].getX(), musicProducersReal[i-1].getY() + musicProducersReal[i-1].getHeight() + yOffsetText);
			musicProducersArtist[i].setSize(Init.CREDITS_TEXT_SIZE[0], Init.CREDITS_TEXT_SIZE[1]);
			musicProducersArtist[i].setFont(font);
			musicProducersArtist[i].setForeground(new Color(255, 255, 255));
			
			musicProducersReal[i].setLocation(labelProd.getX(), musicProducersArtist[i].getY() + musicProducersArtist[i].getHeight() + yOffsetText);
			musicProducersReal[i].setSize(Init.CREDITS_TEXT_SIZE[0], Init.CREDITS_TEXT_SIZE[1]);
			musicProducersReal[i].setFont(fontItalic);
			musicProducersReal[i].setForeground(new Color(255, 255, 255));
			
			this.add(musicProducersReal[i]);
			this.add(musicProducersArtist[i]);
		}
		
		labelGrap.setSize((int) (Init.TITLE_SIZE_SMALL[0] * scaleX), (int) (Init.TITLE_SIZE_SMALL[1] * scaleY));
		labelGrap.setLocation((resX - labelGrap.getWidth()) / 2 + xOffset, 2 * ySpacing + yOffset);
		labelGrap.setIcon(new ImageIcon(Engine.getScaledImage(Engine.getImage("titleCreditsGrap.png"), Init.TITLE_SIZE_SMALL[0], Init.TITLE_SIZE_SMALL[1])));
		for(int i = 0; i < graphicalArtist.length; i++) {
			if(i == 0) graphicalArtist[i].setLocation(labelGrap.getX(), labelGrap.getY() + labelGrap.getHeight() + yOffsetText);
			else graphicalArtist[i].setLocation(labelGrap.getX(), graphicalArtist[i-1].getY() + graphicalArtist[i-1].getHeight() + yOffsetText);
			graphicalArtist[i].setSize(Init.CREDITS_TEXT_SIZE[0], Init.CREDITS_TEXT_SIZE[1]);
			graphicalArtist[i].setFont(font);
			graphicalArtist[i].setForeground(new Color(255, 255, 255));
			
			this.add(graphicalArtist[i]);
		}
		
		labelSpec.setSize((int) (Init.TITLE_SIZE_SMALL[0] * scaleX), (int) (Init.TITLE_SIZE_SMALL[1] * scaleY));
		labelSpec.setLocation((resX - labelSpec.getWidth()) / 2, 3 * ySpacing + yOffset);
		labelSpec.setIcon(new ImageIcon(Engine.getScaledImage(Engine.getImage("titleCreditsSpec.png"), Init.TITLE_SIZE_SMALL[0], Init.TITLE_SIZE_SMALL[1])));
		for(int i = 0; i < specialThanksCompany.length; i++) {
			if(i == 0) specialThanksCompany[i].setLocation(labelSpec.getX(), labelSpec.getY() + labelSpec.getHeight() + i * yOffsetText);
			else specialThanksCompany[i].setLocation(specialThanksName[i-1].getX(), specialThanksName[i-1].getY() + specialThanksName[i-1].getHeight() + yOffsetText);
			specialThanksCompany[i].setSize(Init.CREDITS_TEXT_SIZE[0], Init.CREDITS_TEXT_SIZE[1]);
			specialThanksCompany[i].setFont(font);
			specialThanksCompany[i].setForeground(new Color(255, 255, 255));
			
			specialThanksName[i].setLocation(labelSpec.getX(), specialThanksCompany[i].getY() + specialThanksCompany[i].getHeight() + yOffsetText);
			specialThanksName[i].setSize(Init.CREDITS_TEXT_SIZE[0], Init.CREDITS_TEXT_SIZE[1]);
			specialThanksName[i].setFont(fontItalic);
			specialThanksName[i].setForeground(new Color(255, 255, 255));
			
			this.add(specialThanksName[i]);
			this.add(specialThanksCompany[i]);
		}
		
		this.add(labelProg);
		this.add(labelProd);
		this.add(labelGrap);
		this.add(labelSpec);
		this.add(title);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0 , 0, getWidth(), getHeight(), this);
	}
}