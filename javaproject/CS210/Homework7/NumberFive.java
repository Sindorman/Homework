
import java.util.*;
public class NumberFive {
	public static void main(String[] args){
		boolean UserWon = false;
		Random rand = new Random();
		Scanner MasterMind = new Scanner(System.in);
		int[] SecretNumber = new int[4];
		int[] UserNumber = new int[4];
		boolean print = true;
		boolean Spaces = false;
		String UserInput = "";
		int y = 0;
		for(int x = 0; x < 4; x++){
			SecretNumber[x] = rand.nextInt(9);
		}
		do{
			print = true;
			boolean [] check = {false, false, false, false};
			y = 0;
			int WrongPlace = 0;
			int Correct = 0;
			int CorrectPlace = 0;
			
			
			do{
			//boolean Wrong = false;
			System.out.print("Please write 4 digit number using spaces between each element of the number: ");
			
			UserInput = MasterMind.nextLine();
				
			
			}while(UserInput.length() != 7);
			for(int x = 0; x < 4; x++){
				
				char number = UserInput.charAt(y);
				UserNumber[x] = Character.getNumericValue(number);
				
					y +=2;				
			}
			for(int x = 0; x < SecretNumber.length; x++){
				if(SecretNumber[x] == UserNumber[x]){
					Correct++;
					CorrectPlace++;
					check[x] = true;
				}
				
				if (Correct == 4){
					System.out.print("Congradulations, you have guessed the number");
					UserWon = true;
					print = false;
					break;
					
				}
				
			}
			for(int h = 0; h < SecretNumber.length; h++){
				for(int n = 0; n < SecretNumber.length; n++){
					if(h != n && check[h] == false){
						if(SecretNumber[h] == UserNumber[n]){
							WrongPlace++;
							Correct++;
						}
					}
				}
			}
			if(print == false){
				
			}else if(print == true){
			System.out.printf("You have %d correct. %d in correct place. %d in wrong place %n", Correct, CorrectPlace, WrongPlace );
			}
		}while(UserWon != true);
	}
	
}
