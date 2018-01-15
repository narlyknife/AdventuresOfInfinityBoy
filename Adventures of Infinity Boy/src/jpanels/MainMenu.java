package jpanels;
import java.awt.Container;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Main;

public class MainMenu extends JPanel implements ActionListener{
	
	// Button for "Start game", "Settings", "Scoreboard", "Credits" 	
	JButton startGame, settings, scoreBoard, credits;
	
	static JFrame mainMenuFrame = new JFrame();
	
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	
	public MainMenu() {
		System.out.println(resX);
		System.out.println(resY);
		
		// Declaration
		startGame = new JButton("Start Game");
		settings = new JButton("Settings");
		scoreBoard = new JButton("Scoreboard");
		credits = new JButton("Credits");
		
		// Outline
		startGame.setLocation(((int) (resX * 0.45)), ((int) (resY * 0.4)));
		
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
		mainMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainMenuFrame.setUndecorated(true);
		MainMenu p = new MainMenu();
		p.setSize(resX, resY);
		BoxLayout box = new BoxLayout(p, BoxLayout.PAGE_AXIS);
		p.setLayout(box);
		mainMenuFrame.setVisible(true);
		
	}
}
