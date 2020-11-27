package code;

public class ExInvalidCommand extends Exception {
	private static final long serialVersionUID = 1L;
	public ExInvalidCommand() {super("Invalid Command!");}
	public ExInvalidCommand(String message) {super(message);}
}
