package code;

import org.apache.log4j.Logger;

public class CmdAddItem extends RecordedCommand {
	
	// Log4J - Program Log Declaration
	static Logger logger = Logger.getLogger(CmdAddItem.class);
	
	//Instance Field
	private int dimension;
	private Item item;
	private Slot slot;
	
	@Override
	public void execute(String[] cmdParts) {
		
		logger.info("Add Item Command Executing!");
		
		try {
			
			//If the command arguments are less than 3
			if (cmdParts.length < 3) {
				throw new ExInsufficientArguments();
			}
			
			//Accept the Input and store it
			String sDimension = cmdParts[1];
			Day sArrivalDate = SystemDate.getInstance();
			String sDepartureDate = cmdParts[2];
			dimension = Integer.parseInt(sDimension);
			
			//Check if the Input is valid
			if (Warehouse.getInstance().getSlotList().size() == 0) {
				throw new ExNoSlotInWarehouse();
			}
			
			//Check if the delivery date is on or before the system date
			if ((new Day(sDepartureDate).compareTo(sArrivalDate) <= 0)) {
				throw new ExInvalidDate();
			}
			
			//Check if the dimension is small than 1 or larger than the largest slot
			if (dimension < 1 || dimension > Warehouse.getInstance().getTheLargestSlotSize()) {
				throw new ExInvalidItemDimension(
						"Invalid Dimension Input! The size should be >0 and <="+
				Warehouse.getInstance().getTheLargestSlotSize()+" (The largest slot size).");
			}
			
			//Create the item if the above exceptions is not thrown
			item = new Item(dimension,sArrivalDate,new Day(sDepartureDate));
			slot = item.getCurrentSlot();
			if (slot != null) {
				if (slot.getFreeVolume() == 0)
		        	System.out.println("Slot #"+ slot.getSlotID() +" is Full!");
				System.out.println("Item #"+item.getItemID()+" with size("+item.getDimensions()+") is added in Slot ID #"+ 
						item.getCurrentSlot().getSlotID()+ " ; Delivery Date: " + item.getDepartureDate());
			}
			
			//Add this to undo list
			addUndoCommand(this);
			clearRedoList();
			
			logger.info("Add Item Command Completed!");
			
		} catch (ExInsufficientArguments e) {
			logger.error("ExInsufficientArguments");
			System.out.println(e.getMessage());
		} catch (ExInvalidItemDimension e) {
			logger.error("ExInvalidItemDimension");
			System.out.println(e.getMessage());
		} catch (ExInvalidDate e) {
			logger.error("ExInvalidDate");
			System.out.println(e.getMessage());
		} catch (ExNoSlotInWarehouse e) {
			logger.error("ExNoSlotInWarehouse");
			System.out.println(e.getMessage());
		} catch (ExWrongDateFormat e) {
			logger.error("ExWrongDateFormat");
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			logger.error("NumberFormatException");
			System.out.println("Wrong Input Format!");
		}
	}

	//Undo Function
	@Override
	public void undoMe() {
		slot.removeItem(item);
		System.out.println("Item #"+item.getItemID()+" with size("+item.getDimensions()+") is removed from Slot ID #"+ 
				slot.getSlotID()+ " ; Delivery Date: " + item.getDepartureDate());
		addRedoCommand(this);
		logger.info("Add Item Command Undo!");
	}

	//Redo Function
	@Override
	public void redoMe() {
		if (item.getDepartureDate().compareTo(SystemDate.getInstance()) <= 0) {
			System.out.println("Cannot redo. The previous item date is before or equals to system date.");
		} else {
			slot.addItem(item);
			if (slot.getFreeVolume() == 0)
		       	System.out.println("Slot #"+ slot.getSlotID() +" is Full!");
			System.out.println("Item #"+item.getItemID()+" with size("+item.getDimensions()+") is added in Slot ID #"+ 
					item.getCurrentSlot().getSlotID()+ " ; Delivery Date: " + item.getDepartureDate());
			addUndoCommand(this);
		}
		logger.info("Add Item Command Redo!");
	}
}
