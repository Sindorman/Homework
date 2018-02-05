

public class NumberOne {
	public static void main (String[] args){
		String [] Array = {"belt", "hat", "jelly", "bubble gum"}; 
		Compute(Array);
	}
	public static void Compute(String[] Array){
		int count = 0;
		
		for( int i = 0; i < Array.length; i ++){
			String Count = Array[i];
			count += Count.length();
		}
		double Average = (double)count/((double)Array.length);
		System.out.print("The average length is: " + Average);
	}
}
