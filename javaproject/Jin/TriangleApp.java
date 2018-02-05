import java.util.Scanner;
public class TriangleApp {

	public static void main(String[] args) {
		// a, b, c, = the values of the triangle's sides 
		// The program will compute the length of the side "b" by utilizing Pythagoras's Theory
		// a^2 - c^2 = b^2
		
		double a, b, c;  // as = a squared, cs = c squared 
		
		Scanner inp = new Scanner(System.in);
		System.out.print("Please Enter the length for side a>>");
		a = inp.nextDouble();
		System.out.print("Please Enter the length for side c>>");
		c = inp.nextDouble();
		inp.close();
		
		b = Math.sqrt((a*a) - (c*c)); 
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
		// Q: is it better to put things in integer form? I also wanted to enable decimal calculations
	}

}
