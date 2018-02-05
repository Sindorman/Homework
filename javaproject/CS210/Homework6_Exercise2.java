import java.io.*;
import java.util.*;

/**
 *
 * @author Richard Duncan
 * Chapter 6, Exercise 12 - stripHTMLTags
 */
public class Homework6_Exercise2 {

    enum ParserState
    {
        DEFAULT,
        IN_TAG
    };

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ParserState state = ParserState.DEFAULT;
       
        Scanner input = new Scanner(new File(args[0]));
        while (input.hasNextLine())
        {
			for (char c : input.nextLine().toCharArray())
			{
				if (state == ParserState.DEFAULT)
				{
					if (c == '<')
					{
						state = ParserState.IN_TAG;
					}
					else
					{
						System.out.print(c);
					}
				}
				else if (state == ParserState.IN_TAG)
				{
					if (c == '>')
					{
						state = ParserState.DEFAULT;
					}
				}
			}
            System.out.println();
        }
    }
}
