package code;

public class CmdSetDate implements Command{
	private String curDate;
	
	@Override
	public void execute(String[] cmdParts) {
		
		try {
			if (cmdParts.length < 2)
				throw new ExInsufficientArguments();
			else
				curDate = cmdParts[1];
			if (new Day(curDate).compareTo(SystemDate.getInstance()) == -1 || 
					new Day(curDate).compareTo(SystemDate.getInstance()) == 0)
				throw new ExInvalidDate();
			SystemDate.getInstance().set(curDate);
			System.out.println("System Date Setting Done. Current Date: " + curDate);
			
		} catch (ExInvalidDate e) {
			System.out.println(e.getMessage());
		} catch (ExWrongDateFormat e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		}
	}
}
