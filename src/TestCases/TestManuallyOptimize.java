package TestCases;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Code.CmdOptimize;
import Code.ExWrongDateFormat;
import Code.SystemDate;

public class TestManuallyOptimize {
	
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
	
}
