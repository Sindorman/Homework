import java.util.*;

public class Test {
	public static void main(String[] args) {
		String map = "Instantiate";
		long startTime = System.currentTimeMillis();
		int count = 0;
		for (int x = 0; x < 1000000; x++){
			count += 1;
		}
		Automobile a = new Automobile(2016, "Ford", "Fusion");
		Automobile b = new Automobile(2012, "Ford", "Fusion");
		System.out.println(a.compareTo(b));
		Animal c = new Shark(false);
		System.out.println(c.numberOfLegs());
		printCharacterFrequency(map);
	}
	public static void printCharacterFrequency(String s) {
		Map<Character, Integer> seen = new TreeMap<Character, Integer>();
		for (int x = 0; x < s.length(); x++){
			char check = s.charAt(x);
			seen.put(check, 0);
		}
		
		for (int x = 0; x < s.length(); x++) {
			char check = s.charAt(x);
			seen.put(check, seen.get(check) + 1);
		}
		System.out.println(seen);
		
	}
	
}

abstract class Animal {
	abstract public int numberOfLegs();
	abstract public boolean isWarmBlooded();
}

interface Named {
	public String name();
}

interface Pet extends Named {
	public Human owner();
}

abstract class Mammal extends Animal {
	public boolean isWarmBlooded() { return true; }
}

abstract class Fish extends Animal {
	public boolean isWarmBlooded() { return false; }
	public int numberOfLegs() { return 0; }
}

class Dog extends Mammal implements Pet {
	private String name; private Human owner;
	public int numberOfLegs() { return 4; }
	public Dog(String n, Human o) { name = n; owner = o; }
	public Human owner() { return owner; }
	public String name() { return name; }
}

class GoldFish extends Fish implements Pet {
	private String name; private Human owner;
	public GoldFish (String n, Human o) { name = n; owner = o; }
	public Human owner() { return owner; }
	public String name() { return name; }
}

class HoneyBadger extends Mammal {
	public int numberOfLegs() { return 4; }
	boolean cares() { return false; }
}

class Shark extends Fish {
	private boolean hasLaser;
	public Shark(boolean hasLaser) { this.hasLaser = hasLaser; }
	public boolean hasLaser() { return hasLaser; }
}

class Human extends Mammal implements Named { 
	private String name;
	public int numberOfLegs() { return 2; }
	public Human(String name) { this.name = name; }
	public String name() { return name; }
}

