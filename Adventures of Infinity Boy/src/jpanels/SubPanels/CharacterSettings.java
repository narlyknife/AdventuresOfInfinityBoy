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

public class CharacterSettings extends JPanel{
	
	private static Image[] char1 = {
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character1def.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character1hov.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character1pre.png")).getImage()
	};
	
	private static Image[] char2 = {
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character2def.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character2hov.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character2pre.png")).getImage()
	};
	
	private static Image[] char3 = {
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character3def.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character3hov.png")).getImage(),
			new ImageIcon(CharacterSettings.class.getResource("/Pictures/character3pre.png")).getImage()
	};
	
	private static ImageIcon[] char1Scaled = {new ImageIcon(), new ImageIcon(), new ImageIcon()};
	private static ImageIcon[] char2Scaled = {new ImageIcon(), new ImageIcon(), new ImageIcon()};
	private static ImageIcon[] char3Scaled = {new ImageIcon(), new ImageIcon(), new ImageIcon()};
	
	private static JLabel label1 = new JLabel(), label2 = new JLabel(), label3 = new JLabel();
	
	private int label1State = 0, label2State = 0, label3State = 0;
	static int pressedLabel = Init.settingsData[1];
	
	public CharacterSettings() {
		this.setBackground(new Color(0 ,0 ,0));
		
		this.setLayout(null);
		
		// MouseListerner for label 1
		label1.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				if(label1State == 0 && pressedLabel != 1) {
					label1.setIcon(char1Scaled[1]);
					label1State = 1;
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(label1State == 1 && pressedLabel != 1) {
					label1.setIcon(char1Scaled[0]);
					label1State = 0;
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				label1.setIcon(char1Scaled[2]);
				pressedLabel = 1;
				
				label2.setIcon(char2Scaled[0]);
				label3.setIcon(char3Scaled[0]);
			}
		});

		// MouseListerner for label 2
		label2.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				if(label2State == 0 && pressedLabel != 2) {
					label2.setIcon(char2Scaled[1]);
					label2State = 1;
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(label2State == 1 && pressedLabel != 2) {
					label2.setIcon(char2Scaled[0]);
					label2State = 0;
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				label2.setIcon(char2Scaled[2]);
				pressedLabel = 2;
				
				label3.setIcon(char3Scaled[0]);
				label1.setIcon(char1Scaled[0]);
			}
		});
		
		// MouseListerner for label 3
		label3.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				if(label3State == 0 && pressedLabel != 3) {
					label3.setIcon(char3Scaled[1]);
					label3State = 1;
				}
			}
			
			public void mouseExited(MouseEvent e) {
				if(label3State == 1 && pressedLabel != 3) {
					label3.setIcon(char3Scaled[0]);
					label3State = 0;
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				label3.setIcon(char3Scaled[2]);
				pressedLabel = 3;
				
				label1.setIcon(char1Scaled[0]);
				label2.setIcon(char2Scaled[0]);
			}
		});
		
		for(int i = 0; i < char1.length; i++) {
			this.add(label1);
			this.add(label2);
			this.add(label3);
		}
	}
	
	public static void setLocations(float panelH, float panelW){
		int spacingX = (int) ((panelW - (3 * (Init.SETTINGS_CHAR_SIZE[0] * (panelW/1327)))) / 4);
		int spacingY = (int) (((panelH - (Init.SETTINGS_CHAR_SIZE[1] * (panelH/530))) / 2));
		
		int charWidth = (int) (Init.SETTINGS_CHAR_SIZE[0] * (panelW/1327));
		int charHeight = (int) (Init.SETTINGS_CHAR_SIZE[1] * (panelH/530));
		
		for(int i = 0; i < char1.length; i++) {
			char1Scaled[i] = new ImageIcon(Engine.getScaledImage(char1[i], charWidth, charHeight));
			char2Scaled[i] = new ImageIcon(Engine.getScaledImage(char2[i], charWidth, charHeight));
			char3Scaled[i] = new ImageIcon(Engine.getScaledImage(char3[i], charWidth, charHeight));
		}
		
		if(pressedLabel != 1) label1.setIcon(char1Scaled[0]);
		else label1.setIcon(char1Scaled[2]);
		
		if(pressedLabel != 2) label2.setIcon(char2Scaled[0]);
		else label2.setIcon(char2Scaled[2]);
		
		if(pressedLabel != 3) label3.setIcon(char3Scaled[0]);
		else label3.setIcon(char3Scaled[2]);
		
		label1.setSize(charWidth, charHeight);
		label2.setSize(charWidth, charHeight);
		label3.setSize(charWidth, charHeight);
		
		label1.setLocation(spacingX, spacingY);
		label2.setLocation(spacingX * 2 + (int) (Init.SETTINGS_CHAR_SIZE[0] * (panelW/1327)), spacingY);
		label3.setLocation(spacingX * 3 + (int) (Init.SETTINGS_CHAR_SIZE[0] * (panelW/1327)) * 2, spacingY);
	}
}
