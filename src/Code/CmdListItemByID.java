package Code;

public class CmdListItemByID implements Command {
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 2) {
				throw new ExInsufficientArguments();
			}
			if(Warehouse.getInstance().warehouseEmpty()) {
				throw new ExWarehouseEmpty();
			}
			
			int ID = Integer.parseInt(cmdParts[1]);
			
			if(ID <= 0) {
				throw new ExOutOfRange();
			}
			
			Item item = Warehouse.getInstance().findItemByID(ID);
			if(item == null) {
				System.out.println("There is no such item!");
			}else
				System.out.println(item.getItemDetails());
		}catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExWarehouseEmpty e) {
			System.out.println("The warehouse is empty!");
		} catch (ExOutOfRange e) {
			System.out.println("Wrong ID number! It should be > 0");
		}
	}

}
