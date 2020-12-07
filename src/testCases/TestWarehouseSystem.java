package testCases;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.*;
public class TestWarehouseSystem {
	
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	
	@Before
	public void setUp() throws Exception {
		Warehouse.getInstance().warehouseReset();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}
	
	@Test
	public void testMain1() throws IOException, NumberFormatException, InterruptedException {
		
		class StubSystem extends WarehouseSystem{
			public void systemInput(Scanner in) {
				return;
			}
		}
	
		WarehouseSystem ws = new StubSystem();

		System.setOut(new PrintStream(outContent));
	    final InputStream original = System.in;
	    
	    String input = "1 -1" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
		ws.execute();
	    System.setIn(original);
	    String expectedResults1 = "Warehouse System Terminated!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testMain2() throws IOException, NumberFormatException, InterruptedException {
		
		class StubSystem extends WarehouseSystem{
			public void fileInput(Scanner in) {
				return;
			}
		}
	
		WarehouseSystem ws = new StubSystem();
	
		System.setOut(new PrintStream(outContent));
	    final InputStream original = System.in;
	    
	    String input = "2 -1" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
		ws.execute();
	    System.setIn(original);
	    String expectedResults1 = "Warehouse System Terminated!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testMain3() throws IOException, NumberFormatException, InterruptedException {
			
		WarehouseSystem ws = new WarehouseSystem();
	    final InputStream original = System.in;
	    
	    String input = "-1" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
		ws.execute();
	    System.setIn(original);
	    String expectedResults1 = "Warehouse System Terminated!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testMain4() throws IOException, NumberFormatException, InterruptedException {
			
		WarehouseSystem ws = new WarehouseSystem();
	    final InputStream original = System.in;
	    
	    String input = "4 -1" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
		ws.execute();
	    System.setIn(original);
	    String expectedResults1 = "Warehouse System Terminated!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testMain5() throws IOException, NumberFormatException, InterruptedException {
			
		WarehouseSystem ws = new WarehouseSystem();
	    final InputStream original = System.in;
	    
	    String input = "13-Oct-2020\n5 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner (System.in);
		ws.systemInput(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testMain6() throws IOException, NumberFormatException, InterruptedException {
		
	    final InputStream original = System.in;
	    
	    String input = "./Testing.txt" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner (System.in);
	    (new WarehouseSystem()).fileInput(scanner);
	    System.setIn(original);
	    String expectedResults1 = "File Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	@Test
	public void testMain7() throws IOException, NumberFormatException, InterruptedException {
			
		WarehouseSystem ws = new WarehouseSystem();
	    final InputStream original = System.in;
	    
	    String input = "abcd -1" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
		ws.execute();
	    System.setIn(original);
	    String expectedResults2 ="Warehouse System Terminated!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults2, output[output.length-1].trim());
	}
}
