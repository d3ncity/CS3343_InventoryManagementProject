package TestCases;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Code.CmdAddSlot;
import Code.CmdOptimize;
import Code.Day;
import Code.Item;
import Code.Optimize;
import Code.Slot;
import Code.SystemDate;
import Code.Warehouse;
public class AddNewSlot {
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SystemDate.createTheInstance("1-Oct-2020");
		Warehouse wh = Warehouse.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testAddNewSlot1() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine = "addSlot|-1";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    //String msg = "Checking when the size of the item is bigger than any slot";
	    assertEquals("Volume Out Of Range. The size can only be (1-100).", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test
	void testAddNewSlot2() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine = "addSlot|101";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    //String msg = "Checking when the size of the item is bigger than any slot";
	    assertEquals("Volume Out Of Range. The size can only be (1-100).", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test //Array index out of bounds exception
	void testAddNewSlot3() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine = "addSlot";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    //String msg = "Checking when the size of the item is bigger than any slot";
	    assertEquals("Insufficient command arguments!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test
	void testAddNewSlot4() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine = "addSlot|4";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    assertEquals("Slot #1 is created and added.", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	    
	}
	
	@Test //Here the command is adding but not added. I guess, need to add an error condition for this as well
	void testAddNewSlot5() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    SystemDate.createTheInstance("13-Oct-2020");
	    String cmdLine = "addingSlot|5";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);
	    System.out.println(outContent.toString());
	    assertEquals("Invalid Command!", outContent.toString());
	}
	
	@Test //When input given is 0, there is a conflict between the error messages, Ivalid slot input & Volume Out of Range!!
	void testAddNewSlot6() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    String cmdLine = "addSlot|0";
	    String [] cmdParts = cmdLine.split("\\|");
	    (new CmdAddSlot()).execute(cmdParts);

	    //String msg = "Checking when the size of the item is less than 1";
	    assertEquals("Invalid Slot Dimension Input!", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}	
}
