package Code;

public class ExDateHasPassed extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ExDateHasPassed() {
		super("Date has already passed!");
	}
	
	public ExDateHasPassed(String msg) {
		super(msg);
	}
}
