package Code;

public class ExNoSlotInWarehouse extends Exception {
	private static final long serialVersionUID = 1L;
	public ExNoSlotInWarehouse() {super("Error. No slot has been created yet!");}
	//public ExNoSlotInWarehouse(String message) {super(message);}
}
