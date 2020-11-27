package code;

public class CmdVisualize implements Command {

	@Override
	public void execute(String[] cmdParts) {
		Warehouse.getInstance().printAllSlotsArray();
	}
}
