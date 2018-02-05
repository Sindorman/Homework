import java.util.*;
public class isUnique {

	public static void main(String[] args) {
		Map<String, String> test = new TreeMap();
		test.put("Logan", "Rachel");
		test.put("Map", "tree");
		test.put("Dog", "Rachel");
		System.out.println(isUnique(test));
		

	}
	
	public static boolean isUnique(Map<String, String> name) {
		ArrayList<String> helper = new ArrayList();
		helper.addAll(name.keySet());		
		for (int x = 0; x < helper.size(); x ++){
			for (int y = x+1; y < helper.size(); y++){
				if (name.get(helper.get(x)).equals(name.get(helper.get(y)))){
					return false;
				}
			}
		}
		return true;
	}

}
