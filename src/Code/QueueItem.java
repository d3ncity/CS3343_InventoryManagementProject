package Code;

public class QueueItem extends Item{

	public QueueItem(int dimensions, Day arrival, Day departure) {
		super(dimensions, arrival, departure);
	}
	
	//Comparing Departure Date / Shipping Date
	@Override
	public int compareTo(Item item) {
		if (this.getDepartureDate().compareTo(item.getDepartureDate())==1) return -1;
		else if (item.getDepartureDate().compareTo(this.getDepartureDate())==1) return 1;
		return 0;
	}

}
