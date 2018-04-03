package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.Init;
import main.Main;

public class PlatformPath extends JPanel{

	// Declarations
	static int resX = Init.getResX();
	static int resY = Init.getResY();
	
	private static int platformPathWidth;
	private static int platformPathHeight;
	
	public PlatformPath() {
		setScaleIndex();
		
//		this.setBackground(Color.green);
	}
	
	// Applying the scaleIndex to the ground objects X and Y dimensions.
	// Size changes can be made manually in Init.
	public void setScaleIndex() {
		platformPathWidth = (int) (Init.getPlatformSize(0) * Init.getScaleIndexX());
		platformPathHeight = (int) (1 * Init.getScaleIndexY());
	}
	
	public static int getPlatformPathWidth() {
		return platformPathWidth;
	}
	
	public static int getPlatformPathHeight() {
		return platformPathHeight;
	}
}