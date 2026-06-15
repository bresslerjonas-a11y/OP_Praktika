package ai_bandit.lab3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Panel_Layout {
	public Panel_Layout() {
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
		JRadioButton mode1 = new JRadioButton("Random bandits");
		JRadioButton mode2 = new JRadioButton("Epsilon-greedy");
		
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
		sideBar.add(new JButton("Reset bandits"));
		sideBar.add(new JButton("Play 1x"));
		sideBar.add(new JButton("Play 10x"));
		sideBar.add(new JButton("Play 100x"));
		sideBar.add(new JButton("Start"));
		
		//Plot
		JPanel plotArea = new JPanel();
		plotArea.setLayout(new GridLayout(2,1));
		
		PlotPanel plot1 = new PlotPanel();
		PlotPanel2 plot2 = new PlotPanel2();
		
		plotArea.add(plot1);
		plotArea.add(plot2);
		
		frame.add(sideBar,BorderLayout.EAST);
		frame.add(plotArea,BorderLayout.CENTER);
		
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		new Panel_Layout();	
	}

}
