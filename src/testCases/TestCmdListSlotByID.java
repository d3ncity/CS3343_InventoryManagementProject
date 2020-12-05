package testCases;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
//import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.*;

public class TestCmdListSlotByID {
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}
	
	@BeforeEach
	void setUp() throws Exception {
		Warehouse.getInstance().warehouseReset();
		SystemDate.createTheInstance("13-Oct-2020");
		System.setOut(new PrintStream(outContent));
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.setOut(stdOut);
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
	void testFindSlotByID_1 () {
	    addSlot("addSlot|2");
	    addSlot("addSlot|3");
	    addSlot("addSlot|4");
	    addItem("addItem|3|14-Oct-2020");
	    String cmdLine5 = "listSlotByID|2";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdListSlotByID()).execute(cmdParts5);
	    String expectedResults1 = "Slot #2 Details:";
	    String expectedResults2 = "1. Dimensions for Item #1 is 3, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
		String[] output = outContent.toString().split("\n");
		for (int i = 0; i < output.length; i++) {
			stdOut.print(output[i]);
		}
		assertEquals(expectedResults1, output[output.length-2].trim());
		assertEquals(expectedResults2, output[output.length-1].trim());
	}
	
	@Test
	void testFindSlotByID_2 () {
	    addSlot("addSlot|3");
	    addItem("addItem|3|14-Oct-2020");
	    String cmdLine5 = "listSlotByID|4";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdListSlotByID()).execute(cmdParts5);
	    String expectedResults1 = "Slot not found! Please check the ID again!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	void testFindSlotByID_3 () {
	    String cmdLine5 = "listSlotByID|1";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdListSlotByID()).execute(cmdParts5);
	    String expectedResults1 = "Warehouse is Empty!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	void testFindSlotByID_4 () {
	    String cmdLine5 = "listSlotByID|-1";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdListSlotByID()).execute(cmdParts5);
	    String expectedResults1 = "Wrong Input format! The input should be >0.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	void testFindSlotByID_5 () {
	    String cmdLine5 = "listSlotByID|abc";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdListSlotByID()).execute(cmdParts5);
	    String expectedResults1 = "The ID input should be integer!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	void testFindSlotByID_6 () {
	    String cmdLine5 = "listSlotByID";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdListSlotByID()).execute(cmdParts5);
	    String expectedResults1 = "Insufficient command arguments!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
}
