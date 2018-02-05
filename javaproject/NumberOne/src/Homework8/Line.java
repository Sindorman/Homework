package Homework8;

public class Line {
	Point p1 = new Point();
	Point p2 = new Point();
	public Line(Point p1, Point p2){
		setLocation(p1, p2);
	}
	public void setLocation(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getP1(){
		return p1;
	}
	public Point getP2(){
		return p2;
	}
	public String toString(){
		return "[" + p1 + ", " +  p2 + "]";
	}
}
