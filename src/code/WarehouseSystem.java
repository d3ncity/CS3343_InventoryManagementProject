package code;

import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("unused")
public class WarehouseSystem {

	// Log4J - Program Log Declaration
	static Logger logger = Logger.getLogger(WarehouseSystem.class);

	// Method #1 - System Input Method
	public void systemInput(Scanner in) {
		(new SystemInputCmd()).execute(in);
	}

	// Method #2 - File Input Method
	public void fileInput(Scanner in) {
		(new FileInputCmd()).execute(in);
	}

	// Method #3 - Print Instructions
	public void printInstructions() {
		System.out.print("---------------------------------------\n" + "Warehouse Management System Menu!\n"
				+ "Function (1): Command from System Input (For daily use)\n"
				+ "Function (2): Command from File Input (For predefined command)\n"
				+ "Type \"-1\" if you want to terminate the program.\n" + "Please select your function (1 / 2)? ");
	}

	public void execute() throws NumberFormatException, InterruptedException {

		// local variable for input method selection
		int functionInput = 0;

		// Accept User Input
		Scanner command = new Scanner(System.in);
		Scanner input = new Scanner(System.in);

		do { // This flow will repeat until user choose to terminate

			try {
				printInstructions();

				functionInput = command.nextInt();

				System.out.print("---------------------------------------\n");

				// Switch to different input methods
				switch (functionInput) {
				case 1:
					systemInput(input);
					break;
				case 2:
					fileInput(input);
					break;
				case -1:
					break;
				default:
					System.out.println("Wrong Command Input! Please retype!");
					break;
				}
			} catch (InputMismatchException e) {
				String badInput = command.next();
	            System.out.println("Wrong Command Input! Please retype!");
	            continue;
			}
			

		} while (functionInput != -1);
		input.close();
		command.close();
		System.out.println("Warehouse System Terminated!");
		logger.info("Warehouse System Terminated!");
	}
}
