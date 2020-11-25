package Code;

public class CmdAddSlot extends RecordedCommand {
	
	//Instance Field
	private int volume;
	private Slot slot;
	
	@Override
	public void execute(String[] cmdParts) {
		
		try {
			
			//If the command arguments are less than 3
			if (cmdParts.length < 2) {
				throw new ExInsufficientArguments();
			}
			
			volume = Integer.parseInt(cmdParts[1]);
			
			//Check if the input is valid
			if (volume < 1 || volume > 100 ) {
				throw new ExOutOfRange();
			}
			
			slot = new Slot (volume);
			addUndoCommand(this);
			clearRedoList();
			
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExOutOfRange e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Wrong Input Format!");
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