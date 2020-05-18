
/**
 * Write a description of YouTubelink here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class YouTubeLink {
    public void findLinks(){
        
        String temp;
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s: ur.lines()){
            temp = s.toLowerCase();
            int indexTarget = temp.indexOf("youtube.com");
            if (indexTarget != -1){
                int startIndex = s.lastIndexOf("\"",indexTarget);
                int stopIndex = s.indexOf("\"", indexTarget+1);
                String url = s.substring(startIndex+1,stopIndex);
                System.out.println(url);
            }
            
        }
        
    }
}
