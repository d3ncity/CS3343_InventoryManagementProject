package Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Code.CmdAddItem;
import Code.CmdAddSlot;
import Code.CmdOptimize;
import Code.ExInsufficientArguments;
import Code.ExInvalidCommand;
import Code.ExWrongDateFormat;
import Code.Main;
import Code.SystemDate;
import Code.Warehouse;

public class TestManuallyOptimize {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		Warehouse.getInstance().reset();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@BeforeEach
	void setUp() throws Exception {
		Warehouse.getInstance().reset();
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test //CmdOptimize could not be checked because it is not printing anything out
	void testManuallyOptimize1() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine = "optimize";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdOptimize()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    assertEquals("Nothing to optimize!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test
	void testManuallyOptimize2() throws ExInvalidCommand, ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine = "optimizing";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdOptimize()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    Main.acceptCmd(cmdParts);
	    assertEquals("Invalid Command!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test
	void testManuallyOptimize3() throws ExInvalidCommand, ExWrongDateFormat, ExInsufficientArguments {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine = "";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdOptimize()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    Main.acceptCmd(cmdParts);
	    assertEquals("Invalid Command!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test //Error: not letting to add items to the warehouse
	void testManuallyOptimize4() throws ExInvalidCommand, ExWrongDateFormat, ExInsufficientArguments {
		Warehouse wh = Warehouse.getInstance();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine1 = "addSlot|1";
	    String [] cmdParts1 = cmdLine1.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts1);
	    String cmdLine2 = "addItem|1";
	    String [] cmdParts2 = cmdLine2.split("\\|");
	    (new CmdAddItem()).execute(cmdParts2);
	    String cmdLine = "optimize";	    
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdOptimize()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    Main.acceptCmd(cmdParts);
	    assertEquals("Manually Optimized!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
}
