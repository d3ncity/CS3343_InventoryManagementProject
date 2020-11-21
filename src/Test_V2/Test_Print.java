package Test_V2;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Code.Day;
import Code.Item;
import Code.Slot;
import Code.SystemDate;
import Code.Warehouse;


public class Test_Print {
	//print all items
		@Test
		void testPrint1()
		{
			ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		    System.setOut(new PrintStream(outContent));
		    
			SystemDate.createTheInstance("13-Oct-2020");
			Warehouse wh = Warehouse.getInstance();
			
			Slot slot2 = new Slot(7);
			
			Item item1 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
			Item item2 = new Item(4,new Day("13-Oct-2020"),new Day("20-Nov-2020"));
			
//			System.out.println("Before Optimized:");
//			System.out.println("__________________________________________");
			//wh.printAllSlotsDetails();
			//Things to discuss, when should we automatically optimized
			wh.optimize();
//			System.out.println("After Optimized:");
//			System.out.println("__________________________________________");
			wh.printAllSlotsDetails();
			assertEquals("Slot #" + slot2.getSlotID() + " is Full!", outContent.toString());
		}
}
