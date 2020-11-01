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

class CheckAvailabilityTest {

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
	void testCheckAvailability_01() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot(1));
		slots.add(new Slot(2));
		slots.add(new Slot(3));
		slots.add(new Slot(4));
		slots.add(new Slot(5));
		
		Warehouse.getInstance().set
		
		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		
		boolean result = Warehouse.getInstance().checkAvailability(item1);
		String msg = "Checking when the warehouse is completely empty";
		assertEquals(true, result, msg);
	}
	
	
	@Test
	void testCheckAvailability_02() {
		fail("Not yet implemented");
	}
	
	@Test
	void testCheckAvailability_03() {
		fail("Not yet implemented");
	}
	
	@Test
	void testCheckAvailability_04() {
		fail("Not yet implemented");
	}
	
	@Test
	void testCheckAvailability_05() {
		fail("Not yet implemented");
	}

}
