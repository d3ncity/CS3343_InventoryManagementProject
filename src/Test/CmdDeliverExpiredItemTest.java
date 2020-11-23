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
import Code.Day;
import Code.Item;
import Code.RecordedCommand;
import Code.Slot;
import Code.SystemDate;

class CmdDeliverExpiredItemTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	void testCmdDeliverExpiredItem_01() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item1 = new Item(1, SystemDate.getInstance(),new Day("09-Oct-2020"));
	    Item item2 = new Item(2, SystemDate.getInstance(),new Day("09-Oct-2020"));
	    Item item3 = new Item(3, SystemDate.getInstance(),new Day("09-Oct-2020"));
	    Item item4 = new Item(4, SystemDate.getInstance(),new Day("09-Oct-2020"));
	    Item item5 = new Item(5, SystemDate.getInstance(),new Day("09-Oct-2020"));
	    
	    String cmdLine = "startNewDay|8-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
	    
	    Item newItem = new Item(1, SystemDate.getInstance(),new Day("11-Oct-2020"));
	    
	    String msg = "Warehouse is full and there is no item that should be departured";
	    assertEquals("Sorry. Currently there is no available slots. The item is added to Queue.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdDeliverExpiredItem_02() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));

	    String cmdLine = "startNewDay|10-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
	    
	    Item newItem = new Item(1, SystemDate.getInstance(),new Day("13-Oct-2020"));
	    
	    String msg = "Checking when all items should be departured due to date";
	    assertEquals("Item #7 with size(1) is added in Slot ID #1 ; Delivery Date: 13-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdDeliverExpiredItem_03() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "startNewDay|13-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
	    
	    Item newItem = new Item(1, SystemDate.getInstance(),new Day("18-Oct-2020"));
	    
	    String msg = "Checking when some items should be departured on this date";
	    assertEquals("Item #8 with size(1) is added in Slot ID #1 ; Delivery Date: 18-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdDeliverExpiredItem_04() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Item item2 = new Item(2, SystemDate.getInstance(),new Day("18-Oct-2020"));
	    Item item3 = new Item(3, SystemDate.getInstance(),new Day("21-Oct-2020"));
	    Item item4 = new Item(4, SystemDate.getInstance(),new Day("21-Oct-2020"));
	    Item item5 = new Item(5, SystemDate.getInstance(),new Day("21-Oct-2020"));
	    
	    String cmdLine = "startNewDay|19-Oct-2020";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdDeliverExpiredItem()).execute(cmdParts);
	    
	    RecordedCommand.undoOneCommand();
	    
	    Item newItem = new Item(2, SystemDate.getInstance(),new Day("21-Oct-2020"));
	    
	    String msg = "Checking undo function";
	    assertEquals("Sorry. Currently there is no available slots. The item is added to Queue.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	
	@Test
	void testCmdDeliverExpiredItem_05() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));

	    RecordedCommand.redoOneCommand();
	    
	    Item newItem = new Item(2, SystemDate.getInstance(),new Day("21-Oct-2020"));
	    
	    String msg = "Checking redo function";
	    assertEquals("Item #14 with size(2) is added in Slot ID #2 ; Delivery Date: 21-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}

}
