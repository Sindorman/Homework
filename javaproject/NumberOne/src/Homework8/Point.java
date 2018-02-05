package Homework8;

public class Point {
	private int x;
	private int y;
	
	public Point(){
		this(0,0);
	
	}
	public Point(int x, int y){
		setLocation(x,y);
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int quadrant(int x, int y){
		
		if(x > 0 && y >0){
			return 1;
		}else if(x < 0 && y > 0){
			return 2;
		}else if(x < 0 && y < 0){
			return 3;
		}else if(x > 0 && y < 0){
			return 4;
		}
		return 0;
	}
	public void flip(int x, int y){
		int swap = x;
		this.x = -y;
		this.y = -swap;
		
	}
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
}
