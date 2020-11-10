package Code;

public class ExItemIsLargerThanSlot extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExItemIsLargerThanSlot() {
		super("Item size is larger than slot!");
	}
	
	public ExItemIsLargerThanSlot(String msg) {
		super(msg);
	}
}
