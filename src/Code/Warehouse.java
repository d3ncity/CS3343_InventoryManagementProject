package Code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
//import java.util.Collections;
import java.util.Queue;

public class Warehouse implements Functions{
	
	//Instance Field
	private ArrayList<Slot> slots;
	private int totalNoOfItems = 0;
	private int totalNoOfSlots = 0;
	private final int AUTONUM = 3;
	
	//Singleton Pattern
	private static Warehouse instance = new Warehouse();
	private Warehouse() {this.slots = new ArrayList<>();}
	public static Warehouse getInstance() {return instance;}
	
	//Getters
	public int getAutoNum() {return AUTONUM;}
	public ArrayList<Slot> getSlotList() {return slots;}
	
	/*
	 * Methods for Warehouse
	 * 1. WarehouseHalfFull(){
	 */
	public boolean warehouseHalfFull() {
		int totalVolume = 0;
		int totalFreeVolume = 0;
		for (Slot s: slots) {
			totalVolume += s.getVolume();
			totalFreeVolume += s.getFreeVolume();
		}
		return totalFreeVolume <= (totalVolume/2);
	}
	
	/*
	 * Methods for ID
	 * 1. getTotalNoOfItems
	 * 2. assignItemID
	 * 3. getTotalNoOfSlots
	 * 4. assignSlotID
	 */
	public int getTotalNoOfItems() {return totalNoOfItems;}
	public int assignItemID() {return ++totalNoOfItems;}
	
	public int getTotalNoOfSlots() {return totalNoOfSlots;}
	public int assignSlotID() {return ++totalNoOfSlots;}
	
	/*
	 * Methods of Slots
	 * 1. +addSlots
	 * 2. +removeSlots
	 * 3. +getTheLargestSlotSize
	 * 4. +findSlotByID
	 */
//	public void setSlots(ArrayList<Slot> slots) {this.slots = slots;}
	public void addSlots(Slot slot) {
		slots.add(slot);
		Collections.sort(slots);
		System.out.println("Slot #" + slot.getSlotID() + " is created and added.");
	}
	public void removeSlots(Slot slot) {
		slots.remove(slot);
		System.out.println("Slot #" + slot.getSlotID() + " is removed.");
	}
	public int getTheLargestSlotSize() {
		return slots.get(slots.size()-1).getVolume();
	}
	public Slot findSlotByID(int ID) {
		for (Slot s: slots) {
			if (s.getSlotID() == ID)
				return s;
		}
		return null;
	}

	/*
	 * Methods for printing
	 * 1. +printAllSlotsDetails
	 * 2. +printAllSlotsArray
	 */
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
	
	public void printAllSlotsArray() {
		for(Slot s: slots) {
			System.out.print("Slot #" + s.getSlotID() + " with Volume: "+ s.getVolume() + ", Remaining Volume: "+ s.getFreeVolume()+" : [");
			for (int i = 0; i < s.getItemsList().size(); i++) {
				System.out.print(s.getItemsList().get(i).getDimensions());
				if (i != s.getItemsList().size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("]");
		}
	}

	/*
	 * Methods for item
	 * 1. +findItemByID
	 * 2. -searchSlotForItem
	 * 3. + moveToSlot
	 */
	
	public Item findItemByID(int ID) {
		for (Slot s: slots) {
			for(Item i : s.getItemsList()) {
				if (i.getItemID() == ID)
					return i;
			}
		}
		return null;
	}
	
	//1. Search for perfect fitted slot
	//2. Search any availble slots
	private Slot searchSlotForItem(Item item) {
		for (Slot s: slots) {
			if (s.getFreeVolume() == item.getDimensions())
				return s;
		}
		for (Slot s: slots) {
			if (s.getFreeVolume() > item.getDimensions())
				return s;
		}
		return null;
	}
	
	ArrayList<Item> queueList = new ArrayList<>();
	@Override
	public void moveToSlot(Item item) {
		//For testing purposes
		added = false;
		
		//Get an available slot
		Slot s = this.searchSlotForItem(item);
		if(s != null) {
			s.addItem(item);
			item.setCurrentSlot(s);	
            if (s.getFreeVolume() == 0) 
            	System.out.println("Slot #"+ s.getSlotID() +" is Full!");
            
			//For testing purposes
			added = true;
		}
		else {
			System.out.println("Sorry. Currently there is no available slots. The item is added to Queue.");
			queueList.add(item);
			Collections.sort(queueList);
			//For testing purposes
			added = false;
		}
		
		if(warehouseHalfFull()) {
			optimize();
		}
		
	}
	
	//For Denny's Function
	public void moveQueueItemToSlot() throws ExEmptyQueue{
		
		//empty queue
		if(queueList.size()==0) {
			throw new ExEmptyQueue();
		}
		
		//dequeue and put into slot
		Item oldQueueItem = queueList.get(0);
		moveToSlot(oldQueueItem);
		queueList.remove(0);
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
            if (s.getFreeVolume() == 0) 
            	System.out.println("Slot #"+ s.getSlotID() +" is Full!");
            
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
