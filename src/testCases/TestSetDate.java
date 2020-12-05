package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.*;

class TestSetDate {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Warehouse.getInstance().warehouseReset();
		SystemDate.createTheInstance("5-Oct-2020");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	public void startNewDay(String cmdLine) {
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdSetDate()).execute(cmdParts);
	}
	
	@Test
	void testCmdSetDate_01() {	    
	    startNewDay("startNewDay|10-Oct-2020");
	    String msg = "Valid date";
	    assertEquals("System Date Setting Done. Current Date: 10-Oct-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdSetDate_02() {
		startNewDay("startNewDay|1-Oct-2020");
	    String msg = "When the date is less than the System Date";
	    assertEquals("Error. The date is before or equals to system date.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdSetDate_03() {
		startNewDay("startNewDay|5-Oct-2020");
	    String msg = "When the date equals to the System Date";
	    assertEquals("Error. The date is before or equals to system date.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdSetDate_04() {
		startNewDay("startNewDay|34-Nov-2020");
	    String msg = "Invalid day";
	    assertEquals("The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdSetDate_05() {
		startNewDay("startNewDay|29-Feb-2021");	    
	    String msg = "February 21, Not a Leap Year";
	    assertEquals("The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdSetDate_06() {
		startNewDay("startNewDay|29-Feb-2024");
	    String msg = "February 21, Leap Year";
	    assertEquals("System Date Setting Done. Current Date: 29-Feb-2024", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdSetDate_07() {
		startNewDay("startNewDay");
	    String msg = "No date";
	    assertEquals("Insufficient command arguments!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdSetDate_10() {
		startNewDay("startNewDay|13-Nek-2020");
	    String msg = "Invalid Date";
	    assertEquals("The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testCmdSetDate_11() {
		startNewDay("startNewDay|13-Nek");
	    String msg = "Invalid Date";
	    assertEquals("The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testSystemDate_01() {
		Warehouse.getInstance().warehouseReset();
		SystemDate.createTheInstance("ab-Oct-2020");
		String msg = "Invalid Date";
	    assertEquals("The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}
	
	@Test
	void testSystemDate_02() {
		SystemDate.createTheInstance("13-Oct-2020");
		SystemDate.createTheInstance("14-Oct-2020");
		String msg = "Invalid Date";
	    assertEquals(true, SystemDate.getSystemDateSet(), msg);
	}
	
	@Test
	void testSystemDate_03() {
		Warehouse.getInstance().warehouseReset();
		SystemDate.createTheInstance("13-Oct");
		String msg = "Invalid Date";
	    assertEquals("The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1), msg);
	}

}
