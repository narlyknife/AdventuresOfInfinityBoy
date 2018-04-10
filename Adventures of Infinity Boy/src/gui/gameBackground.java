package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.Init;
import main.Main;

public class gameBackground extends JPanel{

	// Declarations
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	private static int gameBackgroundWidth;
	private static int gameBackgroundHeight;

	
	private Image chosenImage;
	
	public gameBackground() {
		setScaleIndex();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Draw image background
		g.drawImage(chosenImage, 0, 0, this);
	}
	
	// Applying the scaleIndex to the ground objects X and Y dimensions.
	// Size changes can be made manually in Init.
	public void setScaleIndex() {
		gameBackgroundWidth = (int) (Init.getGameBackgroundSize(0) * Init.getScaleIndexX());
		gameBackgroundHeight = (int) (Init.getGameBackgroundSize(1) * Init.getScaleIndexY());
	}

	public static int getGameBackgroundWidth() {
		return gameBackgroundWidth;
	}
	
	public static int getGameBackgroundHeight() {
		return gameBackgroundHeight;
	}
	
	public void setGameBackgroundImage(Image image) {
		chosenImage = image;
	}
	
}