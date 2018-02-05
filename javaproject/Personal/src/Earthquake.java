import java.util.Arrays;

public class Earthquake {
	private String datetime; 
	private String latitude; 
	private String longitude; 
	private String depth;
	private double magnitude;
	private String location; 
	//private String length; 
	
	public Earthquake(String datetime, String latitude, String longitude, String depth, double magnitude, String location){
		this.datetime = datetime; 
		this.latitude = latitude; 
		this.longitude = longitude; 
		this.depth = depth; 
		this.magnitude = magnitude; 
		this.location = location; 
	}
	
	public Earthquake(Earthquake e){
		this.datetime = e.datetime; 
		this.latitude = e.latitude; 
		this.longitude = e.longitude; 
		this.depth = e.depth; 
		this.magnitude = e.magnitude; 
		this.location = e.location; 
	}
	
	public Earthquake() {
		datetime = "";
		latitude = "";
		longitude = "";
		depth = "";
		magnitude = 0;
		location = "";
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	//public void length(int length){
		
	//}
	@Override
	public String toString() {
	
		return  ("Date Time:" + datetime + "\n" +
                 "Latitude: " + latitude + "\n" +
                 "Longitude: "+ longitude + "\n" +
                 "Depth: " + depth + "\n" +
                 "Magnitude :" + magnitude + "\n" +
                 "State: " + location + "\n\n");
	}
	
	public void print() {
		System.out.print(toString());
	}

}
