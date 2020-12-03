package testCases;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import code.*;
public class TestMain {
	
	PrintStream stdOut = System.out;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine = "addSlot|1";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
		
	
//	@Test
//	public void testMain1() throws IOException, NumberFormatException, InterruptedException {
//		
//		class StubSystem extends WarehouseSystem{
//			public void systemInput(Scanner in) {
//				return;
//			}
//		}
//	
//		WarehouseSystem ws = new StubSystem();
//		
//		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(outContent));
//	    final InputStream original = System.in;
//	    
//	    String input = "1 -1" ;
//	    InputStream in = new ByteArrayInputStream(input.getBytes());
//	    System.setIn(in);
//	    
//		ws.execute();
//	    System.setIn(original);
//	    String expectedResults1 = "Warehouse / Inventory Manager Program END. Thank you for using our program.";
//		String[] output = outContent.toString().split("\n");
//		for (int i = 0; i < output.length; i++) {
//			stdOut.println(output[i]);
//		}
//		assertEquals(expectedResults1, output[output.length-1].trim());
//	}
//	
//	@Test
//	public void testMain2() throws IOException, NumberFormatException, InterruptedException {
//		
//		class StubSystem extends WarehouseSystem{
//			public void fileInput(Scanner in, String filePath) {
//				return;
//			}
//		}
//	
//		WarehouseSystem ws = new StubSystem();
//		
//		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(outContent));
//	    final InputStream original = System.in;
//	    
//	    String input = "2 -1" ;
//	    InputStream in = new ByteArrayInputStream(input.getBytes());
//	    System.setIn(in);
//	    
//		ws.execute();
//	    System.setIn(original);
//	    String expectedResults1 = "Warehouse / Inventory Manager Program END. Thank you for using our program.";
//		String[] output = outContent.toString().split("\n");
//		for (int i = 0; i < output.length; i++) {
//			stdOut.println(output[i]);
//		}
//		assertEquals(expectedResults1, output[output.length-1].trim());
//	}
//	
//	@Test
//	public void testMain3() throws IOException, NumberFormatException, InterruptedException {
//			
//		WarehouseSystem ws = new WarehouseSystem();
//		
//		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(outContent));
//	    final InputStream original = System.in;
//	    
//	    String input = "-1" ;
//	    InputStream in = new ByteArrayInputStream(input.getBytes());
//	    System.setIn(in);
//	    
//		ws.execute();
//	    System.setIn(original);
//	    String expectedResults1 = "Warehouse / Inventory Manager Program END. Thank you for using our program.";
//		String[] output = outContent.toString().split("\n");
//		for (int i = 0; i < output.length; i++) {
//			stdOut.println(output[i]);
//		}
//		assertEquals(expectedResults1, output[output.length-1].trim());
//	}
//	
//	@Test
//	public void testMain4() throws IOException, NumberFormatException, InterruptedException {
//			
//		WarehouseSystem ws = new WarehouseSystem();
//		
//		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(outContent));
//	    final InputStream original = System.in;
//	    
//	    String input = "4 -1" ;
//	    InputStream in = new ByteArrayInputStream(input.getBytes());
//	    System.setIn(in);
//	    
//		ws.execute();
//	    System.setIn(original);
//	    String expectedResults1 = "Warehouse / Inventory Manager Program END. Thank you for using our program.";
//		String[] output = outContent.toString().split("\n");
//		for (int i = 0; i < output.length; i++) {
//			stdOut.println(output[i]);
//		}
//		assertEquals(expectedResults1, output[output.length-1].trim());
//	}
	
	@Test
	public void testMain5() throws IOException, NumberFormatException, InterruptedException {
			
		WarehouseSystem ws = new WarehouseSystem();
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    final InputStream original = System.in;
	    
	    String input = "13-Oct-2020\n5 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner (System.in);
		ws.systemInput(scanner);
	    System.setIn(original);
	    String expectedResults1 = "Warehouse / Inventory Manager Program END. Thank you for using our program.";
		String[] output = outContent.toString().split("\n");
		for (int i = 0; i < output.length; i++) {
			stdOut.println(output[i]);
		}
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
}
