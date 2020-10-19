package Code;

public class Item {

	
	private int dimensions;
	private Day arrivalDate;
	private Day departureDate;
	private int ID;
	
	public Item(int dimensions, Day arrival, Day departure) {
		this.dimensions=dimensions;
		this.arrivalDate = arrival;
		this.departureDate = departure;
		//Harvey V1.0 - automatically ID
		this.ID = Warehouse.getInstance().assignItemsID();
		//this.ID = ID;
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
	
}
