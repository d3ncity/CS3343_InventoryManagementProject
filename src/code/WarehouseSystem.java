package code;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

//import java.io.File;
//import java.io.FileNotFoundException;

public class WarehouseSystem {

	static Logger logger = Logger.getLogger(Main.class);
	
	public void systemInput(Scanner in) {
		SystemInputCmd sInput = new SystemInputCmd();
		sInput.execute(in);
	}
	public void fileInput(Scanner in, String filePath) {
		FileInputCmd fInput = new FileInputCmd();
		fInput.execute(in, filePath);
	}

	public void execute() throws NumberFormatException, InterruptedException {
		
		logger.info("Warehouse Management System Program START....");

		int functionInput = 0;
		String filePath = null;

		Scanner command = new Scanner(System.in);
		Scanner in = new Scanner(System.in);

		do {
			System.out.print("---------------------------------------\n" + "Warehouse Management System Menu!\n"
					+ "Function (1): Command from System Input (For daily use)\n"
					+ "Function (2): Command from File Input (For predefined command)\n"
					+ "Type \"-1\" if you want to terminate the program.\n" + "Please select your function (1 / 2)? ");

			functionInput = in.nextInt();

//			try {

				System.out.print("---------------------------------------\n");
				switch (functionInput) {
					case 1:
						systemInput(in);
						break;
					case 2:
						fileInput(in, filePath);
						break;
					case -1:
						break;
					default:
						System.out.print("Wrong Command Input! Please retype!");
						break;
				}
//
//			} catch (NoSuchElementException e) {
//				logger.error("ExNoSuchElement: No command is found!");
//				System.out.println("No command is found!");
//			}
		} while (functionInput != -1);
		in.close();
		command.close();
		System.out.println("Warehouse / Inventory Manager Program END. Thank you for using our program.");
		logger.info("Warehouse / Inventory Manager Program END. Thank you for using our program.");
		TimeUnit.SECONDS.sleep(5);
//		System.exit(0);
	}
}
