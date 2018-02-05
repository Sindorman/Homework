
public class Date {
	
	private int year;
	private int month;
	private int day;
	
	
	
	public Date(int year, int month, int day){
		this.year = year;		
		this.month = month;
		this.day = day;
	}
	
	public int getDay(){
		return this.day;
	}
	
	public int getMonth(){
		return this.month;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public void addDays(int days){
		days = days + this.day;
		int x;
		if (isSpecial(this.month)) {
			x = 31;
		}else if (this.month == 2 && isLeapYear()) {
			x = 29;
		}else if (this.month == 2) {
			x = 28;
		}else {
			x = 30;
		}
		
		if (days > x) {	
			while (days > x) {
				this.month ++;
				if (month > 12) {
					this.year++;
					this.month -= 12;
				}
				days -= x;
				if (isSpecial(this.month)) {
					x = 31;
				}else if (this.month == 2 && isLeapYear()) {
					x = 29;
				}else if (this.month == 2) {
					x = 28;
				}else {
					x = 30;
				}
				
			}
			this.day = days;
		}else {
			this.day += days;
		}
		
	}
	
	public void addWeeks(int weeks) {
		int days = 7 * weeks +  this.day;
		int x;
		if (isSpecial(this.month)) {
			x = 31;
		}else if (this.month == 2 && isLeapYear()) {
			x = 29;
		}else if (this.month == 2) {
			x = 28;
		}else {
			x = 30;
		}
		
		if (days > x) {	
			while (days > x) {
				this.month ++;
				if (month > 12) {
					this.year++;
					this.month = 1;
				}
				days -= x;
				if (isSpecial(this.month)) {
					x = 31;
				}else if (this.month == 2 && isLeapYear()) {
					x = 29;
				}else if (this.month == 2) {
					x = 28;
				}else {
					x = 30;
				}
				
			}
			this.day = days;
		}else {
			this.day += days;
		}
		
	}
	
	public int daysTo(Date other){
		int Day = this.day;
		int Month = this.month;
		int year = this.year;
		int x;
		int realDays = 0;
		while ((year != other.year) || (Month != other.month)){
			if (isSpecial(Month)) {
				x = 31;
			}else if (Month == 2 && Helper(year)) {
				x = 29;
			}else if (Month == 2) {
				x = 28;
			}else {
				x = 30;
			}	
			realDays += x;
			Month++;
			if (Month > 12) {
				year++;
				Month = 1;
			}
		}
				
		int correction;
		
		/*if (this.day > other.day) {
			correction = this.day - other.day;
		}else {
			correction = -(other.day - this.day);
		}*/
		correction = Day - other.day; 
				
		return realDays - correction;
	}
	
	
	public String toSt1ring(){
		String month = "";
		String days = "";
		if (this.month < 10) {
			month = "0" + this.month;
		}else {
			month += this.month;
		}
		if (this.day < 10) {
			days = "0" + this.day;
		}else {
			days += this.day;
		}		
		return this.year + "/" + month + "/" + days;
		
	}
	
	public boolean isSpecial(int month){
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
			return true;
		}else{
			return false;
		}
	}
	public boolean isLeapYear(){
		if (this.year % 4 == 0 ){	
			if (this.year % 100 != 0) {
				return true;
			}else if (this.year % 400 == 0){
				return true;
			}else{
				return false;
			}
		}else {
		return false;
		}
	
	}
	
	public boolean Helper(int year){
		if (year % 4 == 0 ){	
			if (year % 100 != 0) {
				return true;
			}else if (year % 400 == 0){
				return true;
			}else{
				return false;
			}
		}else {
		return false;
		}
	
	}
}
