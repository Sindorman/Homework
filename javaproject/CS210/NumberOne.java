import java.io.*;
import java.util.*;

public class NumberOne {
	public static void main(String[] args)
			throws FileNotFoundException{
				Scanner console = new Scanner(System.in);
				Scanner input = getInput(console);
				double count = 0;
				double counteven = 0;
				int sum = 0;
				double precentage= 0;
				while(input.hasNextInt()){
					int number = input.nextInt();
					count++;
					sum += number;
					if(number%2 == 0){
						counteven++;
						
					}
					precentage = (counteven/count*100);
				}
				System.out.printf("%.0f numbers, sum= %d%n", count, sum);
 				System.out.printf("%.0f evens (%.2f%%)", counteven, precentage);
			}
			public static Scanner getInput( Scanner console)
				throws FileNotFoundException{
				System.out.println("input file name?");
				File f = new File(console.nextLine());
				while(!f.canRead()){
					System.out.println("File not found. Try again");
					System.out.println("input file name?");
					f = new File(console.nextLine());
				}
				return new Scanner(f);
			}
}

