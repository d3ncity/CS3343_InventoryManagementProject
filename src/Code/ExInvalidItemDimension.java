package Code;

public class ExInvalidItemDimension extends Exception {
	private static final long serialVersionUID = 1L;
	public ExInvalidItemDimension() {super("Invalid Dimension Input!");}
	public ExInvalidItemDimension(String message) {super(message);}
}
