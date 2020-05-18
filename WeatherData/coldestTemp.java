
/**
 * Write a description of coldestTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class coldestTemp {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for (CSVRecord record: parser){
            if (coldestSoFar == null){
                coldestSoFar = record;
            }
            else{
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                double LowestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp<LowestTemp && currentTemp != -9999){
                    coldestSoFar = record;
                }
            }
        }
        return coldestSoFar;
    }
    
    public String fileWithColdestTemperature(){
        String filename = "";
        CSVRecord coldestTemp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currTemp = coldestHourInFile(parser);
            
            if (coldestTemp == null){
                coldestTemp = currTemp;
                filename = f.getName();
            }
            else {
                double leastTempSoFar = Double.parseDouble(coldestTemp.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currTemp.get("TemperatureF"));
                if (currentTemp < leastTempSoFar && currentTemp != -9999){
                    coldestTemp = currTemp;
                    filename = f.getName();
                }
            }
        }
        return filename;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord leastHumidity = null;
        for (CSVRecord record: parser){
            String strhumi = record.get("Humidity");
            
            if (leastHumidity == null){
                leastHumidity = record;
            }
            if (record.get("Humidity").equals("N/A")){
                continue;
            }
            else{
                double currhumi = Double.parseDouble(strhumi);
                double leastHumiditySoFar = Double.parseDouble(leastHumidity.get("Humidity"));
                if (currhumi < leastHumiditySoFar){
                    leastHumidity = record;
                }
            }
        }
        return leastHumidity;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord leastHumi= null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currHumi = lowestHumidityInFile(parser);
            
            if (leastHumi == null){
                leastHumi = currHumi;
            }
            else if (currHumi.get("Humidity") == "N/A"){
                continue;
            }
            else{
                double leastHumiSoFar = Double.parseDouble(leastHumi.get("Humidity"));
                double currentHumi = Double.parseDouble(currHumi.get("Humidity"));
                if (currentHumi < leastHumiSoFar){
                    leastHumi = currHumi;
                }
            }
        }
        return leastHumi; 
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double avgTemp = 0;
        int cnt =0;
        for(CSVRecord record:parser){
            double Temp = Double.parseDouble(record.get("TemperatureF"));
            avgTemp = avgTemp + Temp;
            cnt++;
        }
        return avgTemp/cnt;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double avgTemp = 0;
        int cnt =0;
        for(CSVRecord record:parser){
            double Temp = Double.parseDouble(record.get("TemperatureF"));
            double Humi = Double.parseDouble(record.get("Humidity"));
            if (Humi>=value){
                avgTemp = avgTemp + Temp;
                cnt++;
            }
        }
        return avgTemp/cnt;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Colder temp is "+ coldest.get("TemperatureF")+ " at the hour " + coldest.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature(){
        String filename = fileWithColdestTemperature();
        System.out.println("Coldest day was in file weather "+filename);
        FileResource fr = new FileResource("nc_weather/2013/"+filename);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Colder temp is "+ coldest.get("TemperatureF")+ " at the hour " + coldest.get("DateUTC"));
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Least Humidity is "+ csv.get("Humidity")+ " at the hour " + csv.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Least Humidity was "+ record.get("Humidity")+" at "+record.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+avg);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average temperature in file is "+avg);
    }
    
    
}
