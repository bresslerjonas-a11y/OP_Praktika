package ai_bandit.lab3;

import java.util.Scanner;
import java.util.Random;

public class MultiBanditApp {

	public static void main(String[] args) {
		
		MultiBandit multiBandit = new MultiBandit(7);
		MultiBanditSolver multiBanditSolver = new MultiBanditSolver(multiBandit);
		int numberBandits = multiBandit.getNumberBandits();
		Random random = new Random();
		
		System.out.println("Gambling: Multi-armed bandit ("+ numberBandits + " bandits)");
		System.out.println("Price   : 1,00 EUR\n");
		System.out.println("How many rounds would you like to play? ");
		
		//scanning Rounds:
		Scanner scanner = new Scanner(System.in);
		int rounds= scanner.nextInt();
		
		//Scanning epsilon:
		System.out.println("Enter epsilon in [0,100] percent (typical value: 15) or any other number for random strategy:");
		int epsilonPercent = scanner.nextInt();
		scanner.close();
		
		System.out.println("Round  |   Bandit   |  Win [EUR] | Net [EUR]");
		System.out.println("_______|____________|____________|__________");
		
		for(int i=0; i<rounds; i++) {
			int index = 0;
			double epsilon = epsilonPercent/100.0;
			if(random.nextDouble() < epsilon) {
				index = multiBanditSolver.chooseRandom();
			}
			else {
				index = multiBanditSolver.chooseGreedy();
			}
			double win = multiBandit.play(index);
			
			multiBanditSolver.addBanditResponse(index, win-1.0);
			double overallProfit = multiBandit.getOverallProfit();
			
			//overallProfit for Player
			double overallProfitPlayer = -overallProfit;
			System.out.printf("%6d |%10d  |%12.2f |%10.2f\n",i+1,index,win,overallProfitPlayer);
		}
		
		//print out the strategy
		if(epsilonPercent <= 100 && epsilonPercent >= 0){
			System.out.println("Applied strategy: epsilon-greedy (epsilon = "+ epsilonPercent +" )\n");
		}
		else {
			System.out.println("\nApplied strategy: random\n");
		}
		
		//print out other statistics
		System.out.println("Multi-armed bandits statistics:");
		System.out.println("Rounds: "+ rounds);
		//Profit Bandit
		double overallProfit = multiBandit.getOverallProfit();
		double banditProfitPerRound = overallProfit/rounds;
		System.out.printf("Profit Bandit: %.2f (%.2f EUR/round)\n",overallProfit,banditProfitPerRound);
	}
}

