package code;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class SystemInputCmd extends InputCommand{

	static Logger logger = Logger.getLogger(Main.class);
	Warehouse wh = Warehouse.getInstance();
	
	public void execute(Scanner in) throws NoSuchElementException {
		
		logger.info("System Input Command Executed");
		
			while (!SystemDate.getSystemDateSet()) {
				System.out.print("Please set the system date (e.g dd-mm-yyyy):\n> ");
				String cmdLine = in.nextLine();
				String[] cmdParts = { "startNewDay", cmdLine };
				SystemDate.createTheInstance(cmdParts[1]);
			} 
	
			int cmdNumber = 0;
			
			if (Warehouse.getInstance().getSlotList().isEmpty()) {
				System.out.print("Please set up the first slot\n");
				this.addSlot(in);
			}

			do {
				
				try {
					System.out.print("---------------------------------------\n");
					System.out.println("System Date: " + this.showSystemDateStatus());
					System.out.println("Automatic Optimization: " + this.showAutomationStatus());
					System.out.print("Please choose the command:\n"
							+ "(1) AddSlot\n"
							+ "(2) AddItem\n"
							+ "(3) Manually Optimize\n"
							+ "(4) Visualize\n"
							+ "(5) List Slot By ID\n"
							+ "(6) List Item By ID\n"
							+ "(7) List Warehouse Details\n"
							+ "(8) Undo\n"
							+ "(9) Redo\n"
							+ "(10) System Reset\n"
							+ "(11) (For Demo) Set Date\n"
							+ "(12) (For Demo) Change Auto Optimization Status\n"
							+ "(13) EXIT\n");
					
					System.out.print("Type command (1 - 13): > ");
					// Trim leading spaces
					cmdNumber = in.nextInt();
					String[] cmd = {"cmd"};
					System.out.print("---------------------------------------\n");
					
					// Blank lines exist in data file as separators. Skip them.
					switch(cmdNumber) {
					case 1:
						this.addSlot(in);
						break;
					case 2:
						this.addItem(in);
						break;
					case 3:
						cmd[0] = "optimize";
						this.noArgumentFunctions(cmd);
						break;
					case 4:
						cmd[0] = "visualize";
						this.noArgumentFunctions(cmd);
						break;
					case 5:
						cmd[0] = "listSlotByID";
						this.noArgumentFunctions(cmd);
						break;
					case 6:
						cmd[0] = "listItemByID";
						this.noArgumentFunctions(cmd);
						break;
					case 7:
						cmd[0] = "listWarehouse";
						this.noArgumentFunctions(cmd);
						break;
					case 8:
						cmd[0] = "undo";
						this.noArgumentFunctions(cmd);
						break;
					case 9:
						cmd[0] = "redo";
						this.noArgumentFunctions(cmd);
						break;
					case 10:
						this.systemReset(in);
						break;
					case 11:
						this.setDate(in);
						break;
					case 12:
						cmd[0] = "changeStatus";
						this.noArgumentFunctions(cmd);
						break;
					case 13:
						break;
					default:
						System.out.println("Invalid Command Number! Please retype!");
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid Command Number! Please retype!");
				}
				
			} while(cmdNumber != 13);
			
			System.out.println("System Input TERMINATED!");
	}
	


	private void addSlot(Scanner in) {
		System.out.print("Size of the slot:\n> ");
		String size = in.next();
		String[] cmdParts = {"addSlot", size};
		super.acceptCmd(cmdParts);

	}
	
	private void addItem(Scanner in) {
		System.out.print("Size of the item:\n> ");
		String size = in.next();
		System.out.print("Date of delivery(e.g dd-mm-yyyy):\n> ");
		String date = in.next();
		String[] cmdParts = {"addItem", size, date};
		super.acceptCmd(cmdParts);
	}
	
	private void setDate(Scanner in) {
		System.out.print("(FOR DEMO) Please input the new date (e.g dd-mm-yyyy):\n> ");
		String date = in.next();
		String[] cmdParts = {"startNewDay", date};
		super.acceptCmd(cmdParts);
	}
	
	private void noArgumentFunctions(String[] cmdParts) {
		super.acceptCmd(cmdParts);
	}
	
	private void systemReset(Scanner in) {
		System.out.println("You have to confirm twice on the reset procedure!");
		for (int i = 0; i < 2; i++) {
			System.out.print("("+ (i+1) + ") Please confirm on system reset (Y/N) :\n> ");
			String confirm = in.next();
			if (confirm.toUpperCase().equals("N"))
				return;
		}
		wh.warehouseReset();
		System.out.println("Warehouse has been reset!");
	}
	
	private String showSystemDateStatus() {
		if (SystemDate.getSystemDateSet()) {
			return SystemDate.getInstance().toString();
		} else {
			return "Nil";
		}
	}
	
	private String showAutomationStatus() {
		if (wh.getAutomationStatus()) {
			return "ON";
		} else {
			return "OFF";
		}
		
	}
}
