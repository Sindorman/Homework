import java.util.*;
import java.io.*;

public class TwoWords {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner dict = new Scanner(new File("dictionary.txt"));
		System.out.println("Please input two words:");
		Scanner input = new Scanner(System.in);
		String first = input.next().toLowerCase();
		String target = input.next().toLowerCase();
		List<String> words = new ArrayList<String>();
		while (dict.hasNextLine()){
			words.add(dict.nextLine());
		}
		//System.out.println(words.size());
		System.out.println("There are: " + Math.abs(words.indexOf(target) - words.indexOf(first)) + " words between " + first + " and " + target);
	}

}
