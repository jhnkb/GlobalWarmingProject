import java.util.Hashtable;

public class Country {

	private String name; //a country has-a name
	private Hashtable<Integer,Double> yeartemp = new Hashtable(); //a country has-a year temp library/hashtable
	private Hashtable<Double, Integer> tempyear = new Hashtable(); //a counrty has-a temp year library/hashtable
	
	//initializes Country object
	public Country(String name, Hashtable<Integer, Double> yeartemp2, Hashtable<Double, Integer> tempyear2 ) {
		this.name = name;
		this.yeartemp = yeartemp2;
		this.tempyear = tempyear2;
	}

	//returns the year/temp hashtable of a country
	public Hashtable<Integer, Double> getyeartemphash() {
		return this.yeartemp;
	}
	
	//returns the temp/year hashtable of a country
	public Hashtable<Double, Integer> gettempyearhash() {
		return this.tempyear;
	}
	
	//returns name of a country
	public String getName() {
		return this.name;
	}
}
