import java.util.*;

public class Test {
	public static final int TRY = 100;
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		Random rand = new Random();
		promt(console, rand);
		
	
	}
	//prompt user and make calculations.
	public static void promt(Scanner console, Random rand){
		int totalscore = 0;
		double win = 0;
		double losses = 0;
		int scissorstime = 0;
		int rocktime = 0;
		int papertime = 0;
		int computerthrow = -1;
		String computername = "";
		String playerthrow;
		String playerthrowcurrent = "";
		String playerthrowprevious = "";
		String playerthrowpreviousprev = "";
		String computerthrowcurrent = "";
		String computerthrowprevious = "";
		String computerthrowpreviousprev = "";
		String panic = "";
					
		do{ 
						
			//check for applying or use random strategy.
			if(computerthrow !=0 || computerthrow !=1 || computerthrow != 2 ){
				computerthrow = rand.nextInt(3);
				if(panic.equals("panic on")){
					computerthrow = rand.nextInt(5);
					computerthrow = Math.abs(computerthrow-3);
				}
			}
			
			
			//determines what computer throw
			 if (computerthrow == 1){
				 computername = "scissors";
			 } else if( computerthrow ==2){
				 computername = "rock";
			 } else if(computerthrow == 0){
				 computername = "paper";
			 }
			 //record 3 computer throws, updating them each time.
			 computerthrowpreviousprev = computerthrowprevious;
			 computerthrowprevious = computerthrowcurrent;
			 computerthrowcurrent = computername;
			  System.out.println("I throw " + computername);
			
			
			do {
			// Check if use input not scissors or rock or paper or quit.
			System.out.println(">>> Please throw rock, paper, or scissors (or quit):");
			 playerthrow = console.next();
			 playerthrow = playerthrow.toLowerCase();
		} while(!playerthrow.equals("scissors") && !playerthrow.equals("rock") && !playerthrow.equals("paper") && !playerthrow.equals("quit"));
			
			//Record user number of same throws, updating them each time.
			playerthrowpreviousprev = playerthrowprevious;
			playerthrowprevious = playerthrowcurrent;
			playerthrowcurrent = playerthrow;
			//Check for Avalanche or Guppy strategy.
			if(playerthrowpreviousprev.equals(playerthrowprevious) && playerthrowprevious.equals(playerthrowcurrent)){
				if(playerthrow.equals("rock")){
					computerthrow = 0;
				} else if(playerthrow.equals("paper")){
					computerthrow = 1;
				} else if(playerthrow.equals("scissors")){
					computerthrow = 2;
				}
			} else if(playerthrowprevious.equals(computerthrowpreviousprev) || playerthrowcurrent.equals(computerthrowprevious)){
				if(playerthrowprevious.equals("rock") || playerthrowcurrent.equals("rock")){
					computerthrow = 0;
				} else if(playerthrowprevious.equals("paper") || playerthrowcurrent.equals("paper")){
					computerthrow = 1;
				} else if(playerthrowprevious.equals ("scissors") || playerthrowcurrent.equals("scissors")){
					computerthrow = 2;
				}
			}
		// check if user want to exit, shows the summary.
		 if(playerthrow.equals("quit")){
			 System.out.println("SUMMARY: (" + win + "/" + totalscore + ") = " + round((win/totalscore*100)) + "% wins, (" + losses + "/" + totalscore + ") = " + round((losses/totalscore*100))+ "% losses" );
		 } else {
		 
		//determines who win and who lose and count it.
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
		  
		   if((win + 50) > (losses)){
			   panic = "panic on";
		   } else if(round(win/totalscore*100) > 37.0){
			   panic = "panic on";
		   } else if(round(win/totalscore*100) < 37.0){
			   panic = "";
		   }
		 }
			
		}while(!playerthrow.equals("quit"));
		
	
		
	}

	/*
	public static void Summary(Random rand){
		for (int x = 1; x < TRY; x++){
		int computerthrowtwo = rand.nextInt(3);
		System.out.println( " " + computerthrowtwo);
		}
	}
	*/
	
	//simple method for rounding.
	public static double round(double n){
		return Math.round((n * 100.0)/ 100.0);
	}
}