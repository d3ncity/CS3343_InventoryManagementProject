package Code;

public class CmdAddItemFromQueue implements Command{
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			Warehouse.getInstance().moveQueueItemToSlot();
		} catch (ExEmptyQueue e) {
			System.out.println(e.getMessage());
		}
	}
}
