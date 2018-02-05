import java.io.*;
import java.util.*;

public class NumberTwo {
	
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner console = new Scanner(System.in);
        Scanner input = getInput(console);
		stripHtmlTags(input);
	}

	public static void stripHtmlTags(Scanner input) throws FileNotFoundException {
		String text = "";
		while (input.hasNextLine()) {
			text += input.nextLine() + "\n";
		}
		int Tag = text.indexOf("<");
		while (Tag >= 0) {
			String start = text.substring(0, Tag);
			text = text.substring(Tag, text.length());
			int TagEnd = text.indexOf(">");
			text = start + text.substring(TagEnd + 1, text.length());
			Tag = text.indexOf("<");
		}
		System.out.print(text);
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
