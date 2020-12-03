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

import code.CmdAddItem;
import code.CmdDeliverExpiredItem;
import code.CmdSetDate;
import code.Day;
import code.ExWrongDateFormat;
import code.Item;
import code.Slot;
import code.SystemDate;
import code.Warehouse;

class MoveToSlotTestV2 {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SystemDate.createTheInstance("1-Oct-2020");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Warehouse.getInstance().warehouseReset();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMoveToSlot_01() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);

		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		
		Warehouse.getInstance().printAllSlotsArray();
		
		String[] output = outContent.toString().split("\n");
		String msg = "Checking when the warehouse is completely empty";
		assertEquals("Slot #1 with Volume: 1, Remaining Volume: 0 : [Item #1(Size:1)]", output[output.length-6].trim(), msg);
	}
	
	
	@Test
	void testMoveToSlot_02() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);

		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(5,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(2,new Day("15-Oct-2020"),new Day("27-Oct-2020"));

		String msg = "Checking when the warehouse is completely full";
		assertEquals("Sorry. Currently there is no available slots. The item is added to Queue.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testMoveToSlot_03() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);

		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		
		Item newItem = new Item(2,new Day("15-Oct-2020"),new Day("27-Oct-2020"));
		
		Warehouse.getInstance().printAllSlotsArray();
		
		String[] output = outContent.toString().split("\n");
		String msg = "Checking when the slot has an item, but there are enough freeVolume for another item";
		assertEquals("Slot #5 with Volume: 5, Remaining Volume: 1 : [Item #6(Size:2), Item #5(Size:2)]", output[output.length-2].trim(), msg);
	}
	
	@Test
	void testMoveToSlot_04() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("19-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(5,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		
		String cmdLine = "startNewDay|19-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
		
		Item newItem = new Item(2,new Day("19-Oct-2020"),new Day("27-Oct-2020"));
		
		Warehouse.getInstance().printAllSlotsArray();
		
		String[] output = outContent.toString().split("\n");
		String msg = "Checking when the item expires in the same day as new item comes";
		assertEquals("Slot #2 with Volume: 2, Remaining Volume: 0 : [Item #6(Size:2)]", output[output.length-5].trim(), msg);
	}
	
	@Test
	void testMoveToSlot_05() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);

		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("19-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(5,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		
		String cmdLine = "startNewDay|21-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
		
		Item newItem = new Item(2,new Day("21-Oct-2020"),new Day("27-Oct-2020"));
		
		Warehouse.getInstance().printAllSlotsArray();
		
		String[] output = outContent.toString().split("\n");

		String msg = "Checking when all items have expired and the warehouse is empty";
		assertEquals("Slot #2 with Volume: 2, Remaining Volume: 0 : [Item #6(Size:2)]", output[output.length-5].trim(), msg);
	}
	
	@Test
	void testMoveToSlot_06() throws ExWrongDateFormat {
		Warehouse wh =  Warehouse.getInstance();
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item5 = new Item(5,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		
		Item newItem = new Item(6,new Day("21-Oct-2020"),new Day("27-Oct-2020"));
		
		boolean result = wh.testResult();
		String msg = "Checking when the item is bigger than any slot";
		assertEquals(false, result, msg);
	}
	
	/*@Test
	void testMoveToSlot_07() throws ExWrongDateFormat {
		Warehouse wh =  Warehouse.getInstance();
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		
		Item newItem = new Item(4,new Day("13-Oct-2020"),new Day("1-Oct-2020"));
		
		boolean result = wh.testResult();
		String msg = "Checking when the departure date is smaller than the system date and arrival date";
		assertEquals(false, result, msg);
	}
	
	@Test
	void testMoveToSlot_08() throws ExWrongDateFormat {
		Warehouse wh =  Warehouse.getInstance();
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(4,new Day("26-Oct-2020"),new Day("23-Oct-2020"));

		boolean result = wh.testResult();
		String msg = "Checking when the departure date is smaller than the arrival date";
		assertEquals(false, result, msg);
	}*/
	
	@Test
	void testMoveToSlot_09() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Warehouse wh =  Warehouse.getInstance();
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		
		String cmdLine = "addItem|-2|23-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);
		
		String[] output = outContent.toString().split("\n");

		String msg = "Checking when the size of an item is negative";
		assertEquals("The size should be >0 and <=5 (The largest slot size).", output[output.length-1].trim(), msg);
	}
	
	/*@Test
	void testMoveToSlot_10() throws ExWrongDateFormat {
		Warehouse wh =  Warehouse.getInstance();
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item newItem = new Item(4,new Day("41-Oct-2020"),new Day("33-Oct-2020"));

		boolean result = wh.testResult();
		String msg = "Checking when arrival and departure dates are not valid";
		assertEquals(false, result, msg);
	}*/
}
