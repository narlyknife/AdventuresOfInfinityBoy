package jpanels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;

@SuppressWarnings("serial")
public class ScoreBoard extends JPanel implements ActionListener{
	
	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	private String[] scores = new String[10];
	
	JButton back;
	
	// Constructor
	public ScoreBoard() {
		
		// Declarations
		back = new JButton("Main Menu");
		
		//Positioning and alignment
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		back.setAlignmentX(CENTER_ALIGNMENT);
		
		// Adding
		Dimension minSizeBelow = new Dimension(100, 750);
		Dimension prefSizeBelow = new Dimension(100, 825);
		Dimension maxSizeBelow = new Dimension(100, 900);
		add(new Box.Filler(minSizeBelow, prefSizeBelow, maxSizeBelow));
		add(back);
		
		// Action listener
		back.addActionListener(this);
		
		// Method calling
		setScores();
		repaint();
	}
	
	// Method
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			
		}
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
