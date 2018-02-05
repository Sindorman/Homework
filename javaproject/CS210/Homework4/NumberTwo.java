import java.util.*;

public class NumberTwo
{
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
	    String word = reverse(console);
		
	}
	
	
	
	public static String reverse(Scanner console){
		String phrase = console.next();
		String result = "";
		for (int i = 0; i < phrase.length(); i++){
			result = phrase.charAt(i) + result;
		}
		phrase = phrase.toLowerCase();
		result = result.toLowerCase();
		
		if ( phrase.equals(result)){
			System.out.print("It's a palindrome");
		} else {
			System.out.print("It's not a palindrome");
		}
		return result;
	}
	
	
}