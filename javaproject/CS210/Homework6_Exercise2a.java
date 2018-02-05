import java.io.*;
import java.util.*;

public class Homework6_Exercise2a {
	
    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        Scanner input = new Scanner(new File(args[0]));
		stripHtmlTags(input);
	}

	public static void stripHtmlTags(Scanner input) throws FileNotFoundException {
		String text = "";
		while (input.hasNextLine()) {
			text += input.nextLine() + "\n";
		}
		int indexOfTag = text.indexOf("<");
		while (indexOfTag >= 0) {
			String start = text.substring(0, indexOfTag);
			text = text.substring(indexOfTag, text.length());
			int indexOfTagEnd = text.indexOf(">");
			text = start + text.substring(indexOfTagEnd + 1, text.length());
			indexOfTag = text.indexOf("<");
		}
		System.out.print(text);
	}
}