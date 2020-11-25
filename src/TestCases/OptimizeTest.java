/**
 * 
 */
package TestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

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

class OptimizeTest {

	PrintStream stdOut = System.out;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Warehouse.getInstance().setAutomation(false);
		SystemDate.createTheInstance("13-Oct-2020");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Warehouse.getInstance().setAutomation(true);
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}

	@BeforeEach
	void setUp() throws Exception {
		Warehouse.getInstance().reset();
	}

	@AfterEach
	void tearDown() throws Exception {
		System.setOut(stdOut);
	}
	
	@Test
	
	void testOptimize1() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    String cmdLine1 = "addSlot|5";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts1);
	    String cmdLine2 = "addSlot|5";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts2);
	    String cmdLine3 = "addItem|2|14-Oct-2020";
	    String [] cmdParts3 = cmdLine3.split("\\|");
	    (new CmdAddItem()).execute(cmdParts3);
	    String cmdLine4 = "addItem|1|14-Oct-2020";
	    String [] cmdParts4 = cmdLine4.split("\\|");
	    (new CmdAddItem()).execute(cmdParts4);
	    String cmdLine5 = "addItem|3|14-Oct-2020";
	    String [] cmdParts5 = cmdLine5.split("\\|");
	    (new CmdAddItem()).execute(cmdParts5);
	    String cmdLine6 = "optimize";
	    String [] cmdParts6 = cmdLine6.split("\\|");
	    (new CmdOptimize()).execute(cmdParts6);
	    String cmdLine7 = "listSlotByID|1";
	    String [] cmdParts7 = cmdLine7.split("\\|");
	    (new CmdListSlotByID()).execute(cmdParts7);
	    String expectedResults1 = "1. Dimensions for Item #3 is 3, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
	    String expectedResults2 = "2. Dimensions for Item #1 is 2, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-2].trim());
		assertEquals(expectedResults2, output[output.length-1].trim());
	}
	

//	@Test
//	void testOptimize1() throws ExWrongDateFormat {
//		Item is = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(5);
//		
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(true, opt.getFound());
//	}
//	
//	@Test
//	void testOptimize2() throws ExWrongDateFormat {
//		Item is = new Item(0, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(1);
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(false, opt.getFound());
//	}
//	
//	@Test
//	void testOptimize3() throws ExWrongDateFormat {
//		Item is = new Item(-1, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(5);
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(false, opt.getFound());
//	}
//	
//	//This test case output must be false
//	@Test
//	void testOptimize4() throws ExWrongDateFormat {
//		Item is = new Item(-1, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(-1);
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(false, opt.getFound());
//	}
//	
//	@Test
//	void testOptimize5() throws ExWrongDateFormat {
//		Item is = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(10);
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(false, opt.getFound());
//	}
//	
//	@Test
//	void testOptimize6() throws ExWrongDateFormat {
//		Item is = new Item(10, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(0);
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(false, opt.getFound());
//	}
//	
//	@Test
//	void testOptimize7() throws ExWrongDateFormat {
//		Item is = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(5);
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(true, opt.getFound());
//	}
//	/*
//	@Test
//	void testOptimize8() throws ExWrongDateFormat {
//		Item is = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(5);
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(true, opt.getFound());
//	}
//	
//	@Test
//	void testOptimize9() throws ExWrongDateFormat {
//		Item is = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(5);
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(true, opt.getFound());
//	}
//	
//	@Test
//	void testOptimize10() throws ExWrongDateFormat {
//		Item is = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Slot ss = new Slot(5);
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is);
//		Optimize opt = new Optimize();
//	    opt.findOnePerfectFit(itemArray, ss);
//	    assertEquals(true, opt.getFound());
//	}
//	//
//*/
//	//test cases for findOnePerfectSubsets
//	@Test
//	void testOptimize8() throws ExWrongDateFormat {
//		Warehouse wh =  Warehouse.getInstance();
//		ArrayList<Slot> slots = new ArrayList<>();
//		Slot ss = new Slot(7);
//		slots.add(ss);
//		//wh.setSlots(slots);
//		Item is1 = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Item is2 = new Item(2, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is1);
//		itemArray.add(is2);
//		Optimize op = new Optimize();
//		op.findOnePerfectSubsets(itemArray, itemArray.size(), 6, ss);
//		assertEquals(true, op.getFound());
//	}
//	
//	@Test
//	void testOptimize9() throws ExWrongDateFormat {
//		Slot ss = new Slot(4);
//		Item is1 = new Item(-5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Item is2 = new Item(2, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		ArrayList<Item> itemArray = new ArrayList<Item>();
//		itemArray.add(is1);
//		itemArray.add(is2);
//		Optimize op = new Optimize();
//		op.findOnePerfectSubsets(itemArray, itemArray.size(), 3, ss);
//		assertEquals(true, op.getFound());
//	}
//	
//	@Test
//	void testOptimizeBuffer1() throws ExWrongDateFormat {
//		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	    System.setOut(new PrintStream(outContent));
//		Warehouse wh = Warehouse.getInstance();
//		Optimize op = new Optimize();
//		Slot slot2 = new Slot(7);
//		
//		Item item1 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Item item2 = new Item(4,new Day("13-Oct-2020"),new Day("20-Nov-2020"));
//		
//		wh.optimize();
//		assertEquals("The system is optimized.", outContent.toString());
//	}
//	
//	//print all items
//	@Test
//	void testPrint1() throws ExWrongDateFormat {
//		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	    System.setOut(new PrintStream(outContent));
//	    
//		SystemDate.createTheInstance("13-Oct-2020");
//		Warehouse wh = Warehouse.getInstance();
//		
//		Slot slot2 = new Slot(7);
//		
//		Item item1 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Item item2 = new Item(4,new Day("13-Oct-2020"),new Day("20-Nov-2020"));
//		
//		System.out.println("Before Optimized:");
//		System.out.println("__________________________________________");
////		wh.printAllSlotsDetails();
//		//Things to discuss, when should we automatically optimized
//		wh.optimize();
//		System.out.println("After Optimized:");
//		System.out.println("__________________________________________");
////		wh.printAllSlotsDetails();
//		assertEquals("Slot #" + slot2.getSlotID() + " is Full!", outContent.toString());
//	}
	
}
