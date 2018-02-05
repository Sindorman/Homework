import java.util.*;
import java.awt.*;
public class SecondTry {
	public static void main(String[] args){			
		Action act = new Action();
		Scanner input = new Scanner(System.in);
		double first = 0;
		double second = 0;		
		double result = 0;		
		String FirstNum = "";
		String SecondNum = "";;
		int times = 1;		
		String UserInput = "";
		System.out.println("Please write an input or \"exit\". It must be operand <space> operator <space> operand, or operator <space> operand");
		do{
			String [] numbers;
			System.out.print(">");
			UserInput = input.nextLine();
			numbers = UserInput.split(" ");
			try{
				if(IsOperator(numbers[0]) == true){
					FirstNum = "" + result;
					first = result;				
					try{
						second = Double.parseDouble(numbers[1]);
						SecondNum = numbers[1];
						if(numbers[1].indexOf("e") >= 0){
							System.out.println("Error: exponent notations are not allowed.");
							continue;
						}
					}catch (NumberFormatException e){
						System.out.println("Error: input must be operand <space> operator <space> operand, or operator <space> operand");
						continue;
					}
				}else if(IsOperator(numbers[1]) == true){
					try{
						if(numbers[0].indexOf("e") >= 0){
							System.out.println("Error: exponent notations are not allowed.");
							continue;
						}
						first = Double.parseDouble(numbers[0]);
						FirstNum = numbers[0];
					}catch (NumberFormatException e){
						System.out.println("Error: input must be operand <space> operator <space> operand, or operator <space> operand");
						continue;
					}
					try{
						if(numbers[2].indexOf("e") >= 0){
							System.out.println("Error: exponent notations are not allowed.");
							continue;
						}
						second = Double.parseDouble(numbers[2]);
						SecondNum = numbers[2];
					}catch (NumberFormatException e){
						System.out.println("Error: input must be operand <space> operator <space> operand, or operator <space> operand");
						continue;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Error: input must be operand <space> operator <space> operand, or operator <space> operand");
				continue;
			}
			if (numbers[1].equals("+") || numbers[0].equals("+")){
				RationalNumber rat = new RationalNumber();
				RationalNumber two = new RationalNumber();
				RationalNumber one = new RationalNumber();
				one.RationalNumber(FirstNum);
				two.RationalNumber(SecondNum);
				act.Action(first, second);
				result = act.adding();
				String output = "" + result;				
				String[] text = output.split("\\.");
				if(text[1].equals("0")){					
					System.out.println(text[0]);
					rat.RationalNumberAddition(one, two, times);
				}else{
				System.out.println(result + "(" + rat.RationalNumberAddition(one, two, times) + ")");
				}
			}else if (numbers[1].equals("-") || numbers[0].equals("-")){
				RationalNumber rat = new RationalNumber();
				RationalNumber two = new RationalNumber();
				RationalNumber one = new RationalNumber();
				one.RationalNumber(FirstNum);
				two.RationalNumber(SecondNum);
				act.Action(first, second);
				result = act.subtract();
				String output = "" + result;				
				String[] text = output.split("\\.");
				if(text[1].equals("0")){
					System.out.println(text[0]);
					rat.RationalNumberSubtraction(one, two, times);
				}else{
					System.out.println(result + "(" + rat.RationalNumberSubtraction(one, two, times) + ")");
				}
			}else if (numbers[1].equals("*") || numbers[0].equals("*")){
				RationalNumber rat = new RationalNumber();
				RationalNumber two = new RationalNumber();
				RationalNumber one = new RationalNumber();
				one.RationalNumber(FirstNum);
				two.RationalNumber(SecondNum);
				act.Action(first, second);
				result = act.multiply();
				String output = "" + result;				
				String[] text = output.split("\\.");
				if(text[1].equals("0")){
					System.out.println(text[0]);
					rat.RationalNumberMultiply(one, two, times);
				}else{
				System.out.println(result + "(" + rat.RationalNumberMultiply(one, two, times) + ")");
				}
				
			}else if (numbers[1].equals("/") || numbers[0].equals("/")){
				if (second == 0){
					System.out.println("Math error, we cannot divide by 0");
					continue;
				}
				RationalNumber rat = new RationalNumber();
				RationalNumber two = new RationalNumber();
				RationalNumber one = new RationalNumber();
				one.RationalNumber(FirstNum);
				two.RationalNumber(SecondNum);
				act.Action(first, second);				
				result = act.divide();
				String output = "" + result;				
				String[] text = output.split("\\.");
				if(text[1].equals("0")){
					System.out.println(text[0]);
					rat.RationalNumberDivide(one, two, times);
				}else{
				System.out.println(result + "(" + rat.RationalNumberDivide(one, two, times) + ")");				
				}
			}
			times++;
			if(times > 9){
				times = 2;
			}
		}while (!UserInput.equals("exit"));
	}
	
	
	
	public static boolean IsOperator(String text){
		if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")){
			return true;
			
		}else {
			return false;
		}
	
	}
	
	
}
