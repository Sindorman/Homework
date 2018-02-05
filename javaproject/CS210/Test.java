public class Test
{
	public static void main(String[] args)
	{
	Between();
	Upper();
	Down();
	Between();
	Down();
	Upper();
	Between();
	}
	public static void Between()
	{
	System.out.println("+---------+");
	}
	public static void Upper()
	{
	System.out.println("|    *    |\n|   /*\\   |\n|  //*\\\\  |\n| ///*\\\\\\ |");
	}
	public static void Down()
	{
	System.out.println("| \\\\\\*/// |\n|  \\\\*//  |\n|   \\*/   |\n|    *    |");
	}
}
