package introduction;

public class BankAccountMain {

	public static void main(String[] args) {
		double money = 1000;
		final double interestRate = 0.0035;
		int years = BankAccount.YearsToDouble(money,interestRate);
		System.out.println("Years to double: "+years);
		}
}
