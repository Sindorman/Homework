import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
	public static void main(String[] args) {
		
		
		double HomeWorkAve = readValue("Enter Program Homework average (0-100):", 0, 100);
		
		double ProgrammingAssignmentSum = 0;
		double DaysLate;
		double AssignmentScore;
		for (int i = 1; i <= 4; i++) {
			AssignmentScore = readValue("Enter programming assignment score " + i + " (0-100):", 0, 100);
			DaysLate = readValue("Number of days late (0-2):", 0, 2);
			AssignmentScore -= 15 * DaysLate;
			//I assume we do not want negative scores as you would get in a case of poor assigment
			//which was submitted two days late
			if (AssignmentScore < 0) {
				AssignmentScore = 0;
			}
			ProgrammingAssignmentSum += AssignmentScore;
		}
		double Midterm = readValue("Midterm(0-100):", 0, 100);
		double Final = readValue("Final (0-100):", 0, 100);
		double Participation = readValue("Participation (0-100):", 0, 100);
		double FinalGrade = (HomeWorkAve*25 + ((ProgrammingAssignmentSum)/4)*45 + ((Midterm + Final)/2)*25 + Participation*5) / 100;
		System.out.print(FinalGrade);
		
	}

    // prompt the user and read a number from the console
    private static int readValue(String prompt) {
		return readValue(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

    // prompt the user and read a number from the console, constrained to a number range
    private static int readValue(String prompt, int min, int max)
    {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			if (!prompt.isEmpty()){
				System.out.print(prompt);
			}
			try
			{
				String input = in.readLine();
				Integer ret = Integer.parseInt(input);
				if (ret < min || ret > max)
				{
					throw new NumberFormatException("value must be between " + min + " and " + max);
				}
				return ret.intValue();
			}
			catch (Exception e)
			{
				System.out.println("Input or formatting error: " + e.getMessage());
			}
		}
    }
}
