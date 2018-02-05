package earthquake;



import java.util.Scanner;

public class EarthquakeApp {

	private EarthquakeRepository repository;

	public EarthquakeApp(){
		repository = new EarthquakeRepository("all_month.csv");
		
		Earthquake[] earthquakes = repository.getEarthquakes();
		System.out.println("There are " + earthquakes.length + 
				" earthquakes found in the " + repository.getFileName());
		
		Earthquake e = repository.getMaxMagnitudeEarthquake();
		System.out.println("\nHighest Magnitude Earthquake:");
		
		e.print();
		
		start();
	}

	private void start() {
		Scanner console = new Scanner(System.in);
		String choice = ""; 	
		do{
			EarthquakeSearchCriteria criteria = getUserCriteria();
			int count = repository.printEarthquakes(criteria);
			//print using print class in earthquakerepository?
			
			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");	
			System.out.println(count + " results found...");
			
			System.out.print("Do you want to continue (yes/no):");
			choice = console.next();
			choice = choice.toLowerCase(); 
		}while(!choice.equals("no"));
		
		System.out.println("Thanks");		
	}

	private EarthquakeSearchCriteria getUserCriteria() {
		Scanner console = new Scanner(System.in);
		EarthquakeSearchCriteria criteria = new EarthquakeSearchCriteria();
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.print("Date [yyyy-mm-dd][from]:");
		criteria.setDateFrom(console.next());
		System.out.print("Date [yyyy-mm-dd][to]:");
		criteria.setDateTo(console.next());
		System.out.print("State:");
		criteria.setState(console.next());
		System.out.print("Magnitude [min]:");
		criteria.setMagnitude(console.nextDouble());
		return criteria;
	}

	public static void main(String[] args) {
		EarthquakeApp app = new EarthquakeApp();
	}
}
