import java.awt.*;
public class RationalNumber {
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
				if(numbers[1].length() > 8){
					numbers[1] = numbers[1].substring(0, 8);
					num = numbers[1];
				
				}
			if(num.length() > 8){
				num = num.substring(0, 8);
			}
			numerator = Integer.parseInt(num);
			denominator = 1;
			if(numbers[1].equals("0")){
			denominator = 1;
			}else{
				for (int x = 0; x < numbers[1].length(); x++){
					denominator *= 10;
				}
			}
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
	public static String RationalNumberMultiply(RationalNumber num1, RationalNumber num2, int times){	
		int gcd;
		if(times == 9){
			times = 1;
			panel.clear();
		}
		RationalNumber rat = new RationalNumber();
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
	public static String RationalNumberDivide(RationalNumber num1, RationalNumber num2, int times){
		RationalNumber rat = new RationalNumber();
		if(times == 9){
			times = 1;
			panel.clear();
		}
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
	public static String RationalNumberAddition(RationalNumber num1, RationalNumber num2, int times){
		RationalNumber rat = new RationalNumber();
		int gcd;
		if(times == 9){
			times = 1;
			panel.clear();
		}
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
	public static String RationalNumberSubtraction(RationalNumber num1, RationalNumber num2, int times){
		int gcd;
		if(times == 9){
			times = 1;
			panel.clear();
		}
		RationalNumber rat = new RationalNumber();
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
		
		if(Denom.equals("1")){
			g.drawString(num, a, y+5);			
		}else{
			g.drawString(num, a, y-2);
			g.drawLine(a-2, y, line, y);
			g.drawString(Denom, b, y+11);
		}
		return out;
	
	}
	public static void DrawOperand(String Operand, int x, int y){
		Graphics g = panel.getGraphics();
		g.drawString(Operand, x, y);
		
	}
}
	
