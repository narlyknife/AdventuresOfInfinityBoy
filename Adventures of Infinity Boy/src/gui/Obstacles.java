package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.Init;
import main.Main;

public class Obstacles extends JPanel{

	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	private static int obstacleHeight;
	private static int obstacleWidth;
	private Image chosenImage;
	
	public Obstacles() {
		System.out.println("New obstacle object created!");
		setScaleIndex();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("Printed obstacle");
		g.drawImage(chosenImage, 0, 0, this);
	}
	
	// Applying the scaleIndex to the ground objects X and Y dimensions.
	// Size changes can be made manually in Init.
	public void setScaleIndex() {
		obstacleWidth = (int) (Init.getObstacleSize(0) * Init.getScaleIndex());
		obstacleHeight = (int) (Init.getObstacleSize(1) * Init.getScaleIndex());
	}
	
	public int getObstacleHeight() {
		return obstacleHeight;
	}
	
	public int getObstacleWidth() {
		return obstacleWidth;
	}
	
	public void setObstacleImage(Image image) {
		chosenImage = image;
	}
	
}