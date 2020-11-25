package Code;

public class CmdOptimize implements Command {

	Warehouse wh = Warehouse.getInstance();
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (wh.warehouseEmpty())
				throw new ExWarehouseEmpty();
			
			//wh.optimize();
			
		} catch (ExWarehouseEmpty e) {
			System.out.println(e.getMessage());
		}
	}
	
}