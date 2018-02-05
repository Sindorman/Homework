import java.util.*;

public class NumberThree {
	public static void main(String[] args){
		
	Random rand = new Random();
	RandomWalk(rand);
	}
	public static void RandomWalk(Random rand){
		int position = 0;
		System.out.println("Position: " + position);
		do {
		int walk = 1;
		int OddEven = rand.nextInt(2);
		if (OddEven == 1){
			walk = -walk;
		}
		position = position + walk;	
		System.out.println("Position: " + position);
		}while(position != 3 && position != -3);
	}
	
}
