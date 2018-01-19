package jpanels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import engine.MainActionListener;
import main.Main;

@SuppressWarnings("serial")
public class ScoreBoard extends JPanel{
	
	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	private String[] scores = new String[10];
	
	JButton[] buttons = {new JButton("Back")};
	
	// Constructor
	public ScoreBoard() {
		
		//Positioning and alignment
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		buttons[0].setAlignmentX(CENTER_ALIGNMENT);
		
		// Adding
		Dimension minSizeBelow = new Dimension(100, 750);
		Dimension prefSizeBelow = new Dimension(100, 825);
		Dimension maxSizeBelow = new Dimension(100, 900);
		add(new Box.Filler(minSizeBelow, prefSizeBelow, maxSizeBelow));
		add(buttons[0]);
		
		MainActionListener.addButton(buttons[0], "back");
		
		// Add custom actionListener
		buttons[0].addActionListener(Main.actionListener);
		
		// Method calling
		setScores();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font(font, Font.PLAIN, 40));
		g.drawString("Scoreboard", (int) (resX * 0.44), (int) (resY * 0.2));
		g.setFont(new Font(font, Font.PLAIN, 18));
		for (int i = 0; i < scores.length; i++) {
			g.drawString(i + 1 + ". " + scores[i],(int) (resX * 0.4),(int) ((resY * 0.3) + (i * (resY * 0.05))));
		}
	}
	
	// Goes trough all scores in the array and if there is no score it fills the spot with "N/A"
	public void setScores() {
		int length = scores.length;
		
		for (int i = 0; i < length; i++) {
			if (scores[i] == null) {
				scores[i] = "N/A";
			}
		}
	}
}
