package Test_V2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Code.Day;
import Code.Item;
import Code.Optimize;
import Code.Slot;

public class Test_findOnePerfectFit {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	//Test cases for the findOnePerfectFit Function
	@Test
	void testOptimize1_v2() {
		Item is = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Slot ss = new Slot(5);
		
		ArrayList<Item> itemArray = new ArrayList<Item>();
		itemArray.add(is);
		Optimize opt = new Optimize();
	    opt.findOnePerfectFit(itemArray, ss);
	    assertEquals(true, opt.getFound());
	}
	
	@Test
	void testOptimize2_v2() {
		Item is = new Item(0, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Slot ss = new Slot(1);
		ArrayList<Item> itemArray = new ArrayList<Item>();
		itemArray.add(is);
		Optimize opt = new Optimize();
	    opt.findOnePerfectFit(itemArray, ss);
	    assertEquals(false, opt.getFound());
	}
	
	@Test
	void testOptimize3_v2() {
		Item is = new Item(-1, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Slot ss = new Slot(5);
		ArrayList<Item> itemArray = new ArrayList<Item>();
		itemArray.add(is);
		Optimize opt = new Optimize();
	    opt.findOnePerfectFit(itemArray, ss);
	    assertEquals(false, opt.getFound());
	}
	
	//This test case output must be false
	@Test
	void testOptimize4_v2() {
		Item is = new Item(-1, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Slot ss = new Slot(-1);
		ArrayList<Item> itemArray = new ArrayList<Item>();
		itemArray.add(is);
		Optimize opt = new Optimize();
	    opt.findOnePerfectFit(itemArray, ss);
	    assertEquals(false, opt.getFound());
	}
	
	@Test
	void testOptimize5_v2() {
		Item is = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Slot ss = new Slot(10);
		ArrayList<Item> itemArray = new ArrayList<Item>();
		itemArray.add(is);
		Optimize opt = new Optimize();
	    opt.findOnePerfectFit(itemArray, ss);
	    assertEquals(false, opt.getFound());
	}
	
	@Test
	void testOptimize6_v2() {
		Item is = new Item(10, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Slot ss = new Slot(0);
		ArrayList<Item> itemArray = new ArrayList<Item>();
		itemArray.add(is);
		Optimize opt = new Optimize();
	    opt.findOnePerfectFit(itemArray, ss);
	    assertEquals(false, opt.getFound());
	}
	
	@Test
	void testOptimize7_v2() {
		Item is = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Slot ss = new Slot(5);
		ArrayList<Item> itemArray = new ArrayList<Item>();
		itemArray.add(is);
		Optimize opt = new Optimize();
	    opt.findOnePerfectFit(itemArray, ss);
	    assertEquals(true, opt.getFound());
	}

}
