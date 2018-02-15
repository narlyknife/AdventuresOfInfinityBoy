package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Init;
import main.Main;

public class GroundBlocks extends JPanel{
	
	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	private int scaleIndex = 1;
	private int groundWidth, groundHeight;
	private Image img = new ImageIcon(this.getClass().getResource("/Pictures/Ground1.png")).getImage();
	
	public GroundBlocks() {
		System.out.println("GroundBlock created");
		getScaleIndex();
		setScaleIndex();
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
	
	// Getting a scaled index to reference how large if any the scaling of the ground object will be.
	// A resolution of 2x will result in a scaleIndex of 2.
	public void getScaleIndex() {
		scaleIndex = (int) resX / Init.getGroundSize(0);
	}
	
	// Applying the claedIndex to the ground objects X and Y dimensions.
	public void setScaleIndex() {
		groundWidth = (int) (Init.getGroundSize(0) * scaleIndex);
		groundHeight = (int) (Init.getGroundSize(1) * scaleIndex);
	}
	
	public int getGroundWidth() {
		return groundWidth;
	}
	
	public int getGroundHeight() {
		return groundHeight;
	}
}
