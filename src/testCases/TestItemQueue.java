package testCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import code.*;

public class TestItemQueue {
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		Warehouse.getInstance().warehouseReset();
//		SystemDate.createTheInstance("1-Oct-2020");
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//		Warehouse.getInstance().warehouseReset();
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//		System.setOut(new PrintStream(outContent));
//		Warehouse.getInstance().warehouseReset();
//		addSlot("addSlot|1");
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//	
//	public void startNewDay(String cmdLine) {
//	    String [] cmdParts = cmdLine.split("\\|");
//		(new CmdSetDate()).execute(cmdParts);
//		(new CmdDeliverExpiredItem()).execute(cmdParts);
//		(new CmdAddItemFromQueue()).execute(cmdParts);
//	}
//	
//	public void addSlot(String cmdLine) {
//		String [] cmdParts = cmdLine.split("\\|");
//	    (new CmdAddSlot()).execute(cmdParts);
//	}
//	
//	public void addItem(String cmdLine) {
//		String[] cmdParts = cmdLine.split("\\|");
//		(new CmdAddItem()).execute(cmdParts);
//	}
//	
	@Test
	public void testSortQueue_1() throws NumberFormatException, ExWrongDateFormat {
		Item itemA = new Item(5, new Day("13-Oct-2020"), new Day("14-Oct-2020"));
		Item itemB = new Item(5, new Day("13-Oct-2020"), new Day("15-Oct-2020"));
		SortQueue sort = new SortQueue();
		assertEquals(1, sort.compare(itemA, itemB));
	}
	
	@Test
	public void testSortQueue_2() throws NumberFormatException, ExWrongDateFormat{
		Item itemA = new Item(5, new Day("13-Oct-2020"), new Day("15-Oct-2020"));
		Item itemB = new Item(5, new Day("13-Oct-2020"), new Day("14-Oct-2020"));
		SortQueue sort = new SortQueue();
		assertEquals(-1, sort.compare(itemA, itemB));
	}
	
	@Test
	public void testSortQueue_3() throws NumberFormatException, ExWrongDateFormat{
		Item itemA = new Item(5, new Day("13-Oct-2020"), new Day("14-Oct-2020"));
		Item itemB = new Item(5, new Day("13-Oct-2020"), new Day("14-Oct-2020"));
		SortQueue sort = new SortQueue();
		assertEquals(0, sort.compare(itemA, itemB));
	}
	
}
