package Code;

public class ExSystemDateIsNotSet extends Exception {
	private static final long serialVersionUID = 1L;
	public ExSystemDateIsNotSet() {super("System Date has not yet been set!");}
	public ExSystemDateIsNotSet(String message) {super(message);}
}
