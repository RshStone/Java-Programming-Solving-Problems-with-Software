
/**
 * Write a description of Part2 here.
 * 
 * @Shaoqing (your name) 
 * @08,24,2020 (a version number or a date)
 */
import java.util.*;
public class Part2 {
    public int howMany(String stringa, String stringb){
        // count how many times stringa is in stirngb
        int count = 0;
        int start = 0;
        while(start != -1){
            start = stringb.indexOf(stringa,start);
            if(start == -1) break;
            count++;
            start += stringa.length();
            // stirnga:AA  stringb:AATTAA;
        }
        return count;
    }
    public void testHowMany(){
        String stringa1 = "AA";
        String stringb1 = "AATTAA";
        System.out.println(howMany(stringa1, stringb1));
        
        String stringa2 = "GAA";
        String stringb2 = "ATGAACGAATTGAATC";
        System.out.println(howMany(stringa2, stringb2));
        
        String stringa3 = "AA";
        String stringb3 = "ATABABAB";
        System.out.println(howMany(stringa3, stringb3));
        
        String stringa4 = "ACAB";
        String stringb4 = "AAAAACABACABAAAACABACABAA";
        System.out.println(howMany(stringa4, stringb4));
    }
}
