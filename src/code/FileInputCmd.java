package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class FileInputCmd extends InputCommand{
	
	static Logger logger = Logger.getLogger(Main.class);
	private static boolean fileFound = false;
	
	public void execute(Scanner in, String filepath) {
		logger.info("File Input Command Executed");
		fileFound = false;
		
		while (!fileFound) {
			System.out.print("Please input the file pathname: ");
			String filepathname = null;
			if (filepath == null)
				filepathname =  in.nextLine();
			else 
				filepathname = filepath;

			Scanner inFile = null;
			try {
				inFile = new Scanner(new File(filepathname));

				// The first command in the file must be to set the system date
				// (eg. "startNewDay 03-Jan-2018"); and it cannot be undone
				// Split by vertical bar character '|' (Regular expression: "\|")
				String cmdLine1 = inFile.nextLine();
				String[] cmdLine1Parts = cmdLine1.split("\\|");

				if (cmdLine1Parts.length == 2 && cmdLine1Parts[0].equals("startNewDay")) {
					// Create Date Instance
					SystemDate.createTheInstance(cmdLine1Parts[1]);
					System.out.println("\n> " + cmdLine1);
				} else {
					// Logging
					logger.error("Exception thrown - System Date is NOT set!");
					throw new ExSystemDateIsNotSet();
				}

				while (inFile.hasNext()) {

					// Trim leading spaces
					String cmdLine = inFile.nextLine().trim();

					// Blank lines exist in data file as separators. Skip them.
					if (cmdLine.equals(""))
						continue;

					System.out.println("\n> " + cmdLine);
					// split the words in actionLine => create an array of word strings
					String[] cmdParts = cmdLine.split("\\|");

					super.acceptCmd(cmdParts);
				}
				
				fileFound = true;
			} catch (FileNotFoundException e) {
				System.out.println("File is not Found!");
				fileFound = false;
			} catch (ExSystemDateIsNotSet e) {
				System.out.println(e.getMessage());
			} finally {
				if (inFile != null)
					inFile.close();
			}
		}
		System.out.println("File Input TERMINATED!");
	}
}
