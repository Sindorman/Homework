import java.util.*;

public class ScaleByk {

	public static void main(String[] args) {
		ArrayList<Integer> List = new ArrayList<Integer>();
		List.add(4);
		List.add(1);
		List.add(2);
		List.add(0);
		List.add(3);
		System.out.println(List);
		ScaleByk(List);
		System.out.println(List);
	}
	public static void ScaleByk(ArrayList<Integer> list) {
		ArrayList<Integer> temporary = new ArrayList<Integer>();
		for (int x = 0; x < list.size(); x++ ){
			for(int y = 0; y < list.get(x); y++){
				if(list.get(x) < 0){
					
				}else{
					temporary.add(list.get(x));
				}
				
			}
		}
		for (int h = list.size()-1; h > 0; h--){
			list.remove(h);
		}
		list.addAll(temporary);
	}
}