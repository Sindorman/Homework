import java.util.Scanner;
public class JavaWeightApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double weight, earth, venus, mars, jupiter, saturn, uranus, neptune;
		
		Scanner inp = new Scanner(System.in); 
		System.out.print("Please enter your weight only in numbers>>");
		weight = inp.nextDouble();
		inp.close();
		
		earth=weight*1.00;
		venus=weight*0.78;
		mars=weight*0.39;
		jupiter=weight*2.65; 
		saturn=weight*1.17;
		uranus=weight*1.05;
		neptune=weight*1.23;
		
		System.out.println();
		System.out.println("Your weight on Earth: " + earth);
		System.out.println("Your weight on Venus: " + venus);
		System.out.println("Your weight on Mars: " + mars);
		System.out.println("Your weight on Jupiter: " + jupiter);
		System.out.println("Your weight on Saturn: " + saturn);
		System.out.println("Your weight on Uranus: " + uranus);
		System.out.println("Your weight on Neptune: " + neptune);
		
	}

}
