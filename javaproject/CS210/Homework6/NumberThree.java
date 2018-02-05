import java.util.*;
import java.io.*;
public class NumberThree {
	public static void main(String[] args) throws FileNotFoundException{
		int linenumber = 0;
		String lineone = "";
		String linetwo = "";
		Scanner console = new Scanner(System.in);
		System.out.println("Enter first file name:");
		File f = new File(console.nextLine());
		while(!f.canRead()){
			System.out.println("File not found. Try again");
			System.out.println("Enter first file name:");
			f = new File(console.nextLine());
		}
		System.out.println("Enter second file name:");
		File h = new File(console.nextLine());
		while(!h.canRead()){
			System.out.println("File not found. Try again");
			System.out.println("Enter second file name:");
			h = new File(console.nextLine());
		}
		Scanner fileone = new Scanner(f);
		Scanner filetwo = new Scanner(h);
		while(fileone.hasNextLine() && filetwo.hasNextLine()){
			linenumber++;
			lineone = fileone.nextLine() + "\n";
			linetwo =filetwo.nextLine() + "\n";
			if(!lineone.equals(linetwo)){
				System.out.println("Diffrence found:");
				System.out.println("Line: " + linenumber);
				System.out.print("< " + lineone);
				System.out.print("> " + linetwo);
			} 
				
			
		}
		
	}
	
}
