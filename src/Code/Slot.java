package Code;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Slot {

	private int volume;
	public int freeVolume;
	private ArrayList<Item> items;
	public Slot(int volume) {
		this.volume = volume;
		freeVolume = volume;
		items= new ArrayList<>();
	}
	
	public int getVolume() {
		return this.volume;
		
	}
	public int getFreeVolume() {
		return this.freeVolume;
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
	
	public void updateSlot() {
		Iterator<Item> itr = items.iterator();
		
		while (itr.hasNext()) {
			Item item = itr.next(); 
			if (item.getDepartureDate().less(SystemDate.getInstance()) || item.getDepartureDate().equals(SystemDate.getInstance())) { 
				this.freeVolume += item.getDimensions();
				itr.remove(); 
			} 
		}
	}
	
}
