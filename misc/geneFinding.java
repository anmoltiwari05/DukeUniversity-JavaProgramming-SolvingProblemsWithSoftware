
/**
 * Write a description of geneFinding here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class geneFinding {
    public String findGene(String dna, int where){
        
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1){
            return "";
        }
        int TAA = findStopIndex(dna, startIndex, "TAA");
        int TAG = findStopIndex(dna, startIndex, "TAG");
        int TGA = findStopIndex(dna, startIndex, "TGA");
        int temp;
        int minIndex;
        if((TAA == -1) || ((TAG != -1) && (TAG<TAA))){
            temp = TAG;
        }
        else{
            temp = TAA;
        }
        
        if ((TGA == -1) || ((temp != -1) && (temp<TGA))){
            minIndex = temp;
        }
        else{
            minIndex = TGA;
        }
        
        if ((TAA == -1) && (TAG == -1) && (TGA == -1)){
            return "";
        }
        String gene = dna.substring(startIndex, minIndex+3);
        return gene;
    }
    
    public int findStopIndex(String dna, int startIndex, String codon){
        int currIndex = dna.indexOf(codon, startIndex);
        while(currIndex != -1){
            int length = currIndex - startIndex;
            if (length % 3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(codon, currIndex+1);
            }
        }
        return -1;
    }
    
    public String findMultiGene(String dna){
        int startIndex = 0;
        while (true){
            String currGene = findGene(dna, startIndex);
            if (currGene.length() == 0){
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene,startIndex) + currGene.length();
    
        }
    
    return "";
    }
    
    public void main(){
        //            012345678901234567890123456789012345
        FileResource fr = new FileResource("DNA.txt");
        
        String dna = fr.asString();
        String result = findMultiGene(dna);
               
    }
}
