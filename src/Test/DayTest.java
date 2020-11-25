package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Code.Day;
import Code.ExWrongDateFormat;
import Code.Item;
import Code.SystemDate;
import Code.Warehouse;

class DayTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Warehouse.getInstance().reset();
		SystemDate.createTheInstance("1-Oct-2020");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDay_01() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("1-Oct-2020");
	  
	    assertEquals(10, day.getMonth());
	}
	
	@Test
	void testDay_02() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("23-Oct-2020");
	  
	    assertEquals(23, day.getDay());
	}

	@Test
	void testDay_03() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day(2020, 11, 5);
	    System.out.println(day);
	  
	    assertEquals("5-Nov-2020", outContent.toString().trim().substring(outContent.toString().trim().lastIndexOf('\n')+1));
	}

	@Test
	void testDay_04() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(1960, 1, -3);
	  
	    assertEquals(false, result);
	}

	@Test
	void testDay_05() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(1960, 7, 28);
		  
	    assertEquals(true, result);
	}

	@Test
	void testDay_06() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(1960, 7, 32);
		  
	    assertEquals(false, result);
	}

	@Test
	void testDay_07() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(2015, 4, 30);
		  
	    assertEquals(true, result);
	}

	@Test
	void testDay_08() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(2015, 2, 29);
		  
	    assertEquals(false, result);
	}
	
	@Test
	void testDay_09() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(2024, 2, 29);
		  
	    assertEquals(true, result);
	}
	
	@Test
	void testDay_10() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(2024, 13, 29);
		  
	    assertEquals(false, result);
	}
	
	@Test
	void testDay_11() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(2015, 2, 29);
		  
	    assertEquals(false, result);
	}
	
	@Test
	void testDay_12() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(2000, 2, 29);
		  
	    assertEquals(true, result);
	}
	
	@Test
	void testDay_13() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    boolean result = Day.valid(1900, 2, 29);
		  
	    assertEquals(false, result);
	}
	
	@Test
	void testDay_14() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("23-Oct-2020");
		  
	    assertEquals(31, day.daysInMonth());
	}
	
	@Test
	void testDay_15() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day(2000, 11, 23);
		  
	    assertEquals(30, day.daysInMonth());
	}
	
	@Test
	void testDay_16() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("23-Feb-2020");
		  
	    assertEquals(29, day.daysInMonth());
	}
	
	@Test
	void testDay_17() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day(2000, 13, 10);
		  
	    assertEquals(-1, day.daysInMonth());
	}
	
	@Test
	void testDay_18() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("23-Oct-2020");
	    Day day1 = new Day("23-Oct-2020");
		  
	    assertEquals(0, day.compareTo(day1));
	}
	
	@Test
	void testDay_19() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("23-Oct-2020");
	    Day day1 = new Day("24-Oct-2020");
		  
	    assertEquals(-1, day.compareTo(day1));
	}
	
	@Test
	void testDay_20() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("23-Oct-2020");
	    Day day1 = new Day("20-Oct-2020");
		  
	    assertEquals(1, day.compareTo(day1));
	}
	
	@Test
	void testDay_21() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("23-Oct-2020");
	    Day day1 = new Day("20-Oct-2020");
		  
	    assertEquals(true, day.more(day1));
	}
	
	@Test
	void testDay_22() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("19-Oct-2020");
	    Day day1 = new Day("20-Oct-2020");
		  
	    assertEquals(false, day.more(day1));
	}
	
	@Test
	void testDay_23() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("23-Oct-2020");
	    Day day1 = new Day("20-Oct-2020");
		  
	    assertEquals(false, day.less(day1));
	}
	
	@Test
	void testDay_24() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("23-Oct-2020");
	    Day day1 = new Day("24-Oct-2020");
		  
	    assertEquals(true, day.less(day1));
	}
	
	@Test
	void testDay_25() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("25-Oct-2020");
	    Day day1 = new Day("24-Oct-2020");
	    Day day2 = new Day("29-Oct-2020");
		  
	    assertEquals(true, day.isBetween(day1, day2));
	}
	
	@Test
	void testDay_26() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("22-Oct-2020");
	    Day day1 = new Day("24-Oct-2020");
	    Day day2 = new Day("29-Oct-2020");
		  
	    assertEquals(false, day.isBetween(day1, day2));
	}
	
	@Test
	void testDay_27() throws ExWrongDateFormat {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Day day = new Day("22-Oct-2020");
	    Day day1 = day.clone();
		  
	    assertEquals(0, day.compareTo(day1));
	}
}
