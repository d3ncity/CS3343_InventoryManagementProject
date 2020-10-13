package Code;

public class SystemDate extends Day{

private static SystemDate instance;
private SystemDate(String sDay) {super(sDay);}
public static SystemDate getInstance(){ return instance;  }

public static void createTheInstance(String sDay) 
{
	if (instance==null) //make sure only one instance can be created (Singleton)
		instance = new SystemDate(sDay);
	else
		instance.set(sDay);
}
}
