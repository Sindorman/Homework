
public class Ticket {

	private int number;
	
	public Ticket(int number){
		this.number = number;
	}
	
	public double getPrice(){
		return 50.0;
	}
	
	public int getNumber(){
		return this.number;
	}
	public String toString(){
		return "Number: " + this.number + ", Price: " + getPrice();
	}
}

