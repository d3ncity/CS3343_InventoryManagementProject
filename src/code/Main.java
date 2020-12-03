package code;

import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class Main {

	//Log4J - Program Log Declaration
	static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws NumberFormatException, InterruptedException, NoSuchElementException {
		
		//Program Start
		logger.info("Warehouse Management System Program START....");

		//Execute the main warehouse framework
		(new WarehouseSystem()).execute();
		
		//Program End
		System.out.println("Warehouse Management System END. Thank you for using our program.");
		logger.info("Warehouse Management System TERMINATED!");
		TimeUnit.SECONDS.sleep(5);
	}
}
