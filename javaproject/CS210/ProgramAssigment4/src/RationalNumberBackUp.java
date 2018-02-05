import java.awt.*;
public class RationalNumberBackUp {
	int numerator;
	int denominator;	
	static DrawingPanel panel = new DrawingPanel(350, 600);
	public void RationalNumber(String text){
		String numbers[] = text.split("\\.");
		String num = "";
		if(text.indexOf(".") <= 0){
			numerator = Integer.parseInt(text);
			denominator = 1;
		}else{
			if (numbers[1].equals("0")){
				num = numbers[0];
			}else{
			num = numbers[0]+numbers[1];
			}
			if (numbers[0].equals("0")){
				if(numbers[1].length() > 5){
					long temporary = Long.parseLong(numbers[1]);
					temporary = Math.round(temporary);
					num = "" + temporary;
				}else{
				num = "" + numbers[1];
				}
			}
			numerator = Integer.parseInt(num);		
			denominator = 10*numbers[1].length();
		}
		
	}
	
	public int getNum(){
		return numerator;
	}
	
	public int getDenom(){
		return denominator;
	}	
	public String toString(){
		return "";
	}
	public static String RationalNumberMultiply(RationalNumberBackUp num1, RationalNumberBackUp num2, int times){	
		int gcd;
		RationalNumberBackUp rat = new RationalNumberBackUp();
		String num = "" + num1.numerator;
		String den = "" + num1.denominator;
		int fixed = Draw(num, den, 40, 40, times * 65);
		num = "" + num2.numerator;
		den = "" + num2.denominator;
		DrawOperand("*", 70 + fixed, times * 65 + 8);
		fixed = Draw(num, den, 105 + fixed, 105 + fixed, times * 65);
		DrawOperand("=", 155 + fixed, times * 65 + 5);
		rat.numerator = num1.numerator * num2.numerator;
		rat.denominator = num1.denominator * num2.denominator;
		if(rat.numerator < 0){
			rat.numerator = Math.abs(rat.numerator);
			gcd = gcd(rat.numerator, rat.denominator);
			rat.numerator = -(rat.numerator/gcd);			
		}else{
		gcd = gcd(rat.numerator, rat.denominator);		
		rat.numerator = rat.numerator/gcd;
		}
		rat.denominator = rat.denominator/gcd;
		num = "" + rat.numerator;
		den = "" + rat.denominator;
		Draw(num, den, 190 + fixed, 190 + fixed, times * 65);
		return rat.numerator + "/" + rat.denominator;
	}
	public static String RationalNumberDivide(RationalNumberBackUp num1, RationalNumberBackUp num2, int times){
		RationalNumberBackUp rat = new RationalNumberBackUp();
		int gcd;
		String num = "" + num1.numerator;
		String den = "" + num1.denominator;
		int fixed = Draw(num, den, 40, 40, times * 65);
		num = "" + num2.numerator;
		den = "" + num2.denominator;
		DrawOperand("/", 70 + fixed, times * 65 + 5);
		fixed = Draw(num, den, 105 + fixed, 105 + fixed, times * 65);
		DrawOperand("=", 155 + fixed, times * 65 + 5);
		rat.numerator = num1.numerator * num2.denominator;
		rat.denominator = num1.denominator * num2.numerator;
		if(rat.numerator < 0){
			rat.numerator = Math.abs(rat.numerator);
			gcd = gcd(rat.numerator, rat.denominator);
			rat.numerator = -(rat.numerator/gcd);			
		}else{
		gcd = gcd(rat.numerator, rat.denominator);		
		rat.numerator = rat.numerator/gcd;
		}
		rat.denominator = rat.denominator/gcd;
		num = "" + rat.numerator;
		den = "" + rat.denominator;
		Draw(num, den, 190 + fixed, 190 + fixed, times * 65);
		return rat.numerator + "/" + rat.denominator;
	}
	public static String RationalNumberAddition(RationalNumberBackUp num1, RationalNumberBackUp num2, int times){
		RationalNumberBackUp rat = new RationalNumberBackUp();
		int gcd;
		String num = "" + num1.numerator;
		String den = "" + num1.denominator;
		int fixed = Draw(num, den, 40, 40, times * 65);
		num = "" + num2.numerator;
		den = "" + num2.denominator;
		DrawOperand("+", 70 + fixed, times * 65 + 5);
		fixed = Draw(num, den, 105 + fixed, 105 + fixed, times * 65);
		DrawOperand("=", 155 + fixed, times * 65 + 5);
		rat.numerator = num1.numerator * num2.denominator + num2.numerator * num1.denominator;
		rat.denominator = num1.denominator * num2.denominator;
		if(rat.numerator < 0){
			rat.numerator = Math.abs(rat.numerator);
			gcd = gcd(rat.numerator, rat.denominator);
			rat.numerator = -(rat.numerator/gcd);			
		}else{
		gcd = gcd(rat.numerator, rat.denominator);		
		rat.numerator = rat.numerator/gcd;
		}
		rat.denominator = rat.denominator/gcd;
		num = "" + rat.numerator;
		den = "" + rat.denominator;
		Draw(num, den, 190 + fixed, 190 + fixed, times * 65);
		return rat.numerator + "/" + rat.denominator;
	}
	public static String RationalNumberSubtraction(RationalNumberBackUp num1, RationalNumberBackUp num2, int times){
		int gcd;
		RationalNumberBackUp rat = new RationalNumberBackUp();
		String num = "" + num1.numerator;
		String den = "" + num1.denominator;
		int fixed = Draw(num, den, 40, 40, times * 65);
		num = "" + num2.numerator;
		den = "" + num2.denominator;
		DrawOperand("-", 70 + fixed, times * 65 + 5);
		fixed = Draw(num, den, 105 + fixed, 105 + fixed, times * 65);
		DrawOperand("=", 155 + fixed, times * 65 + 5);
		rat.numerator = num1.numerator * num2.denominator - num2.numerator * num1.denominator;
		rat.denominator = num1.denominator * num2.denominator;
		if(rat.numerator < 0){
			rat.numerator = Math.abs(rat.numerator);
			gcd = gcd(rat.numerator, rat.denominator);
			rat.numerator = -(rat.numerator/gcd);			
		}else{
		gcd = gcd(rat.numerator, rat.denominator);		
		rat.numerator = rat.numerator/gcd;
		}
		rat.denominator = rat.denominator/gcd;
		num = "" + rat.numerator;
		den = "" + rat.denominator;
		Draw(num, den, 190 + fixed, 190 + fixed, times * 65);
		return rat.numerator + "/" + rat.denominator;
	}
	public static int gcd(int a, int b) {
	    if (a == 0)
	        return b;

	    while (b != 0) {
	        if (a > b)
	            a = a - b;
	        else
	            b = b - a;
	    }

	    return a;
	}
	public static int Draw(String num, String Denom, int a, int b, int y){		
		Graphics g = panel.getGraphics();		
		FontMetrics font = g.getFontMetrics();
		int width =  font.stringWidth(num);				
		int widthInit = font.stringWidth(Denom);				
		int line = a;
		int out;
		if(widthInit > width){
			a = a+(widthInit-width)/2;
			line += widthInit;
			out = widthInit;
		}else if(width > widthInit){
			b = b+(width-widthInit)/2;
			line += width;
			out = width;
		}else{
			line += width;
			out = width;
		}
		
		
		g.drawString(num, a, y-2);
		g.drawLine(a-2, y, line, y);
		g.drawString(Denom, b, y+11);
		return out;
	
	}
	public static void DrawOperand(String Operand, int x, int y){
		Graphics g = panel.getGraphics();
		g.drawString(Operand, x, y);
		
	}
}
	
