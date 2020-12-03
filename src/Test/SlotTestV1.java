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

import code.Day;
import code.ExWrongDateFormat;
import code.Item;
import code.Slot;
import code.SystemDate;
import code.Warehouse;

class SlotTestV1 {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SystemDate.createTheInstance("1-Oct-2020");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Warehouse.getInstance().warehouseReset();;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSlot_01() throws NumberFormatException, ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Slot slot1 = new Slot(1);
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    
	    slot1.printItemsInSlot();
	  
	    assertEquals("1. Dimensions for Item #1 is 1, Arrival Date: 1-Oct-2020, Departure Date: 10-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test
	void testSlot_02() throws NumberFormatException, ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Slot slot1 = new Slot(3);
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    Item item2 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    
	    slot1.removeItem(item2);
	    slot1.printItemsInSlot();
	  
	    assertEquals("1. Dimensions for Item #1 is 1, Arrival Date: 1-Oct-2020, Departure Date: 10-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test
	void testSlot_03() { 
	    Slot slot1 = new Slot(5);

	    assertEquals(5, slot1.getVolume());
	}
	
	@Test
	void testSlot_04() throws NumberFormatException, ExWrongDateFormat {

	    Slot slot1 = new Slot(5);
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    Item item2 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	  
	    assertEquals(3, slot1.getFreeVolume());
	}
	
	@Test
	void testSlot_05() throws NumberFormatException, ExWrongDateFormat {

	    Slot slot1 = new Slot(5);
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    Item item2 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	  
	    assertEquals(1, slot1.getSlotID());
	}
	
	@Test
	void testSlot_06() throws NumberFormatException, ExWrongDateFormat {
		ArrayList<Item> list = new ArrayList<Item>();
	    Slot slot1 = new Slot(5);
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    Item item2 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    list.add(item1);
	    list.add(item2);
	  
	    assertEquals(list, slot1.getItemsList());
	}
	
	@Test
	void testSlot_07() {
	    Slot slot1 = new Slot(5);
	    Slot slot2 = new Slot(3);

	    
	    assertEquals(1, slot1.compareTo(slot2));
	}
	
	@Test
	void testSlot_08() {
	    Slot slot1 = new Slot(3);
	    Slot slot2 = new Slot(5);

	    
	    assertEquals(-1, slot1.compareTo(slot2));
	}
	
	@Test
	void testSlot_09() {
	    Slot slot1 = new Slot(5);
	    Slot slot2 = new Slot(5);

	    
	    assertEquals(0, slot1.compareTo(slot2));
	}
}
