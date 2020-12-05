package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.*;

class ItemTestV1 {

	@BeforeAll
	@SuppressWarnings("unused")
	static void setUpBeforeClass() throws Exception {
		Warehouse.getInstance().warehouseReset();
		SystemDate.createTheInstance("1-Oct-2020");
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
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
	void testItem_01() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    System.out.print(item1.getItemDetails());
	    
	    String[] output = outContent.toString().split("\n");
	    
	    assertEquals("Current Slot #1 ;Dimensions for Item #1 is 1; Arrival Date: 1-Oct-2020, Departure Date: 10-Oct-2020", output[output.length-1].trim());
	}
	
	@Test
	void testItem_02() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("11-Oct-2020"));
	    Day arr = item1.getArrivalDate();
	    System.out.println(arr);
	  
	    assertEquals("1-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test
	void testItem_03() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("11-Oct-2020"));
	    Day arr = item1.getDepartureDate();
	    System.out.println(arr);

	    assertEquals("11-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test
	void testItem_04() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	  
	    assertEquals(4,item1.getItemID());
	}
	
	@Test
	void testItem_05() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(3, SystemDate.getInstance(), new Day("10-Oct-2020"));
	  
	    assertEquals(3,item1.getDimensions());
	}
	
	@Test
	void testItem_06() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Slot newSlot = new Slot(5);
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    item1.setCurrentSlot(newSlot);
	  
	    assertEquals(newSlot, item1.getCurrentSlot());
	}
	
	@Test
	void testItem_07() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(3, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    Item item2 = new Item(2, SystemDate.getInstance(), new Day("10-Oct-2020"));
	  
	    assertEquals(1, item1.compareTo(item2));
	}
	
	@Test
	void testItem_08() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(2, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    Item item2 = new Item(2, SystemDate.getInstance(), new Day("10-Oct-2020"));
	  
	    assertEquals(0, item1.compareTo(item2));
	}
	
	@Test
	void testItem_09() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    Item item2 = new Item(2, SystemDate.getInstance(), new Day("10-Oct-2020"));
	  
	    assertEquals(-1, item1.compareTo(item2));
	}
	
	@Test
	void testItem_10() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(1, SystemDate.getInstance(), new Day("10-Oct-2020"));
	    System.out.println(item1);
	  
	    assertEquals("Dimensions for Item #13 is 1, Arrival Date: 1-Oct-2020, Departure Date: 10-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}

}
