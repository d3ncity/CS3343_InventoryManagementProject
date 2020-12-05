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
public class TestMain {
	
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
		Main main = new Main();
	    String input = "-1" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
		Main.main(null);
		System.setIn(System.in);
	    String expectedResults1 = "Warehouse Management System END. Thank you for using our program.";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length-1].trim());
	}
}
