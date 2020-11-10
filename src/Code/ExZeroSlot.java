package Code;

public class ExZeroSlot extends Exception {
	private static final long serialVersionUID = 1L;
	public ExZeroSlot() {super("Error. No slot has been created yet!");}
	public ExZeroSlot(String message) {super(message);}
}
