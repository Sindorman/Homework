import java.util.*;
public class Calculactor2 {
	public static void main(String[] args){
		boolean result = true;
		Promt(result);
	}
	
	
	public static String Promt(boolean result){
		
		Scanner input = new Scanner(System.in);
		boolean error;
		System.out.println("Please write an input. It must be operand <space> operator <space> operand, or operator <space> operand");
		do{
			error = false;
			String line = input.nextLine();
			String[] UserInput = line.split(" ");
			for (int x = 0; x <= 2 ; x += 2){
				if (IsOperand(UserInput[x]) == false){
					error = true;
				}
			}
			if (IsOperator(UserInput[1]) == false){
				
				
				error = true;
			}
			if (error == true){
				System.out.println("Error: input must be operand <space> operator <space> operand, or operator <space> operand");
			}
			if ((UserInput[0].equals("+") || UserInput[0].equals("-") || UserInput[0].equals("*") || UserInput[0].equals("/")) && result == true){
				error = false;
				PromtWithResult(UserInput);
			}
		}while (error == true );
		 
		return "";
		
	}
	public static String PromtWithResult(String[] UserInput){
		Scanner input = new Scanner(System.in);
		
		boolean error;
		while (IsOperand(UserInput[1]) == false){
			error = true;
			do{
				error = false;
				System.out.println("Error: input must be operand <space> operator <space> operand, or operator <space> operand");
				String line = input.nextLine();
				UserInput = line.split(" ");
			}while (error == true);
		}
		
		 
		return "";
		
	}
	public static boolean IsOperator(String text){
		if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")){
			return true;
			
		}else {
			return false;
		}
	
	}
	
	public static boolean IsOperand(String text){
		if(text.indexOf("+") >= 0 || text.indexOf("-") >= 0 || text.indexOf("*") >= 0 || text.indexOf("/") >= 0 || text.indexOf(" ") >= 0){
			return false;
		}else{
			return true;
		}
		
	}
}
