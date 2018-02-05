import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueue {
	public static void main(String[] args) {
		Queue<Integer> one = new LinkedList<Integer>();
		one.add(3); one.add(5); one.add(4); one.add(17); one.add(6); 
		one.add(83); one.add(1); one.add(84); one.add(16); one.add(37);
		System.out.println(one);
		rearrange(one);
		System.out.println(one);
		Stack<Integer> two = new Stack<Integer>();
		two.push(1); two.push(2); two.push(8); two.push(6); two.push(-1); two.push(15); two.push(7);
		System.out.println(two);
		switchPairs(two);
		System.out.println(two);
		Stack<Integer> three = new Stack<Integer>();
		three.push(2); three.push(2); three.push(2); three.push(2); three.push(2);
		three.push(4); three.push(4); three.push(81); three.push(17); three.push(17);
		System.out.println(three);
		compressDuplicates(three);
		System.out.println(three);
		
	}
	
	public static void rearrange(Queue<Integer> queue) {
		Stack<Integer> aux = new Stack<Integer>();
		for (int i = 0; i < 2; i++) {
			for (int x = queue.size(); x > 0; x--) {
				if (queue.peek() % 2 != 0) {
					aux.push(queue.remove());
				}else {
					queue.add(queue.remove());
				}
			}
			for (int x = aux.size(); x > 0; x-- ) {
				queue.add(aux.pop());
			}	
		}		
	}
	
	
	
	public static void switchPairs(Stack<Integer> stack) {
		Queue<Integer> aux = new LinkedList<Integer>();
		for (int i = stack.size(); i > 0; i--) {
			aux.add(stack.pop());
		}
		for (int i = aux.size(); i > 0; i--) {
			stack.push(aux.remove());
		}
		for (int i = stack.size(); i > 0; i = i - 2) {
			if (stack.size() != 1) {
				int one = stack.pop();
				int two = stack.pop();
				aux.add(two);
				aux.add(one);
			}else if (stack.size() == 1) {
				aux.add(stack.pop());
			}
		}
		for (int i = aux.size(); i > 0; i--) {
			stack.push(aux.remove());
		}	
	}
	
	public static boolean isSorted(Stack<Integer> stack) {
		Stack<Integer> aux = new Stack<Integer>();
		if (stack.size() == 1) {
			return true;
		}
		for (int i = stack.size(); i > 0; i--) {
			if (stack.size() == 1) {
				for (int y = aux.size(); y > 0; y--) {
					stack.push(aux.pop());
				}
				return true;
			}else {
				int one = stack.pop();
				int two = stack.pop();
				if (one > two) {
					stack.push(two);
					stack.push(one);
					for (int z = aux.size(); z > 0; z--) {
						stack.push(aux.pop());
					}
					return false;
				}
				stack.push(two);
				aux.push(one);
			}
		}
		for (int i = aux.size(); i > 0; i--) {
			stack.push(aux.pop());
		}
		return true;
	}
	
	public static void compressDuplicates(Stack<Integer> stack) {
		Queue<Integer> aux = new LinkedList<Integer>();		
		int count = 1;
		for (int i = stack.size(); i > 0; i--) {
			if (stack.size() == 1 || stack.size() == 0) {
				aux.add(count);
				aux.add(stack.pop());
				for (int x = aux.size(); x > 0; x--) {
					stack.push(aux.remove());
				}
			}else {
				int one = stack.pop();
				int two = stack.pop();
				if (one == two) {
					count++;
					stack.push(two);
				}else {
					aux.add(count);
					aux.add(one);
					stack.push(two);
					//stack.push(two);					
					count = 1;
				}
			}
		}
		for (int x = stack.size(); x > 0; x--) {
			aux.add(stack.pop());			
		}
		for (int x = aux.size(); x > 0; x--) {
			stack.push(aux.remove());
		}
		switchPairs(stack);		
	}
}
