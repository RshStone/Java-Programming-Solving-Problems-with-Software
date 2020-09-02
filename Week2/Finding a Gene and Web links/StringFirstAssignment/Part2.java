import java.io.*;
import edu.duke.*;
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @Shaoqing（你的名字）
 * @18/08/2020（一个版本号或者一个日期）
 */
public class Part2 {
     public static void main(String [] args){
         testSimpleGene();
        }
     public static void testSimpleGene(){
        //create dna 
        //dna with TAA but no ATG
        String startCondon = "TAA";
        String stopCondon = "ATG";
        
        String dna1 = "TAAAT";
        System.out.println(dna1);
        String a = findSimpleGene(dna1,startCondon,stopCondon);
        if(a != null){
            String A = a.toLowerCase();
            System.out.println(A);
        }
        else{
            System.out.println(a);
        }
    
        //dna with ATG but no TAA
        String dna2 = "TATG";
        System.out.println(dna2);
        String b = findSimpleGene(dna2,startCondon,stopCondon);
        if(b != null){
            String B = b.toLowerCase();
            System.out.println(B);
        }
        else{
            System.out.println(b);
        }
    
        //dna with TAA and ATG, the result is not the multiple of 3
        String dna3 = "TAAAATG";
        System.out.println(dna3);
        String c = findSimpleGene(dna3,startCondon,stopCondon);
        if(c != null){
            String C = c.toLowerCase();
            System.out.println(C);
        }
        else{
            System.out.println(c);
        }
    
        //dna with TAA and ATG, the result is the multiple of 3
        String dna4 = "taaatg";
        String DNA4 = dna4.toUpperCase();
        System.out.println(DNA4);
        String d = findSimpleGene(DNA4,startCondon,stopCondon);
        if(d != null){
            String D = d.toLowerCase();
            System.out.println(D);
        }
        else{
            System.out.println(d);
        }
        
        String dna5 = "TAAATG";
        System.out.println(dna5);
        String E = findSimpleGene(dna5,startCondon,stopCondon);
        if(E != null){
            String e = E.toLowerCase();
            System.out.println(e);
        }
        else{
            System.out.println(E);
        }
    
    }
    public static String findSimpleGene(String dna, String startCondon, String stopCondon){
        //find index position of start&&stop condon
        if(dna.indexOf(startCondon) == -1) return null;
        int start = dna.indexOf(startCondon);
        System.out.println("startCondon is " + start);
        if(dna.indexOf(stopCondon) == -1) return null;
        int stop = dna.indexOf(stopCondon);
        System.out.println("stopCondon is " + stop);
        // if there is no start or stop condon, the result is -1
        // judge if the two index is the multiple of 3 
        if((stop - start ) % 3 == 0){
            String result = dna.substring(start, stop + 3);
            return result;
        }
        return null;
    }
}
