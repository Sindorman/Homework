import java.util.Scanner;

public class GradeCalculator1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner inp = new Scanner(System.in);
		//date 
		System.out.println("Enter the number of students:");
		int n = inp.nextInt();
			for (int i=1; i<=n; i++){
				studentinfo(inp);
			}

	}
	public static void studentinfo(Scanner inp){
		System.out.println("Enter next student's information:");
		System.out.println("Name:");
		String name = inp.next();
		
		System.out.print("q1");
		double q1 = inp.nextDouble();
		System.out.print("q2");
		double q2 = inp.nextDouble();
		System.out.print("q3");
		double q3 = inp.nextDouble();
		System.out.print("q4");
		double q4 = inp.nextDouble();
		System.out.print("q5");
		double q5 = inp.nextDouble();
		double qt = ((q1+q2+q3+q4+q5)*2)/100;
		
		double at = assignment(inp);
		
		System.out.print("e1");
		double e1 = inp.nextDouble();
		System.out.print("e2");
		double e2 = inp.nextDouble();
		System.out.print("e3");
		double e3 = inp.nextDouble();
		double et = ((e1*10) + (e2*20) + (e3*30))/100;
		
		//double z = qt+at+et;
		//System.out.println(z);
		
		if (q5>q4 && e3>e2 && e2>e1) {
				et +=5;
			}
		
		double t = qt + at + et; 
		System.out.format(name.toUpperCase()+"SCORE:%.2f%n",t); // 
		
		String grade;
		if (t >= 93){
			grade = "A"; 
		} else if (t>=90) {
			grade = "A-";
		} else if (t >= 87) {
			grade = "B+";
		} else if (t >= 83) {
			grade = "B";	
		} else if (t >= 80) {
			grade = "B-";
		} else if (t >= 77) {
			grade = "C+";
		} else if (t >= 74) {
			grade = "C";
		} else if (t >= 70) {
			grade = "C-";
		} else if (t >= 68) {
			grade = "D+";
		} else if (t >= 60) {
			grade = "D";
		} else {
			grade = "F";
		}
		
		System.out.println(name.toUpperCase() + "\'S LETTER GRADE:" + grade);
	}


	public static double assignment(Scanner inp){
		System.out.print("a1");
		double a1 = inp.nextDouble();
		System.out.print("a2");
		double a2 = inp.nextDouble();
		System.out.print("a3");
		double a3 = inp.nextDouble();
		System.out.print("a4");
		double a4 = inp.nextDouble();
		System.out.print("a5");
		double a5 = inp.nextDouble();
		System.out.print("a6");
		double a6 = inp.nextDouble();
		double at = (((a1+a2)*2.5) + ((a3+a4+a5)*5) + (a6*10))/100;
	
		if (a6>a5 && a5>a4 && a4>a3 && a3>a2) {
		at+=5;
	}
		
	return at; 
	}
}



