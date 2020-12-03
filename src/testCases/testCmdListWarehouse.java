package testCases;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
//import java.io.OutputStream;
import java.io.PrintStream;
//import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.*;

public class testCmdListWarehouse {
	
	PrintStream stdOut = System.out;

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
		System.setOut(stdOut);
	}
	
	@Test
	void testListWarehouse1 () {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    String cmdLine1 = "addSlot|2";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts1);
	    String cmdLine6 = "listWarehouse";
	    String [] cmdParts6 = cmdLine6.split("\\|");
		(new CmdListWarehouse()).execute(cmdParts6);
	    String expectedResults = "Slot #1 is empty, volume is 2";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults, output[output.length-2].trim());
	}
	
	@Test
	void testListWarehouse2 () {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    String cmdLine1 = "addSlot|2";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts1);
	    String cmdLine2 = "addItem|1|14-Oct-2020";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    String cmdLine6 = "listWarehouse";
	    String [] cmdParts6 = cmdLine6.split("\\|");
		(new CmdListWarehouse()).execute(cmdParts6);
	    String expectedResults1 = "Slot #1, volume is 2, free volume is 1";
	    String expectedResults2 = "1. Dimensions for Item #1 is 1, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
	    String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-3].trim());
		assertEquals(expectedResults2, output[output.length-2].trim());
	}
	
	@Test
	void testListWarehouse3 () {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    String cmdLine1 = "addSlot|1";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts1);
	    String cmdLine2 = "addItem|1|14-Oct-2020";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    String cmdLine6 = "listWarehouse";
	    String [] cmdParts6 = cmdLine6.split("\\|");
		(new CmdListWarehouse()).execute(cmdParts6);
	    String expectedResults1 = "Slot #1, volume is 1, free volume is 0";
	    String expectedResults2 = "1. Dimensions for Item #1 is 1, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-3].trim());
		assertEquals(expectedResults2, output[output.length-2].trim());
	}
}
