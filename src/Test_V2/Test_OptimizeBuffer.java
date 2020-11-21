package Test_V2;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Code.Day;
import Code.Item;
import Code.Optimize;
import Code.Slot;
import Code.Warehouse;

public class Test_OptimizeBuffer {
	
	@Test
	void testOptimizeBuffer1()
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Warehouse wh = Warehouse.getInstance();
		Optimize op = new Optimize();
		Slot slot2 = new Slot(7);
		
		Item item1 = new Item(3,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(4,new Day("13-Oct-2020"),new Day("20-Nov-2020"));
		
		wh.optimize();
		assertEquals("The system is optimized.", outContent.toString());
	}
	
}
