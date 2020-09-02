
import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of Part1 here.
 * 
 * @Shaoqing (your name) 
 * @27/08/2020 (a version number or a date)
 */
public class Part1 {
    public void listExporters(CSVParser parser, String exportOfInterest){
        for(CSVRecord record : parser){
            String export = record.get("Country");
            System.out.println(export);

            if(export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void whoExportsCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
}
