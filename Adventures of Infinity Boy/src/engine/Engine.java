package engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import main.Init;

public class Engine {

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
}