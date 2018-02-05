import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Triangle implements Shape {

	private Point one;
	private int size;
	private Color color;
	private boolean tested;
	
	public Triangle (Point one, int size) {
		this.one = one;
		this.size = size;
	}
	
	@Override
	public void render(Graphics g) {
		if (tested){
			Polygon poly = new Polygon();
			poly.addPoint((int)one.getX(), (int)one.getY() + 1);
			poly.addPoint((int)one.getX() + 25 * size - 2, (int)one.getY() + 30 * size - 1);
			poly.addPoint((int)one.getX() - 25 * size + 2, (int)one.getY() + 30 * size - 1);
			g.setColor(color);
			g.fillPolygon(poly);
			g.setColor(Color.BLACK);
		}else {
			g.drawLine((int)one.getX(), (int)one.getY(), (int)one.getX() + 25 * size, (int)one.getY() + 30 * size);
			g.drawLine((int)one.getX(), (int)one.getY(), (int)one.getX() - 25 * size, (int)one.getY() + 30 * size);
			g.drawLine((int)one.getX() + 25 * size, (int)one.getY() + 30 * size, (int)one.getX() - 25 * size, (int)one.getY() + 30 * size);
			Polygon poly = new Polygon();
			poly.addPoint((int)one.getX(), (int)one.getY() + 1);
			poly.addPoint((int)one.getX() + 25 * size - 2, (int)one.getY() + 30 * size - 1);
			poly.addPoint((int)one.getX() - 25 * size + 2, (int)one.getY() + 30 * size - 1);
			g.setColor(Color.WHITE);
			g.fillPolygon(poly);
			g.setColor(Color.BLACK);
		}
	}

	@Override
	public boolean hitTest(Point pt, HitTestChangeListener listener) {
		if ((( (int)pt.getX() >= (int)one.getX() - 25 * size) && ((int)pt.getX() <= (int)one.getX() + 25 * size)) && ((int)pt.getY() >= one.getY() && (int)pt.getY() <= (int)one.getY() + 30 * size)){
			listener.OnHitTestChange();
			this.tested = true;			
			return true;
		}else{
			this.tested = false;			
			return false;
		}
		
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
		
	}

	@Override
	public double getArea() {
		double sideA = sideLength(one, new Point((int)one.getX() + 25 * size, (int)one.getY() + 30 * size));
		double sideB = sideLength(one, new Point((int)one.getX() - 25 * size, (int)one.getY() + 30 * size));
		double sideC = sideLength(new Point((int)one.getX() + 25 * size, (int)one.getY() + 30 * size), new Point((int)one.getX() - 25 * size, (int)one.getY() + 30 * size));
		double s = (sideA + sideB + sideC) / 2;		
		return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
	}

	@Override
	public Point getPosition() {		
		return this.one;
	}

	@Override
	public void setPosition(Point point) {
		this.one = point;
		
	}
	
	public double sideLength(Point one, Point two){
		return Math.sqrt(((two.getX() - one.getX()) * (two.getX() - one.getX()) + (two.getY() - one.getY()) * (two.getY() - one.getY())));
	}
}
