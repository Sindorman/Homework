import java.util.*;
import java.io.*;
public class ProgramAssigmentTest {
	public static void main(String [] args)throws FileNotFoundException, IOException{
		String MainText = "";
		String text = "";
		File h = new File("testout.txt");
		Scanner input = new Scanner(new File(args[0]));
		PrintStream output = new PrintStream(new File(args[1]));
		Spaces(input, output);
	}
	public static void  Spaces(Scanner input, PrintStream output) throws FileNotFoundException{
		String Read = "";
		String finalOut = "";
		String State = "";
		boolean StartOfSentence = true;
		boolean Comma = false;
		boolean Space = false;
		boolean Dot = false;
		int commanumber = -1;
		int spacenumber = 0;
		int StartOfTheSentence = -1;
		int CommaJammed = -1;
		int Periodnumber = -1;
		int PeriodJammed = -1;
		boolean Period = false;
		
		while(input.hasNextLine()){
			Read = input.nextLine();
			String [] tokens = Read.split(" ");
			
			for(int x = 0; x < tokens.length; x++){
				String TokenCurrent = tokens[x];
				if(TokenCurrent.equals("")){
					
				} else{
					finalOut += tokens[x] + " ";
				}
			}
				tokens = finalOut.split(" ");
				finalOut = "";
			for(int y = 0; y < tokens.length; y++){
				
				String TokenCurrent = tokens[y];
				String TokenNext;
								
				StartOfTheSentence = TokenCurrent.indexOf(".");
					
				if(StartOfTheSentence > 0){
					StartOfSentence = true;
				}
				if(y != tokens.length-1){
					TokenNext = tokens[y+1];
				} else{
					TokenNext = "";
				}
				
				String TokenPrevious = "";
				if(y != 0){
					TokenPrevious = tokens[y-1];
				} 
				commanumber = TokenNext.indexOf(",");		
				CommaJammed = TokenCurrent.indexOf(",");
												
				if(commanumber == 0 || CommaJammed > 0){
					Comma = true;
				}
				if(TokenNext.equals(",")){
					Space = true;
				}
				if(Comma == true){
					if(Space == true && CommaJammed < 0){
						finalOut += TokenCurrent + TokenNext.charAt(commanumber);
						Space = false;
					}else if(Comma == true && CommaJammed > 0 ){
						finalOut += TokenCurrent.substring(0, CommaJammed+1)+ " " + TokenCurrent.substring(CommaJammed+1, TokenCurrent.length()) + " ";
						CommaJammed = -1;						
					}else {
						finalOut += TokenCurrent + TokenNext.charAt(commanumber) + " ";
					}
					tokens[y+1] = TokenNext.substring(commanumber+1, TokenNext.length());
					Comma = false;
					commanumber = -1;
				}else{
					if(StartOfSentence == true && StartOfTheSentence <= 0){
						String Case = TokenCurrent.substring(0, 1);
						tokens[y] = Case.toUpperCase() + TokenCurrent.substring(1, TokenCurrent.length());
						finalOut += tokens[y] + " ";
						StartOfSentence = false;
						StartOfTheSentence = -1;
					}else if(StartOfSentence == true && StartOfTheSentence > 0){
						String Case = TokenNext.substring(0, 1);
						tokens[y+1] = Case.toUpperCase() + TokenNext.substring(1, TokenNext.length());
						finalOut += TokenCurrent + "  ";
						StartOfSentence = false;
						StartOfTheSentence = -1;
					}else if(TokenCurrent.equals("i")) { 
						tokens[y] = TokenCurrent.toUpperCase();
						finalOut += tokens[y] + " ";
					}else {
						finalOut += TokenCurrent + " ";
					}
				}
			}
			finalOut += "\n";
			tokens = finalOut.split(" ");
			//System.out.print(finalOut);
			finalOut = "";
			for( int b = 1; b <=3; b++){
				if(b == 1){
					State = ".";
				}else if(b == 2){
					State = "!";
				} else if(b == 3){
					State = "?";
				}
				 
				for(int x = 0; x < tokens.length; x++){
					String TokenCurrent = tokens[x];
					String TokenNext;
					if(x != tokens.length-1){
						TokenNext = tokens[x+1];
					} else{
						TokenNext = "";
					}
					
					String TokenPrevious = "";
					if(x != 0){
						TokenPrevious = tokens[x-1];
					} 
					StartOfTheSentence = TokenCurrent.indexOf(State);
					
					if(StartOfTheSentence > 0){
						StartOfSentence = true;
					}
					
					Periodnumber = TokenNext.indexOf(State);
									
					PeriodJammed = TokenCurrent.indexOf(State);
					if(StartOfTheSentence == TokenCurrent.length()){
						PeriodJammed = -1;
					}
					if(Periodnumber == 0 || PeriodJammed > 0){
						Period = true;
					}
					if(TokenNext.equals(State)){
						Space = true;
					}
					if(Period == true && StartOfSentence != true){
						if(Space == true && PeriodJammed < 0){
							finalOut += TokenCurrent + TokenNext.charAt(Periodnumber) + " ";
							Space = false;
						}else if(Period == true && PeriodJammed > 0 ){							
							String First = TokenCurrent.substring(PeriodJammed+1, PeriodJammed+2);
							finalOut += TokenCurrent.substring(0, PeriodJammed+1)+ "  " + First.toUpperCase() + TokenCurrent.substring(PeriodJammed+2, TokenCurrent.length()) + " ";
							PeriodJammed = -1;
							StartOfSentence = false;
							StartOfTheSentence = -1;
							
						}else {
							if(TokenCurrent.equals("")){
								finalOut += TokenCurrent + " ";
							}else{
							finalOut += TokenCurrent + TokenNext.charAt(Periodnumber) + "  ";
							StartOfSentence = true;
							StartOfTheSentence = Periodnumber;
							}
						}
						tokens[x+1] = TokenNext.substring(Periodnumber+1, TokenNext.length());
						Period = false;
						Periodnumber = -1;
						
					}else{
						if(StartOfSentence == true && StartOfTheSentence <= 0){
							if(TokenCurrent.equals("")){
								String Case = TokenNext.substring(0, 1);
								tokens[x] = Case.toUpperCase() + TokenNext.substring(1, TokenNext.length());
								finalOut += tokens[x] + " ";
								StartOfSentence = false;
								StartOfTheSentence = -1;
							}else{
							String Case = TokenCurrent.substring(0, 1);
							tokens[x] = Case.toUpperCase() + TokenCurrent.substring(1, TokenCurrent.length());
							finalOut += tokens[x] + " ";
							StartOfSentence = false;
							StartOfTheSentence = -1;
							}
						}else if((StartOfSentence == true && StartOfTheSentence > 0) && (StartOfTheSentence == TokenCurrent.length())){
							if(TokenNext.equals("")){
								TokenNext = tokens[x+2];
								String Case = TokenNext.substring(0, 1);
								tokens[x+2] = Case.toUpperCase() + TokenNext.substring(1, TokenNext.length());
								finalOut += TokenCurrent + " ";
								StartOfSentence = false;
								StartOfTheSentence = -1;
								TokenNext = tokens[x+1];
							}else{
							
								String Case = TokenNext.substring(0, 1);
								tokens[x+1] = Case.toUpperCase() + TokenNext.substring(1, TokenNext.length());
								finalOut += TokenCurrent + " ";
								StartOfSentence = false;
								StartOfTheSentence = -1;
							}
						}else if(TokenCurrent.equals("i")) { 
							tokens[x] = TokenCurrent.toUpperCase();
							finalOut += tokens[x] + " ";
						}else {
							finalOut += TokenCurrent + " ";
						}
					}
										
				}
				if( b != 3){
					tokens = finalOut.split(" ");
				}
				
				if(b != 3){
					finalOut = "";
				} 
				//System.out.print(finalOut);
			}
			output.print(finalOut + "\n");
			finalOut += "\n";			
			
			
		}
		System.out.print(finalOut);
	}
}