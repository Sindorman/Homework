
public class Triangle implements Shape {
	
	private double a;
	private double b;
	private double c;
	
	//construct a new triangle with given side length
	public Triangle(double a, double b, double c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	//returns this triangle's area using Heron's formula
	public double getArea(){
		double s = (a + b + c) / 2.0;
		return Math.sqrt(s * (s - a) * (s - b) * (s - c));
	}
	
	//returns the perimeter if this triangle
	public double getPerimeter(){		
		return a + b + c;
	}
	
	public boolean equals(Shape triangle){
		try{
			triangle = (Triangle)triangle;
		}catch (ClassCastException e){
			System.out.println("Illegal format, please make sure it's a Triangle");
		}
		Triangle test = (Triangle)triangle;
		if(this.a == test.a && this.b == test.b && this.c == test.c){
			return true;
		}else {
			return false;
		}
	}
	
	public String toString(){
		return "a: " + this.a + " b: " + this.b + " c: " + this.c;
	}
}
