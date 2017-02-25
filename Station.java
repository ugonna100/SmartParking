import java.util.HashMap;
import java.util.ArrayList;
public class Station 
{
	private double [] location;
	private HashMap<Integer, ArrayList<Double>> monthMetric;
	private String name;
	
	public Station()
	{
		monthMetric = new HashMap<Integer, ArrayList<Double>>();
		location = new double [2];
		name = "";
		
	}
	public Station(double lat, double lon)
	{
		location = new double[]{lat ,lon};
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
	public void add(int month, ArrayList<Double>data)
	{
		monthMetric.put(month, data);
	}
	public ArrayList<Double> getFilled()
	{
		ArrayList<Double> values = new ArrayList<Double>();
		for(int i = 0; i < monthMetric.size(); i++)
		{
			for(double filled: monthMetric.get(i))
			{
				values.add(filled);
			}
		}
		return values;
	}
	public ArrayList<Double> getFilled(int month)
	{
		return monthMetric.get(month);
	}
}
