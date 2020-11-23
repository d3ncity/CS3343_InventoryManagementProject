package Code;

<<<<<<< HEAD
public class CmdSetDate extends RecordedCommand{

	private String prvDate;
=======
public class CmdSetDate implements Command{

//	private String prvDate;
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
	private String curDate;
	
	@Override
	public void execute(String[] cmdParts) {
<<<<<<< HEAD
		prvDate = SystemDate.getInstance().toString();
		try {
			if(cmdParts.length < 2) {
				throw new ExInsufficientArguments();
			}
			curDate = cmdParts[1];
			if(new Day(curDate).valid() == false) {
				throw new ExInvalidDate(
						"Invalid Date!");
			}
=======
//		prvDate = SystemDate.getInstance().toString();
		curDate = cmdParts[1];
		try {
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
			if (new Day(curDate).compareTo(SystemDate.getInstance()) == -1 || 
					new Day(curDate).compareTo(SystemDate.getInstance()) == 0)
				throw new ExInvalidDate();
			SystemDate.getInstance().set(curDate);
<<<<<<< HEAD
			addUndoCommand(this);
			clearRedoList();
=======
//			addUndoCommand(this);
//			clearRedoList();
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
			
			System.out.println("System Date Setting Done. Current Date: " + curDate);
			
		} catch (ExInvalidDate e) {
			System.out.println(e.getMessage());
<<<<<<< HEAD
		} catch (ExInsufficientArguments e) {
=======
		} catch (ExWrongDateFormat e) {
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7
			System.out.println(e.getMessage());
		}
	}

<<<<<<< HEAD
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
=======
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
>>>>>>> aa6393ef26ac5c37fdfd69c026083b84a07f74e7

}
