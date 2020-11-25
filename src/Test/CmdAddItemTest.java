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
import Code.CmdDeliverExpiredItem;
import Code.CmdSetDate;
import Code.Day;
import Code.Item;
import Code.RecordedCommand;
import Code.Slot;
import Code.SystemDate;
import Code.Warehouse;

class CmdAddItemTest {
	
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
	void testCmdAddItem_01() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "addItem|3|10-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);
	    
	    String msg = "Checking when there is no slot in the warehouse yet";
	    assertEquals("Error. No slot has been created yet!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_02() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Slot slot1 = new Slot(1);
		Slot slot2 = new Slot(2);
		Slot slot3 = new Slot(3);
		Slot slot4 = new Slot(4);
		Slot slot5 = new Slot(5);
	    
	    String cmdLine = "addItem|3|10-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);
	    
	    String msg = "Checking when the command line is correct and the warehouse is empty";
	    assertEquals("Item #1 with size(3) is added in Slot ID #3 ; Delivery Date: 10-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_03() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "addItem|6|13-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);

	    String msg = "Checking when the size of the item is bigger than any slot";
	    assertEquals("The size should be >0 and <=5 (The largest slot size).", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_04() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "addItem|0|13-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);

	    String msg = "Checking when the size of the item is less than 1";
	    assertEquals("The size should be >0 and <=5 (The largest slot size).", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_05() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "addItem|1|1-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);

	    String msg = "Checking when the departure date equals to system date";
	    assertEquals("Error. The date is before or equals to system date.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_06() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "addItem|4|13-Sep-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);

	    String msg = "Checking when the departure date is less than system date";
	    assertEquals("Error. The date is before or equals to system date.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_07() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "addItem|1";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddItem()).execute(cmdParts);

	    String msg = "Checking when the command line does not contain enough arguments";
	    assertEquals("Insufficient command arguments!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_08() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "startNewDay|11-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
	    
	    String cmdLine1 = "addItem|1|13-Oct-2020";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddItem()).execute(cmdParts1);
	    
	    String cmdLine2 = "addItem|2|13-Oct-2020";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    
	    String cmdLine3 = "addItem|4|13-Oct-2020";
	    String [] cmdParts3 = cmdLine3.split("\\|");
	    (new CmdAddItem()).execute(cmdParts3);
	    
	    String cmdLine4 = "addItem|5|13-Oct-2020";
	    String [] cmdParts4 = cmdLine4.split("\\|");
	    (new CmdAddItem()).execute(cmdParts4);
	    
	    String cmdLine5 = "addItem|3|13-Oct-2020";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdAddItem()).execute(cmdParts5);
	    
	    String cmdLine6 = "addItem|3|14-Oct-2020";
	    String [] cmdParts6 = cmdLine6.split("\\|");
	    (new CmdAddItem()).execute(cmdParts6);
	    
	    String msg = "Checking when the warehouse is full";
	    assertEquals("Sorry. Currently there is no available slots. The item is added to Queue.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_09() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "startNewDay|15-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
	    
	    String cmdLine2 = "addItem|5|20-Oct-2020";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    
	    RecordedCommand.undoOneCommand();
	    
	    String cmdLine3 = "addItem|5|21-Oct-2020";
	    String [] cmdParts3 = cmdLine3.split("\\|");
	    (new CmdAddItem()).execute(cmdParts3);
	    
	    String msg = "Checking undo command for CmdAddItem";
	    assertEquals("Item #9 with size(5) is added in Slot ID #5 ; Delivery Date: 21-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_10() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "startNewDay|22-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
	    
	    String cmdLine2 = "addItem|5|24-Oct-2020";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    
	    RecordedCommand.undoOneCommand();
	    RecordedCommand.redoOneCommand();
	    
	    String cmdLine3 = "addItem|5|25-Oct-2020";
	    String [] cmdParts3 = cmdLine3.split("\\|");
	    (new CmdAddItem()).execute(cmdParts3);
	    
	    String msg = "Checking redo command for CmdAddItem when there is no addition of items between undo and redo";
	    assertEquals("Sorry. Currently there is no available slots. The item is added to Queue.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdAddItem_11() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));

	    String cmdLine = "startNewDay|1-Nov-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
	    
	    String cmdLine1 = "addItem|1|13-Nov-2020";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddItem()).execute(cmdParts1);
	    
	    String cmdLine2 = "addItem|2|13-Nov-2020";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    
	    String cmdLine3 = "addItem|4|13-Nov-2020";
	    String [] cmdParts3 = cmdLine3.split("\\|");
	    (new CmdAddItem()).execute(cmdParts3);
	    
	    String cmdLine4 = "addItem|5|13-Nov-2020";
	    String [] cmdParts4 = cmdLine4.split("\\|");
	    (new CmdAddItem()).execute(cmdParts4);
	    
	    String cmdLine5 = "addItem|1|13-Nov-2020";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdAddItem()).execute(cmdParts5);
	    
	    String cmdLine6 = "addItem|2|14-Nov-2020";
	    String [] cmdParts6 = cmdLine6.split("\\|");
	    (new CmdAddItem()).execute(cmdParts6);
	    
	    String msg = "Checking when the slot has an item, but there are enough freeVolume for another item";
	    assertEquals("Item #17 with size(2) is added in Slot ID #3 ; Delivery Date: 14-Nov-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
}
