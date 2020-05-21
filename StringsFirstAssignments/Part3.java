
/**
 * Write a description of Part3 here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb){
        int firstOcc = stringb.indexOf(stringa);
        int lengthA = stringa.length();
        int secondOcc = stringb.indexOf(stringa, firstOcc+1);
        if (secondOcc != -1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void testing(){
        boolean result = twoOccurrences("by", "A story by Abby Long");
        System.out.println("by appears at least twice in 'A story by Abby Long' : "+result);
        
        result = twoOccurrences("atg", "ctgtatgta");
        System.out.println("atg appears at least twice in ctgtatgta : "+result);
        
        String LastPart = lastPart("zoo","forest");
        System.out.println("zoo and forest : "+LastPart);
        
        LastPart = lastPart("an","banana");
        System.out.println("an and banana : "+LastPart);
    }
    
    public String lastPart(String stringa, String stringb){
        int firstOcc = stringb.indexOf(stringa);
        int lengthA = stringa.length();
        if (firstOcc == -1){
            return stringb;        
        }
        else{
            String result = stringb.substring(firstOcc+lengthA);
            return result;
        }        
    }
}
