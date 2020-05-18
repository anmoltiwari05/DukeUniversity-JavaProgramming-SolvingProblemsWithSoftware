
/**
 * Write a description of babyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class babyNames {
    public void totalBirths(CSVParser parser){
        int totalNumber = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int countNameGirls = 0;
        int countNameBoys = 0;
        for (CSVRecord record:parser){
            String gender = record.get(1);
            int number = Integer.parseInt(record.get(2));
            if (gender.equals("F")){
                countNameGirls++;
                totalGirls += number;            
            }
            else{
                countNameBoys++;
                totalBoys += number;
            }
            totalNumber += number;
        }
        System.out.println("Total Number of kids: "+totalNumber);
        System.out.println("Total Number of girls: "+totalGirls);
        System.out.println("Total Number of boys: "+totalBoys);
        System.out.println("Total Girl Names: "+countNameGirls);
        System.out.println("Total Boy Names: "+countNameBoys);
    }
    
    public int getRank(int year, String name, String gender){
    FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
    CSVParser parser = fr.getCSVParser(false);
    int rank = 0;
    for (CSVRecord record : parser){
        String currName = record.get(0);
        String currGender = record.get(1);
        if (gender.equals(currGender)){
            rank++;
            if (name.equals(currName)){
                return rank;
            }
        }
    }
    return -1;
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        int currRank = 0;
        for (CSVRecord record : parser){
            String name = record.get(0);
            String currGender = record.get(1);
            if (gender.equals(currGender)){
                currRank++;
                if(currRank == rank){
                    return name;
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear);
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        String file = "";
        for (File f: dr.selectedFiles()){
            String filename = f.getName();
            int startIndex = filename.indexOf("b");
            int stopIndex = filename.indexOf(".", startIndex);
            int year = Integer.parseInt(filename.substring(startIndex+1, stopIndex));
            int currRank = getRank(year, name, gender);
            if (highestRank == 0 && currRank != -1){
                highestRank = currRank;
                file = filename;
            }
            if(currRank < highestRank && currRank>0){
                highestRank = currRank;
                file = filename;
            }
        }
        System.out.println("In the file: "+ file);
        return highestRank;
    }
    
    public double getAverageRank(String name, String gender){
        double avgRank = 0;
        double cnt = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            String filename = f.getName();
            int startIndex = filename.indexOf("b");
            int stopIndex = filename.indexOf(".", startIndex);
            int year = Integer.parseInt(filename.substring(startIndex+1, stopIndex));
            int currRank = getRank(year, name, gender);
            if (currRank != -1){
                avgRank += currRank;
                cnt++;
            }
        }
        return avgRank/cnt;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        int totalBirth = 0;
        for (CSVRecord record : parser){
            String currName = record.get(0);
            String currGender =  record.get(1);
            int birthCount = Integer.parseInt(record.get(2));
            if (currGender.equals(gender)){
                if (currName.equals(name)){
                    return totalBirth;
                }
                totalBirth +=  birthCount;
            }
        }
        return -1;
    }
        
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        totalBirths(parser);
    }
    
    public void testGetRank(){
        int rank = getRank(1971, "Frank", "M");
        System.out.println("Rank is: "+rank);
    }
    
    public void testGetName(){
        String name = getName(1982, 450, "M");
        System.out.println("Name is: "+name);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public void testYearOfHighestRank(){
        int highestRank = yearOfHighestRank("Mich", "M");
        System.out.println("Highest rank is: "+highestRank);
    }
    
    public void testGetAverageRank(){
        double avgRank = getAverageRank("Robert", "M");
        System.out.println("Average rank is: "+avgRank);
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int birth = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("Total Births Ranked Higher is: "+birth);
    }
    
    public void tester(){
        DirectoryResource dr = new DirectoryResource();
        int index = 0;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record:parser){
                String currName = record.get(0);
                if (currName.equals("Mich")){
                    index = 1;
                }
            }
        }
        System.out.println(index);
    }
}
