package Code;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, NumberFormatException {
		
//		//For testing
//		Scanner readLine = new Scanner(System.in);		
//		
//		System.out.println("Program Started.");
//		SystemDate.createTheInstance("12-Oct-2020");
//		
//		String cmdLine = "";
//		
//		while(cmdLine != "End") {
//			System.out.print(">");
//			cmdLine = readLine.nextLine().trim();
//			if(cmdLine.equals("")) continue;
//			String[] cmdParts = cmdLine.split("\\|");
//			
//			//For Execution
//		    if(cmdParts[0].equals("startNewDay")) {
//		    	(new CmdSetDate()).execute(cmdParts);  
//		    	(new CmdDeliverExpiredItem()).execute(cmdParts);  
//		    }
//		    else if(cmdParts[0].equals("addSlot"))
//	    	(new CmdAddSlot()).execute(cmdParts);
//		    else if(cmdParts[0].equals("addItem"))
//	    	(new CmdAddItem()).execute(cmdParts);
//		    else if(cmdParts[0].equals("optimize"))
//	    	(new CmdOptimize()).execute(cmdParts);
//		    
//		    //For Printing Details
//		    else if(cmdParts[0].equals("visualize"))
//	    	(new CmdVisualize()).execute(cmdParts);
//		    else if(cmdParts[0].equals("listSlotByID"))
//	    	(new CmdListSlotByID()).execute(cmdParts);
//		    else if(cmdParts[0].equals("listItemByID"))
//	    	(new CmdListItemByID()).execute(cmdParts);
//		    else if(cmdParts[0].equals("listWarehouse"))
//	    	(new CmdListWarehouse()).execute(cmdParts);
//		    
//
//		    //For Undo and Redo
//			else if (cmdParts[0].equals("undo"))
//				RecordedCommand.undoOneCommand();
//			else if (cmdParts[0].equals("redo"))
//				RecordedCommand.redoOneCommand();	
//			
//		}
//		System.out.println("Program Ended.");
//			
//		readLine.close();
		
//		Scanner in = new Scanner(System.in);
//		System.out.println("Please input the file pathname: ");
//		String filepathname = in.nextLine();
		
		Scanner inFile = null;
		
		try {
//			inFile = new Scanner(new File(filepathname));
			inFile = new Scanner(new File("Testing.txt"));
			System.out.println("Program Started.");
			//The first command in the file must be to set the system date 
			//(eg. "startNewDay 03-Jan-2018"); and it cannot be undone
			String cmdLine1 = inFile.nextLine();
			String[] cmdLine1Parts = cmdLine1.split("\\|");
			
			
			//Split by vertical bar character '|' (Regular expression: "\|")
			System.out.println("\n> "+cmdLine1);
			SystemDate.createTheInstance(cmdLine1Parts[1]);
				
			while(inFile.hasNext()) {
				
				//Trim leading spaces
				String cmdLine = inFile.nextLine().trim();
				
				//Blank lines exist in data file as separators.  Skip them.
			    if(cmdLine.equals("")) continue;
			    
			    System.out.println("\n>" + cmdLine);
			    //split the words in actionLine => create an array of word strings
			    String[] cmdParts = cmdLine.split("\\|");
			    
				//For Execution
			    if(cmdParts[0].equals("startNewDay")) {
			    	(new CmdSetDate()).execute(cmdParts);  
			    	(new CmdDeliverExpiredItem()).execute(cmdParts);  
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
				else
					throw new ExInvalidCommand();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (NumberFormatException e) {
			System.out.println("The system date should have format of \"dd-mmName-yyyy\" e.g 12-Oct-2020.");
		} catch (ExInvalidCommand e){
			System.out.println(e.getMessage());
		} finally {
			//Finished Reading the File
			if (inFile != null)
				inFile.close();
//			in.close();	
			System.out.println("Program Ended.");
		}
		

		
		
//		/*
//		 * Test Cases for Harvey NG V2.0
//		 * Please follow this order
//		 * The results would be shown immediately
//		 */
//		SystemDate.createTheInstance("13-Oct-2020");
//		Warehouse warehouse = Warehouse.getInstance();
//		Slot slot1 = new Slot(1);
//		Slot slot2 = new Slot(2);
//		Slot slot3 = new Slot(3);
//		Slot slot4 = new Slot(4);
//		Slot slot5 = new Slot(5);
//		
//		Item item1 = new Item(1,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Item item2 = new Item(2,new Day("13-Oct-2020"),new Day("20-Nov-2020"));
//		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("25-Oct-2020"));
//		Item item4 = new Item(4,new Day("13-Oct-2020"),new Day("25-Oct-2020"));
//		Item item5 = new Item(2,new Day("13-Oct-2020"),new Day("25-Oct-2020"));
//		Item item6 = new Item(2,new Day("13-Oct-2020"),new Day("25-Oct-2020"));
//	
//		System.out.println("Before Optimized:");
//		System.out.println("__________________________________________");
//		warehouse.printAllSlotsDetails();
//		//Things to discuss, when should we automatically optimized
//		warehouse.optimize();
//		System.out.println("After Optimized:");
//		System.out.println("__________________________________________");
//		warehouse.printAllSlotsDetails();
	}
}
