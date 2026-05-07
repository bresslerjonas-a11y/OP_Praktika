package ai_bandit.lab1;

import java.util.Scanner;
import java.util.Random;

public class MultiBanditApp {

	public static void main(String[] args) {
		System.out.println("Gambling: Multi-armed bandit");
		System.out.println("Price   : 1,00 EUR\n");
		System.out.println("How many rounds would you like to play? ");
		
		//scanning Rounds:
		Scanner scanner = new Scanner(System.in);
		int rounds= scanner.nextInt();
		scanner.close();
		
		MultiBandit multiBandit = new MultiBandit(7);
		Random random = new Random();
		
		System.out.println("Round  |   Bandit   |  Win [EUR] | Net [EUR]");
		System.out.println("_______|____________|____________|__________");
		
		for(int i=0; i<rounds; i++) {
			
			int index = random.nextInt(multiBandit.getNumberBandits());
			double win = multiBandit.play(index);
			double overallProfit = multiBandit.getOverallProfit();
			
			//overallProfit for Player
			double overallProfitPlayer = overallProfit*-1;
			System.out.printf("%6d |%10d  |%12.2f |%10.2f\n",i+1,index,win,overallProfitPlayer);
		}
		
		System.out.println("\nMulti-armed bandits statistics:");
		System.out.println("Rounds: "+ rounds);
		//Profit Bandit
		double overallProfit = multiBandit.getOverallProfit();
		double banditProfitPerRound = overallProfit/rounds;
		System.out.printf("Profit Bandit: %.2f (%.2f EUR/round)\n",overallProfit,banditProfitPerRound);

	}

}
