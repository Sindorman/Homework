import java.io.*;
import java.util.*;
public class ProgramAssigment3_2 {
	
	public static void main(String[] args) throws FileNotFoundException{
		String MainText = "";
		Scanner console = new Scanner(System.in);
		String text = "";
		String data = "";
		File h = new File("test.txt");
		Scanner input = new Scanner(h);
		//System.out.print("Please write name of output file like \"Testoutput.txt\": ");
		//PrintStream output = new PrintStream(new File(console.nextLine()));
			
			//MainText = Spaces(text, console, h);
			MainText = CommaFix(MainText, h, text, data);
			
		System.out.print(MainText);
	}
	public static Scanner getInput( Scanner console)
			throws FileNotFoundException{
			System.out.println("input file name? (like \"Test.txt\"):");
			File f = new File(console.nextLine());
			while(!f.canRead()){
				System.out.println("File not found. Try again");
				System.out.println("input file name? (like \"Test.txt\"):");
				f = new File(console.nextLine());
			}
			return new Scanner(f);
		}
	public static String Spaces(String text, Scanner console, File h) throws FileNotFoundException{
		//Scanner data = getInput(console);
		Scanner data = new Scanner(h);
		if(data.hasNextLine()){
			
				while(data.hasNext()){
					text += data.next()+ " ";
					
				}
				text += "\n";
		}
		
		
		
		
		return text;
	}
	public static String CommaFix(String MainText, File h, String text, String data) throws FileNotFoundException {
		Scanner comma = new Scanner(h);
		String Truetext = "";
		while(comma.hasNext()){
			text += comma.next() + " ";
		    String Truecomma = ",";
		    String Truedot = ".";
		    Truetext = text +  text.replaceAll(Truecomma, Truecomma);
		    Truetext = text +  text.replaceAll(Truedot, Truedot);
		    
		}
		 return Truetext;
	}
}

