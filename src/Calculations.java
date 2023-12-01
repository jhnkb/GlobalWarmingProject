

public interface Calculations {
	
	public Double gethottestTemp(String country);
	
	public Double getcoldestTemp(String country);
	
	public Double getpercentChange (String country, Integer year1, Integer year2);

	public double tempDifference(String country, Integer year1, Integer year2);
	
	public Double converttoC(Double temp);
	
	public Double converttoF(Double temp);

}
