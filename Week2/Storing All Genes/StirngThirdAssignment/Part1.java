
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
                System.out.println("FindStopCodon " + stopIndex);
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
  
    public StorageResource getAllGenes(String dna){
       //初步拟定这里的算法出现比较大的问题  做个标记，之后有时间再改吧
       
       int startIndex = 0;
       int count = 0;
       String temp = dna.toUpperCase();
       System.out.println("CTG count is " + countCTG(temp));
       System.out.println(temp);
       StorageResource genelist = new StorageResource();
       while(true){
           startIndex = temp.indexOf("ATG", startIndex);
           System.out.println("First startIndex is " + startIndex);
           if(startIndex == -1){
               break;
           }
           int stop1 = findStopCodon(temp, startIndex, "TAA");
           int stop2 = findStopCodon(temp, startIndex, "TAG");
           int stop3 = findStopCodon(temp, startIndex, "TGA");
           int stop = Math.min(stop1, stop2);
           stop = Math.min(stop3, stop);
           System.out.println("Stop is " + stop);
           //ATGCCCCCCBBBTAAATGTGA
           //012345678901234567890
           //ATGTAAATGTAGATTGAE
           //01234567890123456789
           if(stop == dna.length()){
               break;
           }
           String gene = dna.substring(startIndex, stop + 3);
           count++;
           genelist.add(gene);
           startIndex = stop + 3;
           System.out.println("Second index is " + startIndex);
           System.out.println("GetAllGenes " + gene);
       }
       System.out.println("count of genes is " + count);
       return genelist;
    }
    
    public void testGetAllGenes(){
        String dna = "ATGTAAATGTAGATGTGA";
        StorageResource genes = getAllGenes(dna);
        for(String g: genes.data()){
            System.out.println(g);
        }
    }
    
    public double cgRatio(String dna){
        int count = 0;
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G' || dna.charAt(i) == 'c' || dna.charAt(i) == 'g'){
                count++;
            }
        }
        double ratio = count/(double) dna.length();
        return ratio;
    }
    public int countCTG(String dna){
        int count = 0;
        int start = 0;
        while(start != -1){
            start = dna.indexOf("CTG", start);
            if(start == -1) break;
            start++;
            count++;
        }
        return count;
    }
    
    public void processGenes(StorageResource sr){
        int countLength = 0;
        int countRatio = 0;
        int maxLength = 0;
        int count = 0;
        for(String g: sr.data()){
            count++;
            if(g.length() > 60){
                System.out.println("the length of stirng is more than 6: " + g + " length of it is " + g.length());
                countLength++;
            }
            if(cgRatio(g) > 0.35){
                System.out.println( g + " the cgratio is " + cgRatio(g));
                countRatio++;
            }
            if(g.length() > maxLength){
                maxLength = g.length();
            }
        }
        System.out.println("the count of genes is " + count);
        System.out.println("the length of the longest gene is " + maxLength);
        System.out.println("the number of Strings in sr that are longer than 60 characters is " + countLength);
        System.out.println("the number of Strings in sr whose C-G-ratio is higher than 0.35 is " + countRatio);
    }
    
    public void testProcessGenes(){
        String dna1 = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAG";
        //String dna1 = "nonCodingDNAxxxMyGeneATGmyGenexTAAxxGeneATGTAACATGTAAATGCendTAATAAnonCodingDNAxTAGxTGA";
        StorageResource genes1 = getAllGenes(dna1);
        processGenes(genes1);
        System.out.println();
        
        /*String dna2 = "ATGTAAATGTAGATTGA";
                     //012345678901234567890
        System.out.println(dna2);
        StorageResource genes2 = getAllGenes(dna2);
        processGenes(genes2);
        System.out.println();
        
        String dna3 = "ATGTAAATGCCCTAG";
        System.out.println(dna3);
        StorageResource genes3 = getAllGenes(dna3);
        processGenes(genes3);
        System.out.println();
        
        String dna4 = "ATGTAAATGTAA";
        System.out.println(dna4);
        StorageResource genes4 = getAllGenes(dna4);
        processGenes(genes4);*/
        
    }
    
    public void testDna(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource genes = getAllGenes(dna);
        processGenes(genes);
    }
}
