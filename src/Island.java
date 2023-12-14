import java.util.Hashtable;

//an island is a country object
public class Island extends Country{
	private String name; //an island has-a name
	private Hashtable<Integer,Double> yeartemp = new Hashtable(); //an island has-a year temp library/hashtable
	private Hashtable<Double, Integer> tempyear = new Hashtable(); //an island has-a temp year library/hashtable
	
	//initializes Island country
	public Island(String name, Hashtable<Integer, Double> yeartemp2, Hashtable<Double, Integer> tempyear2) {
		super(name, yeartemp2, tempyear2);
		this.name = name;
		this.yeartemp = yeartemp2;
		this.tempyear = tempyear2;
	}
}
