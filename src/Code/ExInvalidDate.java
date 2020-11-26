package Code;

public class ExInvalidDate extends Exception {
	private static final long serialVersionUID = 1L;
	public ExInvalidDate() {super("Error. The date is before or equals to system date.");}
	public ExInvalidDate(String message) {super(message);}
}
