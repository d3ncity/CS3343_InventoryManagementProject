package Code;

public class ExUnknownCommand extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExUnknownCommand() {
		super("Unknown Command");
	}
	
	public ExUnknownCommand(String msg) {
		super(msg);
	}
}
