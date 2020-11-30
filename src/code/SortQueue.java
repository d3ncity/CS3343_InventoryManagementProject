package code;

import java.util.Comparator;

//Just for sorting the queue
public class SortQueue implements Comparator<Item> {

	//Comparing Departure Date / Shipping Date
	 public int compare(Item a, Item b){
		 if (a.getDepartureDate().compareTo(b.getDepartureDate())==1) return -1;
		 else if (b.getDepartureDate().compareTo(a.getDepartureDate())==1) return 1;
		 return 0;
	 } 
}
