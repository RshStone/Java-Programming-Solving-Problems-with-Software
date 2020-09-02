
/**
 * Write a description of Part1 here.
 * 
 * @Shaoqing (your name) 
 * @28/08/2020 (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class Part1 {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
    }
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record: parser){
            String country1 = record.get("Country");
            if(country1.contains(country)){
                String result = country1 + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return result;
            }
        }
        return null;
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem) ){
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record: parser){
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()){
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }
    public void Test(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //parser = fr.getCSVParser();
        //System.out.println(numberOfExporters(parser, "cocoa"));
        //parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
                            
    }
}
