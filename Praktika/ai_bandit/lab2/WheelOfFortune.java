package ai_bandit.lab2;

public class WheelOfFortune extends Gambling {
	
	private int numberFields = 0;
	private double[] categoryWins;
	private double[] categoryChances;
	
	public WheelOfFortune(double pricePerRound, int[]  categoryNumberFields, double[] categoryWins) {
		super(pricePerRound);
		this.categoryWins = categoryWins;
		
		//Calculate Fields
		numberFields = 0;
		for(int Field : categoryNumberFields) {
			numberFields += Field;
		}
		
		//Array for Probabilities
		categoryChances = new double[categoryNumberFields.length];
		
		//Calculate Probabilities
		for(int i=0; i<categoryNumberFields.length; i++) {
			categoryChances[i]= categoryNumberFields[i]/(double) numberFields;
		}
	}
	
	public int getNumberFields() {
		return numberFields;
	}

	double determineWin() {
		double r = random.nextDouble();
		double sum = 0;
		
		for(int i = 0 ; i < categoryChances.length; i++) {
			sum += categoryChances[i];
			
			if(r < sum) {
				return categoryWins[i];
			}
		}
		return 0.0;
	}
}
