package code;
import java.util.ArrayList;

public class Slot implements Comparable<Slot> {

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
	//Add item into the slot
	public void addItem(Item item) {
		items.add(item);
		this.freeVolume -= item.getDimensions();
		item.setCurrentSlot(this);
	}
	
	//Remove item from slot
	public void removeItem(Item item) {
		this.freeVolume += item.getDimensions(); //TAG
		items.remove(item);
		item.setCurrentSlot(null);		
	}
	
	//For printing the items details
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
