package code;

import org.apache.log4j.Logger;

public class InputCommand {
	
	static Logger logger = Logger.getLogger(Main.class);
	
	Warehouse wh = Warehouse.getInstance();
	
	public void acceptCmd(String[] cmdParts) {
		try {
			// For Execution
			if (cmdParts[0].equals("startNewDay")) {
				(new CmdSetDate()).execute(cmdParts);
				(new CmdDeliverExpiredItem()).execute(cmdParts);
				(new CmdAddItemFromQueue()).execute(cmdParts);
			} else if (cmdParts[0].equals("addSlot"))
				(new CmdAddSlot()).execute(cmdParts);
			else if (cmdParts[0].equals("addItem"))
				(new CmdAddItem()).execute(cmdParts);
			else if (cmdParts[0].equals("optimize"))
				(new CmdOptimize()).execute(cmdParts);

			// For Printing Details
			else if (cmdParts[0].equals("visualize"))
				(new CmdVisualize()).execute(cmdParts);
			else if (cmdParts[0].equals("listSlotByID"))
				(new CmdListSlotByID()).execute(cmdParts);
			else if (cmdParts[0].equals("listItemByID"))
				(new CmdListItemByID()).execute(cmdParts);
			else if (cmdParts[0].equals("listWarehouse"))
				(new CmdListWarehouse()).execute(cmdParts);
			else if (cmdParts[0].equals("changeStatus")) {
				if (wh.getAutomationStatus())
					wh.setAutomationStatus(false);
				else 
					wh.setAutomationStatus(true);
				System.out.println("Automation Status Changed!");
			}

			// For Undo and Redo
			else if (cmdParts[0].equals("undo"))
				RecordedCommand.undoOneCommand();
			else if (cmdParts[0].equals("redo"))
				RecordedCommand.redoOneCommand();
//			else if (cmdParts[0].equals("EXIT"))
//				return;
			else {
				// Logging
				Exception e = new ExInvalidCommand();
				logger.error("EXCEPTION: " + e.getMessage());
				throw new ExInvalidCommand();
			}
		} catch (ExInvalidCommand e) {
			System.out.println(e.getMessage());
		} 
	}
}
