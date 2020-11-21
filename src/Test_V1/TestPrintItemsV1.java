package Test_V1;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Code.Day;
import Code.Item;
import Code.Slot;
import Code.SystemDate;
import Code.Warehouse;

public class TestPrintItemsV1 {
	//print all items
		@Test
		void testPrint1()
		{
			ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		    System.setOut(new PrintStream(outContent));
		    
			SystemDate.createTheInstance("13-Oct-2020");
			//Warehouse.getInstance();
			
			Slot slot2 = new Slot(7);
			
			Item item1 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
			Item item2 = new Item(4,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
			ArrayList<Item> items = new ArrayList<>();
			items.add(item1);
			items.add(item2);
			
			
			Warehouse.getInstance().printItemsInSlots();
			//Things to discuss, when should we automatically optimized
			Warehouse.getInstance().optimize(items);
			
			Warehouse.getInstance().printItemsInSlots();
			assertEquals("Slot is Full!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
		}
}
