package Code;

public class CmdAddSlot extends RecordedCommand {
	
	//Instance Field
	private int volume;
	private Slot slot;
	
	@Override
	public void execute(String[] cmdParts) {
<<<<<<< HEAD
		volume = Integer.parseInt(cmdParts[1]);
=======
		
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
		try {
			
			//If the command arguments are less than 3
			if (cmdParts.length < 2) {
				throw new ExInsufficientArguments();
			}
<<<<<<< HEAD
			//Check if the input is valid
			if (volume < 1 || volume > 100) {
=======
			
			volume = Integer.parseInt(cmdParts[1]);
			
			//Check if the input is valid
			if (volume < 1 || volume > 100 ) {
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
				throw new ExOutOfRange();
			}
			
			slot = new Slot (volume);
			addUndoCommand(this);
			clearRedoList();
			
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExOutOfRange e) {
			System.out.println(e.getMessage());
<<<<<<< HEAD
=======
		} catch (NumberFormatException e) {
			System.out.println("Wrong Input Format!");
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
		}
	}

	@Override
	public void undoMe() {
		Warehouse wh = Warehouse.getInstance();
		wh.removeSlots(slot);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		Warehouse wh = Warehouse.getInstance();
		wh.addSlots(slot);
		addUndoCommand(this);
	}

}
