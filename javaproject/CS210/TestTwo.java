import java.util.*;
import java.io.*;
public class TestTwo {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		Scanner input = getInput(console);
		String foo = "";
		while (input.hasNextLine()) {
			foo += input.nextLine() + "\n";
		}
		
		final int INITIAL=0; 
		final int AFTER_COMMA=1; 
		int state = INITIAL; 
		for (char c : foo.toCharArray()) { 
			if (state == INITIAL){ 
				if (c == ',') { 
				state = AFTER_COMMA; 
				} else {
					System.out.print(c); 
				} 
			} else if (state == AFTER_COMMA){ 
				state = INITIAL; 
			} 
		}
		System.out.println();
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
	
}