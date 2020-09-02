
/**
 * Write a description of Part1 here.
 * 
 * @Shaoqing (your name) 
 * @24,08,2020 (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCondon){
        int stopIndex = dna.indexOf(stopCondon, startIndex + 3);
        while(stopIndex != -1){
            if((stopIndex - startIndex) % 3 == 0 ){
                System.out.println(stopIndex);
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCondon, stopIndex + 1);
            }
        }
        return dna.length();
    }
    public void testFindStopCodon(){
        String dna = "ATGAAATAATAGTGA";
                    //012345678901234
        //CTGCCTGCATGATCGTA
        //01234567890123456
        //find the startIndex of dna and the start condon is "ATG"
        int startIndex = dna.indexOf("ATG");
        //test different situations of different stop condon TAA TAG TGA
        int stopIndex1 = findStopCodon(dna, startIndex, "TAA");
        System.out.println(stopIndex1);
        int stopIndex2 = findStopCodon(dna, startIndex, "TAG");
        System.out.println(stopIndex2);
        int stopIndex3 = findStopCodon(dna, startIndex, "TGA");
        System.out.println(stopIndex3);
    }
    public String findGene(String dna){
       int start = 0;
       start = dna.indexOf("ATG", start);
       if(start == -1){
           return null;
       }
       int stop1 = findStopCodon(dna, start, "TAA");
       int stop2 = findStopCodon(dna, start, "TAG");
       int stop3 = findStopCodon(dna, start, "TGA");
       int stop = Math.min(stop1, stop2);
       stop = Math.min(stop3, stop);
       if(stop == dna.length()){
           return null;
       }
       String gene = dna.substring(start, stop + 3);
       start++;
       return gene;
    }
    public void testFindGene(){
        //without ATG
        String dna1 = "TAATAGTGA";
        System.out.println(dna1);
        System.out.println(findGene(dna1));
        //ATG and multiple stop codon
        String dna2 = "ATGTAATAGTGA";
        System.out.println(dna2);
        System.out.println(findGene(dna2));
        //ATG and not the multiple of 3
        String dna3 = "ATGTTAATAGTGA";
        System.out.println(dna3);
        System.out.println(findGene(dna3));
        //ATG with one stop codon
        String dna4 = "ATGTAABBBBBB";
        System.out.println(dna4);
        System.out.println(findGene(dna4));
        //many genes
        String dna5 = "ATGTAABBBATGTGA";
        System.out.println(dna5);
        System.out.println(findGene(dna5));
        printAllGenes(dna5);
    }
    public void printAllGenes(String dna){
       int start = 0;
       while(start != -1){
           start = dna.indexOf("ATG", start);
           int stop1 = findStopCodon(dna, start, "TAA");
           int stop2 = findStopCodon(dna, start, "TAG");
           int stop3 = findStopCodon(dna, start, "TGA");
           int stop = Math.min(stop1, stop2);
           stop = Math.min(stop3, stop);
           if(stop == dna.length()){
               break;
           }
           String gene = dna.substring(start, stop + 3);
           start++;
           System.out.println(gene);
       }
       
    }
}
