package code;

public class CmdListSlotByID implements Command {
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 2) {
				throw new ExInsufficientArguments();
			}
			int ID = Integer.parseInt(cmdParts[1]);
			if (ID <= 0)
				throw new ExWrongInputFormat();
			
			if (Warehouse.getInstance().warehouseEmpty()) {
				throw new ExWarehouseEmpty();
			} else {
				Slot slot = Warehouse.getInstance().findSlotByID(ID);
				if (slot == null) {
					System.out.println("Slot not found! Please check the ID again!");
				} else {
					System.out.println("Slot #"+slot.getSlotID()+" Details:");
					slot.printItemsInSlot();
				}					
			}			
		} catch (NumberFormatException e) {
			System.out.println("The ID input should be integer!");
		} catch (ExInsufficientArguments e) {
			System.out.print(e.getMessage());
		} catch (ExWrongInputFormat e) {
			System.out.print(e.getMessage());
		} catch (ExWarehouseEmpty e) {
			System.out.print(e.getMessage());
		}
	}

}
