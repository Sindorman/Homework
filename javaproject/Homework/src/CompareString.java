
public class CompareString implements Comparable <String>{
	private String one;
	
	public void setOne(String one){ this.one = one;}
	public CompareString(String one){
		this.one = one;
	}
	public static void main(String[] args) {
		CompareString test = new CompareString("hello");
		System.out.println(test.compareTo("I say hi"));
		
	}
	
	public int compareTo(String two) {
		String[] first = this.one.split(" ");
		String[] second = two.split(" ");
		return first.length - second.length;
	}

}
