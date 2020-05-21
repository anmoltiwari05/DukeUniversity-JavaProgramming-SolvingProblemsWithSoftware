
/**
 * Write a description of Part2 here.
 * 
 * @author Anmol Tiwari 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon){
        if (dna == dna.toUpperCase()){
            int indexStart = dna.indexOf(startCodon);
            if (indexStart == -1){
                return "";
            }
            int indexStop = dna.indexOf(stopCodon, indexStart+3);
            if (indexStop == -1){
                return "";
            }
            String gene = dna.substring(indexStart, indexStop+3);
            if (gene.length()%3 == 0){
                return gene;
            }

        }
        
        else{
            dna = dna.toUpperCase();
            int indexStart = dna.indexOf(startCodon);
            if (indexStart == -1){
                return "";
            }
            int indexStop = dna.indexOf(stopCodon, indexStart+3);
            if (indexStop == -1){
                return "";
            }
            String gene = dna.substring(indexStart, indexStop+3);
            if (gene.length()%3 == 0){
                return gene.toLowerCase();
            }       
        }
        return "";
    }
    
    public void testSimpleGene(){
        String startCodon = "ATG";
        String stopCodon = "TAA";
        String dna = "ATCTGCAAA"; // no ATG and TAA
        String gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is:"+gene);
        
        dna = "ATATGATACTG"; // no TAA
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is:"+gene);
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC"; // valid gene
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is:"+gene);
        
        dna = "aatgacgtactaaac"; // valid gene lower case
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is:"+gene);
        
        dna = "AATCACGTACTAAAC"; // no ATG
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is:"+gene);
        
        dna = "AATGACGACTAAAC"; // gene not a multiple of 3
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is"+gene);
    }
}
