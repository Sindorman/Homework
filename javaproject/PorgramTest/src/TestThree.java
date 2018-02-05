
public class TestThree {
	public static void main(String[] args){
		System.out.println(factorial(5));
		System.out.print(factorial2(5));
	}
	public static int factorial(int n) {
	        int fact = 1; 
	        for (int i = 1; i <= n; i++) {
	            fact *= i;
	        }
	        return fact;
	    }
	public static int factorial2(int n) {
    	if (n == 1) {
    		return 1;
    	}
    	return n * factorial2(n-1);
    }
}


    