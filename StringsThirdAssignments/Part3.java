
/**
 * Write a description of Part3 here.
 * 
 * @author Anmol Tiwari 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part3 {
    
    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        while(true){
            String gene = findGene(dna, startIndex);
            sr.add(gene);
            if (gene.length()==0){
                break;
            }
            else{
                startIndex = gene.length()+dna.indexOf(gene,startIndex);
            }
        }
        return sr;
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
    
    public int countAllGenes(String dna){
        int cnt =0 ;
        int startIndex = 0;
        while(true){
            String gene = findGene(dna, startIndex);
            
            if (gene.length()==0){
                break;
            }
            else{
                cnt++;
                startIndex = gene.length()+dna.indexOf(gene,startIndex);
            }
        }
        return cnt;
    }
    
    public void processGene(StorageResource sr){
        int cnt60 = 0;
        int cg35 = 0;
        int longestGene = 0;
        int countOfCTG = 0;
        System.out.println("String that are longer than 9 characters:");
        for (String s: sr.data()){
            if (s.length() > 60){
                System.out.println(s);
                cnt60++;
            }
        }
        System.out.println("Number of Strings that longer than 60 characters : "+cnt60);
        System.out.println("Strings whose C-G-ratio is higher than 0.35");
        for (String s: sr.data()){
            if (cgRatio(s) > 0.35){
                System.out.println(s);
                cg35++;
            }
            if (s.length()>longestGene){
                longestGene = s.length();
            }
        }
        System.out.println("number of strings whose CGratio is higher than 0.35 : "+ cg35);
        System.out.println("Length of the longest gene : "+longestGene);
    }
    
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
        //System.out.println("Count of C : "+c);
        double g = count(dna, "G");
        //System.out.println("Count of G : "+g);
        double length = dna.length();
        double ratio = (c+g)/length;
        return ratio;
    }
       
    public int countCTG(String dna){
        return count(dna, "CTG");
    }
       
    public void testProcessGenes(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        int numberOfGenes= 0;
        StorageResource genes = new StorageResource();
        if (dna == dna.toUpperCase()){
            genes = getAllGenes(dna);
            processGene(genes);
            numberOfGenes = countAllGenes(dna);
            System.out.println("Number of Genes found in this dna : "+numberOfGenes);
            System.out.println("Number of CTG Codons found in this dna : "+countCTG(dna));
        }
        else{
            dna = dna.toUpperCase();
            genes = getAllGenes(dna);
            processGene(genes);
            numberOfGenes = countAllGenes(dna);
            System.out.println("Number of Genes found in this dna : "+numberOfGenes);
            System.out.println("Number of CTG Codons found in this dna : "+countCTG(dna));
        }
    }
}
