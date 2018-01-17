package jpanels.SubPanels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

public class GameOver extends JPanel implements ActionListener{
	
	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	JLabel title;
	JButton retry, back;
	
	public GameOver() {
		
		// Declarations
		title = new JLabel("Game Over!");
		retry = new JButton("Retry");
		back = new JButton("Main Menu");
		
		title.setFont(new Font(font, Font.PLAIN, 40));
		
		// Positioning and alignment
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		title.setAlignmentX(CENTER_ALIGNMENT);
		retry.setAlignmentX(CENTER_ALIGNMENT);
		back.setAlignmentX(CENTER_ALIGNMENT);
		
		// Adding
		Dimension minSize = new Dimension(40,10);		Dimension prefSize = new Dimension(40, 20);		Dimension maxSize = new Dimension(40, 30);
		Dimension minSizeAbove = new Dimension(40,300);		Dimension prefSizeAbove = new Dimension(40, 350);		Dimension maxSizeAbove = new Dimension(40, 400);
		add(new Box.Filler(minSizeAbove, prefSizeAbove, maxSizeAbove));
		add(title);
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(retry);
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(back);
		
		// Applying action listener
		retry.addActionListener(this);
		back.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == retry) {
			
		}
		if (e.getSource() == back) {
			
		}
	}
}
