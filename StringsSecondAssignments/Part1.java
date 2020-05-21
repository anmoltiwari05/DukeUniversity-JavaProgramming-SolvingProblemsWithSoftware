
/**
 * Write a description of Part1 here.
 * 
 * @author Anmol Tiwari 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        while(stopIndex != -1){
            int length = stopIndex-startIndex;
            if (length%3 == 0){
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon, stopIndex+1);
            }
        }
        return -1;
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1){
            return "";
        }
        
        int TAA = findStopCodon(dna, startIndex, "TAA");
        int TGA = findStopCodon(dna, startIndex, "TGA");
        int TAG = findStopCodon(dna, startIndex, "TAG");
        int minIndex = 0;
        if ((TAA == -1) || ((TGA != -1 ) && (TGA<TAA))){
            minIndex = TGA;
        }
        else{
            minIndex = TAA;
        }
        
        if ((minIndex == -1) || ((TAG != -1) && (TAG<minIndex))){
            minIndex = TAG;
        }
        
        if (minIndex == -1){
            return "";
        }
        
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String gene = findGene(dna, startIndex);
            System.out.println(gene);
            if (gene.length()==0){
                break;
            }
            else{
                startIndex = gene.length()+dna.indexOf(gene,startIndex);
            }
        }   
    }
    
    public void testFindStopCodon(){
        //            01234567890123456789
        String dna = "ATGAAAATAAAATAAATGA";
        int result = findStopCodon(dna, 0, "TAA");
        System.out.println(result);
    }
    
    public void testFindGene(){
        //            012345678901234567890
        String dna = "ATGAAAAATAATACTTGA";
        printAllGenes(dna);
    }
}
