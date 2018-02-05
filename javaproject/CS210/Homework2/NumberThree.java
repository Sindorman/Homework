public class NumberThree{
	public static void main(String[] args){
	Figure();							//request Figure.
	}
	public static void Top(){ 
		System.out.print("+");
		for(int j = 1; j<=2; j++){ 		//for (each of 2 lines){
			for(int i =1; i<=3; i++){  		//for(each of 3 lines){
			System.out.print("="); 				//write equals on the output line.}
			}
		System.out.print("+");   		//write plus on the output line.}
		}	
		System.out.println();			//go to a new line of output.
	}
	public static void Center(){		
		System.out.print("|");			//write pipe on the output line.
		for (int i = 1; i<=2; i++){		//for (each of 2 lines){
		System.out.print(" ");				//write a space on the output line.}
		}
		System.out.print(" ");			//write a space on the output line.
	}
	public static void Body(){
		for(int i = 1; i<=3; i++){		//for (each of 3 lines){
			Center();					//request Center
			Center();					//request Center
			System.out.println("|");	//write pipe on the output line.
		}
	}
	public static void Figure(){		//for (each of 1 lines){
		for(int i = 0; i<1; i++){
			Top();						//request Top;
			Body();						//request Body;
			Top();						//request Top;
			Body();						//request Body;
			Top();						//request Top;
		}
	}
}