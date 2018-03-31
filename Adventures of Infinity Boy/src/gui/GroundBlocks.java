package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.Init;
import main.Main;

public class GroundBlocks extends JPanel{
	
	// Declarations
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	private static int groundWidth;
	private static int groundHeight;
	private Image chosenImage;
	
	public GroundBlocks() {
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
		groundWidth = (int) (Init.getGroundSize(0) * Init.getScaleIndexX());
		groundHeight = (int) (Init.getGroundSize(1) * Init.getScaleIndexY());
	}
	
	public int getGroundWidth() {
		return groundWidth;
	}
	
	public static int getGroundHeight() {
		return groundHeight;
	}
	
	public void setGroundImage(Image image) {
		chosenImage = image;
	}
}
