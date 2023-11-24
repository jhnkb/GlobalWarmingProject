

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Library implements Calculations{


	public	ArrayList<String> indexlist = new ArrayList<String>();
	public ArrayList<Country> library1 = new ArrayList<Country>();
	public Hashtable<Integer,Double> yeartemp = new Hashtable<>();
	public Hashtable<Double, Integer> tempyear = new Hashtable<>();
	public Country country;
	
	public Library() {
	indexlist = createCountryList("testcsv.csv");
	library1 = createLibrary("testcsv.csv"); 
	//"C:\\Users\\jboli\\OneDrive\\Desktop\\testcsv.csv"
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
				Hashtable<Integer,Double> year = new Hashtable<>();
				Hashtable<Double,Integer> temp = new Hashtable<>();
				String[] tokens = line.split(",");
				data.add(Arrays.asList(tokens));
				for (int i = 1961, j = 1; i < 2023 && j <63; i++, j++) {
					
					year.put(Integer.valueOf(i), Double.valueOf(tokens[j]));
					
					
					temp.put(Double.valueOf(tokens[j]), Integer.valueOf(i));
					}
				country = new Country(tokens[0], year, temp);
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
		Double temp = library1.get(index).getyeartemphash().get(year);
		return temp;
	}
	
	public Integer getYear(String country, Double temp) {
		int index = indexlist.indexOf(country);
		Integer year = library1.get(index).gettempyearhash().get(temp);
		return year;
	}

	@Override
	public Double gethottestTemp(String country) {
		int index = indexlist.indexOf(country);
		Country country1 = library1.get(index);
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

	@Override
	public Double getcoldestTemp(String country) {
		int index = indexlist.indexOf(country);
		Country country1 = library1.get(index);
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
		
		Double change = ((temp1-temp2)/temp1)*100;
		
		
		return change;
	}

	@Override
	public double tempDifference(String country, Integer year1, Integer year2) {
		Double temp1 = getTemp(country, year1);
		Double temp2 = getTemp(country, year2);
		
		Double difference = Math.abs(temp1-temp2);
		return difference;
	}
	

}