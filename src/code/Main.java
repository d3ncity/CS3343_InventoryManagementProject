package code;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

//import java.io.File;
//import java.io.FileNotFoundException;

public class Main {

	static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws NumberFormatException, InterruptedException, NoSuchElementException {
		
		logger.info("Warehouse Management System Program START....");

		WarehouseSystem ws = new WarehouseSystem();
		
		ws.execute();
		
		System.out.println("Warehouse / Inventory Manager Program END. Thank you for using our program.");
		logger.info("Warehouse / Inventory Manager Program END. Thank you for using our program.");
		TimeUnit.SECONDS.sleep(5);
//		System.exit(0);
	}
}
