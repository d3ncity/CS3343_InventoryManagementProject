package code;

public class CmdSetDate implements Command{

//	private String prvDate;
	private String curDate;
	
	@Override
	public void execute(String[] cmdParts) {
//		prvDate = SystemDate.getInstance().toString();
		curDate = cmdParts[1];
		try {
			if (new Day(curDate).compareTo(SystemDate.getInstance()) == -1 || 
					new Day(curDate).compareTo(SystemDate.getInstance()) == 0)
				throw new ExInvalidDate();
			SystemDate.getInstance().set(curDate);
//			addUndoCommand(this);
//			clearRedoList();
			
			System.out.println("System Date Setting Done. Current Date: " + curDate);
			
		} catch (ExInvalidDate e) {
			System.out.println(e.getMessage());
		} catch (ExWrongDateFormat e) {
			System.out.println(e.getMessage());
		}
	}

//	@Override
//	public void undoMe() {
//		SystemDate.getInstance().set(prvDate);
//		addRedoCommand(this);		
//	}
//
//	@Override
//	public void redoMe() {
//		SystemDate.getInstance().set(curDate);
//		addUndoCommand(this);
//	}

}
