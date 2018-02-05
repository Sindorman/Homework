import java.io.*;
import java.util.Scanner;
public class Test {
	
	public static void main(String [] args)throws FileNotFoundException, IOException{
		Scanner console = new Scanner(System.in);
		File f = new File("test.txt");
		Scanner data = new Scanner(f);
		String text = "";

		while(data.hasNextLine()){
			text += data.next() + " ";

		}
		String MainText = "";
		//System.out.print("Please write name of output file like \"Testoutput.txt\": ");
        //PrintStream output = new PrintStream(new File(console.nextLine()));
		//Spaces(text, MainText, output);
        ComaFix(console, text);
		
	}
	public static Scanner getInput( Scanner console)
			throws FileNotFoundException{
			System.out.println("input file name? (like \"Test.txt\")");
			File f = new File(console.nextLine());
			while(!f.canRead()){
				System.out.println("File not found. Try again");
				System.out.println("input file name? (like \"Test.txt\")");
				f = new File(console.nextLine());
			}
			return new Scanner(f);
		}
	public static String Spaces(String text, String MainText, PrintStream output){
		Scanner data = new Scanner(text);
		if(data.hasNext()){
			text = data.next();
			while(data.hasNext()){
				output.print(" " + data.next());
			}
		}
		output.println();
		return text;
				
	}
	public static void ComaFix(Scanner console, String text) throws FileNotFoundException {
		Scanner coma = new Scanner(text);
		String main = "";
		while(coma.hasNextLine()){
			main +=coma.nextLine();
		}
		int comanumber = main.indexOf(" ,");
		if(comanumber >= 0){
			String start = main.substring(0, comanumber);
			main += start + ", " + main.substring(comanumber+2, main.length());
			
		}
		System.out.print(main);
	}
}
