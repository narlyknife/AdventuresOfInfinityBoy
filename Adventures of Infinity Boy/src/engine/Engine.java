package engine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Character;
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
			PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter(path)));
	
			for(int data : dataStream){
				file.println(data);
			}
			
			file.close();
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
		try {
			return ImageIO.read(Engine.class.getResourceAsStream("/resources/" + name));
		} catch (IOException e) {
			System.out.println("ERROR: Couldn't load image" + name);
			e.printStackTrace();
			return null;
		}
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
		ImageIcon value[] = new ImageIcon[2];
		
		if(!name.toLowerCase().contains("title")) {
			value[0] = new ImageIcon(Engine.getScaledImage(getImage(name + "Def.png"), (int) x, (int) y));
			value[1] = new ImageIcon(Engine.getScaledImage(getImage(name + "Hov.png"), (int) x, (int) y));
		}
		else {
			value[0] = new ImageIcon(Engine.getScaledImage(getImage(name + ".png"), (int) x, (int) y));
		}
			
		return value;
	}

	public static void playAudio(String name) {
		try {
			System.out.println("NOTE: Playing audio");
	        clip = AudioSystem.getClip();
	        InputStream audioSrc = Engine.class.getResourceAsStream("/Audio/" + name);
	        InputStream bufferedIn = new BufferedInputStream(audioSrc);
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
	        clip.open(audioStream);
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
				if(panelName.equals("gamepanel")) {
					GamePanel.startMusic();
					GamePanel.resumeGame();
					GamePanel.reset();
					KeyHandler.gamePaused = false;
					Main.showSubPanel(Main.getSubPanel("pause"), false);
					Main.showSubPanel(Main.getSubPanel("gameover"), false);
				}
				if(panelName.equals("settings")) {
					Engine.readTxtFile(Init.SETTINGS_PATH);
					Settings.changeSettingPanel("gamesettings");
				}
//				if(panelName.equals("mainmenu")) Engine.stopAudio();
				
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
				
				} else if(panelName.equals("retry")) {  // Retry
					GamePanel.reset();
					GamePanel.resumeGame();
					GamePanel.startMusic();
					Main.showSubPanel(Main.getSubPanel("pause"), false);
					Main.showSubPanel(Main.getSubPanel("gameover"), false);
					
					KeyHandler.gamePaused = false;
          
				} else if(panelName.equals("resume")){	// Resume
					System.out.println("Note: Game has been resumed");
					Main.showSubPanel(Main.getSubPanel("pause"), false);
					GamePanel.resumeGame();
					Engine.resumeAudio();
					
					KeyHandler.gamePaused = false;
				} else {
					Main.setPanel(panelName);
				}
			}
		});
	}
	
	// Choose a random image for a ground block
	public static Image pickRandomImage(Image[] img) {
		int i = (int) (Math.random() * 10);
		
		if(i >= 0 && i < 2) return img[0];
		else if(i >= 2 && i < 5) return img[1];
		else if(i >= 5 && i < 7) return img[2];
		else return img[3];
	}
	
	public static boolean intersects(Character character, JPanel obstacle) {
		Area areaA = new Area(character.getBounds());
		Area areaB = new Area(obstacle.getBounds());
		
		return areaA.intersects(areaB.getBounds2D());
	}
}