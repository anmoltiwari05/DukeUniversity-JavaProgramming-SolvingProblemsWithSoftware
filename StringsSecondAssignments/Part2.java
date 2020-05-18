
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int startIndex = 0;
        int cnt = 0;
        while(true){
            startIndex = stringb.indexOf(stringa, startIndex);
            if(startIndex == -1){
                break;
            }
            cnt++;
            startIndex = startIndex + stringa.length();
        }
        
        return cnt;
    }
    
    public void testHowMany(){
        int result = howMany("AA","ATAAAA");
        System.out.println(result);
    }
}
