import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javax.swing.*;
//import java.awt.Font;
//import java.awt.Graphics;
import java.util.Scanner;

public class Calc1Grade extends JFrame {
	
	//private ArrayList<JCheckBox> box;
	private static JPanel boxpanel; 
	
	public static void main(String[] args) {
		System.out.println("Hello, this is grade calculator.");
		JFrame frame = new JFrame("test");
		String in = JOptionPane.showInputDialog(frame, "What's your name?");
		//prompt("Please type something");
		System.out.println("Hello, " + in);
		initialization();
		System.exit(0);

	}
	public static double prompt(String text) {		
		//Font f = new Font(10);
		
		double ret;
		boolean check = true;
		do {	
			try {
				System.out.println(text + ": ");
				Scanner in = new Scanner(System.in);
				check = true;
				ret = in.nextDouble();
				in.close();
				return ret;
			} catch (InputMismatchException e) {
				check = false;
				System.out.println("Error, please type number in format 1.2 from 0 - 100");
			}
		}while(check == false);
			return 0;		
	}
	public static void initialization() {
		//box.add(0, new JCheckBox("Assigments"));
		//box.add(1, new JCheckBox("Quizes"));
		//box.add(2, new JCheckBox("Exams"));
		//box.add(3, new JCheckBox("Assigments"));
		boxpanel = new JPanel(new GridLayout(0,1));
		boxpanel.add(new JCheckBox("Assigments"));
		boxpanel.add(new JCheckBox("Quizes"));
		boxpanel.add(new JCheckBox("Exams"));
		add(boxpanel, BorderLayout.LINE_START);
		//setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}
}
