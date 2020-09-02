
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @Shaoqing（你的名字）
 * @18/08/2020（一个版本号或者一个日期）
 */
import java.io.*;
import edu.duke.*;
public class Part3 {
    public static void main(String []args){
        testing();
    }
    public static boolean twoOccurrences(String stringa, String stringb){
        //if stirnga appears at least twice in stringb, return true
        int count = 0;
        int start = stringb.indexOf(stringa);
        while(start != -1){
            start = stringb.indexOf(stringa,start + 1);
            count++;
        }
        if(count >= 2){
            return true;
        }
        return false;
    }
    public static void testing(){
        String stringa = "an";
        String stringb = "anbananan";
        System.out.println("stringa is " + stringa + "," + "stringb is " + stringb);
        System.out.println("The lastPart string is " + lastPart(stringa,stringb));
        boolean result1 = twoOccurrences(stringa, stringb);
        System.out.println("twoOccurrences is " + result1);
        System.out.println();
        
        stringa = "black";
        stringb = "white";
        System.out.println("stringa is " + stringa + "," + "stringb is " + stringb);
        System.out.println("The lastPart string is " + lastPart(stringa,stringb));
        boolean result2 = twoOccurrences(stringa, stringb);
        System.out.println("twoOccurrences is " + result2);
        System.out.println();
        
        stringa = "a";
        stringb = "adog";
        System.out.println("stringa is " + stringa + "," + "stringb is " + stringb);
        System.out.println("The lastPart string is " + lastPart(stringa,stringb));
        boolean result3 = twoOccurrences(stringa, stringb);
        System.out.println("twoOccurrences is " + result3);
        
        // test lastPart method
        
    }
    public static String lastPart(String stringa, String stringb){
        int start = stringb.indexOf(stringa);
        if(start == -1) return stringb;
        stringb = stringb.substring(start + stringa.length());
        return stringb;
    }
}
