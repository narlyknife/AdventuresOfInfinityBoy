package gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JPanel;

import main.Init;

public class PlatformCollision extends JPanel{

	// Declarations
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	private static int platformCollisionWidth;
	private static int platformCollisionHeight;
	
	public PlatformCollision() {
		setScaleIndex();
		
		this.setBackground(Color.black);
	}
	
	// Applying the scaleIndex to the ground objects X and Y dimensions.
	// Size changes can be made manually in Init.
	public void setScaleIndex() {
		platformCollisionWidth = (int) (10 * Init.getScaleIndexX());
		platformCollisionHeight = (int) (Init.getPlatformSize(1) * Init.getScaleIndexY());
	}
	
	public static int getplatformCollisionWidth() {
		return platformCollisionWidth;
	}
	
	public static int getplatformCollisionHeight() {
		return platformCollisionHeight;
	}
}