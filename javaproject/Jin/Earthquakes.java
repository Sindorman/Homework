package assignment5;
import java.util.*;
import java.io.*;

/*
 * Author: Jinjoo Son
 * Date: 11/20/2016
 * Description:This program searches a all_month.csv file, according to the value provided by the user. 
 * 			   It will display the search results, and will 
 * 			   Store this in a new file. 
 * 
 */

// use continue-if

public class Earthquakes {

	public static void main(String[] args) {
		
		// imports the data, and prepares for reading the data. 
		File file = new File("all_month.csv");
		int length = 0;
		try {
			Scanner sc = new Scanner(file);
			length = getLength(sc);
			System.out.println(length);
	
		// creates array for the input data. 
		String[] date = new String[length];
		String[] state = new String[length];
		double[] magnitude = new double[length];
		
		// inputs the values into the arrays. 
		sc = new Scanner(file);
		sc.nextLine();
		int index = 0; 
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			Scanner lineScanner = new Scanner(line);
			String statestring = " "; 		// this is for extracting statename only. 
			String magnitudevalue = "0.0"; 	// this is for storing the string value to double value. 
			lineScanner.useDelimiter(",");
			date[index] = lineScanner.next();
			lineScanner.next(); // how to jump more easily? 
			lineScanner.next();
			lineScanner.next();
			try{
			magnitudevalue = lineScanner.next();
				if (magnitudevalue.equals(null)) {    // nullpointerexception? 
					magnitudevalue = "0.0"; 
				} else {
					magnitude[index] = Double.parseDouble(magnitudevalue);
				}
			lineScanner.next();
			} catch (NumberFormatException e) {
				magnitude[index] = 0.0;
			}

			/*while(!(lineScanner.hasNext())){ 	// if there are no magnitude value in the file 
				lineScanner.next();
				continue;
			}
			lineScanner.next();
			*/
			if(!(lineScanner.hasNext())){		// for NoSuchElementException 
				
				continue;
			} else{
				statestring = lineScanner.next();
				state[index] = statestring.substring(1,statestring.length()-1);
			}
			index++;
			lineScanner.close(); 
		}
		System.out.println(date[4]);
		System.out.println(magnitude[4]);
		System.out.println(state[4]);
		
	
		// this part interprets the user's decision to continue or not. 
		toRunOrNotToRun(length, date, state, magnitude);
		sc.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!!");
		}
	}
	
	// this part interprets the user's decision to continue or not. 
	public static void toRunOrNotToRun(int length, String[] date, String[] state, double[] magnitude) {
		
		// this is the program entry display 
		System.out.println("===========Earthquake Search Application===========");
				
		String answer = "0";		
		do {
			Scanner console = new Scanner(System.in);
			getInput(length, date, state, magnitude, console);
			
			answer=validator(console);
			//console.close();			
		} while (!(answer.equals("no") && !(answer.equals("No") && !(answer.equals("n")))));
		
		// if the answer is no; 
		System.out.println("Thanks!");
		System.out.println("Be Earthquake Prepared….");
		
		
	}
	
	// displays the console, prompts input, and prints out the search result. 
	public static void getInput(int length, String[] date, String[] state, double[] magnitude, Scanner input) {			
		System.out.println("===================================================");
		try {
			Scanner console = new Scanner(System.in); 
			System.out.print("Date [yyyy-mm-dd][from]:");
			String fromdate = console.next();
			//validator(fromdate, console); 
			System.out.print("Date [yyyy-mm-dd][ to ]:");
			String todate = console.next();
			System.out.print("State:");
			String statename = console.next();
			statename = statename.substring(0,1).toUpperCase() + statename.substring(1).toLowerCase();
			System.out.print("Magnitude [min]");
			double minmagnitude = console.nextDouble(); 
	
			searchArray(fromdate, todate, statename, minmagnitude, magnitude, date, state, length);
			console.close();
		} catch (InputMismatchException e) {
			System.out.println("Please enter in valid format");
		}
		//console.close();
	}
	
	public static String validator(Scanner console){
		//System.out.print("Do you want to continue (yes/no): ");
		String answer = "";	
		do {		
			System.out.println("Not a valid entry, please enter in correct wording");
			System.out.print("Do you want to continue (yes/no): ");
			answer = console.next();
		}while((answer.equals("yes") && answer.equals("no")));
			
		
		return answer; 
	}
	
	// searches the array
	public static void searchArray(String fromdate, String todate, String statename, double minmagnitude, double[] magnitude, String[] date, String[] state, int length) {
		int counter = 0;	
		System.out.println(date[4] + statename);
		
		try{
			String result = "";
		for(int i=0;i<length;i++){ 
			if (date[i].substring(0, 10).compareTo(todate)<=0
				&& date[i].substring(0, 10).compareTo(fromdate)>=0 
				&& state[i].equals(statename) 
				&& (magnitude[i])>=minmagnitude){ 
			result = (date[i] + ">>" + "Magnitude:" + magnitude[i]); 
			System.out.println(result); 
			counter++;
		
			}
			
		}
		} catch (NullPointerException e) { // why??? 
		} 
		if (!(counter == 0)){
			System.out.println("---------------------------------------");
			System.out.println("Result found: " + counter);
		} else if (counter == 0) {
			System.out.println("No results found");
		}
		
	}
	
	// calculates the length (the number of lines in the file
	public static int getLength(Scanner sc){
		int counter = 0; 
		sc.nextLine();
		while(sc.hasNextLine()){
			sc.nextLine();
			counter++;
		}
		
		return counter; 
	}

}
