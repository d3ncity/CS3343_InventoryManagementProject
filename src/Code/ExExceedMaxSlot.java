package Code;

public class ExExceedMaxSlot extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExExceedMaxSlot() {
		super("Max slot size = 100 cannot be exceeded!");
	}
	
	public ExExceedMaxSlot(String msg) {
		super(msg);
	}
}
