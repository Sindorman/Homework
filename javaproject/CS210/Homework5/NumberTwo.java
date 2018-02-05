import java.util.*;

public class NumberTwo {
	public static void main(String[] args){
		Scanner num = new Scanner(System.in);
		int first = num.nextInt();
		int second = num.nextInt();
		System.out.print("GCD of: " + gcd(first,second));
		
	}	
	public static int gcd(int a, int b) {
	    if (a == 0)
	        return b;

	    while (b != 0) {
	        if (a > b)
	            a = a - b;
	        else
	            b = b - a;
	    }

	    return a;
	}
}
