public class NumberFour{
	public static final int SUBHEIGHT = 4;
	
	public static void main(String[] args){
		TopOrBottom();
		Upper();
		Lower();
		TopOrBottom();
	}
	
	public static void TopOrBottom(){
		System.out.print("|");
		for(int i = 1; i <=(2*SUBHEIGHT); i++){
			System.out.print("\"");
		}
		System.out.println("|");
	}
	
	public static void Upper(){
		for(int line = 1; line<=SUBHEIGHT; line++){
			System.out.print(" ");
			for(int i = 1; i< line; i++){
				System.out.print(" ");
			}
		
			System.out.print("\\");
			//for (int i = 1; i<= (2*SUBHEIGHT - 2 * line); i++){
			for (int i = 1; i<= (2* (SUBHEIGHT - line)); i++){
				System.out.print(":");
			
			}
		
			System.out.print("/");
		
			for (int i = 1; i< line; i++){
				System.out.print(" ");
			}
		
			System.out.println();
		}
	
	}
	public static void Lower(){
		for(int line = 1; line<=SUBHEIGHT; line++){
			System.out.print(" ");
			for(int i = 1; i<= (SUBHEIGHT-line); i++){
				System.out.print(" ");
			}
		
		System.out.print("/");
		for (int i = 1; i<=  2 * (line-1); i++){
			System.out.print(":");
			
		}
		System.out.print("\\");
		for (int i = 1; i< line; i++){
			System.out.print(" ");
		}
		System.out.println();
		}
	}
	public static void Middle(){
		for(int i = 1; i<= (SUBHEIGHT); i++){
			System.out.print(" ");
			
		}
	System.out.print("||");
		for (int i = 1; i<= (SUBHEIGHT); i++){
			System.out.print(" ");
		}
		System.out.println();
	}
}     
