import java.io.*;
import java.util.*;

/*
 * Simple word processor to eliminate excess spaces between words
 */
public class WPSimple {

	public static void main(String[] args) throws IOException {
		// TODO - update this to do error checking, not throw exceptions out of this function
		Scanner input = new Scanner(new File(args[0])); // first argument is input filename
		PrintStream output = new PrintStream(new File(args[1])); // second arg is output filename
		
		stripSpaces(input, output);
	}

	static void stripSpacesWithLineScanner(Scanner input, PrintStream output) {
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Scanner lineScanner = new Scanner(line);
			while (lineScanner.hasNext()) {
				output.print(lineScanner.next() + " ");
			}
			output.println();
		} 		
	}

	static void stripSpaces(Scanner input, PrintStream output) {

		// read the entire file into the String variable text
		String text = "";
		while (input.hasNextLine()) {
			text += input.nextLine() + "\n";
		}
		
		final int State_INIT = 0;
		final int State_SEEN_SPACE = 1;
		
		int state = State_INIT;
		
		for (char c : text.toCharArray()) {		
			if (state == State_INIT) {
				if (c == ' ') {
					output.print(c);
					state = State_SEEN_SPACE;
				} else {
					output.print(c);					
				}
			} else if (state == State_SEEN_SPACE) {
				if (c != ' ') {
					output.print(c);
					state = State_INIT;
				}				
			}			
		}
	}

	static void stripSpacesAndras(Scanner input, PrintStream output) {

		// read the entire file into the String variable text
		String text = "";
		while (input.hasNextLine()) {
			text += input.nextLine() + "\n";
		}

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			
			if (i != text.length() - 1) {
				char nextChar = text.charAt(i+1);
				
				if (!(c == ' ' && nextChar == ' ')) 
					output.print(c);			
			}
			else {
				output.print(c);
			}			
		}
	}
	
	static void stripSpacesButDropNewlines(Scanner input, PrintStream output) {
/*		while (input.hasNextLine()) {
			String line = 
			System.out.println(input);
		} */
		
		while (input.hasNext()) {
			output.print(input.next() + " ");
		}
		output.println();
	}


}