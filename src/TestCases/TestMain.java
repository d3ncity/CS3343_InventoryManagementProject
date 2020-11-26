package TestCases;
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
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    String[] args = null;
	    final InputStream original = System.in;
	    String str = "Testing2.txt";
	    System.setIn(new ByteArrayInputStream(str.getBytes()));
	    Main.main(args);
	    System.setIn(original);
	    String expectedResults1 = "File not found!";
	    String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
	
	@Test
	public void testMain3() throws NumberFormatException, FileNotFoundException
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String[] args = null;
		String str = "Testing.txt";
		System.setIn(new ByteArrayInputStream(str.getBytes()));
	    Main.main(args);
		String expectedResults1 = "Slot #1 is created and added.";
		String expectedResults2 = "Slot #2 is created and added.";
		String expectedResults3 = "Slot #3 is created and added.";
		String expectedResults4 = "Item #1 with size(3) is added in Slot ID #2 ; Delivery Date: 14-Oct-2020";
		String expectedResults5 = "Slot #2 Details:";
		String expectedResults6 = "1. Dimensions for Item #1 is 3, Arrival Date: 13-Oct-2020, Departure Date: 14-Oct-2020";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[5].trim());
		assertEquals(expectedResults2, output[8].trim());
		assertEquals(expectedResults3, output[11].trim());
		assertEquals(expectedResults4, output[14].trim());
		assertEquals(expectedResults5, output[17].trim());
		assertEquals(expectedResults6, output[18].trim());
	}
	
	@Test
	public void testMain4() throws FileNotFoundException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    String[] args = null;
	    final InputStream original = System.in;
	    String str = "Testing3.txt";
	    System.setIn(new ByteArrayInputStream(str.getBytes()));
	    Main.main(args);
	    System.setIn(original);
	    String expectedResults1 = "File not found!";
	    String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
