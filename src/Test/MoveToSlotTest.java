package Test;

import static org.junit.jupiter.api.Assertions.*;

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

class MoveToSlotTest {
	Warehouse wh =  Warehouse.getInstance();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SystemDate.createTheInstance("1-Oct-2020");
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
	void testMoveToSlot_01() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		wh.setSlots(slots);
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		wh.moveToSlot(item1);
		boolean result = wh.testResult();
		String msg = "Checking when the warehouse is completely empty";
		assertEquals(true, result, msg);
	}
	
	
	@Test
	void testMoveToSlot_02() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(5,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(2,new Day("15-Oct-2020"),new Day("27-Oct-2020"));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		slots.get(3).addItem(item4);
		slots.get(4).addItem(item5);
		
		wh.setSlots(slots);
		
		wh.moveToSlot(newItem);
		boolean result = wh.testResult();
		String msg = "Checking when the warehouse is completely full";
		assertEquals(false, result, msg);
	}
	
	@Test
	void testMoveToSlot_03() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(2,new Day("15-Oct-2020"),new Day("27-Oct-2020"));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		slots.get(3).addItem(item4);
		slots.get(4).addItem(item5);
		
		wh.setSlots(slots);
		
		wh.moveToSlot(newItem);
		boolean result = wh.testResult();
		String msg = "Checking when the slot has an item, but there are enough freeVolume for another item";
		assertEquals(true, result, msg);
	}
	
	@Test
	void testMoveToSlot_04() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("19-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(5,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(2,new Day("19-Oct-2020"),new Day("27-Oct-2020"));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		slots.get(3).addItem(item4);
		slots.get(4).addItem(item5);
		
		wh.setSlots(slots);
		
		wh.moveToSlot(newItem);
		boolean result = wh.testResult();
		String msg = "Checking when the item expires in the same day as new item comes";
		assertEquals(true, result, msg);
	}
	
	@Test
	void testMoveToSlot_05() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("19-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(5,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(2,new Day("21-Oct-2020"),new Day("27-Oct-2020"));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		slots.get(3).addItem(item4);
		slots.get(4).addItem(item5);
		
		wh.setSlots(slots);
		
		wh.moveToSlot(newItem);
		boolean result = wh.testResult();
		String msg = "Checking when all items have expired and the warehouse is empty";
		assertEquals(true, result, msg);
	}
	
	@Test
	void testMoveToSlot_06() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(5,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(6,new Day("21-Oct-2020"),new Day("27-Oct-2020"));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		slots.get(3).addItem(item4);
		slots.get(4).addItem(item5);
		
		wh.setSlots(slots);
		
		wh.moveToSlot(newItem);
		boolean result = wh.testResult();
		String msg = "Checking when the item is bigger than any slot";
		assertEquals(false, result, msg);
	}
	
	@Test
	void testMoveToSlot_07() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(4,new Day("13-Oct-2020"),new Day("1-Oct-2020"));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		
		wh.setSlots(slots);
		
		wh.moveToSlot(newItem);
		boolean result = wh.testResult();
		String msg = "Checking when the departure date is smaller than the system date and arrival date";
		assertEquals(false, result, msg);
	}
	
	@Test
	void testMoveToSlot_08() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(4,new Day("26-Oct-2020"),new Day("23-Oct-2020"));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		
		wh.setSlots(slots);
		
		wh.moveToSlot(newItem);
		boolean result = wh.testResult();
		String msg = "Checking when the departure date is smaller than the arrival date";
		assertEquals(false, result, msg);
	}
	
	@Test
	void testMoveToSlot_09() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(-2,new Day("13-Oct-2020"),new Day("23-Oct-2020"));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		
		wh.setSlots(slots);
		
		wh.moveToSlot(newItem);
		boolean result = wh.testResult();
		String msg = "Checking when the size of an item is negative";
		assertEquals(false, result, msg);
	}
	
	@Test
	void testMoveToSlot_10() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(4,new Day("41-Oct-2020"),new Day("33-Oct-2020"));
		
		slots.get(0).addItem(item1);
		slots.get(1).addItem(item2);
		slots.get(2).addItem(item3);
		
		wh.setSlots(slots);
		
		wh.moveToSlot(newItem);
		boolean result = wh.testResult();
		String msg = "Checking when arrival and departure dates are not valid";
		assertEquals(false, result, msg);
	}
}
