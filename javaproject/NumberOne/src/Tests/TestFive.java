package Tests;

public class TestFive {
	public static void main(String[] args){
		methodA();
	}
	public static void methodA(){
		methodC(4);
		methodB(35);
	}
	public static void methodB(int arg){
		if(arg <= 0){
			System.out.println("methodB: done!");
		} else {
			methodB(methodC(arg)/2);
		}
	}
	public static int methodC(int arg){
		int i = arg;
		do{
			System.out.println("methodC:" + i);
			i--;
		} while(i % 3 != 0);
		return i;
	}
}
