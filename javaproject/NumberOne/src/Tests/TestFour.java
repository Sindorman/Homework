package Tests;

public class TestFour {
	public static void main(String[] args){
	printSomething();
	}
	public static void printSomething(){
		final int SIZE = 4;
		for(int i = -SIZE; i <= SIZE; i++){
			for(int j = -SIZE; j <= SIZE; j++){
				if(Math.abs(i) == Math.abs(j)){
					System.out.print("x");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
}
