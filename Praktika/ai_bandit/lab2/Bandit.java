package ai_bandit.lab2;

public class Bandit extends Gambling {
	
	private double averageWin = 0;
	private double stdDevWin = 0;
	
	//Constructor:
		public Bandit(String name, double pricePerRound, double averageWin, double stdDevWin) {
			super(name,pricePerRound);
			this.averageWin = averageWin;
			this.stdDevWin = stdDevWin;
		}
		
		public Bandit(double pricePerRound, double averageWin, double stdDevWin) {
			this("Bandit", pricePerRound, averageWin, stdDevWin);
		}
		
		double determineWin() {
			return averageWin + stdDevWin * random.nextGaussian();
	}
}
