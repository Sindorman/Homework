import java.util.*;

public class NumberFive{
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		System.out.print("Insert a frist latitude in Degrees: ");
		double Latone = Math.toRadians(console.nextDouble());
		System.out.print("Insert a frist longitude in Degrees: ");
		double Longone = Math.toRadians(console.nextDouble());
		System.out.print("Insert a second latitude in Degrees: ");
		double Lattwo = Math.toRadians(console.nextDouble());
		System.out.print("Insert a second longitude in Degrees: ");
		double Longtwo = Math.toRadians(console.nextDouble());
		
		double delta = Math.acos((Math.sin(Latone))*(Math.sin(Lattwo))+Math.cos(Latone)*Math.cos(Lattwo)*Math.cos(Longtwo-Longone));
		double rdelta = Round(delta*6372.795);
		System.out.print("Distance between two Cities is: " + rdelta + " km");
	}
	public static double Round( double n){
		return Math.round(n * 1000.0)/ 1000.0;
	}
	
}