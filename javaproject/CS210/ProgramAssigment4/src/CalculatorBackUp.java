import java.util.*;

public class CalculatorBackUp {
	

	public static void main(String[] args) {
		Action act = new Action();
		double first = 0;
		double second = 0;
		boolean numberOneType;
		boolean numberTwoType;
		boolean changed = false;
		double result = 0;
		boolean exit = false;
		int x = 0;
		String UserInput = "";
		Scanner input = new Scanner(System.in);
	
		do{
		System.out.print(">");
		UserInput = input.nextLine();
		
		if(UserInput.equals("exit")){
			exit = true;
			break;
		}else{
			String[] calcul = UserInput.split(" ");
			
			if(changed == true && calcul[0].length() > 1){
				do{
				System.out.println("Error: input must be operand <space> operator <space> operand, or operator <space> operand");
				System.out.print(">");
				UserInput = input.nextLine();
				calcul = UserInput.split(" ");
				}while (calcul[0].length() > 1);
			}else if (calcul.length != 3 && exit == false && changed == false){
				do{
				System.out.println("Error: input must be operand <space> operator <space> operand, or operator <space> operand");
				System.out.print(">");
				UserInput = input.nextLine();
				if(UserInput.equals("exit")){
					exit = true;
					break;
				}
				
				calcul = UserInput.split(" ");
				
				}while (calcul.length != 3 && exit == false);
			}
			if(calcul.length < 3){
				x = 1;
			}else{
				x = 2;
			}
			if (calcul[0].indexOf(".") > 0){
				numberOneType = true;
				
			} else{
				numberOneType = false;
			}
			if (calcul[x].indexOf(".") > 0){
				numberTwoType = true;
			} else{
				numberTwoType = false;
			}
			if(changed == true && calcul.length == 2){
				first = result;
			}else{
			first = Double.parseDouble(calcul[0]);
			}
			second = Double.parseDouble(calcul[x]);
			if (calcul[1].equals("+") || calcul[0].equals("+")){
				act.Action(first, second);
				result = act.adding();				
				System.out.println(result);
				changed = true;
			}else if (calcul[1].equals("-") || calcul[0].equals("-")){
				act.Action(first, second);
				result = act.subtract();
				System.out.println(result);
				changed = true;
			}else if (calcul[1].equals("*") || calcul[0].equals("*")){
				act.Action(first, second);
				result = act.multiply();
				System.out.println(result);
				changed = true;
			}else if (calcul[1].equals("/") || calcul[0].equals("/")){
				if(calcul[1].equals("0") || calcul[2].equals("0")){
					System.out.println("Math error, we cannot divide by 0");
				}else{
				act.Action(first, second);
				result = act.divide();
				System.out.println(result);
				changed = true;
				}
			}else{ 
				System.out.println("Error: input must be operand <space> operator <space> operand, or operator <space> operand");
			}
		}
		exit = false;
		}while(!UserInput.equals("exit"));
	}
	

}
