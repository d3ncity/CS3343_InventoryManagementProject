package Code;

public class CmdListWarehouse implements Command {

	@Override
	public void execute(String[] cmdParts) {
		Warehouse.getInstance().printAllSlotsStatus();
	}
}
