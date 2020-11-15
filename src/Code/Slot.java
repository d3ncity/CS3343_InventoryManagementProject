package Code;
import java.util.ArrayList;

public class Slot implements Comparable<Slot>{

	//Instance Field
	private int volume;
	public int freeVolume;
	private ArrayList<Item> items;
	private int slotID;
	
	//Constructor
	public Slot(int volume) {
		this.volume = volume;
		this.freeVolume = volume;
		this.items= new ArrayList<>();
		this.slotID = Warehouse.getInstance().assignSlotID();
		Warehouse.getInstance().addSlots(this);
	}
	
	//Getters
	public int getVolume() {return this.volume;}
	public int getFreeVolume() {return this.freeVolume;}
	public int getSlotID() {return this.slotID;}
	public ArrayList<Item> getItemsList() {return items;}

	/*
	 * Methods for Item
	 *  +addItem
	 *  +removeItem
	 *  +printItemsInSlot
	 */
	public void addItem(Item item) {
		items.add(item);
		this.freeVolume = this.freeVolume - item.getDimensions();
		item.setCurrentSlot(this);
		System.out.println("Item #"+item.getItemID()+" with size("+item.getDimensions()+") is added in Slot ID #"+ 
		item.getCurrentSlot().getSlotID()+ " ; Delivery Date: " + item.getDepartureDate());
	}
	
	public void removeItem(Item item) {
		this.freeVolume+=item.getDimensions();
		items.remove(item);
		item.setCurrentSlot(null);		
	}
	
	public void printItemsInSlot() {
		for(int i =0;i<items.size();i++) {
			System.out.println(i+1+". "+items.get(i).toString());
		}
	}
	
	//Comparing Slot Size
	@Override
	public int compareTo(Slot slot) {
		if (this.volume < slot.getVolume()) return -1;
		else if (this.volume > slot.getVolume()) return 1;
		return 0;
	}
	
}