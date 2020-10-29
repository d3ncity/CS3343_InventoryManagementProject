package Code;

public class Item implements Comparable<Item>{

	
	private int dimensions;
	private Day arrivalDate;
	private Day departureDate;
	private int ID;
	private Slot currentSlot;
	
	public Item(int dimensions, Day arrival, Day departure) {
		this.dimensions=dimensions;
		this.arrivalDate = arrival;
		this.departureDate = departure;
		//Harvey V1.0 - automatical ID
		this.ID = Warehouse.getInstance().assignItemID();
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
	public int getItemID() {
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
	
	
	//Harvey V2.0 - Comparing Item Size
	@Override
	public int compareTo(Item item) {
		if (this.dimensions < item.getDimensions()) return -1;
		else if (this.dimensions > item.getDimensions()) return 1;
		return 0;
	}
	
}
