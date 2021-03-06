import java.util.*;
import java.io.*;

public class Strings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
	}
	
	 static boolean isAnagram(String a, String b) {
	        
	        // Complete the function by writing your code here.
	        Map<Character, Integer> m1 = new HashMap<Character, Integer>();
	        Map<Character, Integer> m2 = new HashMap<Character, Integer>();
	        //Set<Character> c = new HasSet<Character>();
	        for (int x = 0; x < a.length(); x++) {
	            char one = Character.toLowerCase(a.charAt(x));
	            char two = Character.toLowerCase(b.charAt(x));
	            m1.put(one, (m1.containsKey(one)) ? (m1.get(one) + 1) : (1));
	            m2.put(two, (m2.containsKey(two)) ? (m2.get(two) + 1) : (1));
	        }
	        //c = m.keySet();
	        for (int x = 0; x < a.length(); x++) {
	            if (m1.get(Character.toLowerCase(a.charAt(x))) != m2.get(Character.toLowerCase(a.charAt(x)))) return false;
	            if (m1.get(Character.toLowerCase(b.charAt(x))) != m2.get(Character.toLowerCase(b.charAt(x)))) return false;
	        }
	        return true;
	    }
}
