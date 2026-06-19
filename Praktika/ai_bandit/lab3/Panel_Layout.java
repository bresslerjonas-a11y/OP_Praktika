package ai_bandit.lab3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class Panel_Layout {
	// Logik-Komponenten
    private MultiBandit multiBandit;
    private MultiBanditSolver multiBanditSolver;
    private List<Double> balanceHistory;
    private Random random = new Random();
    
    // GUI-Komponenten
    private PlotPanel plot1;
    private PlotPanel2 plot2;
    private JRadioButton mode1; // Random
    private JRadioButton mode2; // Epsilon-Greedy
    
	public Panel_Layout() {
		initGameLogic();
		
		JFrame frame = new JFrame("Hensel's Eleven");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200,800);
		frame.setLocationByPlatform(true);
		
		//Main Layout
		frame.setLayout(new BorderLayout());
		
		//sideBar
		JPanel sideBar = new JPanel();
		sideBar.setLayout(new GridLayout(20,1));
		sideBar.setPreferredSize(new Dimension(180,0));
		sideBar.setBackground(Color.LIGHT_GRAY);
		
		//Button Group
		mode1 = new JRadioButton("Random bandits");
		mode2 = new JRadioButton("Epsilon-greedy");
		
		//default
		mode1.setSelected(true);
		
		ButtonGroup group = new ButtonGroup();
		group.add(mode1);
		group.add(mode2);
		mode1.setBackground(Color.LIGHT_GRAY);
		mode2.setBackground(Color.LIGHT_GRAY);
		
		//add Buttons
		sideBar.add(new JLabel("Selection strategy:"));
		sideBar.add(mode1);
		sideBar.add(mode2);
		
		JButton buttonReset = new JButton("Reset bandits");
		JButton buttonPlay1 = new JButton("Play 1x");
		JButton buttonPlay10 = new JButton("Play 10x");
		JButton buttonPlay100 = new JButton("Play 100x");
		
		sideBar.add(buttonReset);
		sideBar.add(buttonPlay1);
		sideBar.add(buttonPlay10);
		sideBar.add(buttonPlay100);
		
		//Reset Button
		buttonReset.addActionListener(e ->{
			initGameLogic();
			updatePlots();
		});
		
		//Play Buttons
		buttonPlay1.addActionListener(e-> playRounds(1));
		buttonPlay10.addActionListener(e-> playRounds(10));
		buttonPlay100.addActionListener(e-> playRounds(100));
		
		//Plot
		JPanel plotArea = new JPanel();
		plotArea.setLayout(new GridLayout(2,1));
		
		plot1 = new PlotPanel(multiBanditSolver);
		plot2 = new PlotPanel2(balanceHistory);
		
		plotArea.add(plot1);
		plotArea.add(plot2);
		
		frame.add(sideBar,BorderLayout.EAST);
		frame.add(plotArea,BorderLayout.CENTER);
		
		frame.setVisible(true);
		
	}
	
	//reset
	private void initGameLogic() {
	    //first start at the beginning
	    if (balanceHistory == null) {
	        multiBandit = new MultiBandit(7);
	        multiBanditSolver = new MultiBanditSolver(multiBandit);
	        balanceHistory = new ArrayList<>();
	    } else {
	        // Reset while playing
	        multiBandit = new MultiBandit(7);
	        multiBanditSolver = new MultiBanditSolver(multiBandit);
	        balanceHistory.clear(); // clear list
	        
	        // new solver
	        if (plot1 != null) {
	            plot1.setSolver(multiBanditSolver); 
	        }
	    }
	}
	
	private void playRounds(int rounds) {
		for(int i=0; i< rounds; i++) {
			int index = 0;
			
			if(mode1.isSelected()) {
				index = multiBanditSolver.chooseRandom();
			}
			else {
				double epsilon = 0.15;
				if(random.nextDouble() < epsilon) {
					index = multiBanditSolver.chooseRandom();
				}
				else {
					index = multiBanditSolver.chooseGreedy();
				}	
			}
			//play
			double win = multiBandit.play(index);
			
			//Update solver (- cost per round)
			multiBanditSolver.addBanditResponse(index, win - 1.0);
			
			double playerBalance = -multiBandit.getOverallProfit();
			balanceHistory.add(playerBalance);
		}
		updatePlots();	
	}
	private void updatePlots() {
		plot1.repaint();
		plot2.repaint();
	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Panel_Layout());
	}

}
