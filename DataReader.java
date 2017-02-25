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
	private HashMap<Integer,Parking_Info> parkingMetrics;
	
	public DataReader() {
		csvFile = "";
		stationFile = "";
		br = null;
		parkingMetrics = new HashMap<Integer, Parking_Info>();
	}
	
	public DataReader(String csvFile) {
		this.csvFile = csvFile;
		stationFile = "";
		br = null;
		parkingMetrics = new HashMap<Integer, Parking_Info>();
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
				
				System.out.println("Station ID: " + parkingData[1] + " , Month: " + parkingData[2]);
				Parking_Info station = new Parking_Info();
				station.add(parkingData[2], Double.parseDouble(parkingData[5]) / 100);
				parkingMetrics.put(parkingData[1], station);
			}
			br = new BufferedReader(new FileReader(stationFile));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] parkingData = line.split(csvSplitBy);
				
				System.out.println("Station ID: " + parkingData[1] + " , Name: " + parkingData[2]);
				parkingMetrics.get(parkingData[1]).setName(parkingData[2]);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
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
	
	public void exportData() {
		System.out.println("Exporting averages by month");
		FileWriter fr = new FileWriter("/stationMetrics/monthMetrics.csv");
		fr.write("STATION_ID,STATION_NAME,AVG_POP");
		try {
			for (int i = 0; i < parkingMetrics.size(); i++) {
				Parking_Info station = parkingMetrics.get(i);
				double sum = 0;
				for (int j = 0; j < station.getMap().size(); j++) {
					size = station.getFilled(j).size();
					for (double index : station.getFilled(j)) {
						sum += index;
					}
					double average = sum / size;
					fr.write("" + i + "," + station.getName() + "," + average);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (fr != null) {
				try {
					fr.close());
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
