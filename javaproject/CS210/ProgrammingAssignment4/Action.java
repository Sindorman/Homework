
public class Action {
	private double first;
	private double second;
	public void Action(double first,  double second){
		setLocation(first, second);
	}
	public void setLocation(double first, double second){
		this.first = first;
		this.second = second;
	}
	public double getFirst(){
		return first;
	}
	public double getSecond(){
		return second;
	}
	public double adding(){
		return first + second;
	}
	public double subtract(){
		return first - second;
	}
	public double multiply(){
		return first * second;
	}
	public double divide(){
		return first / second;
	}
}
