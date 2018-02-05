import java.util.*;

public class NumberThree
{
	public static void main(String[] arga){
		System.out.print("Enter a number: ");
		System.out.print("Your number in roman numerals is: " + IntegerToRomanNumeral() );
	}
	public static String IntegerToRomanNumeral() {
		Scanner console = new Scanner(System.in);
		int number = console.nextInt();
		if (number < 1 || number > 3999)
			return "Invalid Roman Number Value";
		String a = "";
		while (number >= 1000) {
			a += "M";
			number -= 1000;        
		}
		while (number >= 900) {
			a += "CM";
			number -= 900;
		}
		while (number >= 500) {
			a += "D";
			number -= 500;
		}
		while (number >= 400) {
			a += "CD";
			number -= 400;
		}
		while (number >= 100) {
			a += "C";
			number -= 100;
		}
		while (number >= 90) {
			a += "XC";
			number -= 90;
		}
		while (number >= 50) {
			a += "L";
			number -= 50;
		}
		while (number >= 40) {
			a += "XL";
			number -= 40;
		}
		while (number >= 10) {
			a += "X";
			number -= 10;
		}
		while (number >= 9) {
			a += "IX";
			number -= 9;
		}
		while (number >= 5) {
			a += "V";
			number -= 5;
		}
		while (number >= 4) {
			a += "IV";
			number -= 4;
		}
		while (number >= 1) {
			a += "I";
			number -= 1;
		}    
		return a;
		}
}
