package code;

public class SystemDate extends Day {

	private static SystemDate instance;
	private static boolean systemDateSet = false;

	private SystemDate(String sDay) throws ExWrongDateFormat {
		super(sDay);
	}

	public static SystemDate getInstance() {
		return instance;
	}
	
	public static boolean getSystemDateSet() {return systemDateSet;}

	public static void createTheInstance(String sDay) {
		try {
			if (instance == null) { // make sure only one instance can be created (Singleton)
				instance = new SystemDate(sDay);
				systemDateSet = true;
			}
			else
				instance.set(sDay);
		} catch (NumberFormatException e) {
			System.out.println((new ExWrongDateFormat()).getMessage());
			systemDateSet = false;
		} catch (ExWrongDateFormat e) {
			System.out.println(e.getMessage());
			systemDateSet = false;
		}
	}
}
