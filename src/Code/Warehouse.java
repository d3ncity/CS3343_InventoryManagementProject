package Code;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Collections;

public class Warehouse implements Functions{
	//Singleton Pattern
	//Instance Field
	private static Warehouse instance = new Warehouse();
	private ArrayList<Slot> slots;
	public static Warehouse getInstance() {return instance;}
	
	//Constructor
	private Warehouse() {this.slots = new ArrayList<>();}
	public void setSlots(ArrayList<Slot> slots) {
		this.slots = slots;
	}
	
	public void addSlots(Slot slot) {
		slots.add(slot);
	}
	
//	public static void createInstance(ArrayList<Slot> slots) {
//		if(instance == null) {
//			instance = new Warehouse(slots);
//		}
//		else {
//			instance.set(slots);
//		}
//	}

	//Harvey V1.0 - ID Assignment
	private int totalNoOfItems = 0;
	private int totalNoOfSlots = 0;
	
	public int getTotalNoOfItems() {return totalNoOfItems;}
	public int assignItemID() {	return ++totalNoOfItems;}
	
	public int getTotalNoOfSlots() {return totalNoOfSlots;}
	public int assignSlotID() {return ++totalNoOfSlots;}

	
	//Functions - Display all Slots details
	public void printAllSlotsDetails() {
		for(Slot s: slots) {
			System.out.println("----------------------------------");
			if(s.getFreeVolume()==s.getVolume()){
				System.out.println("Slot #"+ s.getSlotID() +" is empty, volume is "+ s.getVolume());
			}
			else {
				if(s.getFreeVolume()==0){
					System.out.println("Slot #" + s.getSlotID() + " is Full!");
				}
				System.out.println("Slot #"+ s.getSlotID() +", volume is "+ s.getVolume()+ ", free volume is "+ s.getFreeVolume());
				s.printItemsInSlot();
			}
		}
		System.out.println("----------------------------------");
	}
	

	//Function - Moving an item to slot
	//Search for a suitable slots
	public Slot searchForSlot(Item item) {
		//First priority find the best fitted slots
		for (Slot s: slots) {
			if (s.getFreeVolume() == item.getDimensions())
				return s;
		}
		//Second priority allocate available slots
		for (Slot s: slots) {
			if (s.getFreeVolume() > item.getDimensions())
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
				System.out.println("Item #"+item.getItemID()+" is in Slot#"+ item.getCurrentSlot().getSlotID()+ " " + SystemDate.getInstance());
			}
			else {
				System.out.println("Error. The departure date is before or equals to system date.");
			}
		}
		else {
			System.out.println("Sorry. Currently there is no available slots.");
		}
		if (this.totalNoOfItems % 3 == 0) {
			//Things to discuss: point for optimization
//			optimize();
		}
	}
	
	//Optimize Algorithm
	private ArrayList<Item> itemsBuffer  = new ArrayList<>();	
	public void allocateAllItemToBuffer() {
		ArrayList<Item> temp = new ArrayList<>();
		for(Slot s: slots) {
			for (Item i : s.getItemsList()) {
				temp.add(i);				
				itemsBuffer.add(i);
			}
			for (int i = 0; i < temp.size(); i++) {
				s.removeItem(temp.get(i));
			}
			temp.clear();
		}
	}
	
	public void optimize() {
		allocateAllItemToBuffer();
		Collections.sort(itemsBuffer);
		Optimize opt = new Optimize();
    	for (Slot s: slots) {
            opt.findOnePerfectFit(itemsBuffer, s);
            for (int i = 0; i < s.getVolume(); i++) {
                opt.findOnePerfectSubsets(itemsBuffer, itemsBuffer.size(), s.getFreeVolume()-i, s); 
            }
            ArrayList<Item> optList = opt.getOptimizedItem();
            for (int i = 0; i < optList.size(); i++) {
            	itemsBuffer.remove(optList.get(i));
            }
            opt.reset();
    	}
	}
	
//	 // Denny - Optimize the storage function(Arrays of slots)  - void 
//	@Override
//	public void optimize(ArrayList<Item> items ) {
//		
//		//Please double-check the logic
//		//sort items by size (smallest to largest freeVolume)
//		//sort slots by size (smallest to largest dimensions)
//		
//		boolean wasOptimized  = false;
//		for(Item i: items) {
//			for(Slot s: slots) {
//				System.out.println("Item volume= " + i.getDimensions());
//				System.out.println("Slot FREE volume= " + s.getFreeVolume());
//				if(s.freeVolume>0 && s.getFreeVolume()==i.getDimensions()) {
//					System.out.println("Item current slot= " + i.getCurrentSlot().toString() + " " + i.getCurrentSlot());
//					i.getCurrentSlot().removeItem(i);
//					i.setCurrentSlot(s);
//					s.addItem(i);
//					System.out.println("Item was swapped!");
//					wasOptimized = true;
//				}
//			}
//		}
//		
//		if(wasOptimized) {
//			System.out.println("Optimizing was done.");
//		}
//	}
	
}
