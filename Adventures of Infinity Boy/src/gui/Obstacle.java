package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.Init;
import main.Main;

public class Obstacle extends JPanel{
	
	// Declarations
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	private static int obstacleWidth;
	private static int obstacleHeight;
	
	private Image chosenImage;
	
	public Obstacle() {
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
		obstacleWidth = (int) (Init.getObstacleSize(0) * Init.getScaleIndexX());
		obstacleHeight = (int) (Init.getObstacleSize(1) * Init.getScaleIndexY());
	}
	
	public static int getObstacleWidth() {
		return obstacleWidth;
	}
	
	public static int getObstacleHeight() {
		return obstacleHeight;
	}
	
	public void setObstacleImage(Image image) {
		chosenImage = image;
	}
}
