package testCases;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.*;

public class TestFileSystem {
	
	//Make sure the file is in the filepath
	
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
	public void testFile1() {
	    final InputStream original = System.in;
	    String input = "testTXT/Test1.txt" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner (System.in);
	    (new FileInputCmd()).execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "Slot #1 is created and added.";
	    String expectedResults2 = "File Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-2].trim());
		assertEquals(expectedResults2, output[output.length-1].trim());
	}
	
	@Test 
	public void testFile2() {
	    final InputStream original = System.in;
	    String input = "testTXT/Test2.txt" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner (System.in);
	    (new FileInputCmd()).execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Date has not yet been set!";
	    String expectedResults2 = "File Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-2].trim());
		assertEquals(expectedResults2, output[output.length-1].trim());
	}
	
	@Test 
	public void testFile3() {
	    final InputStream original = System.in;
	    String input = "testTXT/Test3.txt" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner (System.in);
	    (new FileInputCmd()).execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "System Date has not yet been set!";
	    String expectedResults2 = "File Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-2].trim());
		assertEquals(expectedResults2, output[output.length-1].trim());
	}
	
	@Test 
	public void testFile4() {
	    final InputStream original = System.in;
	    String input = "testTXT/Test4.txt" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner (System.in);
	    (new FileInputCmd()).execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "The file is empty!";
	    String expectedResults2 = "File Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-2].trim());
		assertEquals(expectedResults2, output[output.length-1].trim());
	}
	
	@Test 
	public void testFile5() {
	    final InputStream original = System.in;
	    String input = "testTXT/NoSuchFile.txt" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner (System.in);
	    (new FileInputCmd()).execute(scanner);
	    System.setIn(original);
	    String expectedResults1 = "File is not Found!";
	    String expectedResults2 = "File Input TERMINATED!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-2].trim());
		assertEquals(expectedResults2, output[output.length-1].trim());
	}

}
