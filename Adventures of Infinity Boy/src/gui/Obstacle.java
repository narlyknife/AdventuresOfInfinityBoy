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
	private static int obstacleLargeWidth;
	private static int obstacleLargeHeight;
	
	private Image chosenImage;
	
	public Obstacle() {
		obstacleWidth = Init.getObstacleSize(0);
		obstacleHeight = Init.getObstacleSize(1);
		obstacleLargeWidth = Init.getObstacleLargeSize(0);
		obstacleLargeHeight = Init.getObstacleLargeSize(1);
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Draw image background
		g.drawImage(chosenImage, 0, 0, this);
	}
	
	public static int getObstacleWidth() {
		return obstacleWidth;
	}
	
	public static int getObstacleHeight() {
		return obstacleHeight;
	}
	
	public static int getObstacleLargeWidth() {
		return obstacleLargeWidth;
	}
	
	public static int getObstacleLargeHeight() {
		return obstacleLargeHeight;
	}
	
	public void setObstacleImage(Image image) {
		chosenImage = image;
	}
}
