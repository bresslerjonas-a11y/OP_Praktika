package ai_bandit.lab1;

import java.util.Scanner;

public class BanditApp {

	public static void main(String[] args) {
		System.out.println("Gambling: One-armed bandit");
		System.out.println("Price   : 1,00 EUR\n");
		System.out.println("How many rounds would you like to play? ");
		
		//scanning Rounds:
		Scanner scanner = new Scanner(System.in);
		int rounds= scanner.nextInt();
		scanner.close();
		Bandit bandit = new Bandit(1.0,0.8,0.2);
		
		System.out.println("Round  |  Win [EUR]  | Net [EUR]");
		System.out.println("_______|_____________|__________");
		
		for(int i=0; i<rounds; i++) {
			
			double win = bandit.play();
			double overallProfit = bandit.getOverallProfit();
			
			//overallProfit for Player
			double overallProfitPlayer = overallProfit*-1;
			System.out.printf("%6d |%12.2f |%10.2f\n",i+1,win,overallProfitPlayer); 
		}
		
		System.out.println("\nOne-armed bandits statistics:");
		System.out.println("Rounds: "+rounds);
		//Profit Bandit
		double overallProfit = bandit.getOverallProfit();
		double banditProfitPerRound = overallProfit/rounds;
		System.out.printf("Profit Bandit: %.2f (%.2f EUR/round)\n",overallProfit,banditProfitPerRound);
	}

}
