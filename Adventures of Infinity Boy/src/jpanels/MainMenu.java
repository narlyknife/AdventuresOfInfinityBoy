package jpanels;
import main.Main;
import main.Init;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements ActionListener{
	
	// Button for "Start game", "Settings", "Scoreboard", "Credits" 
	
	JButton startGame, settings, scoreBoard, credits;
	//static JFrame mainMenuFrame = new JFrame();
	
	public MainMenu() {
		
		// Declaration
		startGame = new JButton("Start Game");
		settings = new JButton("Settings");
		scoreBoard = new JButton("Scoreboard");
		credits = new JButton("Credits");
		
		// Outline
		// startGame.setBounds(Main._init.getResX(), /* 40% */, /* 55% */, /* 45% */);
		
		// Adding
		add(startGame);	add(settings);	add(scoreBoard);	add(credits);
		
		// ActionListener
		startGame.addActionListener(this);
		settings.addActionListener(this);
		scoreBoard.addActionListener(this);
		credits.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		//mainMenuFrame.setLayout(null);
		
	}
}
