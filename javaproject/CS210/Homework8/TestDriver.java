public class TestDriver {
	public static void main(String[] args){
		Point r = new Point();
		System.out.println("quadrant is: " + r.quadrant(-1, 1));
		r = new Point();		
		r.flip(-1, 3);
		System.out.println("Fliped point is: " + r.toString());
		Point one = new Point(4, 3);
		Point two = new Point(2, -1);
		Line l = new Line(one , two );
		System.out.print("Line is: " + l.toString());
	}
}