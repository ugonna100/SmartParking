import java.io.File;

public class Main {
	public static void main(String[] args) {
		File f = new File(DataReader.class.getResource("fixedInfo.csv").getFile());
		System.out.println(f.getAbsolutePath());
		
		DataReader dr = new DataReader("C:/Users/Bakuano/Documents/[]Programming Projects/SmartParking/fixedInfo.csv");
		dr.setStationFile("C:/Users/Bakuano/Documents/[]Programming Projects/SmartParking/Station.csv");
		dr.fillData();
		
		dr.exportMonth();
		dr.exportYear();
	}
	
}
