public class newone{
	public static void main(String[] args){
		double tempf = 98.6;
		double tempc = 0.0;
		ftoc(tempf, tempc);
		
	}
	
	public static void ftoc(double tempf, double tempc){
		tempc = (tempf - 32)*5/9;
		System.out.println("Body temp in C is:" + tempc);
	}
	
}