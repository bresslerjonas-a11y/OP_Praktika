package ai_bandit.lab3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PlotPanel2 extends JPanel{
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		int width = getWidth();
		int height = getHeight()/2;
		int margin = 20;
		
		//print Axis
		g2.setColor(Color.black);
		
		//X-Axis
		g2.drawLine(margin, height - margin, width - margin, height - margin);
		
		//Y-Axis
		g2.drawLine(margin, margin, margin, 2*height-margin);	
		
		g2.drawString("Available money (abs. max.: 10,00)", margin , margin - 5);
	}

}
