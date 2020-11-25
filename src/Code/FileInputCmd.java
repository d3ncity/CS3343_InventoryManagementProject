package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class FileInputCmd extends InputCommand{
	
	static Logger logger = Logger.getLogger(Main.class);
	
	public void execute(Scanner in) {
//		Scanner in = new Scanner (System.in);
		logger.info("File Input Command Executed");
		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();

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
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ExWrongDateFormat e) {
			System.out.println(e.getMessage());
		} catch (ExSystemDateIsNotSet e) {
			System.out.println(e.getMessage());
		} finally {
			if (inFile != null)
				inFile.close();
		}
	}

}
