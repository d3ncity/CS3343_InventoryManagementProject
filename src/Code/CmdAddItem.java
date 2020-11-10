package Code;

public class CmdAddItem extends RecordedCommand {
	
	private int dimension;
	private Item item;
	private Slot slot;
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 3) {
				throw new ExInsufficientArguments();
			}
			//Accept the Input
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
						"Invalid Dimension Input!\nThe size should be >0 and <="+
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
