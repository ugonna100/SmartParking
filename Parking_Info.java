import java.util.*;
public class Parking_Info 
{
	private int [] longitude = new int [2];
	private int [] lattitude = new int [2];
	private ArrayList<Double> listofData = new ArrayList<Double>();
	private double data;
	
	public void add(double data)
	{
		listofData.add(data);
	}
}
