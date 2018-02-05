import java.util.*;
public class TestFour {
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		String input = "";
		input = console.nextLine();
		char current;
		char previous = 0;
		char next = 0;
		
		
		for (int x = 0; x < input.length(); x++){
			if(x!=0){
				previous = input.charAt(x-1);
			}
			if(x!=input.length()-1){
				next = input.charAt(x+1);
			} else{
				next = 0;
			}
			current = input.charAt(x);
			
			if(!Character.isLetter(previous) && !Character.isLetter(next)){
				if(current == 'i'){
					current = Character.toUpperCase(current);
					System.out.print(current);
				}
			}else{
				System.out.print(current);
			}
		}
	}
}
