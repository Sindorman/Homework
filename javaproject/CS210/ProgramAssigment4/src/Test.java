import java.util.*;
public class Test {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String test = input.nextLine();
		String[] TestString = test.split("\\.");
		for(int x = 0; x < TestString.length; x ++){
			System.out.print(TestString[x]);
		}
	}
	
}
