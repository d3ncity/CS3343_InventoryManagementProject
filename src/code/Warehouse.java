package code;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Collections;
//import java.util.Queue;

public class Warehouse implements Functions{
	
	//Instance Field
	private ArrayList<Slot> slots;
	private int totalNoOfItems = 0;
	private int totalNoOfSlots = 0;
	private ArrayList<Item> queueList;
	private boolean automation = true;
	
	//Singleton Pattern
	private static Warehouse instance = new Warehouse();
	private Warehouse() {
		this.slots = new ArrayList<>();
		this.queueList = new ArrayList<>();
	}
	public static Warehouse getInstance() {return instance;}
	
	//Getters
	public ArrayList<Slot> getSlotList() {return slots;}
	public boolean getAutomationStatus() {return automation;}
	public void setAutomationStatus(boolean value) {this.automation = value;}
	public ArrayList<Item> getQueue(){return this.queueList;}
	
	/*
	 * Methods for Warehouse
	 * 1. warehouseHalfFull()
	 * 2. warehouseEmpty()
	 * 3. warehouseReset()
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
	
	public boolean warehouseEmpty() {
		if (slots.size() == 0) return true;
		else
			for (Slot s: slots)
				if (s.getItemsList().size() != 0) return false;
		return true;
	}
	
	public void warehouseReset() {
		this.totalNoOfItems = 0;
		this.totalNoOfSlots = 0;
		slots.clear();
		this.automation = true;
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
	public void addSlots(Slot slot) {
		slots.add(slot);
		Collections.sort(slots);
		System.out.println("Slot #" + slot.getSlotID() + " is created and added.");
	}
	public void removeSlots(Slot slot) {
		slots.remove(slot);
		System.out.println("Slot #" + slot.getSlotID() + " is removed.");
	}
	public int getTheLargestSlotSize() {return slots.get(slots.size()-1).getVolume();}
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
				System.out.print("Item #"+s.getItemsList().get(i).getItemID()+"(Size:" + s.getItemsList().get(i).getDimensions()+")");
				if (i != s.getItemsList().size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("]");
		}
		System.out.print("Items Queue: [");
		for (int i = 0; i < this.queueList.size(); i++) {
			System.out.print("Item #"+this.queueList.get(i).getItemID()+"(Size:" + this.queueList.get(i).getDimensions()+")");
			if (i != this.queueList.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
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
	//2. Search any available slots
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
	
	
	public void moveToSlot(Item item) {
		//For testing purposes
		added = false;
		
		//Get an available slot
		Slot s = this.searchSlotForItem(item);
		if(s != null) {
			s.addItem(item);
			item.setCurrentSlot(s);	
            
			//For testing purposes
			added = true;
		}
		else {
			System.out.println("Sorry. Currently there is no available slots. The item is added to Queue.");
			queueList.add(item);
			Collections.sort(queueList, new SortQueue());
			//For testing purposes
			added = false;
		}
		
		if(warehouseHalfFull() && automation) {
			optimize();
		}
	}
	
	//Moving item from the queue to the slot
	public void moveQueueItemToSlot() throws ExEmptyQueue{
		//If the queue is empty
		if(queueList.size()==0) {throw new ExEmptyQueue();}
		Item oldQueueItem = queueList.get(queueList.size()-1);
		moveToSlot(oldQueueItem);
		queueList.remove(queueList.size()-1);
	}
	
	public void printQueue() {
		System.out.print("[");
		for(Item i: queueList) {
			System.out.print(i.getDimensions());
				if (i != queueList.get(queueList.size() - 1))
					System.out.print(", ");
		}
		System.out.println("]");
	}
	
	//For test cases purposes
	private boolean added = true;
	public boolean testResult() {return added;}
	
	//Optimize Algorithm
	//Algorithm Step 1: Allocate the items to buffer
	private ArrayList<Item> itemsBuffer  = new ArrayList<>();	
	public void allocateAllItemToBuffer() {
		ArrayList<Item> temp = new ArrayList<>();
		for(Slot s: slots) {
			if (s.getFreeVolume() != s.getVolume()) {
				for (Item i : s.getItemsList()) {
					temp.add(i);				
					itemsBuffer.add(i);
				}
				for (int i = 0; i < temp.size(); i++)
					s.removeItem(temp.get(i));
				temp.clear();
			}
		}
	}
	
	public void optimize() {
		allocateAllItemToBuffer();
		Collections.sort(itemsBuffer);
		Optimize opt = new Optimize();
    	for (Slot s: slots) {
    		
    		//Find one item that can perfectly fit the slot.
            opt.findOnePerfectFit(itemsBuffer, s);
            
            //Find multiple items which can perfectly fit the slot when combine.
            if (!opt.getFound())
                for (int i = 0; i < s.getVolume(); i++)
                    opt.findOnePerfectSubsets(itemsBuffer, itemsBuffer.size(), s.getFreeVolume()-i, s); 

            //Remove the item from buffer list
            ArrayList<Item> optList = opt.getOptimizedItem();
            for (int i = 0; i < optList.size(); i++)
            	itemsBuffer.remove(optList.get(i));
            opt.reset();
    	}
	}	
	
}
