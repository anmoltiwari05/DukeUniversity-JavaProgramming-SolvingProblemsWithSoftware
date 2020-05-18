
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public int count(String dna, String letter){
        int startIndex = dna.indexOf(letter);
        int cnt = 0;
        while(true){
            
            if(startIndex == -1){
                break;
            }
            cnt++;
            startIndex = dna.indexOf(letter, startIndex+1);
        }   
    
        return cnt;
    }
    public double cgRatio(String dna){
        double c = count(dna, "C");
        System.out.println("Count of C : "+c);
        double g = count(dna, "G");
        System.out.println("Count of G : "+g);
        double length = dna.length();
        double ratio = (c+g)/length;
        return ratio;
    }
       
    public int countCTG(String dna){
        return count(dna, "CTG");
    }
    
    public void test(){
        String dna = "ATGCCATAGCTG";
        double result = cgRatio(dna);
        System.out.println("cgRatio : "+result);
        System.out.println("CTG Count : "+countCTG(dna));
    }
}
