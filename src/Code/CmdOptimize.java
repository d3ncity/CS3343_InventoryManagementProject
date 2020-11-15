package Code;

public class CmdOptimize implements Command {
	
	@Override
	public void execute(String[] cmdParts) {
			Warehouse.getInstance().optimize();
	}
	
}
