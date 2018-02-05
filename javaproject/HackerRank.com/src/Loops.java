import java.util.*;
public class Loops {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a; int b; int n; int t;
        Scanner s = new Scanner(System.in);
        t = s.nextInt();
        if (t >= 0 && t <= 500) {
            for (int x = 0; x < t; x++) {            
                a = s.nextInt(); b = s.nextInt(); n = s.nextInt();
                if ((a >= 0 && a <= 50) && (b >= 0 && b <= 50) && (n >= 1 && n <= 15)) {    
                    int temp = (int)(a + Math.pow(2, 0) * b);
                    if (n == 1) {
                        System.out.print(temp);
                    }else {
                       System.out.print(temp + " ");
                    }
                    for (int y = 1; y < n - 1; y++) {
                        temp += (int)(Math.pow(2, y) * b);
                        System.out.print(temp + " ");
                    }
                    temp += (int)(Math.pow(2, n - 1) * b);
                    System.out.println((int)temp);
                }
            }
        }
	}

}
