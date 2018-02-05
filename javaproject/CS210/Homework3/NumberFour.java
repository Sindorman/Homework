public class NumberFour{
	public static final int deposit = 100;
	public static void main(String[] args){
		double balance;
		double interest;
		double depositshow;
		double currentbalance;
		double startingbalance = 1000;
		System.out.println("current balance: \tInterest: \tDeposit: \tNew Balance: ");
		for( int year = 1; year <= 25; year++){
			balance = startingbalance;
			currentbalance = startingbalance+startingbalance*0.065;
			interest = currentbalance-startingbalance;
			startingbalance = currentbalance + deposit;// Because question were confusing
			depositshow = deposit*year;
			
			System.out.println(round2(balance) + "\t" + "\t" + "\t" + round2(interest) + "\t" +"\t" + round2(deposit) + "\t" + "\t" + round2(currentbalance));
		}
		
		
	}
	public static double round2(double n){
			return Math.round(n * 100.00)/ 100.00;
	}
}