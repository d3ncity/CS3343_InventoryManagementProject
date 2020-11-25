package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Code.CmdAddItem;
import Code.CmdAddItemFromQueue;
import Code.CmdDeliverExpiredItem;
import Code.CmdSetDate;
import Code.Day;
import Code.Item;
import Code.RecordedCommand;
import Code.Slot;
import Code.SystemDate;
import Code.Warehouse;

class CmdAddItemFromQueueTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	    
	    String msg = "Checking when there is no slot in the warehouse yet";
	    assertEquals("The Queue is Empty!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
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
	    
	    String msg = "Checking when there is no slot in the warehouse yet";
	    assertEquals("Sorry. Currently there is no available slots. The item is added to Queue.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
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
  	        
	    String msg = "Checking when there is no slot in the warehouse yet";
	    assertEquals("Item #9 with size(3) is added in Slot ID #3 ; Delivery Date: 2-Nov-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
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

	    String msg = "Checking when there is no slot in the warehouse yet";
	    assertEquals("Item #13 with size(1) is added in Slot ID #2 ; Delivery Date: 10-Nov-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
}
