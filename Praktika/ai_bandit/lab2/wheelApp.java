package ai_bandit.lab2;

import java.util.Scanner;

public class wheelApp {

	public static void main(String[] args) {
		System.out.println("Gambling: Wheel of fortune");
		System.out.println("Price   : 1,00 EUR\n");
		System.out.println("Fields  : 30 ");
		System.out.println("How many rounds would you like to play? ");
		
		//scanning Rounds:
		Scanner scanner = new Scanner(System.in);
		int rounds= scanner.nextInt();
		scanner.close();
		WheelOfFortune wheelOfFortune = new WheelOfFortune(1, new int[]{15,10,4,1}, new double[]{0,1,2,5}) ;
		
		System.out.println("Round  |  Win [EUR]  | Net [EUR]");
		System.out.println("_______|_____________|__________");
		
		for(int i=0; i<rounds; i++) {
			
			double win = wheelOfFortune.play();
			double overallProfit = wheelOfFortune.getOverallProfit();
			
			//overallProfit for Player
			double overallProfitPlayer = -overallProfit;
			System.out.printf("%6d |%12.2f |%10.2f\n",i+1,win,overallProfitPlayer); 
		}
		
		System.out.println("\nWheel of fortune’s statistics:");
		System.out.println("Rounds: "+rounds);
		//Profit Bandit
		double overallProfit = wheelOfFortune.getOverallProfit();
		double banditProfitPerRound = overallProfit/rounds;
		System.out.printf("Profit Bandit: %.2f (%.2f EUR/round)\n",overallProfit,banditProfitPerRound);
	}

}
