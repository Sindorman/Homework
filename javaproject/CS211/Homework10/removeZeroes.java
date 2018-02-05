import java.util.*;
public class removeZeroes {

	public static void main(String[] args) {
		ArrayList<Integer> name = new ArrayList<Integer>();
		name.add(0);
		name.add(7);
		name.add(2);
		name.add(0);
		name.add(0);
		name.add(4);
		name.add(0);
		System.out.println(name);
		removeZeroes(name);
		System.out.println(name);


	}
	
	
	
	public static void removeZeroes(ArrayList<Integer> list){
		for (int x = list.size()-1; x >= 0; x--){
			if (list.get(x) == 0){
				list.remove(x);
			}
		}
	}

}
