package Code;

import java.util.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	static Logger logger = Logger.getLogger(Main.class);

//	public static void fileInputCommand(Scanner in) {
//		logger.info("File Input Command Executed");
//		System.out.print("Please input the file pathname: ");
//		String filepathname = in.nextLine();
//
//		Scanner inFile = null;
//		try {
//			inFile = new Scanner(new File(filepathname));
//
//			// The first command in the file must be to set the system date
//			// (eg. "startNewDay 03-Jan-2018"); and it cannot be undone
//			// Split by vertical bar character '|' (Regular expression: "\|")
//			String cmdLine1 = inFile.nextLine();
//			String[] cmdLine1Parts = cmdLine1.split("\\|");
//
//			if (cmdLine1Parts.length == 2 && cmdLine1Parts[0].equals("startNewDay")) {
//				// Create Date Instance
//				SystemDate.createTheInstance(cmdLine1Parts[1]);
//				System.out.println("\n> " + cmdLine1);
//			} else {
//				// Logging
//				logger.error("Exception thrown - System Date is NOT set!");
//				throw new ExSystemDateIsNotSet();
//			}
//
//			while (inFile.hasNext()) {
//
//				// Trim leading spaces
//				String cmdLine = inFile.nextLine().trim();
//
//				// Blank lines exist in data file as separators. Skip them.
//				if (cmdLine.equals(""))
//					continue;
//
//				System.out.println("\n> " + cmdLine);
//				// split the words in actionLine => create an array of word strings
//				String[] cmdParts = cmdLine.split("\\|");
//
//				acceptCommand(cmdParts);
//			}
//			
//		} catch (FileNotFoundException e) {
//			System.out.println(e.getMessage());
//		} catch (ExWrongDateFormat e) {
//			System.out.println(e.getMessage());
//		} catch (ExSystemDateIsNotSet e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if (inFile != null)
//				inFile.close();
//		}
//	}
//
//	public static void systemInputCommand(Scanner in) throws ExWrongDateFormat, ExSystemDateIsNotSet {
//		logger.info("System Input Command Executed");
//		
//		System.out.print("Please first set the system date\n>");
//		String cmdLine = in.nextLine();
//		String[] cmdParts = cmdLine.split("\\|");
//
//		if (cmdParts.length == 2 && cmdParts[0].equals("startNewDay")) {
//			// Create Date Instance
//			SystemDate.createTheInstance(cmdParts[1]);
//		} else {
//			// Logging
//			logger.error("Exception thrown - System Date is NOT set!");
//			throw new ExSystemDateIsNotSet();
//		}
//		
//		System.out.print("Please type the command:\n");
//		do {
//			System.out.print(">");
//			// Trim leading spaces
//			cmdLine = in.nextLine().trim();
//		
//			// Blank lines exist in data file as separators. Skip them.
//			if (cmdLine.equals(""))
//				continue;
//
////			System.out.println("\n> " + cmdLine);
//			// split the words in actionLine => create an array of word strings
//			cmdParts = cmdLine.split("\\|");
//	
//			acceptCommand(cmdParts);
//			
//			
//		}while(!cmdLine.toUpperCase().equals("EXIT"));
//	}
//
//	public static void acceptCommand(String[] cmdParts) {
//	
//		try {
//			// For Execution
//			if (cmdParts[0].equals("startNewDay")) {
//				(new CmdSetDate()).execute(cmdParts);
//				(new CmdDeliverExpiredItem()).execute(cmdParts);
//				(new CmdAddItemFromQueue()).execute(cmdParts);
//			} else if (cmdParts[0].equals("addSlot"))
//				(new CmdAddSlot()).execute(cmdParts);
//			else if (cmdParts[0].equals("addItem"))
//				(new CmdAddItem()).execute(cmdParts);
//			else if (cmdParts[0].equals("optimize"))
//				(new CmdOptimize()).execute(cmdParts);
//
//			// For Printing Details
//			else if (cmdParts[0].equals("visualize"))
//				(new CmdVisualize()).execute(cmdParts);
//			else if (cmdParts[0].equals("listSlotByID"))
//				(new CmdListSlotByID()).execute(cmdParts);
//			else if (cmdParts[0].equals("listItemByID"))
//				(new CmdListItemByID()).execute(cmdParts);
//			else if (cmdParts[0].equals("listWarehouse"))
//				(new CmdListWarehouse()).execute(cmdParts);
//
//			// For Undo and Redo
//			else if (cmdParts[0].equals("undo"))
//				RecordedCommand.undoOneCommand();
//			else if (cmdParts[0].equals("redo"))
//				RecordedCommand.redoOneCommand();
//			else if (cmdParts[0].equals("EXIT"))
//				return;
//			else {
//				// Logging
//				Exception e = new ExInvalidCommand();
//				logger.error("EXCEPTION: " + e.getMessage());
//				throw new ExInvalidCommand();
//			}
//		} catch (ExInvalidCommand e) {
//			System.out.println(e.getMessage());
//		} 
//	}

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

			functionInput = command.nextInt();

			try {

				System.out.print("---------------------------------------\n");

				switch (functionInput) {
					case 1:
						SystemInputCmd sInput = new SystemInputCmd();
//						systemInputCommand(in);
						sInput.execute(in);
						break;
					case 2:
						FileInputCmd fInput = new FileInputCmd();
						fInput.execute(in);
//						fileInputCommand(in);
						break;
					case -1:
						break;
					default:
						throw new ExInvalidCommand();
				}

			} catch (NoSuchElementException e) {
				System.out.println("No command is found!");
			} catch (ExInvalidCommand e) {
				System.out.println(e.getMessage());
			}
//			} catch (ExWrongDateFormat e) {
//				System.out.println(e.getMessage());
//			} catch (ExSystemDateIsNotSet e) {
//				System.out.println(e.getMessage());
//			}

		} while (functionInput != -1);
		in.close();
		command.close();
		logger.info("Warehouse / Inventory Manager Program END. Thank you for using our program.");
	}
}
