package Code;

import java.util.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, NumberFormatException {
		
		//denny logging addition
		Logger logger = Logger.getLogger(Main.class);
		logger.info("Warehouse / Inventory Manager Program START....");
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please input the file pathname: ");
		String filepathname = in.nextLine();
		
		Scanner inFile = null;
		
		try {
			inFile = new Scanner(new File(filepathname));
//			inFile = new Scanner(new File("./Testing.txt"));
//			System.out.println("Program Started.");
			//The first command in the file must be to set the system date 
			//(eg. "startNewDay 03-Jan-2018"); and it cannot be undone
			String cmdLine1 = inFile.nextLine();
			String[] cmdLine1Parts = cmdLine1.split("\\|");
			
			
			//Split by vertical bar character '|' (Regular expression: "\|")
			if (cmdLine1Parts.length == 2 && cmdLine1Parts[0].equals("startNewDay")) {
				SystemDate.createTheInstance(cmdLine1Parts[1]);
				System.out.println("\n> "+cmdLine1);
			} else {
				//denny logging addition
				logger.error("Exception thrown - System Date NOT set!");
				throw new ExSystemDateIsNotSet();
			}
			
				
			while(inFile.hasNext()) {
				
				//Trim leading spaces
				String cmdLine = inFile.nextLine().trim();
				
				//Blank lines exist in data file as separators.  Skip them.
			    if(cmdLine.equals("")) continue;
			    
			    System.out.println("\n> " + cmdLine);
			    //split the words in actionLine => create an array of word strings
			    String[] cmdParts = cmdLine.split("\\|");
			    
				//For Execution
			    if(cmdParts[0].equals("startNewDay")) {
			    	(new CmdSetDate()).execute(cmdParts);  
			    	(new CmdDeliverExpiredItem()).execute(cmdParts);
			    	(new CmdAddItemFromQueue()).execute(cmdParts);
			    }
			    else if(cmdParts[0].equals("addSlot"))
				(new CmdAddSlot()).execute(cmdParts);
			    else if(cmdParts[0].equals("addItem"))
				(new CmdAddItem()).execute(cmdParts);
			    else if(cmdParts[0].equals("optimize"))
				(new CmdOptimize()).execute(cmdParts);
			    
			    //For Printing Details
			    else if(cmdParts[0].equals("visualize"))
				(new CmdVisualize()).execute(cmdParts);
			    else if(cmdParts[0].equals("listSlotByID"))
				(new CmdListSlotByID()).execute(cmdParts);
			    else if(cmdParts[0].equals("listItemByID"))
				(new CmdListItemByID()).execute(cmdParts);
			    else if(cmdParts[0].equals("listWarehouse"))
				(new CmdListWarehouse()).execute(cmdParts);
			    

			    //For Undo and Redo
				else if (cmdParts[0].equals("undo"))
					RecordedCommand.undoOneCommand();
				else if (cmdParts[0].equals("redo"))
					RecordedCommand.redoOneCommand();
				else {
					
					//denny logging addition
					Exception e = new ExInvalidCommand();
					logger.error("EXCEPTION: " + e.getMessage());
					throw new ExInvalidCommand();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (NoSuchElementException e) {
			System.out.println("No command is found!");
		} catch (ExInvalidCommand e){
			System.out.println(e.getMessage());
		} catch (ExSystemDateIsNotSet e) {
			System.out.println(e.getMessage());
		} catch (ExWrongDateFormat e) {
			System.out.println(e.getMessage());
		} finally {
			//Finished Reading the File
			if (inFile != null)
				inFile.close();
			in.close();	
			Optimize opt = new Optimize();
			System.out.println(opt.getFound());
			
			logger.info("Warehouse / Inventory Manager Program END. Thank you for using our program.");
//			System.out.println("Program Ended.");
		}
	}
}
