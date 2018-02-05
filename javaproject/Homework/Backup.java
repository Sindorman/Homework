import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class BackUp implements Shape {
	
	private Point One;
	private Point Two;
	private Point Three;
	private Point Four;
	
	public Rectangle(int height, int width){
		Two.setLocation(0, height);
		Three.setLocation(width, height);
		One.setLocation(0, 0);
		Four.setLocation(width, 0);
	}
	
	public double sideLength(Point one, Point two) {
		return Math.sqrt((One.getX()- Two.getX()) * (One.getX()- Two.getX()) - (One.getY()- Two.getY()) * (One.getY()- Two.getY()));
		
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hitTest(Point pt, HitTestChangeListener listener) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getArea() {		
		return sideLength(One, Two) * sideLength(Two, Three);
	}

	@Override
	public Point getPosition() {
		Point position = new Point();
		position.setLocation(Three.getX()- Two.getX(), Two.getY()-One.getY());
		
		return position;
	}

	@Override
	public void setPosition(Point point) {
		double heigth = Two.getY() - One.getY();
		double width = Three.getX() - Two.getX();
		One.setLocation(point.getX() - (width/2), point.getY() - heigth/2);
		Two.setLocation(point.getX() - (width/2), point.getY() + heigth/2);
		Three.setLocation(point.getX() + (width/2), point.getY() + heigth/2);
		Four.setLocation(point.getX() + (width/2), point.getY() - heigth/2);
	}
	
	public void Draw(Graphics G){
		G.drawLine((int)One.getX(), (int)One.getY(), (int)Two.getX(), (int)Two.getY());
		G.drawLine((int)Two.getX(), (int)Two.getY(), (int)Three.getX(), (int)Three.getY());
		G.drawLine((int)Four.getX(), (int)Four.getY(), (int)Four.getX(), (int)Four.getY());
		G.drawLine((int)One.getX(), (int)One.getY(), (int)Four.getX(), (int)Four.getY());
	}

}
