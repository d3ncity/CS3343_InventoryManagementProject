package code;

public class CmdListItemByID implements Command {
	
	@Override
	public void execute(String[] cmdParts) {
		int ID = Integer.parseInt(cmdParts[1]);
		Item item = Warehouse.getInstance().findItemByID(ID);
		System.out.println(item.getItemDetails());
	}

}
