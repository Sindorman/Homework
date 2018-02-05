import java.util.*;

public class NumberOne
{
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		System.out.println("Write range:");
		int x = console.nextInt();
		int y = console.nextInt();
		if (x < y){
			for( int h = 1; h < y; h++){
			System.out.print(x + " ");
			x++;
			}
			
		} else if( x > y){
			for ( int n = x; x >= y; n-- ){
				System.out.print(x + " ");
				x--;
			}
		} else {
			System.out.print(x);
		}
	}
	
}