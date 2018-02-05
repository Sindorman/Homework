import java.util.*;
public class GCD {
	public static void main(String[] args){
		Scanner num = new Scanner(System.in);
		int first = num.nextInt();
		int second = num.nextInt();		
		int divide = gcd(first,second);
		System.out.print("Ration is: " + first/divide + "/" + second/ divide );
			
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
