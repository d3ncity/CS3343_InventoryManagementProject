package Code;

import java.util.ArrayList;
//import java.util.Collections;

public class Warehouse implements Functions{

	private static Warehouse instance = null;
	
	private ArrayList<Slot> slots;
	
	private Warehouse(ArrayList<Slot> slots) {
		this.slots = slots;
	}
	
	public static Warehouse getInstance() {
		
		return instance;
	}

	//Harvey V1.0 - ID Assignment
	
	private int totalNoOfItems = 0;
	private int totalNoOfSlots = 0;
	
	public int getTotalNoOfItems() {return totalNoOfItems;}
	public int assignItemID() {	return totalNoOfItems++;}
	
	public int getTotalNoOfSlots() {return totalNoOfSlots;}
	public int assignSlotID() {return totalNoOfSlots++;}

	
	public void set(ArrayList<Slot> slots) {
		this.slots = slots;
		
	}
	public static void createInstance(ArrayList<Slot> slots ) {
		if(instance == null) {
			instance = new Warehouse(slots);
		}
		else {
			instance.set(slots);
		}
	}
		
	public void printItemsInSlots() {
		for(int i=0;i<slots.size();i++) {
			
			if(slots.get(i).getFreeVolume()==slots.get(i).getVolume()){
				System.out.println("Slot "+(i+1)+" is empty, volume is "+ slots.get(i).getVolume());
				System.out.println();
			}
			else {
				if(slots.get(i).getFreeVolume()==0){
					System.out.println("Slot " + (i+1) + " is Full!");
				}
				System.out.println("Slot "+(i+1)+", volume is "+ slots.get(i).getVolume()+ ", free volume is "+ slots.get(i).getFreeVolume());
				slots.get(i).printItemsInSlot();
				System.out.println();
			}
		}
	}
	
	//Harvey - putting in slots don't forget to check SystemDate
	public Slot searchForSlot(Item item) {
		for (Slot s: slots) {
			if (s.getFreeVolume() >= item.getDimensions())
				return s;
		}
		return null;
	}
	
	
	@Override
	public void moveToSlot(Item item) {
		//Get an available slot
		Slot s = this.searchForSlot(item);
		if(s != null) {
			//Check SystemDate
			if (item.getDepartureDate().compareTo(SystemDate.getInstance()) > 0) {
				s.addItem(item);
				item.setCurrentSlot(s);
				System.out.println("Item #"+item.getItemID()+" is in Slot#slotnumber"+ item.getCurrentSlot().getSlotID() + SystemDate.getInstance());
			}
			else {
				System.out.println("Error. The departure date is before or equals to system date.");
			}
		}
		else {
			System.out.println("Sorry. Currently there is no available slots.");
		}
		
	}
	
	 // Denny - Optimize the storage function(Arrays of slots)  - void 
	@Override
	public void optimize(ArrayList<Item> items ) {
		
		//Please double-check the logic
		//sort items by size (smallest to largest freeVolume)
		//sort slots by size (smallest to largest dimensions)
		
		boolean wasOptimized  = false;
		for(Item i: items) {
			for(Slot s: slots) {
				System.out.println("Item volume= " + i.getDimensions());
				System.out.println("Slot FREE volume= " + s.getFreeVolume());
				if(s.freeVolume>0 && s.getFreeVolume()==i.getDimensions()) {
					System.out.println("Item current slot= " + i.getCurrentSlot().toString() + " " + i.getCurrentSlot());
					i.getCurrentSlot().removeItem(i);
					i.setCurrentSlot(s);
					s.addItem(i);
					System.out.println("Item was swapped!");
					wasOptimized = true;
				}
			}
		}
		
		if(wasOptimized) {
			System.out.println("Optimizing was done.");
		}
	}
	
}
