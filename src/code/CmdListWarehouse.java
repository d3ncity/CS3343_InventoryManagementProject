package code;

public class CmdListWarehouse implements Command {

	@Override
	public void execute(String[] cmdParts) {
		Warehouse.getInstance().printAllSlotsDetails();
	}
}
