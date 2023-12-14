

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

//Library class uses the calculations interface
public class Library implements Calculations{

	public	ArrayList<String> indexlist = new ArrayList<String>(); //a library object has-an indexlist comprised of name of countries
	public ArrayList<Country> library = new ArrayList<Country>(); //a library object has-a library comprised of countries and their hashtables
	
	//initializes Library object
	public Library() {
	indexlist = createCountryList("countries.csv", "islands.csv");
	library = createLibrary("countries.csv", "islands.csv"); 
	}

	//creates a country list using text files
	//returns an array list of strings (country names)
	public ArrayList<String> createCountryList(String countryfile, String islandfile) {
		
		//create file objects from the filenames
		File file = new File(countryfile);
		File file2 = new File(islandfile);
		String line;
		//create ArrayList and List 
		List <List<String>> data = new ArrayList<>(); 
		ArrayList<String> list = new ArrayList<String>();
			
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			//read file until it reaches the end of file
			//adds countries line by line from csv to the array list
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				data.add(Arrays.asList(tokens));
				list.add(tokens[0]);
				 
			}
		} catch (Exception e) 
			{
			e.printStackTrace();
			}
		
		try (BufferedReader br = new BufferedReader(new FileReader(file2)))
		{
			//read file until it reaches the end of file
			//adds countries line by line from csv to the same array list
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				data.add(Arrays.asList(tokens));
				list.add(tokens[0]);
				 
			}
		} catch (Exception e) 
			{
			e.printStackTrace();
			}
		//sorts the list alphabetically
		Collections.sort(list);
		
		//assigns the list to the index list
		//returns index list
		this.indexlist = list;
		return indexlist;
		}
	
	public ArrayList<Country> createLibrary(String countryfile, String islandfile) {
		
		//create file objects from the filenames
		File file = new File(countryfile);
		File file2 = new File(islandfile);
		String line;
		List <List<String>> data = new ArrayList<>();
		ArrayList<Country> countrylist = new ArrayList<Country>(); 
		
			
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			//read file until it reaches the end of file
			//adds countries line by line from csv to the same array list
			while ((line = br.readLine()) != null) {
				Hashtable<Integer,Double> year = new Hashtable<>();
				Hashtable<Double,Integer> temp = new Hashtable<>();
				String[] tokens = line.split(",");
				data.add(Arrays.asList(tokens));
				
				//adding elements of year temp from the csv
				//adding elements of temp year from the csv
				for (int i = 1961, j = 1; i < 2023 && j <63; i++, j++) {
					
					year.put(Integer.valueOf(i), Double.valueOf(tokens[j]));
					
					
					temp.put(Double.valueOf(tokens[j]), Integer.valueOf(i));
					}
				//creates country object 
				Country country = new Country(tokens[0], year, temp);
				//adds country object to the country list
				countrylist.add(country);
			}
		} catch (Exception e) 
			{
			e.printStackTrace();
			}
		
		try (BufferedReader br = new BufferedReader(new FileReader(file2)))
		{
			//read file until it reaches the end of file
			//adds countries line by line from csv to the same array list
			while ((line = br.readLine()) != null) {
				Hashtable<Integer,Double> year = new Hashtable<>();
				Hashtable<Double,Integer> temp = new Hashtable<>();
				String[] tokens = line.split(",");
				data.add(Arrays.asList(tokens));
				
				//adding elements of year temp from the csv
				//adding elements of temp year from the csv
				for (int i = 1961, j = 1; i < 2023 && j <63; i++, j++) {
					
					year.put(Integer.valueOf(i), Double.valueOf(tokens[j]));
					
					temp.put(Double.valueOf(tokens[j]), Integer.valueOf(i));
					}
				//creates the island object using variables above
				Island island = new Island(tokens[0], year, temp);
				
				//adds island object into the country list
				countrylist.add(island);
			}
		} catch (Exception e) 
			{
			e.printStackTrace();
			}
		
		//sorts country list alphabetically
		Collections.sort(countrylist, new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                return country1.getName().compareTo(country2.getName());
            }
        });
		
		//assigns country list to library
		this.library = countrylist;
		
		//returns library
		return this.library;
		}
	
	//returns temp of a given country and year
	public Double getTemp(String country1, Integer year) {	
		int index = indexlist.indexOf(country1);
		Double temp = library.get(index).getyeartemphash().get(year);
		return temp;
	}
	
	//returns year of a given temp and country
	public Integer getYear(String country, Double temp) {
		int index = indexlist.indexOf(country);
		Integer year = library.get(index).gettempyearhash().get(temp);
		return year;
	}
	
	//returns country from the given index
	public Object getCountry(int index) {
		Object country = library.get(index);
		return country;
	}
	
	
	//returns the hottest temp of a country/island
	@Override
	public Double gethottestTemp(String country) {
		int index = indexlist.indexOf(country);
		Country country1 = library.get(index);
		Double[] arr = new Double[62];

		
		for (int i = 1961, j = 0; i < 2023 && j < 63; i++, j++) {
			arr[j] = country1.getyeartemphash().get(i);
			
		}
		
		Double hottesttemp = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > hottesttemp) {
				hottesttemp = arr[i];
			}
		}
		return hottesttemp;
	}

	//returns the coldest temp of a country/island
	@Override
	public Double getcoldestTemp(String country) {
		int index = indexlist.indexOf(country);
		Country country1 = library.get(index);
		Double[] arr = new Double[62];
		
		
		for (int i = 1961, j = 0; i < 2023 && j < 63; i++, j++) {
			arr[j] = country1.getyeartemphash().get(i);
		}
		
		Double coldesttemp = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < coldesttemp) {
				coldesttemp = arr[i];
			}
		}
		return coldesttemp;
	}

	@Override
	public Double getpercentChange (String country, Integer year1, Integer year2) {
		
		Double temp1 = getTemp(country, year1);
		Double temp2 = getTemp(country, year2);
		
		Double change = ((temp2-temp1)/temp1)*100;
		
		
		return change;
	}

	//returns the percent change of temperature of a country from one year to another
	@Override
	public Double tempDifference(String country, Integer year1, Integer year2) {
		Double temp1 = getTemp(country, year1);
		Double temp2 = getTemp(country, year2);
		
		Double difference = Math.abs(temp1-temp2);
		return difference;
	}

	//converts the temp to Fahrenheit
	@Override
	public Double converttoF(Double temp) {
		Double far;
		far = (temp * (9/5)) + 32;
		return far;
	}

	//converts the temp to Celcius
	@Override
	public Double converttoC(Double temp) {
		Double cel;
		cel = (temp - 32) * (9/5);
		return cel;
	}
	

}
