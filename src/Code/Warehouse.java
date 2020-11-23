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
<<<<<<< HEAD
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
	 */
	public boolean warehouseHalfFull() {
		int totalVolume = 0;
		int totalFreeVolume = 0;
		for (Slot s: slots) {
			totalVolume += s.getVolume();
			totalFreeVolume += s.getFreeVolume();
		}
		return totalFreeVolume <= (totalVolume/2);
=======
	private ArrayList<Item> queueList;
	
	//Singleton Pattern
	private static Warehouse instance = new Warehouse();
	private Warehouse() {
		this.slots = new ArrayList<>();
		this.queueList = new ArrayList<>();
	}
	public static Warehouse getInstance() {return instance;}
	
	//Getters
	public ArrayList<Slot> getSlotList() {return slots;}
	
	/*
	 * Methods for Warehouse
	 * 1. WarehouseHalfFull()
	 * 2. WarehouseEmpty()
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
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
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
<<<<<<< HEAD
//	public void setSlots(ArrayList<Slot> slots) {this.slots = slots;}
=======
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
	public void addSlots(Slot slot) {
		slots.add(slot);
		Collections.sort(slots);
		System.out.println("Slot #" + slot.getSlotID() + " is created and added.");
	}
	public void removeSlots(Slot slot) {
		slots.remove(slot);
		System.out.println("Slot #" + slot.getSlotID() + " is removed.");
	}
<<<<<<< HEAD
	public int getTheLargestSlotSize() {
		return slots.get(slots.size()-1).getVolume();
	}
=======
	public int getTheLargestSlotSize() {return slots.get(slots.size()-1).getVolume();}
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
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
<<<<<<< HEAD
	//2. Search any availble slots
=======
	//2. Search any available slots
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
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
	
<<<<<<< HEAD
	ArrayList<Item> queueList = new ArrayList<>();
	@Override
=======
	
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
	public void moveToSlot(Item item) {
		//For testing purposes
		added = false;
		
		//Get an available slot
		Slot s = this.searchSlotForItem(item);
		if(s != null) {
			s.addItem(item);
			item.setCurrentSlot(s);	
<<<<<<< HEAD
            //if (s.getFreeVolume() == 0) 
            	//System.out.println("Slot #"+ s.getSlotID() +" is Full!");
=======
            if (s.getFreeVolume() == 0) 
            	System.out.println("Slot #"+ s.getSlotID() +" is Full!");
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
            
			//For testing purposes
			added = true;
		}
		else {
			System.out.println("Sorry. Currently there is no available slots. The item is added to Queue.");
			queueList.add(item);
<<<<<<< HEAD
=======
			Collections.sort(queueList, new SortQueue());
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
			//For testing purposes
			added = false;
		}
		
<<<<<<< HEAD
		/*if(warehouseHalfFull()) {
			optimize();
		}*/
		
	}
	
	//For Denny's Function
	public void moveQueueItemToSlot() {

=======
		if(warehouseHalfFull())
			optimize();
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
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
	}
	
	//For test cases purposes
	private boolean added = true;
	public boolean testResult() {return added;}
	
	//Optimize Algorithm
	private ArrayList<Item> itemsBuffer  = new ArrayList<>();	
	public void allocateAllItemToBuffer() {
		ArrayList<Item> temp = new ArrayList<>();
		for(Slot s: slots) {
			for (Item i : s.getItemsList()) {
				temp.add(i);				
				itemsBuffer.add(i);
			}
			for (int i = 0; i < temp.size(); i++)
				s.removeItem(temp.get(i));
			temp.clear();
		}
	}
	
	public void optimize() {
		allocateAllItemToBuffer();
		Collections.sort(itemsBuffer);
		Optimize opt = new Optimize();
    	for (Slot s: slots) {
    		
    		//Find one item that can perfectly fit the slot.
            opt.findOnePerfectFit(itemsBuffer, s);
            if (s.getFreeVolume() == 0) 
            	System.out.println("Slot #"+ s.getSlotID() +" is Full!");
            
<<<<<<< HEAD
            if (!opt.getFound()) {
                for (int i = 0; i < s.getVolume(); i++) {
                    opt.findOnePerfectSubsets(itemsBuffer, itemsBuffer.size(), s.getFreeVolume()-i, s); 
                }
            }
            
=======
            //Find multiple items which can perfectly fit the slot when combine.
            if (!opt.getFound())
                for (int i = 0; i < s.getVolume(); i++)
                    opt.findOnePerfectSubsets(itemsBuffer, itemsBuffer.size(), s.getFreeVolume()-i, s); 

            //Remove the item from buffer list
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
            ArrayList<Item> optList = opt.getOptimizedItem();
            for (int i = 0; i < optList.size(); i++)
            	itemsBuffer.remove(optList.get(i));
            opt.reset();
    	}
	}	
	
<<<<<<< HEAD
}
=======
}
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
