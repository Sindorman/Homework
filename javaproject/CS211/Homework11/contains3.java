import java.util.*;
public class contains3 {

	public static void main(String[] args) {
		List<String> test = new ArrayList<String>();
		test.add("Dog");
		test.add("Man");
		test.add("Dog");
		test.add("Hello");
		test.add("Hi");
		test.add("Dog");		
		System.out.println(contains3(test));
		
	}
	
	public static boolean contains3(List<String> name) {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for (int x = 0; x < name.size(); x++){
			if(map.containsKey(name.get(x))){
				map.put(name.get(x), map.get(name.get(x))+1);
			}else{
				map.put(name.get(x), 1);
			}
		}
		for (int y = 0; y < map.size(); y++){
			if (map.get(name.get(y)) >= 3){
				return true;
			}
		}
		return false;
	}

}
