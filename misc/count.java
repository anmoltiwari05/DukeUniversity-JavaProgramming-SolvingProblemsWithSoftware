
/**
 * Write a description of count here.
 * 
 * @author Anmol Tiwari 
 * @version (a version number or a date)
 */
public class count {
    public static void main(String args[]){
        String dna = "CTGCCTGCATGATCGTA";
        int pos = dna.indexOf("TG");
        int count = 0;
        while (pos >= 0) {
            count = count + 1;
            pos = dna.indexOf("TG",pos+1);
        }
        System.out.println(count);
        
        
    }
}
