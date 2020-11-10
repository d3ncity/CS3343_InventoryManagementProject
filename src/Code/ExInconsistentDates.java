package Code;

public class ExInconsistentDates extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ExInconsistentDates() {
		super("Departure Date is before Arriving Date!");
	}
	
	public ExInconsistentDates(String msg) {
		super(msg);
	}
}
