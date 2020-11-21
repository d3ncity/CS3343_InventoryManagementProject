package Test_V2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Code.Day;
import Code.Item;
import Code.Optimize;
import Code.Slot;
import Code.Warehouse;

public class Test_findOnePerfectSubset {
	
	//test cases for findOnePerfectSubsets
		@Test
		void testOptimize1()
		{
			Warehouse wh =  Warehouse.getInstance();
			ArrayList<Slot> slots = new ArrayList<>();
			Slot ss = new Slot(7);
			slots.add(ss);
			//wh.setSlots(slots);
			Item is1 = new Item(5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			Item is2 = new Item(2, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			ArrayList<Item> itemArray = new ArrayList<Item>();
			itemArray.add(is1);
			itemArray.add(is2);
			Optimize op = new Optimize();
			op.findOnePerfectSubsets(itemArray, itemArray.size(), 6, ss);
			assertEquals(true, op.getFound());
		}
		
		@Test
		void testOptimize2()
		{
			Slot ss = new Slot(4);
			Item is1 = new Item(-5, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			Item is2 = new Item(2, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			ArrayList<Item> itemArray = new ArrayList<Item>();
			itemArray.add(is1);
			itemArray.add(is2);
			Optimize op = new Optimize();
			op.findOnePerfectSubsets(itemArray, itemArray.size(), 3, ss);
			assertEquals(false, op.getFound());
		}
		
		@Test
		void testOptimize3()
		{
			Slot ss = new Slot(6);
			Item is1 = new Item(3, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			Item is2 = new Item(4, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			ArrayList<Item> itemArray = new ArrayList<Item>();
			itemArray.add(is1);
			itemArray.add(is2);
			Optimize op = new Optimize();
			op.findOnePerfectSubsets(itemArray, itemArray.size(), 3, ss);
			assertEquals(false, op.getFound());
		}
		
		@Test
		void testOptimize4()
		{
			Slot ss = new Slot(6);
			Item is1 = new Item(0, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			Item is2 = new Item(0, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			ArrayList<Item> itemArray = new ArrayList<Item>();
			itemArray.add(is1);
			itemArray.add(is2);
			Optimize op = new Optimize();
			op.findOnePerfectSubsets(itemArray, itemArray.size(), 3, ss);
			assertEquals(false, op.getFound());
		}
		
		@Test
		void testOptimize5()
		{
			Slot ss = null;
			Item is1 = new Item(0, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			Item is2 = new Item(0, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			ArrayList<Item> itemArray = new ArrayList<Item>();
			itemArray.add(is1);
			itemArray.add(is2);
			Optimize op = new Optimize();
			op.findOnePerfectSubsets(itemArray, itemArray.size(), 3, ss);
			assertEquals(false, op.getFound());
		}
		
		@Test
		void testOptimize6()
		{
			Slot ss = new Slot(6);
			Item is1 = null;
			Item is2 = null;
			ArrayList<Item> itemArray = new ArrayList<Item>();
			itemArray.add(is1);
			itemArray.add(is2);
			Optimize op = new Optimize();
			op.findOnePerfectSubsets(itemArray, itemArray.size(), 3, ss);
			assertEquals(false, op.getFound());
		}
		
		@Test
		void testOptimize7()
		{
			Slot ss = new Slot(-6);
			Item is1 = new Item(3, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			Item is2 = new Item(3, new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			ArrayList<Item> itemArray = new ArrayList<Item>();
			itemArray.add(is1);
			itemArray.add(is2);
			Optimize op = new Optimize();
			op.findOnePerfectSubsets(itemArray, itemArray.size(), 3, ss);
			assertEquals(false, op.getFound());
		}
}
