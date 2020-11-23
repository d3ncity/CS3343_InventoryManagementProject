package Code;

import java.util.ArrayList;

<<<<<<< HEAD
public class CmdDeliverExpiredItem extends RecordedCommand{
=======
public class CmdDeliverExpiredItem implements Command{
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
	
	//Instance Field
	Warehouse wh = Warehouse.getInstance();
	private ArrayList<Item> deliveredList = new ArrayList<>();
	private Day day;
<<<<<<< HEAD
=======
	private static int deliveredNum;
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
	
	@Override
	public void execute(String[] cmdParts) {
		
<<<<<<< HEAD
		day = new Day(cmdParts[1]);
		ArrayList<Item> itemList = new ArrayList<>();
		
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
		wh.optimize();
		addUndoCommand(this);
		clearRedoList();
	}

	@Override
	public void undoMe() {
		for (Item i : deliveredList) {
			wh.moveToSlot(i);
		}
		addRedoCommand(this);
		
	}

	@Override
	public void redoMe() {
		ArrayList<Item> itemList = new ArrayList<>();
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
		wh.optimize();
		addUndoCommand(this);		
	}
=======
		try {
			day = new Day(cmdParts[1]);
			ArrayList<Item> itemList = new ArrayList<>();
			
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
				CmdDeliverExpiredItem.deliveredNum = this.deliveredList.size();
				itemList.clear();
			}
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
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7

}
