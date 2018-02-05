import java.io.*;
import java.util.*;

/**
 *
 * @author Richard Duncan
 * Programming Project 2 - CompareFiles
 */
public class Homework6_Exercise3 {

	public static Scanner openScannerOnFile(Scanner userInput, String prompt) {
		Scanner input = null;
		do {
			System.out.print(prompt);
			String filename = userInput.nextLine();
			try {
				input = new Scanner(new File(filename));
			} catch (FileNotFoundException e) {
				System.out.println("Cannot open " + filename);
			}
		} while (input == null);
		return input;			
	}
	
    public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		Scanner input1 = openScannerOnFile(userInput, "Enter a first file name: ");
		Scanner input2 = openScannerOnFile(userInput, "Enter a second file name: ");
	
		int line = 1;
		boolean previousDiffFound = false;
		while (input1.hasNextLine() || input2.hasNextLine()) {
			String line1 = input1.hasNextLine() ? input1.nextLine () : null;
			String line2 = input2.hasNextLine() ? input2.nextLine () : null;

			// we know that at least one of them should be non-null
			boolean diff = ((line1 == null) != (line2 == null)) || !line1.equals(line2);
			
			if (diff) {
				// either print the header or a blankline between differences
				System.out.println(previousDiffFound ? "" : "Differences found:");
				previousDiffFound = true;
			}
			
			if (diff && line1 != null) {
				System.out.println("< " + line1);
			}
			
			if (diff && line2 != null) {
				System.out.println("> " + line2);
			}
			
			line++;			
		}
    }
}
