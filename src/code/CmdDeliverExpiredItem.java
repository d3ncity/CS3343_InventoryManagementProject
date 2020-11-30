package code;

import java.util.ArrayList;

public class CmdDeliverExpiredItem implements Command{
	
	//Instance Field
	Warehouse wh = Warehouse.getInstance();
	private ArrayList<Item> deliveredList = new ArrayList<>();
	private Day day;
	private static int deliveredNum;
	
	@Override
	public void execute(String[] cmdParts) {
		
		try {
			day = new Day(cmdParts[1]);
			ArrayList<Item> itemList = new ArrayList<>();
			ArrayList<Item> itemList2 = new ArrayList<>();
			
			for (Slot s: wh.getSlotList()) {
				for (Item i : s.getItemsList()) {
					if (i.getDepartureDate().compareTo(day) <= 0) {
						itemList.add(i);
					}
				}
				for (Item i: itemList) {
					s.removeItem(i);
					deliveredList.add(i);
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
//				deliveredList.add(i);
			}
			itemList2.clear();
			CmdDeliverExpiredItem.deliveredNum = this.deliveredList.size();
			
			wh.optimize();
		} catch (ExWrongDateFormat e) {
			System.out.println(e.getMessage());
		}
//		addUndoCommand(this);
//		clearRedoList();
	}
	
	public static int getDeliveredNum() {
		return deliveredNum;
	}
	

//	@Override
//	public void undoMe() {
//		for (Item i : deliveredList) {
//			wh.moveToSlot(i);
//		}
//		addRedoCommand(this);
//		
//	}
//
//	@Override
//	public void redoMe() {
//		ArrayList<Item> itemList = new ArrayList<>();
//		for (Slot s: wh.getSlotList()) {
//			for (Item i : s.getItemsList()) {
//				if (i.getDepartureDate().compareTo(day) <= 0) {
//					itemList.add(i);
//				}
//			}
//			for (Item i: itemList) {
//				s.removeItem(i);
//				deliveredList.add(i);
//			}
//			itemList.clear();
//		}
//		wh.optimize();
//		addUndoCommand(this);		
//	}

}
