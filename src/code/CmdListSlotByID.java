package code;

public class CmdListSlotByID implements Command {
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 2) {
				throw new ExInsufficientArguments();
			}
			int ID = Integer.parseInt(cmdParts[1]);
			Slot slot = Warehouse.getInstance().findSlotByID(ID);
			System.out.println("Slot #" + ID + " Details:");
			slot.printItemsInSlot();
		} catch (NumberFormatException e) {
			System.out.print(e.getMessage());
		} catch (ExInsufficientArguments e) {
			System.out.print(e.getMessage());
		}
	}

}
