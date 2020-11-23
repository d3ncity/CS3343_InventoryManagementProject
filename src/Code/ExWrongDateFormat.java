package Code;

public class ExWrongDateFormat extends Exception {
	private static final long serialVersionUID = 1L;
	public ExWrongDateFormat() {super("The date should have format of \\\"dd-mmName-yyyy\\\" e.g 12-Oct-2020.");}
	public ExWrongDateFormat(String message) {super(message);}
}
