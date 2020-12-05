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

class TestDeliverExpiredItem {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	
	public void addItem(String cmdLine) {
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdAddItem()).execute(cmdParts);
	}
	
	public void startNewDay(String cmdLine) {
	    String [] cmdParts = cmdLine.split("\\|");
		(new CmdSetDate()).execute(cmdParts);
		(new CmdDeliverExpiredItem()).execute(cmdParts);
	}

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

	@Test
	void testCmdDeliverExpiredItem_01() throws ExWrongDateFormat {
		addItem("addItem|1|09-Oct-2020");
		addItem("addItem|2|09-Oct-2020");
		addItem("addItem|3|09-Oct-2020");
		addItem("addItem|4|09-Oct-2020");
		addItem("addItem|5|09-Oct-2020");
	    startNewDay("startNewDay|8-Oct-2020");
	    addItem("addItem|1|11-Oct-2020");
	    String[] output = outContent.toString().split("\n");
	    String msg = "Warehouse is full and there is no item that should be departured";
	    assertEquals("Sorry. Currently there is no available slots. The item is added to Queue.", output[output.length-1].trim(), msg);
	}
	
	@Test
	void testCmdDeliverExpiredItem_02() throws ExWrongDateFormat {
	    startNewDay("startNewDay|12-Oct-2020");
	    addItem("addItem|1|13-Oct-2020");
	    String[] output = outContent.toString().split("\n");
	    String msg = "Checking when all items should be departured due to date";
	    assertEquals("Item #7 with size(1) is added in Slot ID #1 ; Delivery Date: 13-Oct-2020", output[output.length-1].trim(), msg);
	}
	
	@Test
	void testCmdDeliverExpiredItem_03() throws ExWrongDateFormat {
		startNewDay("startNewDay|13-Oct-2020");
	    addItem("addItem|1|18-Oct-2020");
	    String[] output = outContent.toString().split("\n");
	    String msg = "Checking when some items should be departured on this date";
	    assertEquals("Item #8 with size(1) is added in Slot ID #1 ; Delivery Date: 18-Oct-2020", output[output.length-1].trim(), msg);
	}
	
	@Test
	void testCmdDeliverExpiredItem_04() throws ExWrongDateFormat {
		startNewDay("startNewDay|43-Dec-2020");
		String[] output = outContent.toString().split("\n");
	    String msg = "Checking when some items should be departured on this date";
	    assertEquals("The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.", output[output.length-1].trim(), msg);
	}	
	
	@Test
	void testCmdDeliverExpiredItem_05() throws ExWrongDateFormat {
		startNewDay("startNewDay");
		String[] output = outContent.toString().split("\n");
	    String msg = "Checking when some items should be departured on this date";
	    assertEquals("Insufficient command arguments!", output[output.length-1].trim(), msg);
	}
}
