package code;

public class Item implements Comparable<Item>{

	//Instance Field
	private int dimensions;
	private Day arrivalDate;
	private Day departureDate;
	private int ID;
	private Slot currentSlot;
	
	//Constructor
	public Item(int dimensions, Day arrival, Day departure) {
		this.dimensions=dimensions;
		this.arrivalDate = arrival;
		this.departureDate = departure;
		this.ID = Warehouse.getInstance().assignItemID();
		this.autoAssign();
	}
	
	//Automatically Assign to Slot
	private void autoAssign() {Warehouse.getInstance().moveToSlot(this);}
	
	//Getters
	public Day getArrivalDate() {return arrivalDate;}
	public Day getDepartureDate() {return departureDate;}
	public int  getDimensions() {return dimensions;}
	public int getItemID() {return this.ID;}
	public String getItemDetails() {
		return "Current Slot #"+this.getCurrentSlot().getSlotID()+ 
				" ;Dimensions for Item #"+this.ID+" is "+this.dimensions+"; Arrival Date: "+this.arrivalDate.toString()+
				", Departure Date: "+ this.departureDate.toString();
	}
	
	//Setter and Getter for Current Slot
	public void setCurrentSlot(Slot s) {this.currentSlot = s;}
	public Slot getCurrentSlot() {return this.currentSlot;}
	
	//Comparing Item Size
	@Override
	public int compareTo(Item item) {
		if (this.dimensions < item.getDimensions()) return -1;
		else if (this.dimensions > item.getDimensions()) return 1;
		return 0;
	}
	
	@Override
	public String toString() {
		return "Dimensions for Item #"+this.ID+" is "+this.dimensions+", Arrival Date: "+this.arrivalDate.toString()+
				", Departure Date: "+ this.departureDate.toString();
	}
	
}
