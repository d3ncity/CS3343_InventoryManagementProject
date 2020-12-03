package code;

import java.util.*;
import org.apache.log4j.Logger;

//import java.io.File;
//import java.io.FileNotFoundException;

public class Main {

	static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws NumberFormatException {

		logger.info("Warehouse Management System Program START....");

		int functionInput = 0;

		Scanner command = new Scanner(System.in);
		Scanner in = new Scanner(System.in);

		do {
			System.out.print("---------------------------------------\n" + "Warehouse Management System Menu!\n"
					+ "Function (1): Command from System Input (For daily use)\n"
					+ "Function (2): Command from File Input (For predefined command)\n"
					+ "Type \"-1\" if you want to terminate the program.\n" + "Please select your function (1 / 2)? ");

			functionInput = Integer.parseInt(command.nextLine());

			try {

				System.out.print("---------------------------------------\n");

				switch (functionInput) {
					case 1:
						SystemInputCmd sInput = new SystemInputCmd();
						sInput.execute(in);
						break;
					case 2:
						FileInputCmd fInput = new FileInputCmd();
						fInput.execute(in);
						break;
					case -1:
						break;
					default:
						System.out.print("Wrong Command Input! Please retype!");
				}

			} catch (NoSuchElementException e) {
				logger.error("ExNoSuchElement: No command is found!");
				System.out.println("No command is found!");
			}
		} while (functionInput != -1);
		in.close();
		command.close();
		logger.info("Warehouse / Inventory Manager Program END. Thank you for using our program.");
		System.exit(0);
	}
}
