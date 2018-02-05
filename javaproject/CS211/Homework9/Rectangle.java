
public class Rectangle implements Shape {
	
	private double width;
	private double height;
	
	public Rectangle(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	//returns the area if this rectangle
	public double getArea(){
		return width * height;
	}
	
	//returns the perimeter of this rectangle
	public double getPerimeter(){
		return 2.0 * (width * height);
	}
	
	public boolean equals(Shape rectangle){
		try{
			rectangle = (Rectangle)rectangle;
		}catch (ClassCastException e){
			System.out.println("Illegal format, please make sure it's a Rectangle");
		}
		Rectangle test = (Rectangle)rectangle;
		if(this.width == test.width && this.height == test.height ){
			return true;
		}else { 
			return false;
		}
	}
	
	public String toString(){
		return "width: " + this.width + " height: " + this.height;
	}
}
