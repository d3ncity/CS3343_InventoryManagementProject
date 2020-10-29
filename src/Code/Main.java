package Code;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = new Scanner(System.in);
//		
//		System.out.println("Please input the file pathname: ");
//		String filepathname = in.nextLine();
//		
//		Scanner inFile = new Scanner(new File(filepathname));
//		
//		//The first command in the file must be to set the system date 
//		//(eg. "startNewDay 03-Jan-2018"); and it cannot be undone
//		String cmdLine1 = inFile.nextLine();
//		String[] cmdLine1Parts = cmdLine1.split("\\|");
//		
//		
//	    //Split by vertical bar character '|' (Regular expression: "\|")
//		System.out.println("\n> "+cmdLine1);
//		SystemDate.createTheInstance(cmdLine1Parts[1]);
//	
//		
//		while(inFile.hasNext()) {
//			String cmdLine = inFile.nextLine().trim();
//			
//			//Blank lines exist in data file as separators.  Skip them.
//		    if(cmdLine.equals("")) continue;
//		    
//		    System.out.println("\n>" + cmdLine);
//		    //split the words in actionLine => create an array of word strings
//		    String[] cmdParts = cmdLine.split("\\|");
//		    if(cmdParts[0].equals("request"))
//		    	(new CmdRequest()).execute(cmdParts);
//		    else if(cmdParts[0].equals("listReservations"))
//		    	(new CmdListReservations()).execute(cmdParts);
//		    else if(cmdParts[0].equals("startNewDay"))
//		    	(new CmdStartNewDay()).execute(cmdParts);
//		    else if(cmdParts[0].equals("cancel"))
//		    	(new CmdCancel()).execute(cmdParts);
//		    		
//		    		
//		}
//		
//		in.close();
		/*
		 * This part has been moved to CmdRequest and CmdListReservation functions
		 * The Command pattern allows us to encapsulate functions
		 * So, clients can issue commands without knowing the actual implementations of the function*/
		 
		 
//		System.out.println(SystemDate.getInstance());
// 		Item item1 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"), 1);
// 		Item item2 = new Item(1,new Day("13-Oct-2020"),new Day("20-Nov-2020"), 2);
// 		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("25-Oct-2020"), 3);
		
		//Harvey NG V1.0 - 2020-Oct-19: Deleted the human input ID;
		
		/*
		 * Test Cases for Harvey NG V2.0
		 * Please follow this order
		 * The results would be shown immediately
		 */
		SystemDate.createTheInstance("13-Oct-2020");
		ArrayList<Slot> slots = new ArrayList<>();
		Warehouse.createInstance(slots);
		Warehouse warehouse = Warehouse.getInstance();
		Slot slot1 = new Slot(5);
		Slot slot2 = new Slot(5);
		Slot slot3 = new Slot(5);
		Slot slot4 = new Slot(5);
		Slot slot5 = new Slot(5);
		slots.add(slot1);
		slots.add(slot2);
		slots.add(slot3);
		slots.add(slot4);
		slots.add(slot5);
		Collections.sort(slots);
		//warehouse.printAllSlotsDetails();		
		
		Item item1 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
		Item item2 = new Item(1,new Day("13-Oct-2020"),new Day("20-Nov-2020"));
		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("25-Oct-2020"));
		
		warehouse.moveToSlot(item1);
		warehouse.moveToSlot(item2);
		warehouse.moveToSlot(item3);
		warehouse.printAllSlotsDetails();
		//Things to discuss, when should we automatically optimized
		warehouse.optimize();
		warehouse.printAllSlotsDetails();
		
		
		
//		//Denny Test Case
//		Item item1 = new Item(2,new Day("13-Oct-2020"),new Day("20-Oct-2020"));
//		Item item2 = new Item(1,new Day("13-Oct-2020"),new Day("20-Nov-2020"));
//		Item item3 = new Item(3,new Day("13-Oct-2020"),new Day("25-Oct-2020"));
//
//
//		
//		Slot slot1 = new Slot(1);
//		Slot slot2 = new Slot(5);
//		Slot slot3 = new Slot(5);
//		Slot slot4 = new Slot(5);
//		Slot slot5 = new Slot(5);
//		
//		slot1.addItem(item1);
//		slot2.addItem(item2);
//		slot3.addItem(item3);
//		
//		slots.add(slot1);
//		slots.add(slot2);
//		slots.add(slot3);
//		slots.add(slot4);
//		slots.add(slot5);
//		
//		
//		Warehouse.getInstance().printItemsInSlots();
//		
//		//denny addition
//		ArrayList<Item> items = new ArrayList<>();
//		items.add(item1);
//		items.add(item2);
//		items.add(item3);
//		
//		//denny addition
//		Warehouse.getInstance().optimize(items);
//		
//		Warehouse.getInstance().printItemsInSlots();
//		//KALYS - Main Class with requests
//		
//		//*/
		
		
	}
}
