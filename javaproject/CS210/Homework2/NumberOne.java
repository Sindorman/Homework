public class NumberOne{
	public static void main(String[] args){
			int f = 0, g = 1;

        for (int i = 0; i <= 12; i++) {
            f = f + g;
            g = f - g;
            System.out.print(f + " ");
			}
		}
}
