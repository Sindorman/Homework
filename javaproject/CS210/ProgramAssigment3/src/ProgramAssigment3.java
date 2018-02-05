import java.util.*;
import java.io.*;
public class ProgramAssigment3 {
	public static void main(String [] args)throws FileNotFoundException, IOException{
		String MainText = "";
		String text = "";
		File h = new File("testout.txt");
		Scanner input = new Scanner(new File(args[0]));
		PrintStream output = new PrintStream(new File(args[1]));
		String Justify = args[2];
		Spaces(input, output, Justify);
	}
	public static void  Spaces(Scanner input, PrintStream output, String Justify) throws FileNotFoundException{
		String Read = "";
		String finalOut = "";
		String State = "";
		boolean StartOfSentence = true;
		boolean Comma = false;
		boolean Space = false;
		int commanumber = -1;
		int CommaJammed = -1;
		String Text = "";
		int numberOfLines = -1;
		int [] lineNumber = new int[500];
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
					
					if(TokenCurrent.equals("i")) { 
						tokens[y] = TokenCurrent.toUpperCase();
						finalOut += tokens[y] + " ";
					}else {
						finalOut += TokenCurrent + " ";
					}
				}
			}
			tokens = finalOut.split(" ");
			finalOut = "";
			for(int x = 1; x <=3; x++){
				if(x == 1){
					State = ".";					
				}else if(x == 2){
					State = "!";
					
				}else {
					State = "?";
				}
				for(int y = 0; y < tokens.length; y++){
					String TokenCurrent = tokens[y];
					String TokenNext;
										
					
					if(y != tokens.length-1){
						TokenNext = tokens[y+1];
					} else{
						TokenNext = "";
					}
					
					String TokenPrevious = "";
					if(y != 0){
						TokenPrevious = tokens[y-1];
					} 
					commanumber = TokenNext.indexOf(State);		
					CommaJammed = TokenCurrent.indexOf(State);
													
					if(commanumber == 0 || CommaJammed > 0){
						Comma = true;
					}
					if(TokenNext.equals(State)){
						Space = true;
					}
					
					if(Comma == true){
						if(Space == true && CommaJammed < 0){
							finalOut += TokenCurrent + TokenNext.charAt(commanumber);
							Space = false;
						}else if(Comma == true && CommaJammed > 0 ){
							if(TokenNext.equals("")){
								finalOut += TokenCurrent.substring(0, CommaJammed+1)+ " " + TokenCurrent.substring(CommaJammed+1, TokenCurrent.length());
							}else{
								finalOut += TokenCurrent.substring(0, CommaJammed+1)+ " " + TokenCurrent.substring(CommaJammed+1, TokenCurrent.length()) + " ";
							}
							
							CommaJammed = -1;						
						}else {
							finalOut += TokenCurrent + TokenNext.charAt(commanumber) + " ";
						}
						tokens[y+1] = TokenNext.substring(commanumber+1, TokenNext.length());
						Comma = false;
						commanumber = -1;
					}else{
						finalOut += TokenCurrent + " ";
						
					}
				}
				tokens = finalOut.split(" ");
				if(x != 3){
				finalOut = "";
				}
				
			}			
			
			finalOut += "\n";			
			
			
		}
		Scanner data = new Scanner(finalOut);
		while(data.hasNextLine()){
			
			Read = data.nextLine();
			String [] tokens = Read.split(" ");			
			for(int x = 0; x < tokens.length; x++){
				String TokenCurrent = tokens[x];
				if(TokenCurrent.equals("")){
					
				} else{
					Text += tokens[x] + " ";
					TokenCurrent = tokens[x] + " ";
					
				}
				
			}
			Text += "\n";
						
		}
		Scanner Cap = new Scanner(Text);
		finalOut = "";
		commanumber = -1;
		while(Cap.hasNextLine()){
			Read = Cap.nextLine();
			String [] tokens = Read.split(" ");
			
				
			for(int x = 0; x < tokens.length; x ++){				
				String TokenCurrent = tokens[x];
				if(StartOfSentence == true){
					TokenCurrent = tokens[0];
					String First = TokenCurrent.substring(0, 1);
					tokens[0] = First.toUpperCase()+ TokenCurrent.substring(1, TokenCurrent.length());
					StartOfSentence = false;
				}
				String TokenNext;
				
				commanumber = TokenCurrent.indexOf(".");
				
				if(x != tokens.length-1){
					TokenNext = tokens[x+1];
				} else{
					TokenNext = "";
				}
				
				String TokenPrevious = "";
				if(x != 0){
					TokenPrevious = tokens[x-1];
				} 
				if(commanumber > 0){
					if(x == tokens.length-1){
						finalOut += tokens[x] + "  ";
						commanumber = -1;
					}else{
						if(TokenNext.equals("")){
							finalOut += tokens[x];
						}else{
					String Case = TokenNext.substring(0, 1);
					tokens[x+1] = Case.toUpperCase() + TokenNext.substring(1, TokenNext.length());
					finalOut+= tokens[x] + "  " ;
					commanumber = -1;
					}
						}
				}else{
					finalOut += tokens[x] + " ";
				}
				
				
			
		}
				tokens = finalOut.split(" ");
				finalOut = "";
				for(int x = 0; x < tokens.length; x ++){				
					String TokenCurrent = tokens[x];
					if(StartOfSentence == true){
						TokenCurrent = tokens[0];
						String First = TokenCurrent.substring(0, 1);
						tokens[0] = First.toUpperCase()+ TokenCurrent.substring(1, TokenCurrent.length());
						StartOfSentence = false;
					}
					String TokenNext;
					
					commanumber = TokenCurrent.indexOf("!");
					
					if(x != tokens.length-1){
						TokenNext = tokens[x+1];
					} else{
						TokenNext = "";
					}
					
					String TokenPrevious = "";
					if(x != 0){
						TokenPrevious = tokens[x-1];
					} 
					if(commanumber > 0){
						if(x == tokens.length){
							finalOut += tokens[x] + "  ";
							commanumber = -1;
						}else{
							if(TokenNext.equals("")){
								finalOut += tokens[x];
							}else{
						String Case = TokenNext.substring(0, 1);
						tokens[x+1] = Case.toUpperCase() + TokenNext.substring(1, TokenNext.length());
						finalOut+= tokens[x] + "   " ;
						commanumber = -1;
						}
							}
					}else{
						finalOut += tokens[x] + " ";
					}
					
					
				
			}	
			
				tokens = finalOut.split(" ");
				finalOut = "";
				for(int x = 0; x < tokens.length; x ++){				
					String TokenCurrent = tokens[x];
					if(StartOfSentence == true){
						TokenCurrent = tokens[0];
						String First = TokenCurrent.substring(0, 1);
						tokens[0] = First.toUpperCase()+ TokenCurrent.substring(1, TokenCurrent.length());
						StartOfSentence = false;
					}
					String TokenNext;
					
					commanumber = TokenCurrent.indexOf("?");
					
					if(x != tokens.length-1){
						TokenNext = tokens[x+1];
					} else{
						TokenNext = "";
					}
					
					String TokenPrevious = "";
					if(x != 0){
						TokenPrevious = tokens[x-1];
					} 
					if(commanumber > 0){
						if(x == tokens.length-1){
							finalOut += tokens[x] + "  ";
							commanumber = -1;
						}else{
							if(TokenNext.equals("")){
								finalOut += tokens[x];
							}else{
						String Case = TokenNext.substring(0, 1);
						tokens[x+1] = Case.toUpperCase() + TokenNext.substring(1, TokenNext.length());
						finalOut+= tokens[x] + "   " ;
						commanumber = -1;
						}
							}
					}else{
						finalOut += tokens[x] + " ";
					}
					
					
				
			}
			
			finalOut += "\n";
			
		}
		Scanner NumberOfLines = new Scanner(finalOut);
		while(NumberOfLines.hasNextLine()){
			numberOfLines++;
			String Line = NumberOfLines.nextLine();
			lineNumber[numberOfLines] = Line.length();
		}
		Scanner FinalScanner = new Scanner(finalOut);
		while(FinalScanner.hasNextLine()){
			if(Justify.equals("-left")){
			Text = FinalScanner.nextLine();						
			output.println(Text);
			}else if(Justify.equals("-right")){
			Text = FinalScanner.nextLine();						
			output.println("      " + Text);
			}else{
				Text = FinalScanner.nextLine();						
				output.println(Text);
			}
				
		}
			
		//output.print(Text);
		
	}
	
}