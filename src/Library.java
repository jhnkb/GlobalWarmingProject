

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Library {


	public	ArrayList<String> indexlist = new ArrayList<String>();
	public ArrayList<Country> library1 = new ArrayList<Country>();
	public Hashtable<Integer,Double> yeartemp = new Hashtable<>();
	public Hashtable<Double, Integer> tempyear = new Hashtable<>();
	public Country country;
	
	public Library() {
	indexlist = createCountryList("C:\\Users\\jboli\\OneDrive\\Desktop\\testcsv.csv");
	library1 = createLibrary("C:\\Users\\jboli\\OneDrive\\Desktop\\testcsv.csv"); 
	}

	public ArrayList<String> createCountryList(String filename) {
		
		File file = new File(filename);
		String line;
		List <List<String>> data = new ArrayList<>(); 
		ArrayList<String> list = new ArrayList<String>();
			
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				data.add(Arrays.asList(tokens));
				list.add(tokens[0]);
				 
			}
		} catch (Exception e) 
			{
			e.printStackTrace();
			}
		this.indexlist = list;
		return this.indexlist;
		}
	
	public ArrayList<Country> createLibrary(String filename) {
		
		File file = new File(filename);
		String line;
		List <List<String>> data = new ArrayList<>();
		ArrayList<Country> countrylist = new ArrayList<Country>(); 
		
			
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				data.add(Arrays.asList(tokens));
				for (int i = 1971, j = 1; i < 2023 && j <63; i++, j++) {
					this.yeartemp.put(Integer.valueOf(i), Double.valueOf(tokens[j]));
					}
				for (int i = 1971, j = 1; i < 2023 && j < 63; i++, j++) {
					this.tempyear.put(Double.valueOf(tokens[j]), Integer.valueOf(i));
					}
				this.country = new Country(tokens[0], yeartemp, tempyear);
				countrylist.add(country);
			}
			
			this.library1 = countrylist;
			
		} catch (Exception e) 
			{
			e.printStackTrace();
			}
		
		return this.library1;
		}
	
	public Double getTemp(String country1, Integer year) {
		
		int index = indexlist.indexOf(country1);
		Country country = library1.get(index);
		Double temp = yeartemp.get(year);
		return temp;
	}
	
	 public static void main(String[] args) {
	 }
	

}
