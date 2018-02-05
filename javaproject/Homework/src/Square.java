import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Square extends Rectangle implements Shape {

	public Square(int X, int Y, int height, int width) {		
		super(X, Y, height, width);
	}
	public void render(Graphics g){
		if(getHit()){
			g.drawRect(getX(), getY(), getWidth(), getHeight());    
			g.setColor(getColor());
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.BLACK);
		}else {
			g.drawRect(getX(), getY(), getWidth(), getHeight());    
			g.setColor(Color.WHITE);
			g.fillRect(getX() + 1, getY() + 1, getWidth()-1, getHeight()-1);
			g.setColor(Color.BLACK);
		}
	}
	public Point getPosition() {
		Point position = new Point(getX(), getY());		
		return position;
	}
	
	public void setPosition(Point point) {
		super.setX((int) point.getX());
		super.setY((int) point.getY());
	}	
	
	public double getArea(){
		return getWidth() * getWidth()-4;
	}
	
}
