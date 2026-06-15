package ai_bandit.lab3;
import java.util.Random;

public class MultiBandit {
	
	private Bandit[] bandits;
	private static Random random = new Random();
	
	
	public MultiBandit (int numberBandits) {
		bandits = new Bandit[numberBandits];
		
		//Get random winner. Number between 0 and numberBandits-1
        int winnerIndex = random.nextInt(numberBandits);
		
        for (int i = 0; i < numberBandits; i++) {
            double price = 1.0;
            double stdDev = 1.0;
            double mean;

            if (i == winnerIndex) {
                //good Bandit. random.nextDouble() = Number between 0 and 1
                mean = 1.1 + random.nextDouble() * (1.3 - 1.1);
            } else {
                //bad Bandit
                mean = 0.5 + random.nextDouble() * (0.8 - 0.5);
            }

            bandits[i] = new Bandit(price, mean, stdDev);
        }
	}
	
	public int getNumberBandits() {
		return bandits.length;
	}
	
	public double getPricePerRound() {
		 if (bandits.length == 0) return 0;
		    return bandits[0].getPricePerRound();
	}
	
	public double getOverallProfit() {
		if(bandits.length==0) return 0;
		double sum=0;
		for(Bandit b: bandits) {
			sum += b.getOverallProfit();
		}
		return sum;
	}
	
	public int getRoundsPlayed() {
		int sum = 0;
		for(Bandit b: bandits) {
			sum+= b.getRoundsPlayed();
		}
		return sum;
	}
	
	public double getMeanProfitPerRound() {
		int rounds =  getRoundsPlayed();
		if (rounds==0) return 0;
		return getOverallProfit()/getRoundsPlayed();
	}
	
	public double play(int banditIndex) {
		 if (banditIndex < 0 || banditIndex >= bandits.length) {
			 throw new IllegalArgumentException("Ungültiger Index");
		 }
		 return bandits[banditIndex].play();
	}
}
