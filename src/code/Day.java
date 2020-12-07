package code;

public class Day implements Cloneable, Comparable<Day> {

	// Instance Field
	private int year;
	private int month;
	private int day;
	private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";

	// Constructor
	public Day(int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;
	}

	//Setters
	public Day(String sDay) throws NumberFormatException, ExWrongDateFormat {
		this.set(sDay);
	}

	// Getters
	public int getMonth() {return this.month;}
	public int getDay() {return this.day;}
	
	//Methods
	
	// Set year,month,day based on a string like 01-Mar-2020
	public void set(String sDay) throws ExWrongDateFormat, NumberFormatException {
		
		//Split by regex "-"
		String[] sDayParts = sDay.split("-");

		// If the input is not in the format of XX-XXX-XXXX
		if (sDayParts.length != 3) {
			throw new ExWrongDateFormat();
		}

		// Apply Integer.parseInt for sDayParts[0]
		int getDay = Integer.parseInt(sDayParts[0]); 
		
		int getMonth = -1;
		//Check if the month name is valid
		if (MonthNames.contains(sDayParts[1]))
			getMonth = MonthNames.indexOf(sDayParts[1]) / 3 + 1;
		else
			throw new ExWrongDateFormat();
		
		// Apply Integer.parseInt for sDayParts[2]
		int getYear = Integer.parseInt(sDayParts[2]);
		
		if(Day.valid(getYear, getMonth, getDay)) {
			this.day = getDay;
			this.month = getMonth;
			this.year = getYear;	
		} else {
			throw new ExWrongDateFormat();
		}
		
	}
	
	// check if a given year is a leap year
	static public boolean isLeapYear(int y) {
		if (y % 400 == 0)
			return true;
		else if (y % 100 == 0)
			return false;
		else if (y % 4 == 0)
			return true;
		else
			return false;
	}

	// check if y,m,d valid
	static public boolean valid(int y, int m, int d) {
		switch (m) {
		case 1:	case 3:	case 5:	case 7:	case 8:	case 10: case 12:
			return d >= 1 && d <= 31;
		case 4:	case 6: case 9:	case 11:
			return d >= 1 && d <= 30;
		case 2:
			if (isLeapYear(y))
				return d >= 1 && d <= 29;
			else
				return d >= 1 && d <= 28;
		}
		return false;
	}
	
	@Override
	public int compareTo(Day another) {
		int self = this.getAsInt();
		int other = another.getAsInt();
		if (self == other)
			return 0;
		else if (self > other)
			return 1;
		else
			return -1;

	}

	public int getAsInt() {
		int self;
		self = this.year * 10000;
		self += this.month * 100;
		self += this.day;
		return self;
	}

	// Return a string for the day like dd MMM yyyy
	@Override
	public String toString() {
		return day + "-" + MonthNames.substring((month - 1) * 3, (month - 1) * 3 + 3) + "-" + year;
	}
}
