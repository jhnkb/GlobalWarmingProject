import java.util.Hashtable;

public class Country {

	private String name;
	private Double temp;
	private Integer year;
	private Hashtable<Integer,Double> yeartemp = new Hashtable();
	private Hashtable<Double, Integer> tempyear = new Hashtable();
	
	
	public Country(String name3, Hashtable<Integer, Double> yeartemp2, Hashtable<Double, Integer> tempyear2 ) {
		this.name=name3;
		this.yeartemp = yeartemp2;
		this.tempyear = tempyear2;
	}
	
}
