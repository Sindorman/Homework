import java.util.*;
import java.io.*;
public class NumberThree {
	public static void main(String[] args) throws FileNotFoundException{
		String lineone = "";
		String linetwo = "";
		Scanner console = new Scanner(System.in);
		Scanner fileone = getInput(console);
		Scanner filetwo = getInput(console);
		while(fileone.hasNextLine()){
			lineone += fileone.nextLine() + "\n";
		}
		while(filetwo.hasNextLine()){
			linetwo +=filetwo.nextLine() + "\n";
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
}
