public class NumberFive{
	
	public static final int stair = 5;
	
	public static void main(String[] args){
		Man();
	}
	public static void Man(){
			for(int stairnumber = 1; stairnumber <= stair; stairnumber++){
				for(int i = 5*(stair-stairnumber); i> 0; i--){
					System.out.print(" ");
				
				}
				System.out.print("  O  ******");
				for(int a =5*(stairnumber-1); a > 0; a--){
					System.out.print(" ");
				}
				System.out.print("*");
					
				for(int k = 1; k>0; k--){
					System.out.println();
						for(int x = 5*(stair-stairnumber);x > 0; x--){
							System.out.print(" ");
						}
					System.out.print(" /|\\ *");
						for(int a =5*(stairnumber); a > 0; a--){
							System.out.print(" ");
						}
					System.out.println("*");
						for(int x = 5*(stair-stairnumber);x > 0; x--){
							System.out.print(" ");
						}
					System.out.print(" / \\ *");
						for(int a =5*(stairnumber); a > 0; a--){
							System.out.print(" ");
						}
					System.out.println("*");
				}
			
			}
		for(int b = 1; b<=stair+1; b++){
			System.out.print("*****");
		}
		System.out.print("**");
	}
	
	
}
		
	
	
