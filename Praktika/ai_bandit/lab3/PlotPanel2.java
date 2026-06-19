package ai_bandit.lab3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

public class PlotPanel2 extends JPanel{
	private List<Double> balanceHistory;
	
	public PlotPanel2(List<Double> balanceHistory) {
        this.balanceHistory = balanceHistory;
    }
	
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
		
		//find maximum
        double maxAbsBalance = 10.0; // Standard-Minimum-Skalierung
        for (double balance : balanceHistory) {
            if (Math.abs(balance) > maxAbsBalance) {
                maxAbsBalance = Math.abs(balance);
            }
        }
        
        g2.drawString(String.format("Available money (abs. max.: %.2f)", maxAbsBalance), margin, margin - 10);
        
		if (balanceHistory.isEmpty()) return;
		
		int prevX = margin;
		int prevY = height;
		
		//print chart
		int totalPoints = balanceHistory.size(); //totalPoints = rounds
		double xStep = (double) (width - 2*margin)/ Math.max(1, totalPoints-1); 
		double yScale = (double) (height - margin) / maxAbsBalance;
		
		for(int i=0; i< totalPoints;i++) {
			double balance = balanceHistory.get(i);
			int currX = margin + (int) (i* xStep);
			int currY = height - (int) (balance * yScale);
			
			//Choose color: Green if >0, red if <0
			if(balance>=0) {
				g2.setColor(Color.GREEN);
			} else {
				g2.setColor(Color.RED);
			}
			
			g2.drawLine(currX, height, currX, currY);
			
			prevX = currX;
			prevY = currY;
		}
	}
}
