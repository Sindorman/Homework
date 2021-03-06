import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;


public class EarthquakeRepository {
	private Earthquake[] earthquakes;
	
	private String fileName; 
	public EarthquakeRepository(String fileName){
		this.fileName = fileName; 
		File file = new File(fileName);
	int length = 0;
		try {
			Scanner sc = new Scanner(file);
			length = setLength(sc);
			earthquakes = new Earthquake[length];
			
			sc = new Scanner(file);
			loadEarthquakes(sc); 
			
		} catch (FileNotFoundException e) {			  // for FileNotFoundException  
		System.out.println("File Not Found!!");
		}
	}
	
	// this will figure out the total length of the array and the input file. 
	private int setLength(Scanner sc){
		int length = 0; 							 // for counting the length of the file 
		sc.nextLine();
		while(sc.hasNextLine()){
			sc.nextLine();
			length++;
		}
		
		return length; 
	}
	
	public int length(int length){
		return length; 
	}
	
	
	// this will figure out the maximum magnitude earthquake 
	public Earthquake getMaxMagnitudeEarthquake() {
		double max = 0;					
		int arraynumber = 0;
		try {
		for(int i = 0 ; i<earthquakes.length-1;i++){
			if(earthquakes[i].getMagnitude() > max){ // first it will compare with 0 max value, and
				max = earthquakes[i].getMagnitude(); // max value will be continuously updated as the larger value(than array value)
				earthquakes[i].print();
				arraynumber = i;
			}
			
			
			
		}
		earthquakes[0].print();
		} catch (NullPointerException e) {			 // handling NullPointerException 
		}
		return earthquakes[arraynumber];			 // returns the earthquake object in stored array #i. 
		
	}
	
	
	// this will load the empty arrays with earthquake objects that stores detailed information
	private void loadEarthquakes(Scanner sc) {
		sc.nextLine();
		int i = 0;
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			Scanner lineScanner = new Scanner(line);
			String statename = ""; 
			String magnitudevalue ="0.0";
			lineScanner.useDelimiter(",");
			String date = lineScanner.next();
			String latitude = lineScanner.next();
			String longitude = lineScanner.next(); 
			String depth = lineScanner.next();
			double magnitude = 0.0;
			try {
			magnitudevalue = lineScanner.next();
				if (magnitudevalue.equals(null)) {	// for nullPointerException
					magnitudevalue = "0.0"; 
					magnitude = Double.parseDouble(magnitudevalue);
				} else {
					magnitude = Double.parseDouble(magnitudevalue);
				}
			lineScanner.next();
			} catch (NumberFormatException e) {
				magnitude = 0.0;
				
			}
				if(!(lineScanner.hasNext())){
					continue; 
				} else { 
					statename = lineScanner.next(); 
					statename = statename.substring(1, statename.length()-1);
			}
			String location = statename; 	
			Earthquake e = new Earthquake(date, latitude, longitude, depth, magnitude, location);
			earthquakes[i] = e; 
			i++;
		}
		Earthquake[] temp = new Earthquake[earthquakes.length];
		int nullcount = 0;
		for(int x = 0, y = 0; x < earthquakes.length; x++) {
			if(earthquakes[x] != null ) {
				temp[y] = earthquakes[x];
				y++;
			}else if (earthquakes[x] == null) {
				nullcount++;
			}
		}
		earthquakes = new Earthquake[earthquakes.length-nullcount];
		for (int x = 0; x < earthquakes.length; x++) {
			earthquakes[x] = temp[x];
		}
		
	}
	
	// this gets the filename 
	public String getFileName() {
		// also need to set setters too? 
		return fileName;
	}
	
	
	
	public Earthquake[] getEarthquakes(){
		return earthquakes; 
	}

	// this will search the arrays according to the given criteria. 
	public int printEarthquakes(EarthquakeSearchCriteria criteria) {
		// also print out the result. 
	//searchArray(fromdate, todate, statename, minmagnitude, magnitude, date, state, length, counter);
		int resultcount = 0;
		String result = "";
		try{
			
			for(int i=0;i<earthquakes.length;i++){ 
				if (earthquakes[i].getDatetime().compareTo(EarthquakeSearchCriteria.getDateTo())<=0
					&& earthquakes[i].getDatetime().compareTo(EarthquakeSearchCriteria.getDateFrom())>=0 
					&& earthquakes[i].getLocation().equals(EarthquakeSearchCriteria.getState()) 
					&& (earthquakes[i]).getMagnitude()>=EarthquakeSearchCriteria.getMagnitude()){ 
						earthquakes[i].print();
						resultcount++;
					
				}
			}
		} catch (NullPointerException e) { 
		} 
		return resultcount;
	}
}

