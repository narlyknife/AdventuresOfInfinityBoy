package engine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jpanels.GamePanel;
import jpanels.Settings;
import main.Init;
import main.Main;

public class Engine {

	static Clip clip;
	
	public static void main(String[] args) {
		
	}
	
	// Write data to a specific file relative to the projects directory
	public static void writeTxtFile(String path, int[] dataStream){
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path)));		// Creating PrintWriter object from a selected file
			
			for(int data : dataStream){
				writer.println(data);
			}
			
			writer.close();
			System.out.println("\nDONE: New settings file created");
		} catch (IOException e) {
			System.out.println("\nERROR: Failed creating new settings file\t\t\tX");
			e.printStackTrace();
		}
	}
	
	// Read data to a specific file relative to the projects directory
	public static void readTxtFile(String path) {
		 try {
			 BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(path)));	// Creating BufferedReader object from a selected file
		        
		     int i = 0;
		     String TempSettingValue;

		     while((TempSettingValue = file.readLine()) != null){				// As long as the line is not empty, fill the data streams element with the data from the file
		        Init.settingsData[i] = Integer.parseInt(TempSettingValue);
		        System.out.println("\tData on line " + (i+1) + " is: " + Init.settingsData[i]);
		        
		        i++;
		     }

		     file.close();
		 	} catch(IOException e){
			 System.out.println("ERROR: Something went wrong loading the Settings\t\t\tX");
		 }
	}
	
	public static Image getImage(String name) {
		return new ImageIcon(Engine.class.getResource("/Pictures/" + name)).getImage();
	}
	
	// Get a scaled version of an image
	public static Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	public static ImageIcon[] getScaledImageicon(String name, float x, float y) {
		ImageIcon value[] = new ImageIcon[3];
		
		value[0] = new ImageIcon(Engine.getScaledImage(getImage(name + "def.png"), (int) x, (int) y));
		value[1] = new ImageIcon(Engine.getScaledImage(getImage(name + "hov.png"), (int) x, (int) y));
		
		return value;
	}

	public static void playAudio(String name) {
		try {
			System.out.println("NOTE: Playing audio");
	        clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	        		Engine.class.getResourceAsStream("/Audio/" + name));
	        clip.open(inputStream);
	        clip.start();
		} catch(Exception e) {
			System.out.println("ERROR: Failed to load audio");
			e.printStackTrace();
		}
	}
	
	public static void stopAudio() {
		clip.stop();
	}
	
	public static void resumeAudio() {
		clip.start();
	}
	
	public static void volumeAudio(float value) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(value);
	}
	
	public static void animateLabel(JLabel label, ImageIcon[] icons, String panelName) {
		label.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				label.setIcon(icons[1]);
			}
			
			public void mouseExited(MouseEvent e) {
				if(panelName.equals("gamesettings") && Settings.activePanel != 1) label.setIcon(icons[0]);
				if(panelName.equals("difficultysettings") && Settings.activePanel != 2) label.setIcon(icons[0]);
				if(panelName.equals("charactersettings") && Settings.activePanel != 3) label.setIcon(icons[0]);
				
				if(!(panelName.equals("gamesettings") || panelName.equals("difficultysettings") || panelName.equals("charactersettings"))) {
					label.setIcon(icons[0]);
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				if(panelName.equals("quit")) System.exit(0);
				if(panelName.equals("gamepanel")) GamePanel.startMainThread();
				if(panelName.equals("settings")) {
					Engine.readTxtFile(Init.SETTINGS_PATH);
					Settings.changeSettingPanel("gamesettings");
				}
				
				if(panelName.equals("gamesettings") || panelName.equals("difficultysettings") || panelName.equals("charactersettings")) {
					Settings.changeSettingPanel(panelName);
					
					if(panelName.equals("gamesettings")) Settings.activePanel = 1;
					if(panelName.equals("difficultysettings")) Settings.activePanel = 2;
					if(panelName.equals("charactersettings")) Settings.activePanel = 3;
					
					Settings.buttons[0].setIcon(Settings.imgGame[0]);
					Settings.buttons[1].setIcon(Settings.imgDifficulty[0]);
					Settings.buttons[2].setIcon(Settings.imgCharacter[0]);
					
					label.setIcon(icons[1]);
				} else if(panelName.equals("save")){
					Settings.saveData();
					
					Main.setPanel("mainmenu");
				} else {
					Main.setPanel(panelName);
				}
			}
		});
	} 
}