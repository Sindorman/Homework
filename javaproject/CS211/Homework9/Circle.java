
public class Circle implements Shape {
	
	private double radius;
	
	//construct a new Circle with the given radius;
	public Circle(double radius){
		this.radius = radius;
	}
	
	//returns the area of this circle
	public double getArea(){
		return Math.PI * radius * radius;
	}
	
	//returns the perimeter of this circle
	public double getPerimeter(){
		return 2.0 * Math.PI * radius;
	}
	
	public boolean equals(Shape circle){
		try{
			circle = (Circle)circle;
		}catch (ClassCastException e){
			System.out.println("Illegal format, please make sure it's a Circle");
		}
		Circle test = (Circle)circle;
		if(this.radius == test.radius){
			return true;
		}else {
			return false;
		}
		
	}
	
	public String toString(){
		return "radius: " + this.radius;
	}
}
