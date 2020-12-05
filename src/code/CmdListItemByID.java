package code;

public class CmdListItemByID implements Command {
	
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
				Item item = Warehouse.getInstance().findItemByID(ID);
				if (item == null) {
					System.out.println("Item not found! Please check the ID again!");
				} else
					System.out.println(item.getItemDetails());				
			}			
		} catch (NumberFormatException e) {
			System.out.println("The ID input should be integer!");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExWarehouseEmpty e) {
			System.out.println(e.getMessage());
		} catch (ExWrongInputFormat e) {
			System.out.println(e.getMessage());
		}
	}

}
