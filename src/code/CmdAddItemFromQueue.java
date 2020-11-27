package code;

public class CmdAddItemFromQueue implements Command{
	
	private int itr = CmdDeliverExpiredItem.getDeliveredNum();
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			
			for (int i = 0; i < itr; i++)
				Warehouse.getInstance().moveQueueItemToSlot();
			
		} catch (ExEmptyQueue e) {
			System.out.println(e.getMessage());
		}
	}
}
