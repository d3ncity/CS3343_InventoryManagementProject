package Test_V1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Code.Day;
import Code.Item;
import Code.Slot;
import Code.SystemDate;
import Code.Warehouse;

class TestWarehouseV1 {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testOptimize1() {
		//fail("Not yet implemented");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
		Item item2 = new Item(3,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
		Item item3 = new Item(2,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
		
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(5));
		slots.add(new Slot(5));
		slots.add(new Slot(5));
		slots.add(new Slot(5));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		
		
		Warehouse.createInstance(slots);
		Warehouse.getInstance().printItemsInSlots();
		
		//denny addition
		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		
		//denny addition
		Warehouse.getInstance().optimize(items);
	    
		assertEquals("Optimizing was done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
		
	}
	
	@Test
	public void testOptimize2() {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
		Item item2 = new Item(-1,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
		Item item3 = new Item(1,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
		
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(5));
		slots.add(new Slot(5));
		slots.add(new Slot(5));
		slots.add(new Slot(5));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		
		
		Warehouse.createInstance(slots);
		Warehouse.getInstance().printItemsInSlots();
		
		//denny addition
		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		
		//denny addition
		Warehouse.getInstance().optimize(items);
	    
		assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
		
	}



@Test
public void testOptimize3() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(0,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(0,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(0,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(1));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

@Test
public void testOptimize4() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(12,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(10,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(1));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

//Throwing an IndexOutofBound Exception
@Test
public void testOptimize5() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(5));
	slots.add(new Slot(1));
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

//When the value of the item is null, it should throw up an error.
@Test
public void testOptimize6() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = null;
	Item item2 = null;
	Item item3 = null;
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(1));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

@Test
public void testOptimize7() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(9,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(1,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(1));
	slots.add(new Slot(15));
	slots.add(new Slot(2));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

@Test
public void testOptimize8() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(9,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(1,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(0));
	slots.add(new Slot(0));
	slots.add(new Slot(0));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

//If the slot size is -ve, it should throw up an error.
@Test
public void testOptimize9() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(9,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(2,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(1));
	slots.add(new Slot(10));
	slots.add(new Slot(-1));
	
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

@Test
public void testOptimize10() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(5,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(4,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	Item item4 = new Item(1,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 4);
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(1));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(1));
	slots.add(new Slot(5));
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

//Case when the Arraylist of slots is null
@Test
public void testOptimize13() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(5,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(4,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	Item item4 = new Item(1,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 4);
	
	ArrayList<Slot> slots = null; //new ArrayList<>();
//	slots.add(new Slot(1));
//	slots.add(new Slot(5));
//	slots.add(new Slot(5));
//	slots.add(new Slot(1));
//	slots.add(new Slot(5));
	
//	slots.get(0).addItem(item1);
//	slots.get(1).addItem(item2);
//	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

//Case when there are no slots in the ArrayList
@Test
public void testOptimize14() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(5,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(4,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	Item item4 = new Item(1,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 4);
	
	ArrayList<Slot> slots = new ArrayList<>();
//	slots.add(new Slot(1));
//	slots.add(new Slot(5));
//	slots.add(new Slot(5));
//	slots.add(new Slot(1));
//	slots.add(new Slot(5));
	
//	slots.get(0).addItem(item1);
//	slots.get(1).addItem(item2);
//	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

@Test
public void testOptimize11() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(2147483647,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(9,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(1,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	slots.add(new Slot(5));
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was not done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}

@Test
public void testOptimize12() {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	
    Item item1 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
	Item item2 = new Item(1,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
	Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
	
	ArrayList<Slot> slots = new ArrayList<>();
	slots.add(new Slot(4));
	slots.add(new Slot(2));
	slots.add(new Slot(1));
	
	slots.get(0).addItem(item1);
	slots.get(1).addItem(item2);
	slots.get(2).addItem(item3);
	
	
	Warehouse.createInstance(slots);
	Warehouse.getInstance().printItemsInSlots();
	
	//denny addition
	ArrayList<Item> items = new ArrayList<>();
	items.add(item1);
	items.add(item2);
	items.add(item3);
	
	//denny addition
	Warehouse.getInstance().optimize(items);
    
	assertEquals("Optimizing was done.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	
}
		
}