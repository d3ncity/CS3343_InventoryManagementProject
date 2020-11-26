package TestCases;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
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
	public void testMain() throws IOException {
	    System.out.println("main");
	    String[] args = null;
	    final InputStream original = System.in;
	    final FileInputStream fips = new FileInputStream(new File("[path_to_file]"));
	    System.setIn(fips);
	    Main.main(args);
	    System.setIn(original);
	}
	
	@Test
	public void testMain1()
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String cmdLine = "startNewDay|13-Oct-2020";
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdSetDate()).execute(cmdParts);
		String cmdLine1 = "addSlot|5";
		String[] cmdParts1 = cmdLine1.split("\\|");
		(new CmdAddSlot()).execute(cmdParts1);
		String cmdLine4 = "addItem|5|14-Oct-2020";
		String[] cmdParts4 = cmdLine4.split("\\|");
		(new CmdAddItem()).execute(cmdParts4);
		String cmdLine6 = "optimize";
		String[] cmdParts6 = cmdLine6.split("\\|");
		(new CmdOptimize()).execute(cmdParts6);
		Main.acceptCmd(cmdParts);
		String expectedResults1 = "Manually Optimized!";
		String[] output = outContent.toString().split("\n");
		assertEquals(expectedResults1, output[output.length - 1].trim());
	}
}
