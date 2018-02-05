
public class AdvanceTicket extends Ticket {
	private int days;
	public AdvanceTicket(int number, int days) {
		super(number);
		this.days = days;
		// TODO Auto-generated constructor stub
	}

	public double getPrice(){
		if(this.days > 10){
			return 30.0;
		}else {
			return 40.0;
		}
	}
	
	public int getDays(){
		return this.days;
	}
	public String toString(){
		return "Number: " + getNumber() + ", Price: " + getPrice();
	}
}
