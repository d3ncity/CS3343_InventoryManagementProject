package Code;
import java.util.ArrayList; 

public class Optimize {

	//Instance Field
	private Slot optimizingSlot;
	private ArrayList<Item> optimizedItem;
	private boolean[][] truthTable;
	private boolean found;
	
	//Constructor
	public Optimize() {
		this.optimizingSlot = null;
		this.optimizedItem = new ArrayList<>();
	}
	
	public boolean getFound() {
		return this.found;
	}
	
	public void findOnePerfectFit(ArrayList<Item> list,Slot slot) {
		Item item = null;
		for (Item i: list) {
			if (i.getDimensions() == slot.getFreeVolume()) {
				slot.addItem(i);
				item = i;
				found = true;
			}
		}
		list.remove(item);
	}

	public void findOnePerfectSubsets(ArrayList<Item> list, int length, int sum, Slot slot) {
		this.optimizingSlot = slot;
		if (checkPerfectSubset(list, length, sum)) {
	        ArrayList<Item> result = new ArrayList<>(); 
	        findSubsetRecur(list, length-1, sum, result);
		}
		return;
	}
	
	private boolean checkPerfectSubset(ArrayList<Item> list, int length, int sum) {
		if (length == 0 || sum <= 0) {
			return false;
		}
			
        //Constructing truth table
		truthTable = new boolean[length][sum + 1];
		
		//Sum 0 equals true for empty set
        for (int i=0; i<length; ++i) { 
            truthTable[i][0] = true;   
        } 
        
        if (list.get(0).getDimensions() <= sum) {
            truthTable[0][list.get(0).getDimensions()] = true; 
        }

        for (int i = 1; i < length; ++i) {
            for (int j = 0; j < sum + 1; ++j) {
                truthTable[i][j] = (list.get(i).getDimensions() <= j) 
                    	? (truthTable[i-1][j] || truthTable[i-1][j-list.get(i).getDimensions()]) 
                    		: truthTable[i - 1][j]; 
            }
        }
        
        //Check if desire sum have any subset in the ArrayList
        if (truthTable[length-1][sum] == false) 
        {
            return false; 
        }
		return true; 
	}
	
	//A lock to stop recursion
	private boolean unlock = true;
	private void findSubsetRecur(ArrayList<Item> list, int length, int sum, ArrayList<Item> result) {
        
		//Reached to the end of the path
		if (length == 0 && sum != 0 && truthTable[0][sum]) 
        { 
        	if (unlock) {
                result.add(list.get(length));
                rearrangeSubset(result, list); 
                result.clear(); 
                unlock = false;
        	}
            return; 
        }
       
        // End when sum becomes 0
        if (length == 0 && sum == 0) 
        { 
        	if (unlock) {
                rearrangeSubset(result, list); 
                result.clear();
                unlock = false;
        	}
        	return; 
        } 
       
        // If given sum can be achieved after ignoring current element. 
        if (truthTable[length-1][sum]) 
        { 
            // Create a new vector to store path 
            ArrayList<Item> temp = new ArrayList<>(); 
            temp.addAll(result); 
            findSubsetRecur(list, length-1, sum, temp); 
        } 
       
        // If given sum can be achieved after considering current element. 
        if (sum >= list.get(length).getDimensions() && truthTable[length-1][sum-list.get(length).getDimensions()]) 
        { 
            result.add(list.get(length)); 
            findSubsetRecur(list, length-1, sum-list.get(length).getDimensions(), result); 
        } 
	}
	
    private void rearrangeSubset(ArrayList<Item> result, ArrayList<Item> list){
    	found = true;
//    	System.out.println("Optimized!");
    	for (int i = 0; i < result.size(); i++) {
    		this.optimizingSlot.addItem(result.get(i));
    		this.optimizedItem.add(result.get(i));
    	}
    } 
    
    public ArrayList<Item> getOptimizedItem() {
    	return this.optimizedItem;
    }
    
    public void reset(){
    	this.optimizedItem.clear();
    	this.optimizingSlot = null;
    	this.unlock = true;
    	this.found = false;
    }
    
}
