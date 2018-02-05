import java.util.*;
public class RandomWeight {

	public static final double RATE = 0.25;
	public static boolean weightedFlip(Random r) {
		double rand = r.nextDouble();
		return (rand < RATE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final int COUNT=1000;
		Random r = new Random();
		double countTrue = 0;
		
		for (int i = 0; i < COUNT; i++) {
			boolean coinFlip = weightedFlip(r);
			if (coinFlip) countTrue++;
			//System.out.print(coinFlip + " ");
		}
		System.out.printf("\nTRUE RATE = %f\n", countTrue / (double)COUNT);
	}
}