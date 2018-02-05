import java.awt.*;
public class TestDraw {

	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(300, 900);
		Graphics g = panel.getGraphics();
		String num = "1000";
		FontMetrics font = g.getFontMetrics();
		int width =  font.stringWidth(num);
		System.out.println(width);
		String Denom = "1";		
		int widthInit = font.stringWidth(Denom);
		System.out.println(widthInit);
		int x = 35;
		int y = 35;
		int line = 35;
		if(widthInit > width){
			x = 35+(widthInit-width)/2;
			line = 35 + widthInit;
		}else if(width > widthInit){
			y = 35+(width-widthInit)/2;
			line = 35 + width;
		}
		
		
		g.drawString(num, x, 63);
		g.drawLine(34, 65, line, 65);
		g.drawString(Denom, y, 76);

	}

}
