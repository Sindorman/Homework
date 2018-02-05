import static org.junit.Assert.*;

import org.junit.Test;

public class HashIntSetTest {

	@Test
	public void test() {
		HashIntSet test = new HashIntSet();
		test.add(3); test.add(17); test.add(81); test.add(4); test.add(90);
		HashIntSet testTwo = new HashIntSet();
		testTwo.add(3); testTwo.add(17); testTwo.add(81);
		assertEquals(test.containsAll(testTwo), true);
	}
	
	@Test
	public void test2() {
		HashIntSet test = new HashIntSet();
		test.add(3); test.add(17); test.add(81); test.add(4); test.add(90);		
		System.out.println(test.toArray());
	}

}
