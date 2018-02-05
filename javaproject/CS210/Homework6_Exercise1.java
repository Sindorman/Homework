import java.io.*;
import java.util.*;

/**
 *
 * @author Richard Duncan
 * Chapter 6, Exercise 2
 */
public class Homework6_Exercise1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
      
        Scanner input = new Scanner(new File(args[0]));
		int countNumbers = 0;
		int countEvenNumbers = 0;
		int sum = 0;
        while (input.hasNextInt())
        {
			int num = input.nextInt();
			countNumbers++;
			sum += num;
			if (num % 2 == 0)
			{
				countEvenNumbers++;
			}
        }
		System.out.println(countNumbers + " numbers, sum = " + sum);
		System.out.printf("%d evens (%.2f%s)", countEvenNumbers, 100.0 * (double)countEvenNumbers / countNumbers, "%");
		System.out.println();
    }
}
