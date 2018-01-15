package jpanels;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;

public class MainMenu extends JPanel implements ActionListener{
	
	// Button for "Start game", "Settings", "Scoreboard", "Credits" 	
	JButton startGame, settings, scoreBoard, credits;
	//static JFrame mainMenuFrame = new JFrame();
	
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	
	public MainMenu() {
		
		// Declaration
		startGame = new JButton("Start Game");
		settings = new JButton("Settings");
		scoreBoard = new JButton("Scoreboard");
		credits = new JButton("Credits");
		
		// Aligning everything
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		startGame.setAlignmentX(CENTER_ALIGNMENT);
		settings.setAlignmentX(CENTER_ALIGNMENT);
		scoreBoard.setAlignmentX(CENTER_ALIGNMENT);
		credits.setAlignmentX(CENTER_ALIGNMENT);
				
		// Adding components and adding spacing between them
		Dimension minSize = new Dimension(40,10);
		Dimension prefSize = new Dimension(40, 20);
		Dimension maxSize = new Dimension(40, 30);
		Dimension maxSizeAbove = new Dimension(40,600);
		add(new Box.Filler(minSize, prefSize, maxSizeAbove));
		add(startGame);	
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(settings);	
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(scoreBoard);
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(credits);
		Dimension maxSizeBelow = new Dimension(40,200);
		add(new Box.Filler(minSize, prefSize, maxSizeBelow));
		
		// ActionListener
		startGame.addActionListener(this);
		settings.addActionListener(this);
		scoreBoard.addActionListener(this);
		credits.addActionListener(this);
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
