package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.Init;
import main.Main;

public class Platform extends JPanel{

	// Declarations
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	private static int platformWidth;
	private static int platformHeight;

	
	private Image chosenImage;
	
	public Platform() {
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
		platformWidth = (int) (Init.getPlatformSize(0) * Init.getScaleIndexX());
		platformHeight = (int) (Init.getPlatformSize(1) * Init.getScaleIndexY());
	}

	public static int getPlatformWidth() {
		return platformWidth;
	}
	
	public static int getPlatformHeight() {
		return platformHeight;
	}
	

	
	public void setObstacleImage(Image image) {
		chosenImage = image;
	}
	
}