import java.util.Scanner;
public class GradeCalculator_Jin {
	

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
			double q[] = new double[14];
			for (int x = 1; x < 6; x++) {
				System.out.print("quiz " + x + ": ");
				q[x-1] = inp.nextDouble();
			}			
			for (int x = 1; x < 7; x++) {
				System.out.print("assigment " + x + ": ");
				q[x+4] = inp.nextDouble();
			}
			for (int x = 1; x < 4; x++) {
				System.out.print("exam " + x + ": ");
				q[x+10] = inp.nextDouble();
			}			
			
			
			double t = ((q[0]+q[1]+q[2]+q[3]+q[4])*2)/100 + (((q[5]+q[6])*2.5) + ((q[7]+q[8]+q[9])*5) + (q[10]*10))/100 + ((q[11]*10) + (q[12]*20) + (q[13]*30))/100;
			if (q[4] > q[3] && q[13] > q[12] && q[12] > q[11]) {
				t += 5;
		    }
			if (q[10] > q[9] && q[9] > q[8] && q[8]>q[7] && q[7] > q[6]) {
				t += 5;
			}
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
	}





