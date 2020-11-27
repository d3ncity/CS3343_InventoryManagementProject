package code;

public class CmdListSlotByID implements Command {
	
	@Override
	public void execute(String[] cmdParts) {
		int ID = Integer.parseInt(cmdParts[1]);
		Slot slot = Warehouse.getInstance().findSlotByID(ID);
		System.out.println("Slot #" + ID + " Details:");
		slot.printItemsInSlot();
	}

}
