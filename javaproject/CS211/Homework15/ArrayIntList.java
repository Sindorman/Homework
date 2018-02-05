import java.util.Stack;

interface IntList {
	public void add(int value);	
	// precondition: 0 <= index < size
	public void set(int index, int value);
	// precondition: 0 <= index < size
	public int get(int index);
	public int size();	
	// precondition: 0 <= index <= size
	public void add(int index, int value);	
	// precondition: 0 <= index < size
	public int remove(int index);	
	public int indexOf(int value);	
	public boolean isEmpty();	
	public boolean contains(int value);	
}

public class ArrayIntList implements IntList {

	private int[] data;
	private int size;
	
	public ArrayIntList() {
		this(10);
	}

	public ArrayIntList(int capacity) {
		data = new int[capacity];
		size = 0;
	}

	public void add(int value) {
		add(size, value);
	}
	
	// precondition: 0 <= index < size
	public void set(int index, int value) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		this.data[index] = value;
	}

	// precondition: 0 <= index < size
	public int get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return this.data[index];
	}
	
	public int size() {
		return size;
	}
	
	// precondition: 0 <= index <= size
	public void add(int index, int value) {

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		// expand if necessary
		if (index >= data.length) {
			int[] newData = new int[data.length * 2];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
		
		// shift the data after index to make a space
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}		
		this.data[index] = value;		
		size++;
	}
	
	// precondition: 0 <= index < size
	public int remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		int ret = data[index];
		for (int i = index; i < size; i++) {
			data[i] = data[i + 1];
		}
		size--;
		
		// collapse if necessary
		if (size < data.length / 4) {
			int[] newData = new int[data.length / 2];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;			
		}
		
		return ret;
	}
	
	public int indexOf(int value) {
		for (int i = 0; i < size; i++) {
			if (data[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public boolean contains(int value) {
		return this.indexOf(value) >= 0;
	}
	
	public String toString() {
		String ret = "[";
		if (size > 0) {
			ret += data[0];
		}
		
		for (int i = 1; i < size; i++) {
			ret += ", " + data[i];			
		}
		
		ret += "]";
		return ret;
	}
	
	public void replaceAll(int value, int replace) {
		for (int x = 0; x < size; x++) {
			if (data[x] == value) {
				data[x] = replace;
			}
		}
	}
	
	public void reverse() {
		if (size > 1) {
			Stack<Integer> aux = new Stack<Integer>();
			for (int x = size; x > 0; x--) {
				aux.push(remove(data[x]));
			}
			for (int x = aux.size(); x > 0; x--) {
				add(aux.pop());
			}
		}
	}
	
	public ArrayIntList runningTotal() {
		ArrayIntList list2 = new ArrayIntList();
		int sum = 0;
		for (int x = 0; x < size; x++) {
			sum += data[x];
			list2.add(sum);
		}
		return list2;
	}
	
	public void removeFront(int n) {
		for (int x = 0; x < n; x++) {
			remove(0);
		}
	}
	
	public void mirror() {
		int[] aux = data;
		for (int x = size - 1; x >= 0; x --) {
			add(aux[x]);
		}
	}
	
}