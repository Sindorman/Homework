
import java.util.*;
import java.io.*;

public class TestTwo {
	

	
	   public static void main(String args[]){
		   String strategy = "strategy one";
		   int count = 0;
		   int computerthrow = 0;
		   String computername = "";
		   int y;
		   
		   Random r = new Random();
		   count = 5;
		   do{
		   if(strategy.equals("strategy one")){
				
				if( count == 5){
					computerthrow = 0;
					count--;
				} else if( count == 4){
					computerthrow = 1;
					count--;
				} else if (count == 3){
					computerthrow = 0;
					count--;
					
				}else if (count == 2){
					computerthrow = 2;
					count--;
				} else if (count == 1){
					computerthrow = 1;
					count--;
				}
				System.out.print(computerthrow + " ");
		   }
		   }while(count != 0);
		   for(int h = 7; h > 6; h++){
		   y = r.nextInt(6)+1;
		   System.out.println(y);
	   }
		   
			   
		   }
		   
	   
	   public static double round(double n){
			return Math.round((n * 100)/ 100);
		}
}