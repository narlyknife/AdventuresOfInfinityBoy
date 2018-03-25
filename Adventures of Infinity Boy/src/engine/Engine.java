package engine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
			System.out.println("DONE: New settings file created");
		} catch (IOException e) {
			System.out.println("ERROR: Failed creating new settings file\t\t\tX");
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
	
	// Get a scaled version of an image
	public static Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
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
}