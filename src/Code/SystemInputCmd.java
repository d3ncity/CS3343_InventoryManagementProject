package Code;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class SystemInputCmd extends InputCommand{

	static Logger logger = Logger.getLogger(Main.class);
	
	public void execute(Scanner in) {
		logger.info("System Input Command Executed");
		System.out.print("Please set the system date (e.g. 13-Oct-2020):\n> ");
		String cmdLine = in.nextLine();
		String[] cmdParts = {"startNewDay", cmdLine};

		try {
			int cmdNumber = 0;
			SystemDate.createTheInstance(cmdParts[1]);
	
			System.out.print("Please set up the first slot\n");
			this.addSlot(in);
			
			
			do {
				System.out.print("-----------------------\n"
						+ "Please choose the command:\n"
						+ "(1) AddSlot\n"
						+ "(2) AddItem\n"
						+ "(3) Manually Optimize\n"
						+ "(4) Visualize\n"
						+ "(5) List Slot By ID\n"
						+ "(6) List Item By ID\n"
						+ "(7) List Warehouse Details\n"
						+ "(8) Undo\n"
						+ "(9) Redo\n"
						+ "(10) EXIT\n");
				
				System.out.print("Type command (1 - 10): > ");
				// Trim leading spaces
				cmdNumber = in.nextInt();
				String[] cmd = {"cmd"};
				System.out.print("-----------------------\n");
				
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
					break;
				default:
					System.out.println("Invalid Command Number! Please retype!");
					break;
				}
				
			} while(cmdNumber != 10);
		} catch (ExWrongDateFormat e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addSlot(Scanner in) {
		System.out.print("Size of the slot:\n> ");
		String size = in.next();
		String[] cmdParts = {"addSlot", size};
		super.acceptCmd(cmdParts);

	}
	
	public void addItem(Scanner in) {
		System.out.print("Size of the item:\n> ");
		String size = in.next();
		System.out.print("Date of delivery:\n> ");
		String date = in.next();
		String[] cmdParts = {"addItem", size, date};
		super.acceptCmd(cmdParts);
	}
	
	public void noArgumentFunctions(String[] cmdParts) {
		super.acceptCmd(cmdParts);
	}

}
