import static org.junit.Assert.*;

import org.junit.Test;

public class RationalNumberTest {

	@Test
	public void testInit() {
		RationalNumber r = new RationalNumber("5");
		assertEquals(r.numerator, 5);
		assertEquals(r.denominator, 1);
		
		r = new RationalNumber("252");
		assertEquals(r.numerator, 252);
		assertEquals(r.denominator, 1);		
		
		assertEquals("252", r.toString());
	}

	@Test
	public void testMultiply() {
		RationalNumber a1 = new RationalNumber("5");
		RationalNumber a2 = new RationalNumber("5");
		RationalNumber res = RationalNumber.multiply(a1,  a2);
		
//		assertEquals(res.numerator, 25);
//		assertEquals(res.denominator, 1);
		assertEquals("25", res.toString());
		
		a1.denominator = 4;
		res = RationalNumber.multiply(a1,  a2);
		
		assertEquals(res.numerator, 25);
		assertEquals(res.denominator, 4);
		
	}

}
