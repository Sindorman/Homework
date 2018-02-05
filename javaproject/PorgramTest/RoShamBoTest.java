import java.util.*;
import java.io.*;

class DebugTimerTask extends TimerTask
{
    public String message;
    public DebugTimerTask(String msg)
    {
        message = msg;
    }

    public void run()
    {
        throw new RuntimeException("Hang waiting for input from " + message);
    }
}

public class RoShamBoTest {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("USAGE: RoShamBoTest class1 numRuns [-debug]");
        } else {

            // read command line arguments
            String player1 = args[0];
            int numRuns = Integer.parseInt(args[1]);
            boolean debug = (args.length > 2 && args[2].equals("-debug"));

            // create each child process, then create an input stream scanner and an output PrintStream for each.
            Process proc1 = createProcess(player1);
            Scanner input1 = new Scanner(proc1.getInputStream());
            PrintStream output1 = new PrintStream(proc1.getOutputStream(), true /* autoflush */);

			int i = 0;
            for (; i < numRuns; i++) {
                // read the throw from each program
                String throw1 = scanForThrow(input1, "Player 1: " + player1, debug);
 
                if (debug) {
                    System.out.println(player1 + " throws " + throw1);
                }

                // send each throw to the other program
                output1.println("rock");
            }

            // tell each program to quit
            output1.println("quit");
            outputSummary(input1, "Player 1: " + player1);
			System.out.printf("Successfully scanned (%d/%d) throws from %s\n", i, numRuns, player1);
        }
    }

    public static Process createProcess(String classname) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", classname, "-throwFirst");
		pb.redirectErrorStream();
        return pb.start();
    }

    public static void outputSummary(Scanner scanner, String prefix) {

		System.out.println("SUMMARY for " + prefix);
		while (scanner.hasNextLine()) {
			System.out.println("waiting for input from " + prefix);
			String line = scanner.nextLine();           		
            System.out.println(">>>" + prefix + ">" + line + "<");
        }
    }

    public static String scanForThrow(Scanner scanner, String debugPrefix, boolean debug) {

        String line;
        do {
            Timer timer = new Timer();
            timer.schedule(new DebugTimerTask("Waiting for " + debugPrefix + " to throw"), 3000);
            line = scanner.nextLine();
            timer.cancel();
            if (line.equals("I throw rock")) {
                return "rock";
            }
            if (line.equals("I throw paper")) {
                return "paper";
            }
            if (line.equals("I throw scissors")) {
                return "scissors";
            }

            if (debug) {
                System.out.println(">>>" + debugPrefix + ">" + line + "<");
            }
        } while (true);
    }
}