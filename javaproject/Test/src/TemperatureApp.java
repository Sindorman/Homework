import java.util.Scanner;
public class TemperatureApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double F, C;
		
		Scanner inp = new Scanner(System.in);
		System.out.print("Please enter a Farenheit Temperature>>");
		F = inp.nextDouble(); 
		inp.close();
		
		C = (F-32)*5/9;
		
		System.out.format("%.2f F", F);
		System.out.print(" = ");
		System.out.format("%.2f C", C);
	}

}
