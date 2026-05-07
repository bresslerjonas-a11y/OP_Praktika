package ai_bandit.lab1;
import java.util.Random;

public class Bandit {
	
	private static Random random = new Random();
	private String name;
	private double pricePerRound = 0;
	private double averageWin = 0;
	private double stdDevWin = 0;
	private double overallProfit = 0;
	private int roundsPlayed = 0;
	
	//Constructor:
	public Bandit(String name, double pricePerRound, double averageWin, double stdDevWin) {
		this.name = name;
		this.pricePerRound = pricePerRound;
		this.averageWin = averageWin;
		this.stdDevWin = stdDevWin;
	}
	
	public Bandit(double pricePerRound, double averageWin, double stdDevWin) {
		this("Bandit", pricePerRound, averageWin, stdDevWin);
	}
	
	//Getter:
	public String getName() {
		return name;
	}
	
	public double getPricePerRound() {
		return pricePerRound;
	}
	
	public double getOverallProfit() {
		return overallProfit;
	}
	
	public int getRoundsPlayed() {
		return roundsPlayed;
	}
	
	public double getMeanProfitPerRound() {
		if (roundsPlayed == 0) {
			return 0;
		}
		return overallProfit/roundsPlayed;
	}
	
	public double play() {
		double win = determineWin();
		win = Math.max(0.0, win);
		win = Math.round(win*10.0)/10.0;
		double profit = pricePerRound - win;
		
		overallProfit += profit;
		roundsPlayed++;
		return win;
	} 
	
	private double determineWin() {
		return averageWin + stdDevWin * random.nextGaussian();
	}
}
