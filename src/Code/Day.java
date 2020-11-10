package Code;


public class Day implements Cloneable,Comparable<Day>{
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";
	
	//Constructor
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}
	public Day(String sDay) {
		this.set(sDay);
	}
	// check if a given year is a leap year
	static public boolean isLeapYear(int y)
	{
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	
	public void set(String sDay) //Set year,month,day based on a string like 01-Mar-2020
	{
	String[] sDayParts = sDay.split("-");
	this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
	this.year = Integer.parseInt(sDayParts[2]);
	this.month = MonthNames.indexOf(sDayParts[1])/3+1;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}
	public int daysInMonth() {
		switch(month){
		case 1: case 3: case 5: case 7:
		case 8: case 10: case 12:
				 return 31; 
		case 4: case 6: case 9: case 11:
				 return 30; 
		case 2:
				 return 29;
	}
		return -1;
	
		
	}
	@Override
	public int compareTo(Day another)
	{
	int self=this.getAsInt();
	int other=another.getAsInt();
	if(self==other)return 0;
	else if(self>other) return 1;
	else return -1;
	
	}
	
	public int getAsInt() {
		int self;
		self=this.year*10000;
		self+=this.month*100;
		self+=this.day;
		return self;
	}
	
	public boolean more(Day d) {
		if(this.getAsInt()>=d.getAsInt())
			return true;
		else return false;
	}
	
	public boolean less(Day d) {
		if(this.getAsInt()<=d.getAsInt())
			return true;
		else return false;
	}
	
	public boolean isBetween(Day d1, Day d2) {
		boolean flag=false;;
		if(this.more(d1)&&this.less(d2)) {
			flag=true;
		}
		return flag;
	}

	// Return a string for the day like dd MMM yyyy
	@Override
	public String toString() {
		
		
		
		return day+"-"+ MonthNames.substring((month-1)*3, (month-1)*3+3) + "-"+ year;
	}
	@Override
	public Day clone()
	{
	Day copy=null;
	try {
	copy = (Day) super.clone();
	}catch(CloneNotSupportedException e) {
		e.printStackTrace();
	}
	return copy;
}
}
