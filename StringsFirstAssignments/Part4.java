
/**
 * Write a description of Part4 here.
 * 
 * @author Anmol Tiwari 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
    public void WebLinks(){
        URLResource s = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String a : s.words()){
            String lower = a.toLowerCase();
            String target = "youtube.com";
            int indexTarget = lower.indexOf(target);
            if (indexTarget != -1){
                int Start = a.lastIndexOf("\"", indexTarget);
                int Stop = a.indexOf("\"", indexTarget+1);
                String result = a.substring(Start+1, Stop);
                System.out.println(result);
            }
        }
    }
}
