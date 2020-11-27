package code;

public class ExOutOfRange extends Exception {
	private static final long serialVersionUID = 1L;
	public ExOutOfRange() {super("Volume Out Of Range. The size can only be (1-100).");}
	public ExOutOfRange(String message) {super(message);}
}
