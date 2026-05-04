package introduction;

public class BankAccount {
	
	public static int YearsToDouble(double money, double interestRate){
		double target = 2*money;
		int years = 0;
		
		while(target > money) {
			money *= (1 + interestRate);
			years++;
		}
		return years;
	}
}
