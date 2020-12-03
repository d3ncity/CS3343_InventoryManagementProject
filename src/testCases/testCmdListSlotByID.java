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

public class testCmdListSlotByID {
	
	PrintStream standardOut = System.out;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		SystemDate.createTheInstance("13-Oct-2020");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}
	
	@BeforeEach
	void setUp() throws Exception {
		Warehouse.getInstance().warehouseReset();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.setOut(standardOut);
	}
	
//	@Test
//	void testFindItemByID_1 () {
//		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(outContent));
//		String nl = System.lineSeparator();
//	    String cmdLine1 = "addSlot|2";
//	    String [] cmdParts1 = cmdLine1.split("\\|");
//	    (new CmdAddSlot()).execute(cmdParts1);
//	    String cmdLine2 = "addSlot|3";
//	    String [] cmdParts2 = cmdLine2.split("\\|");
//	    (new CmdAddSlot()).execute(cmdParts2);
//	    String cmdLine3 = "addSlot|4";
//	    String [] cmdParts3 = cmdLine3.split("\\|");
//	    (new CmdAddSlot()).execute(cmdParts3);
//	    String cmdLine4 = "addItem|4|14-Oct-2020";
//	    String [] cmdParts4 = cmdLine4.split("\\|");
//	    (new CmdAddItem()).execute(cmdParts4);
//	    String cmdLine5 = "listItemByID|1";
//	    String [] cmdParts5 = cmdLine5.split("\\|");
//		(new CmdListItemByID()).execute(cmdParts5);
//	    String expectedResults = "Current Slot #3 ;Dimensions for Item #1 is 4; Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
//		assertEquals(expectedResults, outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
//	}
	
	@Test
	void testFindSlotByID_1 () {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    String cmdLine1 = "addSlot|2";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts1);
	    String cmdLine2 = "addSlot|3";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts2);
	    String cmdLine3 = "addSlot|4";
	    String [] cmdParts3 = cmdLine3.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts3);
	    String cmdLine4 = "addItem|3|14-Oct-2020";
	    String [] cmdParts4 = cmdLine4.split("\\|");
	    (new CmdAddItem()).execute(cmdParts4);
	    String cmdLine5 = "listSlotByID|2";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdListSlotByID()).execute(cmdParts5);
	    String expectedResults1 = "Slot #2 Details:";
	    String expectedResults2 = "1. Dimensions for Item #1 is 3, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-2].trim());
		assertEquals(expectedResults2, output[output.length-1].trim());
	}
	
}
