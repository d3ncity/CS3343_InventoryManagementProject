package Code;

public class CmdOptimize implements Command {
<<<<<<< HEAD
	
	@Override
	public void execute(String[] cmdParts) {
			Warehouse.getInstance().optimize();
=======

	Warehouse wh = Warehouse.getInstance();
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (wh.warehouseEmpty())
				throw new ExWarehouseEmpty();
			
			wh.optimize();
			
		} catch (ExWarehouseEmpty e) {
			System.out.println(e.getMessage());
		}
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
	}
	
}
