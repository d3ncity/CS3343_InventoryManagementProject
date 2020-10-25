package Code;

import java.util.ArrayList;

public interface Functions {

	//check availability -returning true/false - Nursultan if we can put items in any slot
	boolean checkAvailability(Item item);
	
	
	
	//Putting in the slots(boolean: check availability) - return Slot# +Update - Harvey
	
	 void moveToSlot(Item item);
	 
	 
	 // Denny - Optimize the storage function(Arrays of slots)  - void 
	 
	 void optimize(ArrayList<Item> items); 
	 
	 
}
