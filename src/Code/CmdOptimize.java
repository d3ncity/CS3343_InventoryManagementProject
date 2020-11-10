package Code;

public class CmdOptimize implements Command {
	
	@Override
	public void execute(String[] cmdParts) {
			Warehouse.getInstance().optimize();
			System.out.println("Manually Optimized!");
			Warehouse.getInstance().printAllSlotsDetails();
	}
}
