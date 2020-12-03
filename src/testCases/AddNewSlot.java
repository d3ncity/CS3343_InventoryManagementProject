package testCases;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
//import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import Code.CmdAddSlot;
//import Code.CmdOptimize;
//import Code.CmdSetDate;
//import Code.Day;
//import Code.Item;
//import Code.Main;
//import Code.Optimize;
//import Code.Slot;
//import Code.SystemDate;
//import Code.Warehouse;
import code.*;
public class AddNewSlot {
	
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		SystemDate.createTheInstance("13-Oct-2020");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Warehouse.getInstance().warehouseReset();
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	void tearDown() throws Exception {
		System.setOut(stdOut);
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
	
	@Test //Checking when the size of the item is bigger than any slot
	void testAddNewSlot1() {
	    addSlot("addSlot|-1");
		String[] output = outContent.toString().split("\n");
		String expectedResults1 = "Volume Out Of Range. The size can only be (1-100).";
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testAddNewSlot2() {
		addSlot("addSlot|101");
		String[] output = outContent.toString().split("\n");
		String expectedResults1 = "Volume Out Of Range. The size can only be (1-100).";
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test //Array index out of bounds exception
	void testAddNewSlot3() {
	    addSlot("addSlot");
		String[] output = outContent.toString().split("\n");
		String expectedResults1 = "Insufficient command arguments!";
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testAddNewSlot4() {
	    addSlot("addSlot|4");
		String[] output = outContent.toString().split("\n");
		String expectedResults1 = "Slot #1 is created and added.";
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test //Here the command is adding but not added
	void testAddNewSlot5() {
	    String cmdLine = "addingSlot|1";
	    String [] cmdParts = cmdLine.split("\\|");
	    InputCommand inputCmd = new InputCommand();
	    inputCmd.acceptCmd(cmdParts);
		String[] output = outContent.toString().split("\n");
		String expectedResults1 = "Invalid Command!";
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test //When input given is 0, there is a conflict between the error messages, Ivalid slot input & Volume Out of Range!!
	void testAddNewSlot6() {
		addSlot("addSlot|0");
		String[] output = outContent.toString().split("\n");
		String expectedResults1 = "Volume Out Of Range. The size can only be (1-100).";
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}	
	
	@Test
	void testAddNewSlot7() {	    
		startNewDay("startNewDay|15-Oct-2020");
	    addSlot("addSlot|5");
	    
	    RecordedCommand.undoOneCommand();
	    
	    addSlot("addSlot|6");
	    
	   // String msg = "Checking undo command for CmdAddSlot";
	    String expectedResults1 = "System Date Setting Done. Current Date: 15-Oct-2020";
		String expectedResults2 = "Slot #1 is created and added.";
		String expectedResults3 = "Slot #1 is removed.";
		String expectedResults4 = "Slot #2 is created and added.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 4].trim());
		assertEquals(expectedResults2, output[output.length - 3].trim());
		assertEquals(expectedResults3, output[output.length - 2].trim());
		assertEquals(expectedResults4, output[output.length - 1].trim());
	}
	
	@Test
	void testAddNewSlot8() {
   
		startNewDay("startNewDay|1-Oct-2020");
	    addSlot("addSlot|5");
	    
	    RecordedCommand.redoOneCommand();
	    
	    addSlot("addSlot|6");
	    
	    String expectedResults1 = "Error. The date is before or equals to system date.";
		String expectedResults2 = "Slot #1 is created and added.";
		String expectedResults3 = "Nothing to redo.";
		String expectedResults4 = "Slot #2 is created and added.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 4].trim());
		assertEquals(expectedResults2, output[output.length - 3].trim());
		assertEquals(expectedResults3, output[output.length - 2].trim());
		assertEquals(expectedResults4, output[output.length - 1].trim());
	}
	
	@Test
	void testAddNewSlot9 () {	      
	    addSlot("addSlot|5");
	    
	    RecordedCommand.undoOneCommand();
	    RecordedCommand.redoOneCommand();
	    
	    addSlot("addSlot|5");
	    
	    String expectedResults1 = "Slot #1 is created and added.";
		String expectedResults2 = "Slot #1 is removed.";
		String expectedResults3 = "Slot #1 is created and added.";
		String expectedResults4 = "Slot #2 is created and added.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 4].trim());
		assertEquals(expectedResults2, output[output.length - 3].trim());
		assertEquals(expectedResults3, output[output.length - 2].trim());
		assertEquals(expectedResults4, output[output.length - 1].trim());
	}


	@Test
	void testAddNewSlot10() {
	    addSlot("addSlot|e");
	    String expectedResults1 = "Wrong Input Format!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	void testAddNewSlot11() {
	    startNewDay("startNewDay|Oct 15, 2020");
	    addSlot("addSlot|3");
	    String expectedResults1 = "The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.";
	    String expectedResults2 =  "Slot #1 is created and added.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
		
	}
}
