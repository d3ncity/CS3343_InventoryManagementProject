package TestCases;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import Code.*;
public class TestMain {
	
	PrintStream stdOut = System.out;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SystemDate.createTheInstance("1-Oct-2020");
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
	

	@Test
	public void testMain1() throws IOException {
	    System.out.println("main");
	    String[] args = null;
	    final InputStream original = System.in;
	    final FileInputStream fips = new FileInputStream(new File("Testing.txt"));
	    System.setIn(fips);
	    Main.main(args);
	    System.setIn(original);
	}
	
	@Test
	public void testMain2() throws FileNotFoundException {
		
	    //System.out.println("main");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    String[] args = null;
	    final InputStream original = System.in;
	    final FileInputStream fips = new FileInputStream(new File("Testing2.txt"));
	    System.setIn(fips);
	    Main.main(args);
	    System.setIn(original);
	    
	    
	    String expectedResults1 = "File not Found!";
	    String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output);
	}
	
	@Test
	public void testMain3() throws NumberFormatException, FileNotFoundException
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
//		String cmdLine = "startNewDay|13-Oct-2020";
//		String[] cmdParts = cmdLine.split("\\|");
//		(new CmdSetDate()).execute(cmdParts);
//		String cmdLine1 = "addSlot|5";
//		String[] cmdParts1 = cmdLine1.split("\\|");
//		(new CmdAddSlot()).execute(cmdParts1);
//		String cmdLine4 = "addItem|5|14-Oct-2020";
//		String[] cmdParts4 = cmdLine4.split("\\|");
//		(new CmdAddItem()).execute(cmdParts4);
//		String cmdLine6 = "optimize";
//		String[] cmdParts6 = cmdLine6.split("\\|");
//		(new CmdOptimize()).execute(cmdParts6);
		//Main.acceptCmd(cmdParts);
		//String expectedResults1 = "Manually Optimized!";
		String[] args = null;
		final InputStream original = System.in;
		final FileInputStream fips = new FileInputStream(new File("Testing.txt"));
		System.setIn(fips);
		Main.main(args);
		System.setIn(original);
		String expectedResults1 = "Slot #1 is created and added.";
		String expectedResults2 = "Slot #2 is created and added.";
		String expectedResults3 = "Slot #3 is created and added.";
		String expectedResults4 = "Item #1 with size(3) is added in Slot ID #2 ; Delivery Date: 14-Oct-2020;";
		String expectedResults5 = "Slot #2 is Full!";
		String expectedResults6 = "1. Dimensions for Item #1 is 3, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 6].trim());
		assertEquals(expectedResults2, output[output.length - 5].trim());
		assertEquals(expectedResults3, output[output.length - 4].trim());
		assertEquals(expectedResults4, output[output.length - 3].trim());
		assertEquals(expectedResults5, output[output.length - 2].trim());
		assertEquals(expectedResults6, output[output.length - 1].trim());
	}
}
