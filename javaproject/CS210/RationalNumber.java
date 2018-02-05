
public class RationalNumber {

	int numerator;
	int denominator;
	
	// constructor that takes a string
	public RationalNumber(String val) {
		// NOTE - this only accepts whole numbers, it needs
		// to be extended to handle decimals
		denominator = 1;		
		numerator = Integer.parseInt(val);		
	}
	
	public String toString() {
		if (denominator != 1) {
			throw new RuntimeException("non decimal not implemented");
		}
		String ret = "" + numerator;
		return ret;
	}
	
	private RationalNumber() {
	}
	
	public static RationalNumber multiply(RationalNumber val1, RationalNumber val2) {
		RationalNumber ret = new RationalNumber();
		ret.numerator = val1.numerator * val2.numerator;
		ret.denominator = val1.denominator * val2.denominator;
		return ret;
	}
	
}
