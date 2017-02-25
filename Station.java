import java.util.HashMap;
import java.util.ArrayList;
public class Station 
{
	private double [] location;
	private HashMap<Integer, ArrayList<Double>> monthMetric;
	private String name;
	private int numSpaces;
	
	public Station()
	{
		monthMetric = new HashMap<Integer, ArrayList<Double>>();
		location = new double [2];
		name = "";
		numSpaces = 0;
		
	}
	public Station(double lat, double lon)
	{
		monthMetric = new HashMap<Integer, ArrayList<Double>>();
		location = new double[]{lat ,lon};
		name = "";
		numSpaces = 0;
	}
	
	public void setSpaces(int numSpaces) {
		this.numSpaces = numSpaces;
	}
	
	public int getSpaces() {
		return numSpaces;
	}
	
	public void setMonthMetrics(HashMap<Integer, ArrayList<Double>> monthMetric)
	{
		this.monthMetric = monthMetric;
	}
	public HashMap<Integer, ArrayList<Double>> getMonthMetric()
	{
		return monthMetric;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setLocation(double lat, double lon) {
		location[0] = lat;
		location[1] = lon;
	}
	
	public double[] getLocation() {
		return location;
	}
	
	public void add(int month, double data)
	{
		if (!monthMetric.containsKey(month)) {
			//System.out.println("Month metric does not contain: " + month + " filled: " + data);
			ArrayList<Double> filled = new ArrayList<Double>();
			filled.add(data);
			monthMetric.put(month, filled);
		}
		else {
			//System.out.println("Current arraylist size: " + monthMetric.get(month).size());
			monthMetric.get(month).add(data);
		}
	}
	public ArrayList<Double> getFilled()
	{
		ArrayList<Double> values = new ArrayList<Double>();
		for(int i = 0; i < monthMetric.size(); i++)
		{
			for(double percents: monthMetric.get(i))
			{
				values.add(percents);
			}
		}
		return values;
	}
	public ArrayList<Double> getFilled(int month)
	{
		return monthMetric.get(month);
	}
}
