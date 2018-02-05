import java.util.Scanner;

import org.omg.CORBA.ExceptionList;

public class MathGrade {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the Math 151 Grade calculator.");
		System.out.println(promt(null, null));
	}

	public static int promt(String type, String number) {
		Scanner input = new Scanner(System.in);
		String aux = "";
		boolean parsed = false;
		int ret = 0;
		while (!aux.equals("-") || parsed == false) {
			System.out.println("Please enter your "  + type + " " + number + ":");
			aux = input.next();
			try {
				if(aux.equals("-")){
					break;
				}
				ret = Integer.parseInt(aux);
			} catch (NumberFormatException e){
				continue;
			}
			ret = Integer.parseInt(aux);
			parsed = true;
		}
		return ret;
	}
}
