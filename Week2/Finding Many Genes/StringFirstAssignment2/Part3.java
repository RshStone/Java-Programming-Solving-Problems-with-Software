
/**
 * Write a description of Part1 here.
 * 
 * @Shaoqing (your name) 
 * @24,08,2020 (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCondon){
        int stopIndex = dna.indexOf(stopCondon, startIndex + 3);
        while(stopIndex != -1){
            if((stopIndex - startIndex) % 3 == 0 ){
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCondon, stopIndex + 1);
            }
        }
        return dna.length();
    }
   
    public int countGenes(String dna){
       int start = 0;
       int count = 0;
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
           count++;
           String gene = dna.substring(start, stop + 3);
           start++;
           System.out.println(gene);
       }
       return count;
    }
    
    public void testCountGenes(){
        String string1 = "ATGTAAGATGCCCTAGT";
        int count = countGenes(string1);
        System.out.println(count);
    }
}
