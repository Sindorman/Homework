
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


/*
 * Author: Fatma Cemile Serce
 * Date/Place: November, 2016 / BC
 * Description: The program allows user to perform search operation on last 30-day earthquake data provided by
 * https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.csv
 * The program shall do the followings:
 * - reads data from .csv file 
 * - load datetime, magnitude and state data into  arrays
 * - ask user for search criteria (date interval, minumum magnitude value and state) 
 * - filter the data using the criteria
 * - displays the date-time and magnitude of each earthquake events matching with the search criteria
 * - displays the total number of records/events found after the search operation\
 * - writes the output to a text file
 * - user is allowed to perform search as many time s/he wants 
 * - the program checks the validity of the inputs: date,magnitude,state
 * - the program prints "File not found" message if there is no file.
 */
public class EarthquakeManager {
	
	//this method is the starting point of the application
	public static void start() {
		
		String fileName = ("all_month.csv");
		Scanner sc = createScanner(fileNam ce);
		
		int count = countLines(sc);//count the number of lines(records) in the given file
		
		//if there is no record in the file, produces the warning message and stops the application.
		if(count == 0){
			System.out.println("Input file is empty");
			return;
		}
		printTitle(); //prints the title of the application
		
		String[] dateTimeArray = new String[count]; //array will store datatime of all records in the file
		double[] magnitudeArray = new double[count];//array will store magnitude of all records in the file
		String[] stateArray = new String[count];//array will store location of all records in the file

		//read from the .csv file and load data into arrays
		//need to pass sc as parameter, because it is the source of input
		sc = createScanner(fileName); //need to reset the cursor to the beginning of the file
		loadData(sc, dateTimeArray, magnitudeArray,stateArray);
		
		//arrays are loaded with the data, ready for search operation
		//ask user for search criteria and perform the search operation
		filterData(dateTimeArray, magnitudeArray,stateArray);
	
	}

	//in order to read from .csv file, a scanner object is needed.
	//the scanner object is created if there is a file, and the cursor is set to the beginning of the file
	//if there is no file, then it gives a warning message and returns null
	public static Scanner createScanner(String fileName){
		File file = new File(fileName);
		Scanner sc = null;
		try {
			sc = new Scanner(file);//prepare scanner object to read from .csv file
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		return sc;
	}
	
	public static boolean isMagnitudeValid(String magnitude) {
		//converts a string into double
		//if there is no double value in the string, then it throws a NumberFormatException
		try{
			double mag = Double.parseDouble(magnitude);
		}catch(NumberFormatException e){
			return false;
		}
        return true;
    }
	
	public static boolean isDateValid(String date) {
        //check the length first, if it is different than 10, it is an invalid input
        if (date.length()!=10)
               return false;
        //tokenize the string using - as the delimiter
        //and check if the day/month/year are integers
        //and also check if there is - in between integers
        Scanner dateScanner = new Scanner(date);
        dateScanner.useDelimiter("-");
        try{
          int year = dateScanner.nextInt();
          int month = dateScanner.nextInt();
          int day = dateScanner.nextInt();
        }catch(InputMismatchException e){
               return false;
        }
       
        return true;
    }
	
	public static void filterData(String[] dateTimeArray, double[] magnitudeArray, String[] stateArray){
		String choice = ""; //keeps the user's choice for "yes" or "no"
		Scanner console = new Scanner(System.in); //prepare scanner object to read from Console
		int fileCount = 1; //counts the number of output files to be generated
		do{
			//user inputs: dateFrom, dateTo, state and magnitude
			System.out.println("=========================================================================");
			System.out.print("Date [yyyy-mm-dd][from]:");
			String userDateFrom = console.next();
			System.out.print("Date [yyyy-mm-dd][to]:");
			String userDateTo = console.next();
			System.out.print("State:");
			String userState = console.next();
			System.out.print("Magnitude [min]:");
			String userMagnitude = console.next();
			
			if ((!isDateValid(userDateFrom)) || (!isDateValid(userDateTo)) || (!isMagnitudeValid(userMagnitude))){
				System.out.println("Invalid input");
				continue; //ask user to re-enter search criteria
			}
			
			//prepare the PrintStream object to write to a file
			String fileName = "Earthquakes_"+userState+"_"+fileCount+".txt";//Earthquakes_Alaska_1.txt
			PrintStream out = null;
			try {
				out = new PrintStream(fileName);
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}

			int count=0; //counts the number of matching records
			//traverse the array to search for a match for the given criteria
			for (int i = 0; i < stateArray.length; i++) {
				if (dateTimeArray[i]!=null && stateArray[i]!= null){//since some records are ignored, there might be null values in the array
					String dateTime = dateTimeArray[i].substring(0, 10);//just need to compare the yyyy-mm-dd, so ignore the rest
					if(dateTime.compareTo(userDateFrom)>=0 && dateTime.compareTo(userDateTo)<=0 && stateArray[i].equals(userState)){
						double magnitude = Double.parseDouble(userMagnitude);//convert string to double;
						if(magnitudeArray[i]>=magnitude){
								System.out.println(dateTimeArray[i] + ">>Magnitude:" + magnitudeArray[i]);
								out.println(dateTimeArray[i] + ">>Magnitude:" + magnitudeArray[i]);//write to the output file
								count++;
						}
					}
				}
			}
			if(count==0){//if count=0 it means there is no match found
				System.out.println("No results found");
			}else{
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
				System.out.println("Results found: "+count);
				out.println("Results found: "+count);
			}

			fileCount++; //increment the fileCounter
			
			System.out.print("Do you want to continue (yes/no):");
			choice = console.next();
			choice = choice.toLowerCase(); //user might enter No, NO, nO. Just convert all characters into lowercase 
		}while(!choice.equals("no"));
		
		System.out.println("Thanks");//end of the application

	}

	
    public static void loadData(Scanner sc, String[] dateTimeArray, double[] magnitudeArray, String[] stateArray) {

		sc.nextLine();//skip the first line
		for (int i = 0; i < dateTimeArray.length; i++) {//each array has the same length
			//read line by line
			//sample line://2016-10-19T04:46:12.810Z,48.2106667,-122.6701667,27.37,2,"1km SE of Coupeville, Washington"
			String line = sc.nextLine(); 
		
			//it is a .csv file, meaning that each cell(value) is separated by comma
			//lineScanner tokenizes each line using "," as the delimiter
			Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");
			//process each token(value) and load into corresponding array
			dateTimeArray[i] = lineScanner.next(); //load datatime value into dateTimeArray
			lineScanner.nextDouble();//skip longtitude
			lineScanner.nextDouble();//skip latitude
			lineScanner.nextDouble();//skip depth
				
			//there are few records having no magnitude value
			//if there is no double value next, it means there is no magnitude
			//if this is the case, ignore the record, and continue with the next one.
			if (!lineScanner.hasNextDouble()){
				continue;
			}
			magnitudeArray[i]=lineScanner.nextDouble();//put magnitude value into magnitudeArray

			String placeStr = lineScanner.nextLine(); //read the whole place data into a String variable
			//sample: placeStr="1km SE of Coupeville, Washington"
			//need to tokenize the address to get state out of it	
			Scanner placeScanner = new Scanner(placeStr);
			placeScanner.useDelimiter(",");
			placeScanner.next();//ignore the first part of the address
			//there are few records having no state value
			//if there is no state, then ignore that record and continue with the next record
			if(!placeScanner.hasNext()){
				continue;
			}
			//stateStr will have the values like " CA""
			//we need to get rid of the first blank character and the last double
			//quotation mars
			String stateStr= placeScanner.next();		
			stateArray[i] = stateStr.substring(1, stateStr.length()-1);
		}

	}
    
	public static int countLines(Scanner scanner){
		int count = 0;//counter for the number of lines
		while(scanner.hasNextLine()){
			scanner.nextLine();
			count++;
		}
		count--;//decrement 1 because of the header line
		return count;
	}
    
	public static void printTitle() {
		System.out.println("======================Earthquake Search Application======================");
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		start();
	}

}