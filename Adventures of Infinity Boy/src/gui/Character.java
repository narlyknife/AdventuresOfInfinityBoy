package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.Init;
import main.Main;

public class Character extends JPanel {
	
	// Declarations
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	private static int characterHeight;
	private static int characterWidth;
	private Image chosenImage;
	
	public Character() {
		setScaleIndex();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(chosenImage, 0, 0, this);
	}
	
	// Applying the scaleIndex to the ground objects X and Y dimensions.
	// Size changes can be made manually in Init.
	public void setScaleIndex() {
		characterWidth = (int) (Init.getCharacterSize(0) * Init.getScaleIndexX());
		characterHeight = (int) (Init.getCharacterSize(1) * Init.getScaleIndexY());
	}
	
	public int getCharacterHeight() {
		return characterHeight;
	}
	
	public int getCharacterWidth() {
		return characterWidth;
	}
	
	public void setCharacterImage(Image image) {
		chosenImage = image;
	}
	
	
}