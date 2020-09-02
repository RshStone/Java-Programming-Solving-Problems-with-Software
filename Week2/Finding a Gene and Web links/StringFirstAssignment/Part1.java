
/**
 * the core code for finding gene。
 * 
 * @Shaoqing（你的名字）
 * @17/08/2020（一个版本号或者一个日期）
 */
import java.io.*;
import edu.duke.*;
import java.util.*;

public class Part1 {
    public static void main(String[] args){
        testSimpleGene();
    }
    public static void testSimpleGene(){
        //create dna 
        //dna with TAA but no ATG
        Scanner dna = new Scanner(System.in);
        String dna1 = dna.nextLine();     //TAAAT
        System.out.println(dna1);
        String a = findSimpleGene(dna1);
        System.out.println(a);
        //dna with ATG but no TAA
        String dna2 = dna.nextLine();      // TATG
        System.out.println(dna2);
        String b = findSimpleGene(dna2);
        System.out.println(b);
        //dna with TAA and ATG, the result is not the multiple of 3
        String dna3 = dna.nextLine();      //TAAAATG
        System.out.println(dna3);
        String c = findSimpleGene(dna3);
        System.out.println(c);
        //dna with TAA and ATG, the result is the multiple of 3
        String dna4 = dna.nextLine();         //TAAATG
        System.out.println(dna4);
        String d = findSimpleGene(dna4);
        System.out.println(d);
    }
    public static String findSimpleGene(String dna){
        //find index position of start&&stop condon
        if(dna.indexOf("TAA") == -1) return null;
        int startCondon = dna.indexOf("TAA");
        System.out.println("startCondon is " + startCondon);
        if(dna.indexOf("ATG") == -1) return null;
        int stopCondon = dna.indexOf("ATG");
        System.out.println("stopCondon is " + stopCondon);
        // if there is no start or stop condon, the result is -1
        // judge if the two index is the multiple of 3 
        if((stopCondon - startCondon ) % 3 == 0){
            String result = dna.substring(startCondon, stopCondon + 3);
            return result;
        }
        return null;
    }
}
