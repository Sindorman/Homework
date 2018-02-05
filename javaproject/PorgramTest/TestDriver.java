import java.util.*;

public class TestDriver {
	public static final int TRY = 100;
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		Random rand = new Random();
		
		//Summary(rand);
		promt(console, rand);
		
	
	}
	//prompt user and make calculations.
	public static void promt(Scanner console, Random rand){
		int totalscore = 1;
		double win = 0;
		double losses = 0;
		String computername;
		String playerthrow;
					
		do{ 
			int computerthrow = rand.nextInt(3);
			 if (computerthrow == 1){
				 computername = "scissors";
			 } else if( computerthrow ==2){
				 computername = "rock";
			 } else{ 
				 computername = "paper";
			 }
			  System.out.println("I throw " + computername);
			
			
			do {
			System.out.println(">>> Please throw rock, paper, or scissors (or quit):");
			 playerthrow = console.next();
			 playerthrow = playerthrow.toLowerCase();
		} while(!playerthrow.equals("scissors") && !playerthrow.equals("rock") && !playerthrow.equals("paper") && !playerthrow.equals("quit"));
			
		
		 if(playerthrow.equals("quit")){
			 System.out.println("SUMMARY: (" + win + "/" + totalscore + ") = " + round((win/totalscore*100)) + "% wins, (" + losses + "/" + totalscore + ") = " + round((losses/totalscore*100))+ "% losses" );
		 } else {
		 		 
		  if((playerthrow.equals("scissors") && computerthrow == 1) || (playerthrow.equals("rock") && computerthrow == 2) || (playerthrow.equals("paper") && computerthrow == 0)){
			  System.out.println("We both throw " + computername + "(" + (int)win + "/" + totalscore + ") = " + round((win/totalscore*100)) + "% wins, (" + (int)losses + "/" + totalscore + ") = " + round((losses/totalscore*100)) + "% losses" );
			  totalscore++;
		  } else if((playerthrow.equals("scissors") && computerthrow == 2)){
			  losses++;			  
			  totalscore++;
			  System.out.println("rock beats scissors, I WIN; (" + (int)win + "/" + totalscore + ") = " + round((win/totalscore*100)) + "% wins, (" + (int)losses + "/" + totalscore + ") = " + round((losses/totalscore*100))+ "% losses");
			  			  
			  
			  } else if((playerthrow.equals("paper") && computerthrow == 2)){
				  win++;
				  totalscore++;
				  System.out.println("paper beats rock, YOU WIN; (" + (int)win + "/" + totalscore + ") = " + round((win/totalscore*100)) + "% wins, (" + (int)losses + "/" + totalscore + ") = " + round((losses/totalscore*100)) + "% losses" );
			  } else if ((playerthrow.equals("rock") && computerthrow == 0)){
				  losses++;				 
				  totalscore++;
				  System.out.println("paper beats rock, I WIN; (" + (int)win + "/" + totalscore + ") = " + round((win/totalscore*100)) + "% wins, (" + (int)losses + "/" + totalscore + ") = " + round((losses/totalscore*100)) + "% losses" );
			  } else if ((playerthrow.equals("rock") && computerthrow == 1)){
				  win++;
				  totalscore++;
				  System.out.println("rock beats scissors, YOU WIN; (" + (int)win + "/" + totalscore + ") = " + round((win/totalscore*100)) + "% wins, (" + (int)losses + "/" + totalscore + ") = " + round((losses/totalscore*100))+ "% losses" );
			  } else if ((playerthrow.equals("scissors") && computerthrow == 0)){
				  win++;
				  totalscore++;
				  System.out.println("scissors beats paper, YOU WIN; (" + (int)win + "/" + totalscore + ") = " + round((win/totalscore*100)) + "% wins, (" + (int)losses + "/" + totalscore + ") = " + round((losses/totalscore*100))+ "% losses" );
			  } else if((playerthrow.equals("paper") && computerthrow == 1)){
				  losses++;
				  totalscore++;
				  System.out.println("scissors beats paper, I WIN; (" + (int)win + "/" + totalscore + ") = " + round((win/totalscore*100)) + "% wins, (" + (int)losses + "/" + totalscore + ") = " + round((losses/totalscore*100))+ "% losses" );
			  }
		  //System.out.println("Won: " + win + " Lost " + losses + " totalscore: " + totalscore);
		 }
		}while(!playerthrow.equals("quit"));
		
	
		
	}

	public static void Summary(Random rand){
		for (int x = 1; x < TRY; x++){
		int computerthrowtwo = rand.nextInt(3);
		System.out.println( " " + computerthrowtwo);
		}
	}
	/*public static int Calculate( int calculate){
		int computerturn = random
	}*/
	public static double round(double n){
		return Math.round((n * 100.0)/ 100.0);
	}
}

