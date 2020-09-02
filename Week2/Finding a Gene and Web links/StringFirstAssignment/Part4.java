
/**
 * 在这里给出对类 Part4 的描述。
 * 
 * @Shaoqing（你的名字）
 * @19/08/2020（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.io.*;

public class Part4
{
    public static void main(String [] args){
        readURL();
    }
    public static void readURL()
    {
        //
        /*
         * new URLResource("http://www.something.com/file.ext"), uses the given address to download the referenced file
         * I don't understand URLResource's function. what does download the referenced file mean? 
         * I mean you can download all the content of the webpage in () or 
        we can get another thing.  I try to copy the content of this webpage in word and I use find function but there
        is no youtube. So I feel confused now.
        */
        URLResource file = new  URLResource("https://www.dukelearntoprogram.com//course2/files.php");         
        for (String item : file.words()) 
        {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if(pos != -1){
                int beg = item.lastIndexOf("\"",pos);
                int end = item.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1,end));
            }
        }
    }
}