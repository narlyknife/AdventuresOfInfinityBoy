package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.Init;
import main.Main;

public class GroundBlocks extends JPanel{
	
	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	private static int groundWidth;
	private static int groundHeight;
	private Image chosenImage;
	
	public GroundBlocks() {
		System.out.println("GroundBlock created");
		setScaleIndex();
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("Painted ground");
		//Draw image background
		g.drawImage(chosenImage, 0, 0, this);
	}
	
	// Applying the scaleIndex to the ground objects X and Y dimensions.
	// Size changes can be made manually in Init.
	public void setScaleIndex() {
		groundWidth = (int) (Init.getGroundSize(0) * Init.getScaleIndex());
		groundHeight = (int) (Init.getGroundSize(1) * Init.getScaleIndex());
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
