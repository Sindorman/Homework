public class NumberFour
{
	public static void main(String[] args)
	{
	System.out.println("That lay in the house that Jack built.\n");
	Thisis();
	System.out.println("cat,");
	Ate();
	Lay();
	
	Thisis();
	System.out.println("cat,");
	Killed();
	Ate();
	Lay();
	
	Thisis();
	System.out.println("dog,");
	Worried();
	Killed();
	Ate();
	Lay();
	
	Thisis();
	System.out.println("cow with the crumpled horn,");
	Tossed();
	Worried();
	Killed();
	Ate();
	Lay();
	
	Thisis();
	System.out.println("maiden all forlorn,");
	Two();
	
	Thisis();
	System.out.println("man all tattered and torn,");
	Kissed();
	Two();
	
	Thisis();
	System.out.println("priest all shaven and shorn,");
	Three();
	
	Thisis();
	System.out.println("cock that crowed in the morn,");
	System.out.println("That waked the priest all shaven and shorn,");
	Three();
	
	Thisis();
	System.out.println("This is the farmer sowing the corn,");
	System.out.println("That kept the the cock that crowed in the morn,");
	System.out.println("That waked the priest all shaven and shorn,");
	Three();
	
	}
	public static void Thisis()
	{
	System.out.print("This is the ");
	}
	public static void Ate()
	{
	System.out.println("That ate the malt,");
	}
	public static void Lay()
	{
	System.out.println("That lay in the house that Jack built.\n");
	}
	public static void Killed()
	{
	System.out.println("That killed the rat,");
	}
	public static void Worried()
	{
	System.out.println("That worried the cat,");
	}
	public static void Tossed()
	{
	System.out.println("That tossed the dog,");
	}
	public static void Milk()
	{
	System.out.println("That milked the cow with the crumpled horn,");
	}
	public static void Kissed()
	{
	System.out.println("That kissed the maiden all forlorn,");
	}
	public static void Married()
	{
	System.out.println("That married the man all tattered and torn,");
	}
	public static void Two()
	{
	Milk();
	Tossed();
	Worried();
	Killed();
	Ate();
	Lay();
	}
	public static void Three()
	{
	Married();
	Kissed();
	Milk();
	Tossed();
	Worried();
	Killed();
	Ate();
	Lay();
	}
}