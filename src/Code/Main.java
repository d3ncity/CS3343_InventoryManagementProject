package Code;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		
		SystemDate.createTheInstance("13-Oct-2020");
		
//		System.out.println(SystemDate.getInstance());
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
		Item item2 = new Item(3,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
		Item item3 = new Item(2,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
		
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(5);
		Slot slot3 = new Slot(5);
		Slot slot4 = new Slot(5);
		Slot slot5 = new Slot(5);
		
		slot1.addItem(item1);
		slot2.addItem(item2);
		slot3.addItem(item3);
		
		
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(slot1);
		slots.add(slot2);
		slots.add(slot3);
		slots.add(slot4);
		slots.add(slot5);
		
		
		Warehouse.createInstance(slots);
		Warehouse.getInstance().printItemsInSlots();
		
		//denny addition
		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		
		//denny addition
		Warehouse.getInstance().optimize(items);
		
		Warehouse.getInstance().printItemsInSlots();
		//KALYS - Main Class with requests
		
		
		
		
	}
}
