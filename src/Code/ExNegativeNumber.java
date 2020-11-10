package Code;

public class ExNegativeNumber extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExNegativeNumber() {
		super("Negative values are not allowed!");
	}
	
	public ExNegativeNumber(String msg) {
		super(msg);
	}
}
