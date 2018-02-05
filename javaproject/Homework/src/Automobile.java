
public class Automobile implements Comparable <Automobile>{

	private int year; 
	private String maker;
	private String model;
	
	public Automobile(int year, String maker, String model) {
		this.year = year;
		this.maker = maker;
		this.model = model;
	}
	
	public int getYear() { return this.year;}
	public String getMaker() { return this.maker;}
	public String getModel() { return this.model;}
	public void setYear(int year) { this.year = year;}
	public void setMaker(String maker) { this.maker = maker;}
	public void setModel(String model) { this.model = model;}
	public String toString(){
		return this.year + " " + this.maker + " " + this.model;
	}
	
	public int compareTo(Automobile a) {
		if(this.maker.compareTo(a.getMaker()) != 0){
			return this.maker.compareTo(a.getMaker());
		}else if(this.model.compareTo(a.getModel()) != 0) {
			return this.model.compareTo(a.getModel());
		}else {
			return this.year - a.getYear();
		}
		
	}
}
