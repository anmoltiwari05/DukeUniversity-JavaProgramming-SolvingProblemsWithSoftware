
/**
 * Write a description of Part1 here.
 * 
 * @author Anmol Tiwari 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna){
        int indexStart = dna.indexOf("ATG");
        if (indexStart == -1){
            return "";
        }
        int indexStop = dna.indexOf("TAA", indexStart+3);
        if (indexStop == -1){
            return "";
        }
        String gene = dna.substring(indexStart, indexStop+3);
        if (gene.length()%3 == 0){
            return gene;
        }
        return "";
    }
    
    public void testSimpleGene(){
        String dna = "ATCTGCAAA"; // no ATG and TAA
        String gene = findSimpleGene(dna);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is"+gene);
        
        dna = "ATATGATACTG"; // no TAA
        gene = findSimpleGene(dna);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is"+gene);
        
        dna = "AATGACGTACTAAAC"; // valid gene
        gene = findSimpleGene(dna);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is"+gene);
        
        dna = "AATCACGTACTAAAC"; // no ATG
        gene = findSimpleGene(dna);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is"+gene);
        
        dna = "AATGACGACTAAAC"; // gene not a multiple of 3
        gene = findSimpleGene(dna);
        System.out.println("The dna is:"+dna);
        System.out.println("The gene found in this dna is"+gene);
    }
}
