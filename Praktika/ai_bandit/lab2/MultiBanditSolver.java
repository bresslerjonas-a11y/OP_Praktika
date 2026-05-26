package ai_bandit.lab2;
import java.util.Random;

public class MultiBanditSolver {
	
	private static Random random = new Random();
	private MultiBandit bandits;
	private int[] counts;
	private double[] wins;
	private double epsilon = 0.0;
	
	public MultiBanditSolver(MultiBandit bandits) {
		this.bandits = bandits;
		
		int n = bandits.getNumberBandits();
		
		counts = new int[n];
		wins = new double[n];
	}
	
	public void setGreedyEpsilon(double epsilon){
		this.epsilon = epsilon;
	}
	
	public void addBanditResponse(int banditIndex, double win) {
		counts[banditIndex]++;
		wins[banditIndex] += win;
	}
	
	public double getAverageWin(int banditIndex) {
		if (counts[banditIndex] == 0) {
			return 0.0;
		}
		return wins[banditIndex] / counts[banditIndex];
	}
	
	public int chooseRandom() {
		return random.nextInt(counts.length);
	}
	
	public int chooseGreedy() {
		int bestIndex = 0;
		double bestValue = getAverageWin(0);
		
		for(int i=0;i < counts.length; i++) {
			double value = getAverageWin(i);
			
			if(value>bestValue) {
				bestValue = value;
				bestIndex = i;
			}
		}
		return bestIndex;
	}
}
