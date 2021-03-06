import java.util.*;
public class RandomIncrementer implements Incrementable {
	
	public int integer;

	public RandomIncrementer(){
		Random rand = new Random();
		this.integer = rand.nextInt();
	}

	public void increment() {
		Random rand = new Random();
		int check = rand.nextInt();
		if(check != this.integer){
			this.integer = check;
		}else{
			this.integer = rand.nextInt();
		}
		
	}


	public int getValue() {
		
		return this.integer;
	}
	
	public String toString(){
		return "" + this.integer;
	}
}
