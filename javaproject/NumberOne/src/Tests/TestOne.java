package Tests;
import java.io.*;
import java.util.*;

public class TestOne {
	public static void main(String[] args)
		throws FileNotFoundException{
			Scanner console = new Scanner(System.in);
			PrintStream output = new PrintStream(new File("removed.txt"));
			PrintStream useless = new PrintStream(new File("useless.txt"));
			Scanner input = getInput(console);
			while (input.hasNextLine()){
				String text = input.nextLine();
				Strip(text, output, useless);
				
	}
	
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
		public static void Strip(String text, PrintStream output, PrintStream useless){
			String Text;
			Scanner data = new Scanner(text);
			while(data.hasNext()){
				Text = data.next();
				
					if(Text.equals("<")){
						do{
						useless.print(data.next());
						}while(!text.equals(">"));
					}
				
				output.print(data.next());
				
				
			}
			output.println();
			
			
		}
}
