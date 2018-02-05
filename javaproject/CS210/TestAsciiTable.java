public class TestAsciiTable
{
		public static final int MAX = 128;
		public static final int NUM_PER_LINE = 10;
		
		public static voud outputChar(int i){
			System.out.printf("%3d: %c\t", i, (char)i);
		}
		
		public static void main(String[] args){
			for(int i = 1; i< MAX; i++){
				outputChar(i);				
				if( i % NUM_PER_LINE == 0){
					System.out.println();
				}
			}
			for(int i = 0; i < NUM_PER_LINE; i++){
				for(int j = 0; j < MAX / NUM_PER_LINE; j++)
				{
					outputChar(i * NUM_PER_LINE + j);
				}
				System.out.println();
			}
		}
}