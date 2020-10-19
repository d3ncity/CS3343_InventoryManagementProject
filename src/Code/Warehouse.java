package Code;

import java.util.ArrayList;
import java.util.Collections;

public class Warehouse implements Functions{

	private static Warehouse instance = null;
	
	private ArrayList<Slot> slots;
	
	private Warehouse(ArrayList<Slot> slots) {
		this.slots = slots;
	}
	public static Warehouse getInstance() {
		
		return instance;
	}

//	Harvey V1.0 Things to Discuss#1. Please also refer to Item.java.
//	private int totalNumbersOfItems = 0;
//	
//	public int getTotalNumbersOfItems() {
//		return totalNumbersOfItems;
//	}
//	
//	public int assignItemsID() {
//		return totalNumbersOfItems++;
//	}
	
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
				System.out.println("Slot "+(i+1)+", volume is "+ slots.get(i).getVolume()+ ", free volume is "+ slots.get(i).getFreeVolume());
				slots.get(i).printItemsInSlot();
				System.out.println();
			}
		}
	}
	
	
	//Nursultan - check availability don't forget to check SystemDate
	@Override
	public boolean checkAvailability(Item item) {
		
		return true;
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
		if(checkAvailability(item)) {
			//Get an available slot
			Slot s = this.searchForSlot(item);
			
			//Check SystemDate
			if (item.getDepartureDate().compareTo(SystemDate.getInstance()) > 0) {
				s.addItem(item);
				System.out.print("Item #"+item.getID()+" is in Slot#slotnumber"+ slots.indexOf(s) + SystemDate.getInstance());
			}
			else {
				System.out.print("Error. The departure date is before or equals to system date.");
			}
			
			
		}
		
	}
	
	 // Denny - Optimize the storage function(Arrays of slots)  - void 
	@Override
	public void optimize() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

	
	
	
	
}
