package testCases;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.*;

@SuppressWarnings("unused")
class TestAddItem {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Warehouse.getInstance().warehouseReset();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Warehouse.getInstance().warehouseReset();
	}

	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		Warehouse.getInstance().warehouseReset();
		SystemDate.createTheInstance("1-Oct-2020");
		addSlot("addSlot|1");
		addSlot("addSlot|2");
		addSlot("addSlot|3");
		addSlot("addSlot|4");
		addSlot("addSlot|5");
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
	void testCmdAddItem_01() {	
		Warehouse.getInstance().warehouseReset();
	    addItem("addItem|3|10-Oct-2020");  
		String msg = "Checking when there is no slot in the warehouse yet";
		String expectedResults1 = "Error. No slot has been created yet!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_02() {	    
	    addItem("addItem|3|10-Oct-2020");
	    String msg = "Checking when the command line is correct and the warehouse is empty";
		String expectedResults1 = "Item #1 with size(3) is added in Slot ID #3 ; Delivery Date: 10-Oct-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_03() {    
	    addItem("addItem|6|13-Oct-2020");
	    String msg = "Checking when the size of the item is bigger than any slot";
		String expectedResults1 = "Invalid Dimension Input! The size should be >0 and <=5 (The largest slot size).";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_04() {   
	    addItem("addItem|0|13-Oct-2020");
	    String msg = "Checking when the size of the item is less than 1";
		String expectedResults1 = "Invalid Dimension Input! The size should be >0 and <=5 (The largest slot size).";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_05() { 
	    addItem("addItem|1|1-Oct-2020");
	    String msg = "Checking when the departure date equals to system date";
		String expectedResults1 = "Error. The date is before or equals to system date.";
		String[] output = outContent.toString().split("\n");
		for (int i = 0; i < output.length; i++) {
			stdOut.println(output[i]);
		}
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_06() {  
		addItem("addItem|4|13-Sep-2020");
	    String msg = "Checking when the departure date is less than system date";
		String expectedResults1 = "Error. The date is before or equals to system date.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_07() {	    
	    addItem("addItem|1");
	    String msg = "Checking when the command line does not contain enough arguments";
		String expectedResults1 = "Insufficient command arguments!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_08() {
	    startNewDay("startNewDay|11-Oct-2020");
	    addItem("addItem|1|13-Oct-2020");    
	    addItem("addItem|2|13-Oct-2020");
	    addItem("addItem|4|13-Oct-2020");
	    addItem("addItem|5|13-Oct-2020");
	    addItem("addItem|3|13-Oct-2020");
	    addItem("addItem|3|14-Oct-2020");    
	    String msg = "Checking when the warehouse is full";
		String expectedResults1 = "Sorry. Currently there is no available slots. The item is added to Queue.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_09() {	    
	    startNewDay("startNewDay|15-Oct-2020");
	    addItem("addItem|5|20-Oct-2020");
	    RecordedCommand.undoOneCommand();
	    addItem("addItem|5|21-Oct-2020");
	    String msg = "Checking undo command for CmdAddItem";
	    String expectedResults1 = "Item #2 with size(5) is added in Slot ID #5 ; Delivery Date: 21-Oct-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_10() {
		startNewDay("startNewDay|22-Oct-2020");
	    addItem("addItem|5|24-Oct-2020");   
	    RecordedCommand.undoOneCommand();
	    RecordedCommand.redoOneCommand();
	    addItem("addItem|5|25-Oct-2020");
	    String msg = "Checking redo command for CmdAddItem when there is no addition of items between undo and redo";
	    String expectedResults1 = "Sorry. Currently there is no available slots. The item is added to Queue.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_11() {
		startNewDay("startNewDay|1-Nov-2020");	    
	    addItem("addItem|1|13-Nov-2020");
	    addItem("addItem|2|13-Nov-2020");
	    addItem("addItem|4|13-Nov-2020");
	    addItem("addItem|5|13-Nov-2020");
	    addItem("addItem|1|13-Nov-2020");
	    addItem("addItem|2|14-Nov-2020");
	    String msg = "Checking when the slot has an item, but there are enough freeVolume for another item";
	    String expectedResults1 = "Item #6 with size(2) is added in Slot ID #3 ; Delivery Date: 14-Nov-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_12() {
	    startNewDay("startNewDay|15-Nov-2020");
	    addItem("addItem|1|38-Nov-2020");
	    String msg = "Invalid Date";
	    String expectedResults1 = "The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_13() {
	    addItem("addItem|1|k-Nov-2020");
	    String msg = "wrong format of the day";
	    String expectedResults1 = "Wrong Input Format!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_14() {
	    addItem("addItem|1|29-Feb-2021");
	    String msg = "Invalid Date";
	    String expectedResults1 = "The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_15() {
	    addItem("addItem|1|29-Feb");
	    String msg = "Invalid Date";
	    String expectedResults1 = "The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_16() { 
	    startNewDay("startNewDay|1-Dec-2020");
	    addItem("addItem|5|24-Dec-2020");
	    RecordedCommand.redoOneCommand();
	    String msg = "Checking redo command for CmdAddItem when there is no addition of items between undo and redo";
	    String expectedResults1 = "Nothing to redo.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_17() { 
	    startNewDay("startNewDay|25-Dec-2020");
	    addItem("addItem|4|26-Dec-2020");
	    RecordedCommand.undoOneCommand();
	    RecordedCommand.redoOneCommand();
	    String msg = "Checking redo command for CmdAddItem when there is no addition of items between undo and redo";
	    String expectedResults1 = "Item #1 with size(4) is added in Slot ID #4 ; Delivery Date: 26-Dec-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_18() {
		RecordedCommand.clearUndoList();
	    RecordedCommand.undoOneCommand();
	    String expectedResults1 = "Nothing to undo.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testCmdAddItem_19() {
	    startNewDay("startNewDay|25-Dec-2020");
	    addItem("addItem|4|26-Dec-2020");
	    InputCommand input = new InputCommand();
	    String[] cmdParts1 = {"undo"};
	    input.acceptCmd(cmdParts1);
	    startNewDay("startNewDay|27-Dec-2020");
	    String[] cmdParts2 = {"redo"};
	    input.acceptCmd(cmdParts2);
	    String expectedResults1 = "Cannot redo. The previous item date is before or equals to system date.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	
}
