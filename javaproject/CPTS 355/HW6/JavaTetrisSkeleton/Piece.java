import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Piece {

	/*the character (number) value of the piece*/
    private char number;
    private int curX = 0; //the current X coordinate of the  piece
    private int curY = 0; //the current Y coordinate of the  piece

    public Piece() {
        setNumber('0'); //default number 
    }
    /* get and set methods for "number"*/
    
    public Piece(int X, int Y, char n)
    {
        number = n;
        curX = X;
        curY = Y;
    }

    public void setNumber(char newNumber) {
        this.number = newNumber;
    }
    
    public char getNumber()  { 
    	return number; 
    }
    
    public void setX(int newX){
    	this.curX = newX;
    }
    
    public void setY(int newY){
    	this.curY = newY;
    }
    
    public int getX(){
    	return this.curX;
    }
    
    public int getY(){
    	return this.curY;
    }

    /*sets a random digit to the cell character*/
    public void setRandomNumber() {
        
        Random r = new Random();
        int x = Math.abs(r.nextInt(3)) % 10;
        setNumber(Character.forDigit(x, 10));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Piece p = (Piece) obj;
        if(p.getX() != curX) return false;
        if(p.getY() != curY) return false;
        if(p.getNumber() != number) return false;
        return true;
    }

    public String toString() {
        return "X: " + curX + ", Y: " + curY + ", Number: " + number;
    }
    
}
