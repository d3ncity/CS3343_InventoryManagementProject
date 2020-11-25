package Code;

public class CmdAddItem extends RecordedCommand {
	
	//Instance Field
	private int dimension;
	private Item item;
	private Slot slot;
	
	@Override
	public void execute(String[] cmdParts) {
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
			if ((new Day(sDepartureDate).compareTo(sArrivalDate) == -1 || new Day(sDepartureDate).compareTo(sArrivalDate) == 0)) {
				throw new ExInvalidDate();
			}
			if (dimension < 1 || dimension > Warehouse.getInstance().getTheLargestSlotSize()) {
				throw new ExInvalidItemDimension(
						"Invalid Dimension Input! The size should be >0 and <="+
				Warehouse.getInstance().getTheLargestSlotSize()+" (The largest slot size).");
			}
			
			item = new Item(dimension,sArrivalDate,new Day(sDepartureDate));
			slot = item.getCurrentSlot();
			addUndoCommand(this);
			clearRedoList();
			
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExInvalidItemDimension e) {
			System.out.println(e.getMessage());
		} catch (ExInvalidDate e) {
			System.out.println(e.getMessage());
		} catch (ExNoSlotInWarehouse e) {
			System.out.println(e.getMessage());
		} catch (ExWrongDateFormat e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Wrong Input Format!");
		}
	}

	@Override
	public void undoMe() {
		slot.removeItem(item);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		slot.addItem(item);
		addUndoCommand(this);
	}

}
