

public interface Calculations {
	
	//returns the hottest temp of a country/island
	public Double gethottestTemp(String country);
	
	//returns the coldest temp of a country/island
	public Double getcoldestTemp(String country);
	
	//returns the percent change of temperature of a country from one year to another
	public Double getpercentChange (String country, Integer year1, Integer year2);

	//returns the temp difference of a country from one year to another
	public Double tempDifference(String country, Integer year1, Integer year2);
	
	//converts the temp to Celcius
	public Double converttoC(Double temp);
	
	//converts the temp to Fahrenheit
	public Double converttoF(Double temp);

}
