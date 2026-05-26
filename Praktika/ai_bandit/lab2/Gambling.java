package ai_bandit.lab2;
import java.util.Random;

public abstract class Gambling {
	
	protected static Random random = new Random();
	private String name;
	private double pricePerRound = 0;
	private double overallProfit = 0;
	private int roundsPlayed = 0;
	
	//Constructor:
	public Gambling(String name, double pricePerRound) {
		this.name = name;
		this.pricePerRound = pricePerRound;
	}
	
	public Gambling(double pricePerRound) {
		this("Bandit", pricePerRound);
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
	
	abstract double determineWin();
}
