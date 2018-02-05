package Homework7;

public class NumberTwo {
	public static void main(String[] args){
		int[] array = {1, -2, 4, -4, 9, -6, 16, -8, 25, -10};
		System.out.print(stdev(array));
		
		
	}
	public static double stdev(int[] array){
		 double deviation = Math.sqrt(Summation(array, Average(array))/(array.length-1));
		return deviation;
	}
	public static double Average(int[] AverageNum){
		double sum = 0;
		for(int x = 0; x < AverageNum.length; x++){
			
			sum += AverageNum[x]; 
		}
		
		return sum/AverageNum.length;
	}
	public static double Summation( int[] array, double Average){
		
		double sum = 0;
		for(int x = 0; x < array.length-1; x++){
			sum+= (array[x]-Average)*(array[x]-Average);
		}
		return sum;
	}
}
