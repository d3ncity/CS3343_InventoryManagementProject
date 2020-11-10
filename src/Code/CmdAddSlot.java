package Code;

public class CmdAddSlot extends RecordedCommand {
	
	//Instance Field
	private int volume;
	private Slot slot;
	
	@Override
	public void execute(String[] cmdParts) {
		volume = Integer.parseInt(cmdParts[1]);
		try {
			if (cmdParts.length < 2) {
				throw new ExInsufficientArguments();
			}
			if (volume < 1 || volume > 100) {
				throw new ExOutOfRange();
			}
			
			slot = new Slot (volume);
			addUndoCommand(this);
			clearRedoList();
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExOutOfRange e) {
			System.out.println(e.getMessage());
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
