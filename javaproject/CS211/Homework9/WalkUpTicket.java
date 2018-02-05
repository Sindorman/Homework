
public class WalkUpTicket extends Ticket {
	
	public WalkUpTicket(int number) {
		super(number);
		// TODO Auto-generated constructor stub
	}
	public double getPrice(){
		return 50.0;
	}
	public String toString(){
		return "Number: " + getNumber() + ", Price: " + getPrice();
	}

}
