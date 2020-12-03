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
import code.CmdAddItemFromQueue;
import code.CmdDeliverExpiredItem;
import code.CmdListWarehouse;
import code.CmdSetDate;
import code.CmdVisualize;
import code.Day;
import code.InputCommand;
import code.Item;
import code.RecordedCommand;
import code.Slot;
import code.SystemDate;
import code.Warehouse;

class CmdAddItemFromQueueTest {
	InputCommand in = new InputCommand();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Warehouse.getInstance().warehouseReset();;
		SystemDate.createTheInstance("1-Oct-2020");
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
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
	void testCmdAddItemFromQueue_01() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine1 = "addItem|1|10-Oct-2020";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddItem()).execute(cmdParts1);
	    
	    String cmdLine2 = "addItem|2|10-Oct-2020";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    
	    String cmdLine3 = "addItem|3|10-Oct-2020";
	    String [] cmdParts3 = cmdLine3.split("\\|");
	    (new CmdAddItem()).execute(cmdParts3);
	    
	    String cmdLine5 = "addItem|3|22-Oct-2020";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdAddItem()).execute(cmdParts5);    
	    
	    String cmdLine = "startNewDate|11-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
    	(new CmdAddItemFromQueue()).execute(cmdParts);
    	(new CmdVisualize()).execute(cmdParts);
    	
    	String[] output = outContent.toString().split("\n");
	    
	    assertEquals("Slot #3 with Volume: 3, Remaining Volume: 0 : [Item #4(Size:3)]", output[output.length-2].trim());
	}
	
	@Test
	void testCmdAddItemFromQueue_02() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine1 = "addItem|1|22-Oct-2020";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddItem()).execute(cmdParts1);
	    
	    String cmdLine2 = "addItem|2|22-Oct-2020";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    
	    String cmdLine3 = "addItem|3|26-Oct-2020";
	    String [] cmdParts3 = cmdLine3.split("\\|");
	    (new CmdAddItem()).execute(cmdParts3);
	    
	    String cmdLine5 = "addItem|3|26-Oct-2020";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdAddItem()).execute(cmdParts5);    
	    
	    String cmdLine = "startNewDate|23-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
    	(new CmdAddItemFromQueue()).execute(cmdParts);
    	(new CmdVisualize()).execute(cmdParts);
	    
    	String[] output = outContent.toString().split("\n");
    	assertEquals("Slot #3 with Volume: 3, Remaining Volume: 0 : [Item #8(Size:3)]", output[output.length-2].trim());
	    assertEquals("Items Queue: [Item #7(Size:3)]", output[output.length-1].trim());
	}
	
	@Test
	void testCmdAddItemFromQueue_03() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "startNewDate|27-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
    	(new CmdAddItemFromQueue()).execute(cmdParts);

    	String cmdLine3 = "addItem|3|2-Nov-2020";
  	    String [] cmdParts3 = cmdLine3.split("\\|");
  	    (new CmdAddItem()).execute(cmdParts3);
  	    
  	    String[] output = outContent.toString().split("\n");
	    assertEquals("Item #9 with size(3) is added in Slot ID #3 ; Delivery Date: 2-Nov-2020", output[output.length-1].trim());
	}
	
	@Test
	void testCmdAddItemFromQueue_04() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine5 = "addItem|2|29-Oct-2020";
  	    String [] cmdParts5 = cmdLine5.split("\\|");
  	    (new CmdAddItem()).execute(cmdParts5);
	    
	    String cmdLine3 = "addItem|1|10-Nov-2020";
  	    String [] cmdParts3 = cmdLine3.split("\\|");
  	    (new CmdAddItem()).execute(cmdParts3);
  	    
  	    String cmdLine2 = "addItem|1|10-Nov-2020";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    
	    String cmdLine1 = "addItem|1|10-Nov-2020";
  	    String [] cmdParts1 = cmdLine1.split("\\|");
  	    (new CmdAddItem()).execute(cmdParts1);
	    
	    String cmdLine = "startNewDate|1-Nov-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
    	(new CmdAddItemFromQueue()).execute(cmdParts);
    	(new CmdVisualize()).execute(cmdParts);
    	
    	String[] output = outContent.toString().split("\n");

    	assertEquals("Slot #2 with Volume: 2, Remaining Volume: 0 : [Item #12(Size:1), Item #13(Size:1)]", output[output.length-3].trim());
	}
}
