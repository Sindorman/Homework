import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Hexagon implements Shape {
	
	private Point One;
	private int size;
	private Color color;
	private boolean tested;

	public Hexagon(Point one, int size){
		this.One = one;
		this.size = size;
	}
	
	@Override
	public void render(Graphics g) {
		if (tested){
			Polygon poly = new Polygon();
			poly.addPoint((int)One.getX(), (int)One.getY());
			poly.addPoint((int)One.getX() + 25 * size, (int)One.getY());
			poly.addPoint((int)One.getX() + 40 * size, (int)One.getY() + 20 * size);
			poly.addPoint((int)One.getX() + 25 * size, (int)One.getY() + 40 * size);
			poly.addPoint((int)One.getX(), (int)One.getY() + 40 * size);
			poly.addPoint((int)One.getX() - 15 * size, (int)One.getY() + 20 * size);
			g.setColor(color);
			g.fillPolygon(poly);
		}else {
			g.setColor(color);
			g.drawLine((int)One.getX(), (int)One.getY(), (int)One.getX() + 25 * size, (int)One.getY());
			g.drawLine((int)One.getX() + 25 * size, (int)One.getY(), (int)One.getX() + 40 * size, (int)One.getY() + 20 * size);
			g.drawLine((int)One.getX() + 40 * size, (int)One.getY() + 20 * size, (int)One.getX() + 25 * size, (int)One.getY() + 40 * size);
			g.drawLine((int)One.getX() + 25 * size, (int)One.getY() + 40 * size, (int)One.getX(), (int)One.getY() + 40 * size);
			g.drawLine((int)One.getX(), (int)One.getY() + 40 * size, (int)One.getX() - 15 * size, (int)One.getY() + 20 * size);
			g.drawLine((int)One.getX(), (int)One.getY(), (int)One.getX() - 15 * size, (int)One.getY() + 20 * size);
			g.setColor(Color.WHITE);
			Polygon poly = new Polygon();
			poly.addPoint((int)One.getX() + 1, (int)One.getY() + 1);
			poly.addPoint((int)One.getX() + 25 * size - 1, (int)One.getY() + 1);
			poly.addPoint((int)One.getX() + 40 * size - 1, (int)One.getY() + 20 * size);
			poly.addPoint((int)One.getX() + 25 * size - 1, (int)One.getY() + 40 * size - 1);
			poly.addPoint((int)One.getX() + 1, (int)One.getY() + 40 * size - 1);
			poly.addPoint((int)One.getX() - 15 * size + 1, (int)One.getY() + 20 * size);
			g.fillPolygon(poly);
			g.setColor(Color.BLACK);
		}
		
	}

	@Override
	public boolean hitTest(Point pt, HitTestChangeListener listener) {
		if ((((int)pt.getX() >= (int)One.getX() - 15 * size) && ((int)pt.getX() <= (int)One.getX() + 40 * size)) && (((int)pt.getY() >= (int)One.getY()) && ((int)pt.getY() <= (int)One.getY() + 40 * size))){
			this.tested = true;
			listener.OnHitTestChange();
			return true;
		}else {
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
		double side = sideLength(One, new Point((int)One.getX() + 25 * size, (int)One.getY()));
		return 3 * Math.sqrt(3) / 2 * side * side;
	}

	@Override
	public Point getPosition() {
		
		return this.One;
	}

	@Override
	public void setPosition(Point point) {
		this.One = point;
		
	}

	public double sideLength(Point one, Point two){
		return Math.sqrt(((two.getX() - one.getX()) * (two.getX() - one.getX()) + (two.getY() - one.getY()) * (two.getY() - one.getY())));
	}
}
