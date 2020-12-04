package testCases;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.*;

class TestOptimizeAlgo {
	
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Warehouse.getInstance().setAutomationStatus(false);
		SystemDate.createTheInstance("13-Oct-2020");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Warehouse.getInstance().setAutomationStatus(true);
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
	
	public void addSlot(String cmdLine) {
		String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	}
	
	public void addItem(String cmdLine) {
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdAddItem()).execute(cmdParts);
	}
	public void optimize() {
		String cmdLine = "optimize";
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdOptimize()).execute(cmdParts);
	}
	public void listSlotByID(String cmdLine) {
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdListSlotByID()).execute(cmdParts);
	}
	public void listWarehouse() {
		String cmdLine = "listWarehouse";
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdListWarehouse()).execute(cmdParts);
	}

	@Test
	void testOptimize1() {
		addSlot("addSlot|5");
		addItem("addItem|4|14-Oct-2020");
		optimize();
		String expectedResults2 = "Manually Optimized!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}

	@Test
	void testOptimize2() {
		addSlot("addSlot|5");
		addSlot("addSlot|5");
		addItem("addItem|2|14-Oct-2020");
		addItem("addItem|1|14-Oct-2020");
		addItem("addItem|3|14-Oct-2020");
		optimize();
		listSlotByID("listSlotByID|1");
		String expectedResults1 = "1. Dimensions for Item #3 is 3, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
		String expectedResults2 = "2. Dimensions for Item #1 is 2, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}

	@Test
	void testOptimize3() {
		addSlot("addSlot|5");
		addSlot("addSlot|5");
		addSlot("addSlot|3");
		addItem("addItem|5|14-Oct-2020");
		addItem("addItem|5|14-Oct-2020");
		addItem("addItem|2|14-Oct-2020");
		addItem("addItem|1|14-Oct-2020");
		optimize();
		listWarehouse();
		String expectedResults1 = "1. Dimensions for Item #3 is 2, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
	    String expectedResults2 = "2. Dimensions for Item #4 is 1, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
	    String expectedResults3 = "1. Dimensions for Item #1 is 5, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
	    String expectedResults4 = "1. Dimensions for Item #2 is 5, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-11].trim());
		assertEquals(expectedResults2, output[output.length-10].trim());
		assertEquals(expectedResults3, output[output.length-6].trim());
		assertEquals(expectedResults4, output[output.length-2].trim());
	}

	@Test
	void testOptimize4() throws ExOutOfRange {
		addSlot("addSlot|5");
		addItem("addItem|-5|14-Oct-2020");
		optimize();
		String expectedResults1 = "Invalid Dimension Input! The size should be >0 and <=5 (The largest slot size).";
		String expectedResults2 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}

	@Test
	void testOptimize5() throws ExOutOfRange {
		addSlot("addSlot|0");
		addItem("addItem|5|14-Oct-2020");
		optimize();
		String expectedResults1 = "Volume Out Of Range. The size can only be (1-100).";
		String expectedResults2 = "Error. No slot has been created yet!";
		String expectedResults3 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 3].trim());
		assertEquals(expectedResults2, output[output.length - 2].trim());
		assertEquals(expectedResults3, output[output.length - 1].trim());
	}

	@Test
	void testOptimize6() throws ExOutOfRange {
		addSlot("addSlot|-1");
		addItem("addItem|-1|14-Oct-2020");
		optimize();
		String expectedResults1 = "Volume Out Of Range. The size can only be (1-100).";
		String expectedResults2 = "Error. No slot has been created yet!";
		String expectedResults3 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 3].trim());
		assertEquals(expectedResults2, output[output.length - 2].trim());
		assertEquals(expectedResults3, output[output.length - 1].trim());
	}

	@Test
	void testOptimize7() throws ExOutOfRange {
		addSlot("addSlot|2");
		addItem("addItem|12345678|14-Oct-2020");
		optimize();
		String expectedResults1 = "Invalid Dimension Input! The size should be >0 and <=2 (The largest slot size).";
		String expectedResults2 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}

	@Test
	void testOptimize8() throws ExOutOfRange {
		addSlot("addSlot|0");
		addItem("addItem|0|14-Oct-2020");
		optimize();
		String expectedResults1 = "Volume Out Of Range. The size can only be (1-100).";
		String expectedResults2 = "Error. No slot has been created yet!";
		String expectedResults3 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 3].trim());
		assertEquals(expectedResults2, output[output.length - 2].trim());
		assertEquals(expectedResults3, output[output.length - 1].trim());
	}

	@Test
	void testOptimize9() throws ExInvalidItemDimension {
		addSlot("addSlot|5");
		addItem("addItem|-5|14-Oct-2020");
		optimize();
		String expectedResults1 = "Invalid Dimension Input! The size should be >0 and <=5 (The largest slot size).";
		String expectedResults2 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}

	@Test
	void testOptimize10() throws ExInvalidDate {
		addSlot("addSlot|0");
		addItem("addItem|0|32-Oct-2020");
		optimize();
		String expectedResults1 = "Volume Out Of Range. The size can only be (1-100).";
		String expectedResults2 = "Error. No slot has been created yet!";
		String expectedResults3 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 3].trim());
		assertEquals(expectedResults2, output[output.length - 2].trim());
		assertEquals(expectedResults3, output[output.length - 1].trim());
	}

	@Test
	void testOptimize11() throws ExInvalidDate {
		addSlot("addSlot|0");
		addItem("addItem|0|31-10-2020");
		optimize();
		String expectedResults1 = "Volume Out Of Range. The size can only be (1-100).";
		String expectedResults2 = "Error. No slot has been created yet!";
		String expectedResults3 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 3].trim());
		assertEquals(expectedResults2, output[output.length - 2].trim());
		assertEquals(expectedResults3, output[output.length - 1].trim());
	}
	
	@Test
	void testOptimize12() throws ExInsufficientArguments {
		addSlot("addSlot|5");
		addItem("addItem|5");
		optimize();
		String expectedResults1 = "Insufficient command arguments!";
		String expectedResults2 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}
	
	
	@Test
	void testOptimize13() throws ExInvalidDate {
		addSlot("addSlot|2");
		addItem("addItem|1|13-Oct-2020");
		optimize();
		String expectedResults1 = "Error. The date is before or equals to system date.";
		String expectedResults2 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}
	
	@Test
	void testOptimize14() throws ExInvalidDate {
		addSlot("addSlot|2");
		addItem("addItem|1|12-Oct-2020");
		optimize();
		String expectedResults1 = "Error. The date is before or equals to system date.";
		String expectedResults2 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}
	
	@Test
	void testOptimize15() throws ExInvalidCommand {
		String cmdLine1 = "addingSlot|2";
		String[] cmdParts1 = cmdLine1.split("\\|");
		InputCommand inputCmd = new InputCommand();
		inputCmd.acceptCmd(cmdParts1);
		addItem("addItem|1|12-Oct-2020");
		optimize();
		String expectedResults1 = "Invalid Command!";
		String expectedResults2 = "Error. No slot has been created yet!";
		String expectedResults3 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 3].trim());
		assertEquals(expectedResults2, output[output.length - 2].trim());
		assertEquals(expectedResults3, output[output.length - 1].trim());
	}
	
	@Test
	void testOptimize16() throws ExWrongDateFormat {
		addSlot("addSlot|2");
		addItem("addItem|1|12 Oct, 2020");
		optimize();
		String expectedResults1 = "The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.";
		String expectedResults2 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}
	
	@Test
	void testOptimize17() throws NumberFormatException {
		addSlot("addSlot|2");
		addItem("addItem|1e|12 Oct, 2020");
		optimize();
		String expectedResults1 = "Wrong Input Format!";
		String expectedResults2 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 2].trim());
		assertEquals(expectedResults2, output[output.length - 1].trim());
	}
	
	@Test
	void testOptimize18() throws NumberFormatException {
		addSlot("addSlot|e");
		addItem("addItem|1|12 Oct, 2020");
		optimize();
		String expectedResults1 = "Wrong Input Format!";
		String expectedResults2 = "Error. No slot has been created yet!";
		String expectedResults3 = "Nothing to optimize!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 3].trim());
		assertEquals(expectedResults2, output[output.length - 2].trim());
		assertEquals(expectedResults3, output[output.length - 1].trim());
	}
}
