
public class Inheritance {
	public class Vehicle {}
	public class Car extends Vehicle {}
	public class SUV extends Car {}
	
	public static void main(String[] args) {
		Vehicle v = new SUV();
		Vehicle g = new Car();
		SUV s = new SUV();
		Car c = new Vehicle();
		SUV a = new Car();
		Car b = new SUV();
	}
}
