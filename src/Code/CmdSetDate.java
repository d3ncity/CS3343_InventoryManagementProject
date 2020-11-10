package Code;

public class CmdSetDate extends RecordedCommand{

	private String prvDate;
	private String curDate;
	
	@Override
	public void execute(String[] cmdParts) {
		prvDate = SystemDate.getInstance().toString();
		curDate = cmdParts[1];
		try {
			if (new Day(curDate).compareTo(SystemDate.getInstance()) == -1)
				throw new ExInvalidDate();
			SystemDate.getInstance().set(curDate);
			addUndoCommand(this);
			clearRedoList();
			
			System.out.println("System Date Setting Done.");
			
		} catch (ExInvalidDate e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void undoMe() {
		SystemDate.getInstance().set(prvDate);
		addRedoCommand(this);		
	}

	@Override
	public void redoMe() {
		SystemDate.getInstance().set(curDate);
		addUndoCommand(this);
	}

}
