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

class TestCmdListItemByID {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@SuppressWarnings("unused")
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Warehouse.getInstance().warehouseReset();
		SystemDate.createTheInstance("1-Oct-2020");
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	    System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	void tearDown() throws Exception {

	}
	
	public void startNewDay(String cmdLine) {
	    String [] cmdParts = cmdLine.split("\\|");
		(new CmdSetDate()).execute(cmdParts);
		(new CmdDeliverExpiredItem()).execute(cmdParts);
		(new CmdAddItemFromQueue()).execute(cmdParts);
	}

	public void addItem(String cmdLine) {
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdAddItem()).execute(cmdParts);
	}

	public void listItemByID(String cmdLine) {
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdListItemByID()).execute(cmdParts);
	}
	@Test
	void testCmdListItemByID_01() {
	   	listItemByID("listItemByID|1");
	    String msg = "Checking when there is no items in the warehouse yet";
	    assertEquals("Warehouse is Empty!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdListItemByID_02() {
		addItem("addItem|3|10-Oct-2020");
	    listItemByID("listItemByID|0");
	    String msg = "Checking when ID is invalid";
	    assertEquals("Wrong Input format! The input should be >0.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdListItemByID_03() {
		listItemByID("listItemByID|-3");
	    String msg = "Checking when ID is invalid";
	    assertEquals("Wrong Input format! The input should be >0.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}

	@Test
	void testCmdListItemByID_04() {
		addItem("addItem|1|12-Oct-2020");
	    listItemByID("listItemByID|2");
	    String msg = "Basic situation";
	    assertEquals("Current Slot #1 ;Dimensions for Item #2 is 1; Arrival Date: 1-Oct-2020, Departure Date: 12-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}

	@Test
	void testCmdListItemByID_05() {
		startNewDay("startNewDay|11-Oct-2020");
	    listItemByID("listItemByID|1");
	    String msg = "Checking when the item has expired delivery date";
	    assertEquals("Item not found! Please check the ID again!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdListItemByID_06() {
		listItemByID("listItemByID");
	    String msg = "";
	    assertEquals("Insufficient command arguments!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdListItemByID_07() {
		listItemByID("listItemByID|abc");
	    String msg = "Check if output is integer!";
	    assertEquals("The ID input should be integer!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}


}
