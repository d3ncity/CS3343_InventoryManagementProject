package Code;

public class Item {

	
	private int dimensions;
	private Day arrivalDate;
	private Day departureDate;
	private int ID;
	private Slot currentSlot;
	
	public Item(int dimensions, Day arrival, Day departure,int ID) {
		this.dimensions=dimensions;
		this.arrivalDate = arrival;
		this.departureDate = departure;
		this.ID = ID;
	}
	
	public Day getArrivalDate() {
		return arrivalDate;
	}
	public Day getDepartureDate() {
		return departureDate;
	}
	public int  getDimensions() {
		return dimensions;
	}
	public int getID() {
		return this.ID;
	}
	
	@Override
	
	public String toString() {
		return "Dimensions for Item#"+this.ID+" is "+this.dimensions+"; Arrival Date - "+this.arrivalDate.toString()+
				", Departure Date is "+ this.departureDate.toString();
	}
	
	//added by Denny V1.0 - consideration required
	public void setCurrentSlot(Slot s) {
		this.currentSlot = s;
	}
	
	//added by Denny V1.0 - consideration required
	public Slot getCurrentSlot() {
		return this.currentSlot;
	}
	
}
