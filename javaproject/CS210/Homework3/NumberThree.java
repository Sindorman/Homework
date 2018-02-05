import java.util.*;
public class NumberThree{
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		System.out.print("Write X1 coordinate: ");
		double xone = console.nextInt();
		System.out.print("Write y2 coordinate: ");
		double yone = console.nextInt();
		System.out.print("Write X2 coordinate: ");
		double xtwo = console.nextInt();
		System.out.print("Write Y2 coordinate: ");
		double ytwo = console.nextInt();		
		double c = ((xtwo-xone)*(xtwo-xone)+(ytwo-yone)*(ytwo-yone));
		double d = Math.sqrt(c);
		System.out.print(" Distance is: " + d);
	}
	
		 
		 
	
}
