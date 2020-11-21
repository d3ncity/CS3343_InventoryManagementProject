package Test_V1;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import Code.Day;
import Code.Item;
import Code.Slot;
import Code.SystemDate;
import Code.Warehouse;

public class TestMainV1 {
	
	@Test
	public void TestMain1()
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
		//Warehouse wh = Warehouse.getInstance();
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
		Item item2 = new Item(3,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
		Item item3 = new Item(2,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
		
		/*Item item1 = null;
		Item item2 = null;
		Item item3 = null;*/
		
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
		//wh.printItemsInSlots();
		
		//denny addition
		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
	    
		Warehouse.getInstance().optimize(items);
		//Warehouse.getInstance().printItemsInSlots();
		assertEquals("Optimizing was done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test //NullPointerException; cannot invoke Code.Item.getDimensions()
	public void TestMain2()
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
		//Warehouse wh = Warehouse.getInstance();
		
		/*Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
		Item item2 = new Item(3,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
		Item item3 = new Item(2,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);*/
		
		Item item1 = null;
		Item item2 = null;
		Item item3 = null;
		
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
		//wh.printItemsInSlots();
		
		//denny addition
		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
	    
		Warehouse.getInstance().optimize(items);
		//Warehouse.getInstance().printItemsInSlots();
		assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test //Need to show some othe ourpur like no items found or something similar since item size is 0
	public void TestMain3()
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
		//Warehouse wh = Warehouse.getInstance();
		
		Item item1 = new Item(0,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
		Item item2 = new Item(0,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
		Item item3 = new Item(0,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
		
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
		//wh.printItemsInSlots();
		
		//denny addition
		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
	    
		Warehouse.getInstance().optimize(items);
		//Warehouse.getInstance().printItemsInSlots();
		
		assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test //Should be wrong since it is also accepting negative item values
	public void TestMain4()
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
		
		Item item1 = new Item(-1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
		Item item3 = new Item(0,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
		
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
		//wh.printItemsInSlots();
		
		//denny addition
		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
	    
		Warehouse.getInstance().optimize(items);
		//Warehouse.getInstance().printItemsInSlots();
		
		assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test //Should be wrong since it is also accepting negative item values
	public void TestMain5()
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
		
		Item item1 = new Item(+1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
		Item item2 = new Item(+2,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
		Item item3 = new Item(-3,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
		
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
		//wh.printItemsInSlots();
		
		//denny addition
		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
	    
		Warehouse.getInstance().optimize(items);
		//Warehouse.getInstance().printItemsInSlots();
		
		assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test //Should be wrong since it is also accepting negative item values
	public void TestMain6()
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
		
		Item item1 = new Item(+1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
		Item item2 = new Item(+2,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
		
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
		//wh.printItemsInSlots();
		
		//denny addition
		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
	    
		Warehouse.getInstance().optimize(items);
		//Warehouse.getInstance().printItemsInSlots();
		
		assertEquals("Optimizing was done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}

	
}
