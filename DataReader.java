import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DataReader {
	private String csvFile;
	private String stationFile;
	private BufferedReader br;
	private HashMap<String,Station> parkingMetrics;
	
	public DataReader() {
		csvFile = "";
		stationFile = "";
		br = null;
		parkingMetrics = new HashMap<String, Station>();
	}
	
	public DataReader(String csvFile) {
		this.csvFile = csvFile;
		stationFile = "";
		br = null;
		parkingMetrics = new HashMap<String, Station>();
	}
	
	public String getCSV() {
		return csvFile;
	}
	
	public void setCSV(String csvFile) {
		this.csvFile = csvFile;
	}
	
	public String getStationFile() {
		return stationFile;
	}
	
	public void setStationFile(String stationFile) {
		this.stationFile = stationFile;
	}
	
	public void fillData() {
		System.out.println("Reading dataFile\nFormat: ID, STATION_ID, MONTH, YEAR, NUMBER_OF_CARS_PARKED, PERCENT_FILLED");
		String line = "";
		String csvSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] parkingData = line.split(csvSplitBy);
				
				//System.out.println("Station ID: " + parkingData[1] + " , Month: " + parkingData[2]);
				if (parkingData.length >= 5) {
					//System.out.println("Station ID: " + parkingData[1] + " , Month: " + parkingData[2]);
					if (!parkingMetrics.containsKey(parkingData[1])) {
						Station station = new Station();
						station.add(Integer.parseInt(parkingData[2]), Double.parseDouble(parkingData[5]) / 100);
						parkingMetrics.put(parkingData[1], station);
					}
					else {
						parkingMetrics.get(parkingData[1]).add(Integer.parseInt(parkingData[2]), Double.parseDouble(parkingData[5]) / 100);
					}
				}
			}
			br = new BufferedReader(new FileReader(stationFile));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] parkingData = line.split(csvSplitBy);
				
				if (parkingData.length >= 3) {
					//System.out.println("Station ID: " + parkingData[1] + ", Name: " + parkingData[1] + ", " + parkingData[2]);
					parkingMetrics.get(parkingData[0]).setName(parkingData[1]);
					parkingMetrics.get(parkingData[0]).setSpaces(Integer.parseInt(parkingData[2]));
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void exportMonth() {
		System.out.println("Exporting averages by month");
		FileWriter fr = null;
		try {
			fr = new FileWriter(System.getProperty("user.dir") + "/stationMetrics/monthMetrics.csv");
			fr.write("STATION_ID,STATION_NAME,AVG_POP\n");
			for (String metricIndex : parkingMetrics.keySet()) {
				Station station = parkingMetrics.get(metricIndex);
				double sum = 0;
				//System.out.println("Size: " + station.getMonthMetric().size());
				for (int j = 1; j < station.getMonthMetric().size() + 1; j++) {
					double size = station.getFilled(j).size();
					for (double index : station.getFilled(j)) {
						sum += index;
					}
					double average = sum / size;
					fr.write("" + metricIndex + "," + station.getName() + "," + average + "\n");
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (fr != null) {
				try {
					fr.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void exportYear() {
		System.out.println("Exporting averages by month");
		FileWriter fr = null;
		try {
			fr = new FileWriter(System.getProperty("user.dir") + "/stationMetrics/YearMetrics.csv");
			fr.write("STATION_ID,STATION_NAME,AVG_POP\n");
			for (String metricIndex : parkingMetrics.keySet()) {
				Station station = parkingMetrics.get(metricIndex);
				double sum = 0;
				double size = station.getFilled().size();
				for (double index : station.getFilled()) {
					sum+= index;
				}
				double average = sum / size;
				fr.write("" + metricIndex + "," + station.getName() + "," + average + "\n");
				//System.out.println("Size: " + station.getMonthMetric().size());
				
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (fr != null) {
				try {
					fr.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
