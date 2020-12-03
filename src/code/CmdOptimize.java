package code;

public class CmdOptimize implements Command {

	Warehouse wh = Warehouse.getInstance();
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (wh.warehouseEmpty())
				throw new ExWarehouseEmpty();
			
			wh.optimize();
			System.out.println("Manually Optimized!");
			
		} catch (ExWarehouseEmpty e) {
			System.out.println(e.getMessage());
		}
	}
	
}
