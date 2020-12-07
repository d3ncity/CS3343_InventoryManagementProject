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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import code.*;

@SuppressWarnings("unused")
public class TestSystemInput {
	
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	SystemInputCmd sic = new SystemInputCmd();
	final InputStream original = System.in;
	
	@Before
	public void setUp() throws Exception {
		Warehouse.getInstance().warehouseReset();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}
	
	public void addSlot(String cmdLine) {
		String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	}
	
	@Test
	public void testSystemInput_01() throws IOException, NumberFormatException, InterruptedException { 
	    String input = "13-Oct-2020\n 5 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_02() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
	    String input = "5 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_03() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "1 5 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_04() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "2 5 15-Oct-2020 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_05() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "3 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_06() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "4 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_07() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "5 1 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_08() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "6 1 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_09() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "7 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_10() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "8 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_11() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "9 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_12() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "10 Y Y 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_13() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "11 14-Oct-2020 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_14() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "12 12 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_15() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "14 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
	
	@Test
	public void testSystemInput_16() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "10 N 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		for (int i = 0; i < output.length; i++) {
			stdOut.println(output[i]);
		}
		assertEquals(true, output[output.length-1].trim().contains(expectedResults1));
	}
	
	@Test
	public void testSystemInput_17() throws IOException, NumberFormatException, InterruptedException { 
		SystemDate.createTheInstance("13-Oct-2020");
		addSlot("addSlot|5");
		String input = "abc 13" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Scanner scanner = new Scanner(System.in);
		sic.execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
}
