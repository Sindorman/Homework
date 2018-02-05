import java.util.*;
import java.io.*;

public class TestTwo {
	public static void main(String [] args) throws FileNotFoundException{
		String text = "";
		String MainText = Spaces(text);
		System.out.print(MainText);
		
		
	}
	public static String Spaces(String text) throws FileNotFoundException{
		File f = new File("test.txt");
		Scanner data = new Scanner(f);
		/* while(data.hasNextLine()){
			text += data.nextLine() + "\n";
		}*/
		if(data.hasNext()){
			text += data.next();
			while(data.hasNext()){
				text += " " + data.next();
			}
		}
		text += text + "\n";		
		
		return text;
	}

}
