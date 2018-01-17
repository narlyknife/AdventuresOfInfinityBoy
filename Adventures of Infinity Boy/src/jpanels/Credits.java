package jpanels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

@SuppressWarnings("serial")
public class Credits extends JPanel implements ActionListener{
	
	// Declarations
	static int resX = Main._init.getResX();
	static int resY = Main._init.getResY();
	static String font = Main._init.getOurFont();
	
	JButton back;
	JLabel programmers, herman, folke;
	
	public Credits() {
		
		// Declaration
		back = new JButton("Main Menu");
		programmers = new JLabel("Programmers");
		herman = new JLabel("Herman Eriksson");
		folke = new JLabel("Folke Johansson");
		
		programmers.setFont(new Font(font, Font.PLAIN, 30));
		herman.setFont(new Font(font, Font.PLAIN, 18));
		folke.setFont(new Font(font, Font.PLAIN, 18));
		
		//Positioning and alignment
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		back.setAlignmentX(CENTER_ALIGNMENT);
		programmers.setAlignmentX(CENTER_ALIGNMENT);
		herman.setAlignmentX(CENTER_ALIGNMENT);
		folke.setAlignmentX(CENTER_ALIGNMENT);
		
		
		// Adding
		Dimension minSize = new Dimension(40,10);		Dimension prefSize = new Dimension(40, 20);		Dimension maxSize = new Dimension(40, 30);
		Dimension minSizeAbove = new Dimension(40,200);		Dimension prefSizeAbove = new Dimension(40, 250);		Dimension maxSizeAbove = new Dimension(40, 300);
		add(new Box.Filler(minSizeAbove, prefSizeAbove, maxSizeAbove));
		add(programmers); // Title
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(herman);	// Person
		add(new Box.Filler(minSize, prefSize, maxSize));
		add(folke);		//Person
		Dimension minSizeBelow = new Dimension(100, 100);
		Dimension prefSizeBelow = new Dimension(100, 150);
		Dimension maxSizeBelow = new Dimension(100, 200);
		add(new Box.Filler(minSizeBelow, prefSizeBelow, maxSizeBelow));
		add(back);
		
		// Method calling
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font(font, Font.PLAIN, 50));
		g.drawString("Credits", (int) (resX * 0.457), (int) (resY * 0.2));
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			
		}
	}
}