package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.CmdAddItem;
import code.CmdDeliverExpiredItem;
import code.CmdListItemByID;
import code.CmdSetDate;
import code.Slot;
import code.SystemDate;
import code.Warehouse;

class CmdListItemByIDTest {

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
	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void testCmdListItemByID_01() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "listItemByID|1";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdListItemByID()).execute(cmdParts);
	    
	    String msg = "Checking when there is no items in the warehouse yet";
	    assertEquals("The warehouse is empty!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdListItemByID_02() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "addItem|3|10-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);
	    
	    String cmdLine1 = "listItemByID|0";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdListItemByID()).execute(cmdParts1);
	    
	    String msg = "Checking when ID is invalid";
	    assertEquals("Wrong ID number! It should be > 0", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdListItemByID_03() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "listItemByID|-3";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdListItemByID()).execute(cmdParts);
	    
	    String msg = "Checking when ID is invalid";
	    assertEquals("Wrong ID number! It should be > 0", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}

	@Test
	void testCmdListItemByID_04() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "addItem|1|12-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);
	 
	    String cmdLine1 = "listItemByID|2";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdListItemByID()).execute(cmdParts1);
	    
	    String msg = "Basic situation";
	    assertEquals("Current Slot #1 ;Dimensions for Item #2 is 1; Arrival Date: 1-Oct-2020, Departure Date: 12-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}

	@Test
	void testCmdListItemByID_05() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "startNewDay|11-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
	    
	    String cmdLine1 = "listItemByID|1";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdListItemByID()).execute(cmdParts1);
	    
	    String msg = "Checking when the item has expired delivery date";
	    assertEquals("There is no such item!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdListItemByID_06() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "listItemByID";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdListItemByID()).execute(cmdParts);
	    
	    String msg = "";
	    assertEquals("Insufficient command arguments!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}


}