
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
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
    
    public int countGenes(String dna){
        int cnt = 0;
        int startIndex = 0;
        while(true){
            String gene = findGene(dna, startIndex);
            if (gene.length() == 0){
                break;
            }
            else{
                startIndex = gene.length()+dna.indexOf(gene, startIndex);
                cnt++;
            }
            
        }
        return cnt;
    }
    
    public void testCountGene(){
    int result = countGenes("ATGTAAGATGCCCTAGT");
    System.out.println(result);
    }
}
