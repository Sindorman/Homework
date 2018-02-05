import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayIntListTest {

	@Test
	public void testEmptyToString() {
		IntList list = new ArrayIntList();
		assertEquals(list.toString(), "[]");
	}

	@Test
	public void testAppend() {
		IntList list = new ArrayIntList();
		assertEquals(list.isEmpty(), true);
		list.add(5);
		assertEquals(list.isEmpty(), false);
		assertEquals(list.toString(), "[5]");
		list.add(6);
		assertEquals(list.toString(), "[5, 6]");
		list.set(0,  99);
		assertEquals(list.toString(), "[99, 6]");
		assertEquals(list.get(0), 99);
		assertEquals(list.get(1), 6);
		
		list.add(1, 16);
		assertEquals(list.toString(), "[99, 16, 6]");
		list.add(0, 7);
		assertEquals(list.toString(), "[7, 99, 16, 6]");
		list.add(4, 9);
		assertEquals(list.toString(), "[7, 99, 16, 6, 9]");
		list.add(7);
		list.add(7);
		list.add(7);
		list.add(7);
		list.add(7);
		list.add(7);
		list.add(7);
		list.add(7);
		list.add(7);
		assertEquals(list.toString(), "[7, 99, 16, 6, 9, 7, 7, 7, 7, 7, 7, 7, 7, 7]");
		
		list.remove(3);
		assertEquals(list.toString(), "[7, 99, 16, 9, 7, 7, 7, 7, 7, 7, 7, 7, 7]");
		list.remove(2);
		list.remove(2);
		list.remove(2);
		list.remove(2);
		list.remove(2);
		list.remove(2);
		list.remove(2);
		list.remove(2);
		list.remove(2);
		list.remove(2);
		assertEquals(list.toString(), "[7, 99, 7]");
		
		assertEquals(list.indexOf(99), 1);
		assertEquals(list.indexOf(77), -1);
		
		assertEquals(list.contains(99), true);
		assertEquals(list.contains(77), false);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testOutOfCapacityRange() {
		IntList list = new ArrayIntList();
		assertEquals(list.isEmpty(), true);
		list.get(99);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testOutOfValidRange() {
		IntList list = new ArrayIntList();
		assertEquals(list.isEmpty(), true);
		list.get(5);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testOutOfValidRangeAdd() {
		ArrayIntList list = new ArrayIntList();
		assertEquals(list.isEmpty(), true);
		list.add(3, 5);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testOutOfValidRangeRemove() {
		ArrayIntList list = new ArrayIntList();
		assertEquals(list.isEmpty(), true);
		list.remove(5);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testOutOfValidRangeSet() {
		ArrayIntList list = new ArrayIntList();
		assertEquals(list.isEmpty(), true);
		list.set(5, 7);
	}
	
	@Test
	public void testReplaceAll() {
		ArrayIntList list = new ArrayIntList();
		list.add(11); list.add(-7); list.add(3); list.add(42);
		list.add(3); list.add(0); list.add(14); list.add(3);
		list.replaceAll(3, 999);
		assertEquals(list.toString(), "[11, -7, 999, 42, 999, 0, 14, 999]");
	}
	
	@Test 
	public void testReverse() {
		ArrayIntList list = new ArrayIntList();
		list.add(11); list.add(-7); list.add(3); list.add(42);
		list.add(0); list.add(14); list.add(56);
		list.reverse();
		assertEquals(list.toString(), "[56, 14, 0, 42, 3, -7, 11]");
	}
	
	@Test 
	public void testReverseSingle() {
		ArrayIntList list = new ArrayIntList();
		list.add(11); 
		list.reverse();
		assertEquals(list.toString(), "[11]");
	}
	
	@Test 
	public void testRunningTotal() {
		ArrayIntList list = new ArrayIntList();
		ArrayIntList list2 = new ArrayIntList();
		list.add(2); list.add(3); list.add(5); list.add(4);
		list.add(7); list.add(15); list.add(20); list.add(7);
		list2 = list.runningTotal(); 
		assertEquals(list.toString(), "[2, 3, 5, 4, 7, 15, 20, 7]");
		assertEquals(list2.toString(), "[2, 5, 10, 14, 21, 36, 56, 63]");
	}
	
	@Test
	public void testRemoveFront() {
		ArrayIntList list = new ArrayIntList();
		list.add(8); list.add(17); list.add(9); list.add(24);
		list.add(42); list.add(3); list.add(8);
		list.removeFront(4);
		assertEquals(list.toString(), "[42, 3, 8]");
	}
	
	@Test
	public void testMirror() {
		ArrayIntList list = new ArrayIntList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);	
		assertEquals(list.toString(), "[1, 2, 3, 4]");
		list.mirror();
		assertEquals(list.toString(), "[1, 2, 3, 4, 4, 3, 2, 1]");
	}

}