package Homework8;

//object represents a name.
public class Name {
	private String first;
	private String last;
	private char middle;
	
	public String getNormalOrder(){
		return first + " " + middle +". " + last;
	}
	public String getReverseOrder(){
		
		return last + ", " + first + middle+ ".";
	}
	public Name(String initialFirst, char initialMiddle, String initialLast){
		first = initialFirst;
		middle = initialMiddle;
		last = initialLast;
		
	}
	public String getFirst(){
		return first;
	}
	public String getLast(){
		return last;
	}
	public char getniddle(){
		return middle;
	}
}

