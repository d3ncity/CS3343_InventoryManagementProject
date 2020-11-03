package Code;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Collections;

public class Warehouse implements Functions{
	
	//Singleton Pattern
	//Instance Field
	private static Warehouse instance = new Warehouse();
	private ArrayList<Slot> slots;
	
	//Instance Getter
	public static Warehouse getInstance() {return instance;}
	
	//Constructor
	private Warehouse() {this.slots = new ArrayList<>();}
	
	// V2: #1 Methods of Slots Start ------------------------------ >
	public void setSlots(ArrayList<Slot> slots) {this.slots = slots;}
	public void addSlots(Slot slot) {
		slots.add(slot);
		//Harvey V3: Collection Sort for newly added function
		Collections.sort(slots);
	}
	
	public int getTheLargestSlotSize() {
		//Since we added Collections.sort(slots)
		//We can get the largest slot by getting the size of last slot in slots arrayList
		return slots.get(slots.size()-1).getVolume();
	}
	// V2: #1 Methods of Slots End ------------------------------ >

	// V2: #2 Methods of ID Assignment Start ------------------------------ >
	private int totalNoOfItems = 0;
	private int totalNoOfSlots = 0;
	
	public int getTotalNoOfItems() {return totalNoOfItems;}
	public int assignItemID() {	return ++totalNoOfItems;}
	
	public int getTotalNoOfSlots() {return totalNoOfSlots;}
	public int assignSlotID() {return ++totalNoOfSlots;}
	
	// V2: #2 Methods of ID Assignment End ------------------------------ >
	
	// V1: #3 Methods of Display All Slots Details Start ------------------------------ >
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
	// V1: #3 Methods of Display All Slots Details End ------------------------------ >
	

	// V2: #4 Methods of moving an item to slot when the item is created
	
	// V3: #4.1 Check SystemDate
	// If Arrival Date || Departure Date < SystemDate
	// If Departure Date > Arrival Date
	// Throws Exception
	public boolean checkSystemDate() {
		
		
		
		return true;
	}
	
	//V3: #4.2 Check Item Size
	
	
	
	
	// V2: #4.4 Search for a suitable slots
	public Slot searchForSlot(Item item) {
		//1. Find the best fitted slot
		for (Slot s: slots) {
			if (s.getFreeVolume() == item.getDimensions())
				return s;
		}
		//2. Allocate available slot
		for (Slot s: slots) {
			if (s.getFreeVolume() > item.getDimensions())
				return s;
		}
		return null;
	}
	
	@Override
	public void moveToSlot(Item item) {
		//For testing purposes
		added = false;
		
		//Get an available slot
		Slot s = this.searchForSlot(item);
		if(s != null) {
			//Check SystemDate
			if (item.getDepartureDate().compareTo(SystemDate.getInstance()) > 0) {
				s.addItem(item);
				item.setCurrentSlot(s);
				System.out.println("Item #"+item.getItemID()+" is in Slot#"+ item.getCurrentSlot().getSlotID()+ " " + SystemDate.getInstance());
				
				//For testing purposes
				added = true;
			}
			else {
				System.out.println("Error. The departure date is before or equals to system date.");
				
				
				//For testing purposes
				added = false;
			}
		}
		else {
			System.out.println("Sorry. Currently there is no available slots.");
			
			//For testing purposes
			added = false;
		}
		if (this.totalNoOfItems % 3 == 0) {
			//Things to discuss: point for optimization
//			optimize();
		}
	}
	
	//For test cases purposes
	private boolean added = true;
	public boolean testResult() {
		return added;
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
            if (!opt.getFound()) {
                for (int i = 0; i < s.getVolume(); i++) {
                    opt.findOnePerfectSubsets(itemsBuffer, itemsBuffer.size(), s.getFreeVolume()-i, s); 
                }
            }
            ArrayList<Item> optList = opt.getOptimizedItem();
            for (int i = 0; i < optList.size(); i++) {
            	itemsBuffer.remove(optList.get(i));
            }
            opt.reset();
    	}
	}	
}
