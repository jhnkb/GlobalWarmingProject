import java.util.Hashtable;

public class Island extends Country{
	private String name;
	private Hashtable<Integer,Double> yeartemp = new Hashtable();
	private Hashtable<Double, Integer> tempyear = new Hashtable();
	
	public Island(String name, Hashtable<Integer, Double> yeartemp2, Hashtable<Double, Integer> tempyear2) {
		super(name, yeartemp2, tempyear2);
		this.name = name;
		this.yeartemp = yeartemp2;
		this.tempyear = tempyear2;
	}
}
