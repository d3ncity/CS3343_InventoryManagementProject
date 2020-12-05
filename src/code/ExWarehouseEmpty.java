package code;

public class ExWarehouseEmpty extends Exception {
	private static final long serialVersionUID = 1L;
	public ExWarehouseEmpty() {super("Warehouse is Empty!");}
	public ExWarehouseEmpty(String message) {super(message);}
}
