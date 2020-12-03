package testCases;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.*;

public class TestManuallyOptimize {
	
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SystemDate.createTheInstance("13-Oct-2020");
	}
	
	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.setOut(stdOut);
	}
	public void addSlot(String cmdLine) {
		String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	}
	
	public void addItem(String cmdLine) {
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdAddItem()).execute(cmdParts);
	}
	public void optimize() {
		String cmdLine = "optimize";
		String[] cmdParts = cmdLine.split("\\|");
		(new CmdOptimize()).execute(cmdParts);
	}
	
	
	@Test //CmdOptimize could not be checked because it is not printing anything out
	void testManuallyOptimize1() throws ExWrongDateFormat {
	    optimize();
	    System.out.println(outContent.toString());
	    assertEquals("Nothing to optimize!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test
	void testManuallyOptimize2() throws ExInvalidCommand, ExWrongDateFormat {
	    String cmdLine = "optimizing";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdOptimize()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    InputCommand inputCmd = new InputCommand();
	    inputCmd.acceptCmd(cmdParts);
	    assertEquals("Invalid Command!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test
	void testManuallyOptimize3() throws ExInvalidCommand, ExWrongDateFormat, ExInsufficientArguments {
	    String cmdLine = "";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdOptimize()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    InputCommand inputCmd = new InputCommand();
	    inputCmd.acceptCmd(cmdParts);
	    assertEquals("Invalid Command!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test //Error: not letting to add items to the warehouse
	void testManuallyOptimize4() throws ExInvalidCommand, ExWrongDateFormat, ExInsufficientArguments {
	    addSlot("addSlot|1");
	    addItem("addItem|1|14-Oct-2020");
	    optimize();	    
	    assertEquals("Manually Optimized!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
}
