import java.util.*;
public class countUnique {
	public static void main(String[] args) {
		ArrayList<Integer> test = new ArrayList();
		
		test.add(3); test.add(7); test.add(3); 
		test.add(-1); test.add(2); test.add(3); 
		test.add(7); test.add(2); test.add(15); 
		test.add(15);
		System.out.println(test);
		System.out.println(countUnique(test));
		
		
	}
	
	public static int countUnique(List<Integer> List) {
		Set<Integer> set = new TreeSet<Integer>(); 
		set.addAll(List);
		return set.size();
	}	
	
}
