package Tests;

public class TestTwo {
	public static void main(String[] args){
	String foo = "This is a string with spaces and other characters";
	for (char c : foo.toCharArray()) { 
		if (c != ' ') System.out.print(c);
		} System.out.println();
	}
}
