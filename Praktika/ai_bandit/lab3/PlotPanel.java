package ai_bandit.lab3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PlotPanel extends JPanel {

	public void setSolver(MultiBanditSolver solver) {
		this.solver = solver;
	}

	private MultiBanditSolver solver;
	
	public PlotPanel(MultiBanditSolver solver) {
		super();
		this.solver = solver;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		int width = getWidth();
		int height = getHeight();
		int margin = 20;
		
		//print Axis
		g2.setColor(Color.black);
		
		//X-Axis
		g2.drawLine(margin, height - margin, width - margin, height - margin);
		
		//Y-Axis
		g2.drawLine(margin, margin, margin, height-margin);	
		g2.drawString("Bandit selection count", margin , margin - 5);
		
		if (solver == null) return;
		
		int numBandits = solver.getCounts().length; 
        int[] counts = solver.getCounts();
        
        //find maximum
        int maxCount = 1;
        for(int count: counts) {
        	if(count > maxCount) {
        		maxCount=count;
        	}	
        }
        
        //print bars
        int maxWidth = width - 2*margin;
        int barWidth = maxWidth/numBandits - 10;
        int maxBarHeight = height- 2*margin;
        for(int i = 0; i < numBandits;i++) {
        	int barHeight = (int) (((double) counts[i]/maxCount)  * maxBarHeight);
        	int x = margin + i* (barWidth + 10) + 5;
        	int y = height - margin - barHeight;
        	
        	g2.setColor(Color.DARK_GRAY);
        	g2.fillRect(x, y, barWidth, barHeight);
        	
        	//Number above bars
        	g2.setColor(Color.BLACK);
        	g2.drawString(String.valueOf(counts[i]), x + (barWidth)/4, y-5);
        }	
	}
}
