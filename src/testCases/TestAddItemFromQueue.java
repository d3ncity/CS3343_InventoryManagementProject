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

class TestAddItemFromQueue {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream stdOut = System.out;
	InputCommand in = new InputCommand();
	
	@SuppressWarnings("unused")
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Warehouse.getInstance().warehouseReset();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Warehouse.getInstance().warehouseReset();
		SystemDate.createTheInstance("1-Oct-2020");
		System.setOut(new PrintStream(outContent));
		addSlot("addSlot|1");
		addSlot("addSlot|2");
		addSlot("addSlot|3");
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
	
	public void addSlot(String cmdLine) {
		String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	}
	
	public void addItem(String cmdLine) {
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdAddItem()).execute(cmdParts);
	}
	
	
	@Test
	void testCmdAddItemFromQueue_01() {
	    addItem("addItem|1|10-Oct-2020");
	    addItem("addItem|2|10-Oct-2020");
	    addItem("addItem|3|10-Oct-2020");
	    addItem("addItem|3|22-Oct-2020");	    
	    startNewDay("startNewDay|11-Oct-2020");
    	(new CmdVisualize()).execute(null);
    	String[] output = outContent.toString().split("\n");
	    assertEquals("Slot #3 with Volume: 3, Remaining Volume: 0 : [Item #4(Size:3)]", output[output.length-2].trim());
	}
	
	@Test
	void testCmdAddItemFromQueue_02() {    
	    addItem("addItem|1|22-Oct-2020");
	    addItem("addItem|2|22-Oct-2020");
	    addItem("addItem|3|26-Oct-2020");
	    addItem("addItem|3|26-Oct-2020");
	    startNewDay("startNewDay|23-Oct-2020");
    	(new CmdVisualize()).execute(null); 
    	String[] output = outContent.toString().split("\n");
    	assertEquals("Slot #3 with Volume: 3, Remaining Volume: 0 : [Item #3(Size:3)]", output[output.length-2].trim());
	    assertEquals("Items Queue: [Item #4(Size:3)]", output[output.length-1].trim());
	}
	
	@Test
	void testCmdAddItemFromQueue_03() {
		addItem("addItem|1|26-Oct-2020");
		addItem("addItem|2|26-Oct-2020");
		addItem("addItem|3|26-Oct-2020");
	    startNewDay("startNewDate|27-Oct-2020");
	    addItem("addItem|3|2-Nov-2020");
  	    String[] output = outContent.toString().split("\n");
	    assertEquals("Item #4 with size(3) is added in Slot ID #3 ; Delivery Date: 2-Nov-2020", output[output.length-1].trim());
	}
	
	@Test
	void testCmdAddItemFromQueue_04() {
		addItem("addItem|3|2-Nov-2020");
		addItem("addItem|2|29-Oct-2020");
  	    addItem("addItem|1|10-Nov-2020");
  	    addItem("addItem|1|10-Nov-2020");
	    addItem("addItem|1|10-Nov-2020");
  	    startNewDay("startNewDay|1-Nov-2020");
    	(new CmdVisualize()).execute(null);
    	String[] output = outContent.toString().split("\n");
    	assertEquals("Slot #2 with Volume: 2, Remaining Volume: 0 : [Item #4(Size:1), Item #5(Size:1)]", output[output.length-3].trim());
	}
	
	@Test
	void testCmdAddItemFromQueue_05() {    
	    addItem("addItem|1|23-Nov-2020");
	    addItem("addItem|2|23-Nov-2020");
	    addItem("addItem|3|26-Nov-2020");
	    addItem("addItem|3|26-Nov-2020");
	    addItem("addItem|3|26-Nov-2020");
    	(new CmdVisualize()).execute(null); 
    	String[] output = outContent.toString().split("\n");
    	assertEquals("Slot #3 with Volume: 3, Remaining Volume: 0 : [Item #3(Size:3)]", output[output.length-2].trim());
	    assertEquals("Items Queue: [Item #4(Size:3), Item #5(Size:3)]", output[output.length-1].trim());
	}
	
	@Test
	void testCmdAddItemFromQueue_06() {    
	    addItem("addItem|1|22-Nov-2020");
	    addItem("addItem|2|22-Nov-2020");
	    addItem("addItem|3|26-Nov-2020");
	    addItem("addItem|3|26-Nov-2020");
	    addItem("addItem|3|26-Nov-2020");
    	(new CmdVisualize()).execute(null); 
    	String[] output = outContent.toString().split("\n");
    	assertEquals("Slot #3 with Volume: 3, Remaining Volume: 0 : [Item #3(Size:3)]", output[output.length-2].trim());
	    assertEquals("Items Queue: [Item #4(Size:3), Item #5(Size:3)]", output[output.length-1].trim());
	}
}
