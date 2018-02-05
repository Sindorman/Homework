
public class TimeSpan {
	
	private int hours;
	private int minutes;
	
	public TimeSpan(int hours, int minutes){
		this.hours = hours;
		this.minutes = minutes;
		while (this.minutes >= 60){
			this.hours++;
			this.minutes -= 60;
		}
	}
	
	public void add (TimeSpan span){
	    if (hours < 0 || minutes < 0){
	        throw new IllegalArgumentException();
	    }
	    this.hours += span.hours;
	    this.minutes += span.minutes;
	    
	    this.hours += this.minutes / 60;
	    this.minutes = this.minutes % 60;
	}
	
	public void subtract (TimeSpan span){
	    if (hours < 0 || minutes < 0){
	        throw new IllegalArgumentException();
	    }
	    this.hours -= span.hours;
	    if(span.minutes > this.minutes){
	    	this.hours--;
	    	this.minutes += 60 - span.minutes; 
	    }else {
	    this.minutes -= span.minutes;
	    }
	    
	    this.hours += this.minutes / 60;
	    this.minutes = this.minutes % 60;
	}
	
	public void scale(int factor){
		if (hours < 0 || minutes < 0){
	        throw new IllegalArgumentException();
	    }
		long minutes = (long)(this.hours * 60 + this.minutes) * factor;
		this.hours = 0;
		this.minutes = 0;
		while (minutes >= 60){
			this.hours++;
			minutes = minutes - 60;
		}
		this.minutes = (int) minutes;
	}
	
	public String toString(){
		return hours + "h" + minutes + "m";
	}
	
	public void compareTo(TimeSpan span){
		int timeOne = this.hours*60 + this.minutes;
		int timeTwo = span.hours*60 + this.minutes;
		if(timeOne > timeTwo){
			System.out.println(toString() + " is greater then " + span.toString());
		}else if (timeTwo > timeOne){
			System.out.println(span.toString() + " is greater then " + toString());
		}else {
			System.out.println(span.toString() + " are equal " + toString());
		}
	}

}
