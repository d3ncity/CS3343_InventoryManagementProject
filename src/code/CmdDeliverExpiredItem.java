package code;

import java.util.ArrayList;

public class CmdDeliverExpiredItem implements Command{
	
	//Instance Field
	Warehouse wh = Warehouse.getInstance();
	private Day day;
	private static int freeOutVolume;
	
	@Override
	public void execute(String[] cmdParts) {
		
		try {
			freeOutVolume = 0;
			if (cmdParts.length < 2) {
				throw new ExInsufficientArguments();
			}
			day = new Day(cmdParts[1]);
			ArrayList<Item> itemList = new ArrayList<>();
			ArrayList<Item> itemList2 = new ArrayList<>();
			
			for (Slot s: wh.getSlotList()) {
				for (Item i : s.getItemsList()) {
					if (i.getDepartureDate().compareTo(day) <= 0) {
						itemList.add(i);
						freeOutVolume += i.getDimensions();
					}
				}
				for (Item i: itemList) {
					s.removeItem(i);
				}
				itemList.clear();
			}
			
			for (Item i: wh.getQueue()) {
				if (i.getDepartureDate().compareTo(day) <= 0) {
					itemList2.add(i);
				}
			}
			for (Item i: itemList2) {
				wh.getQueue().remove(i);
			}
			itemList2.clear();
			wh.optimize();
		} catch (ExWrongDateFormat e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static int getFreeOutVolume() {
		return freeOutVolume;
	}
}
