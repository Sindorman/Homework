package Homework7;

public class NumberThree {
	public static void main(String[] args){
		int [] Array = {3, 7, 12, 2, 9, 17, 43, -8, 7};
		boolean Equals = false;
		
		
	}
	public static boolean ifUnique(int [] Array, boolean Equals){
		for( int x = 0; x < Array.length; x++){
			for( int y = x+1; y < Array.length; y++){
				if(Array[x] == Array[y]){
					Equals = true;
					return Equals;
				}
			}
		}		
		return Equals;
	}
}
