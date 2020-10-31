package Code;

public interface Functions {

	//check availability -returning true/false - Nursultan if we can put items in any slot
	boolean checkAvailability(Item item);
	
	//removes item that should exit slot up to this date 
	void setSlotsUpToDate();
	
	
	//Putting in the slots(boolean: check availability) - return Slot# +Update - Harvey
	
	 void moveToSlot(Item item);
	 
	 
	 // Denny - Optimize the storage function(Arrays of slots)  - void 
	 
	 void optimize();
	 
	 
}
