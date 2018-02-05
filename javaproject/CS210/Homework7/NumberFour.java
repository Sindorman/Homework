

public class NumberFour {
	public static void main(String[] args){
		int [][]MatrixOne = {{7, 2, 4}, 
							 {8, 4, -1}};
							 
		int [][]MatrixTwo = {{5, 3, 5}, 
							 {-3, 9, 0}};
		int [][] Array = matrixAdd(MatrixOne, MatrixTwo);
		for(int x = 0; x < Array.length; x++){
			for(int y = 0; y < Array[0].length; y++){
		System.out.printf(" %d ", Array[x][y]);
			}
			System.out.println();
		}
		
	}
	public static int[][] matrixAdd(int[][] MatrixOne, int [][] MatrixTwo){
		int [][]Add = new int[MatrixOne.length][MatrixOne[1].length];
		for(int x = 0; x < MatrixOne.length; x++){
			
			for(int y = 0; y < MatrixOne[0].length; y++){
				Add[x][y] = MatrixOne[x][y] + MatrixTwo[x][y];
			}
		}
		
		return Add;
	}
}
