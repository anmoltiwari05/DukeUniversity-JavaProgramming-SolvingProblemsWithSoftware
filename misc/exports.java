
/**
 * Write a description of exports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class exports {
    public String countryInfo(String country, CSVParser parser){
        for (CSVRecord record: parser){
            String name = record.get("Country");
            if(name.contains(country)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return(country+": "+exports+": "+value);
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record: parser){
            String name = record.get("Exports");
            if (name.contains(exportItem1)){
                if (name.contains(exportItem2)){
                    System.out.println(record.get("Country"));
                }
            }
        }
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem){
        int cnt=0;
        for (CSVRecord record: parser){
            String name = record.get("Exports");
            if (name.indexOf(exportItem) != -1){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    
    public void bigExporters(CSVParser parser, String value){
        for (CSVRecord record: parser){
            String name = record.get("Value (dollars)");
            if (value.length()<name.length()){
                System.out.println((record.get("Country"))+" "+name);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //String result = countryInfo("Nauru", parser);
        //System.out.println(result);
        //parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //parser = fr.getCSVParser();
        //numberOfExporters(parser, "cocoa");
        //parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
