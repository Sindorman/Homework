
public class SequentialIncrementer implements Incrementable {

	public int integer;
	
	public SequentialIncrementer(){
		this.integer = 0;
	}
	
	public void increment() {
		integer++;		
	}

	
	public int getValue() {
		return this.integer;		
	}

	public String toString(){
		return "" + this.integer;
	}
}
