package TestCases;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Code.*;

public class testCmdListWarehouse {
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		SystemDate.createTheInstance("13-Oct-2020");
		Warehouse wh = Warehouse.getInstance();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testListWarehouse1 () {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String nl = System.lineSeparator();
	    String cmdLine1 = "addSlot|2";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts1);
	    String cmdLine6 = "listWarehouse";
	    String [] cmdParts6 = cmdLine6.split("\\|");
		(new CmdListWarehouse()).execute(cmdParts6);
	    String expectedResults = "Slot #1 is empty, volume is 2";
		assertEquals(expectedResults, outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test
	void testListWarehouse2 () {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		//String nl = System.lineSeparator();
	    String cmdLine1 = "addSlot|1";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts1);
	    String cmdLine2 = "addItem|1";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    String cmdLine6 = "listWarehouse";
	    String [] cmdParts6 = cmdLine6.split("\\|");
		(new CmdListWarehouse()).execute(cmdParts6);
	    String expectedResults1 = "Slot #1 is Full!";
		assertEquals(expectedResults1, outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
	
	@Test //Showing a comparision error
	void testListWarehouse3 () {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String nl = System.lineSeparator();
	    String cmdLine1 = "addSlot|2";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts1);
	    String cmdLine2 = "addItem|1";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    String cmdLine6 = "listWarehouse";
	    String [] cmdParts6 = cmdLine6.split("\\|");
		(new CmdListWarehouse()).execute(cmdParts6);
	    String expectedResults2 = "Slot #1, volume is 1, free volume is 1";
		assertEquals(expectedResults2, outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}
}
