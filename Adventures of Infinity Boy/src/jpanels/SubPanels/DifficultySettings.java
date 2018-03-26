package jpanels.SubPanels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Engine;
import main.Init;

public class DifficultySettings extends JPanel{

	private static Image[] dif1 = {
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif1def.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif1hov.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif1pre.png")).getImage()
	};
	
	private static Image[] dif2 = {
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif2def.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif2hov.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif2pre.png")).getImage()
	};
	
	private static Image[] dif3 = {
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif3def.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif3hov.png")).getImage(),
			new ImageIcon(DifficultySettings.class.getResource("/Pictures/dif3pre.png")).getImage()
	};
	
	private static ImageIcon[] dif1Scaled = {new ImageIcon(), new ImageIcon(), new ImageIcon()};
	private static ImageIcon[] dif2Scaled = {new ImageIcon(), new ImageIcon(), new ImageIcon()};
	private static ImageIcon[] dif3Scaled = {new ImageIcon(), new ImageIcon(), new ImageIcon()};
	
	private static JLabel label1 = new JLabel(), label2 = new JLabel(), label3 = new JLabel();
	
	// 0 - Default, 1 - Hover, 2 - Pressed
	private int label1State = 0, label2State = 0, label3State = 0;
	static int pressedLabel = Init.settingsData[0];
	
	public DifficultySettings() {
		this.setBackground(new Color(0 ,0 ,0));
		
		this.setLayout(null);
		
		// MouseListerner for label 1
		label1.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				if(label1State == 0 && pressedLabel != 1) {
					label1.setIcon(dif1Scaled[1]);
					label1State = 1;
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(label1State == 1 && pressedLabel != 1) {
					label1.setIcon(dif1Scaled[0]);
					label1State = 0;
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				label1.setIcon(dif1Scaled[2]);
				pressedLabel = 1;
				
				label2.setIcon(dif2Scaled[0]);
				label3.setIcon(dif3Scaled[0]);
			}
		});

		// MouseListerner for label 2
		label2.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				if(label2State == 0 && pressedLabel != 2) {
					label2.setIcon(dif2Scaled[1]);
					label2State = 1;
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(label2State == 1 && pressedLabel != 2) {
					label2.setIcon(dif2Scaled[0]);
					label2State = 0;
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				label2.setIcon(dif2Scaled[2]);
				pressedLabel = 2;
				
				label3.setIcon(dif3Scaled[0]);
				label1.setIcon(dif1Scaled[0]);
			}
		});
		
		// MouseListerner for label 3
		label3.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				if(label3State == 0 && pressedLabel != 3) {
					label3.setIcon(dif3Scaled[1]);
					label3State = 1;
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(label3State == 1 && pressedLabel != 3) {
					label3.setIcon(dif3Scaled[0]);
					label3State = 0;
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				label3.setIcon(dif3Scaled[2]);
				pressedLabel = 3;
				
				label1.setIcon(dif1Scaled[0]);
				label2.setIcon(dif2Scaled[0]);
			}
		});
			
		this.add(label1);
		this.add(label2);
		this.add(label3);
	}

	public static void setLocations(float panelH, float panelW) {
		int spacingX = (int) ((panelW - (3 * (Init.SETTINGS_DIF_SIZE[0] * (panelW/1327)))) / 4);
		int spacingY = (int) (((panelH - (Init.SETTINGS_DIF_SIZE[1] * (panelH/530))) / 2));
		
		int difWidth = (int) (Init.SETTINGS_DIF_SIZE[0] * (panelW/1327));
		int difHeight = (int) (Init.SETTINGS_DIF_SIZE[1] * (panelH/530));
		
		for(int i = 0; i < dif1.length; i++) {
			dif1Scaled[i] = new ImageIcon(Engine.getScaledImage(dif1[i], difWidth, difHeight));
			dif2Scaled[i] = new ImageIcon(Engine.getScaledImage(dif2[i], difWidth, difHeight));
			dif3Scaled[i] = new ImageIcon(Engine.getScaledImage(dif3[i], difWidth, difHeight));
		}
		
		if(pressedLabel != 1) label1.setIcon(dif1Scaled[0]);
		else label1.setIcon(dif1Scaled[2]);
		
		if(pressedLabel != 2) label2.setIcon(dif2Scaled[0]);
		else label2.setIcon(dif2Scaled[2]);
		
		if(pressedLabel != 3) label3.setIcon(dif3Scaled[0]);
		else label3.setIcon(dif3Scaled[2]);
		
		label1.setSize(difWidth, difHeight);
		label2.setSize(difWidth, difHeight);
		label3.setSize(difWidth, difHeight);
		
		label1.setLocation(spacingX, spacingY);
		label2.setLocation(spacingX * 2 + (int) (Init.SETTINGS_DIF_SIZE[0] * (panelW/1327)), spacingY);
		label3.setLocation(spacingX * 3 + (int) (Init.SETTINGS_DIF_SIZE[0] * (panelW/1327)) * 2, spacingY);
	}

}
