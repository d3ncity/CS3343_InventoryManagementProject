package Code;
import java.util.ArrayList;
import java.util.Collections;

public class Slot {

	private int volume;
	public int freeVolume;
	private ArrayList<Item> items;
	private int slotID;
	
	public Slot(int volume) {
		this.volume = volume;
		freeVolume = volume;
		items= new ArrayList<>();
		//Harvey V1.0 - automatical ID
		this.slotID = Warehouse.getInstance().assignSlotID();
	}
	
	public int getVolume() {
		return this.volume;
	}
	public int getFreeVolume() {
		return this.freeVolume;
	}
	public int getSlotID() {
		return this.slotID;
	}
	public void addItem(Item item) {
		items.add(item);
		this.freeVolume = this.volume - item.getDimensions();
	}
	public ArrayList<Item> getItemsList() {
		return items;
	}
	public void printItemsInSlot() {
		for(int i =0;i<items.size();i++) {
			System.out.println(i+1+". "+items.get(i).toString());
		}
	}
	
}
