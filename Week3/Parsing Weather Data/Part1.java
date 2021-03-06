
/**
 * Write a description of Part1 here.
 * 
 * @Shaoqing(your name) 
 * @Augest28,2020 (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

/*
 * find the ​ coldest​day of the year and other interesting facts about the temperature and humidity in a day. 
 * To test your program, you will use the ​ nc_weather​data folder that has a folder for each year;                
 */
public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord record: parser){
            if(coldestSoFar == null){
                coldestSoFar = record;
            }
            else{
                if(!record.get("TemperatureF").equals("-9999")){
                     if(Double.parseDouble(record.get("TemperatureF"))
                     < Double.parseDouble(coldestSoFar.get("TemperatureF"))){
                         coldestSoFar = record;
                }
                }
            }
        }
        return coldestSoFar;
    }
    /*bug: in this situation, we need to create smallestt CSVRecord so that we can avoid some annoying bugs,
    also it's very easy to understand
    */
    public void testColdHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temp was " + 
        smallest.get("TemperatureF") + "at "
        + smallest.get("DateUTC"));
    }
    public String fileWithColdestTemperature(){
        //we can open many files
        DirectoryResource dr = new DirectoryResource();
        //we use  smallestSoFar to store the record of the coldest temperature now
        CSVRecord smallestSoFar = null;
        // we want coldestFile's name
        double smallest = 99.9;
        String coldestFile = "";
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //we get the CSVParser of the file
            CSVParser parser = fr.getCSVParser();
            // we get the current coldest record
            CSVRecord current = coldestHourInFile(parser);
            // we need make a comparasion between smallestSoFar and current  coldest record
            
            // we want to get the record which has the smaller temperature;
            //smallestSoFar = getTwoOf(smallestSoFar, current);
            //smallest = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            // if the temperature of current coldest record less than smallestSoFar, then get the File's name
            if(Double.parseDouble(current.get("TemperatureF")) < smallest ){
                coldestFile = f.getPath();
                smallest = Double.parseDouble(current.get("TemperatureF"));
            }
        }
        return coldestFile;
    }
    /*
     * call the method ​coldestHourInFile​to determine the coldest temperature
     * on that day.               
     */
    public void testFileWithColdestTemperature(){
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("the name of coldest file is " + coldestFileName);
        FileResource fr = new FileResource(coldestFileName);
        CSVRecord smallestTemp = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was: " + smallestTemp.get("TemperatureF"));
        System.out.println("All of the temperatures on the coldest day were:");
        for (CSVRecord current : fr.getCSVParser()) {
            System.out.println(current.get("DateUTC") + ": " + current.get("TemperatureF"));
        }
    }
    public CSVRecord lowestHumidityInFile               (CSVParser parser){
        CSVRecord lowestRecord = null;
        
        for(CSVRecord record: parser){
            String humidity = record.get("Humidity");
            if(lowestRecord == null){
                lowestRecord = record;
            }
            else{
                if(!humidity.equals("N/A")){
                    int currentHumidity = Integer.parseInt(humidity);
                    int lowestHumidity = Integer.parseInt(lowestRecord.get("Humidity"));
                    if(currentHumidity < lowestHumidity){
                        lowestRecord = record;
                        lowestHumidity = currentHumidity;                
                    }
                }
            } 
        }
        return lowestRecord;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestRecord = lowestHumidityInFile(parser);
        
        System.out.println("The lowest humidity is " + lowestRecord.get("Humidity")
        + "at " + lowestRecord.get("DateUTC"));

        
    }
    public String lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        String f = "";
        for(File file: dr.selectedFiles()){
            FileResource fr = new FileResource(file);
            //getCSVParser
            CSVParser parser = fr.getCSVParser();
            //get the lowest humidity of this file  lowestHumidity1
            CSVRecord currentLowestHumidity = lowestHumidityInFile(parser);
            //compare it with lowestHumidity  
            if(lowestHumidity == null){
                lowestHumidity = currentLowestHumidity;
            }
            else{
                if(Double.parseDouble(currentLowestHumidity.get("Humidity")) < 
                Double.parseDouble(lowestHumidity.get("Humidity"))){
                    lowestHumidity = currentLowestHumidity;
                    f = file.getPath();
                }
            }
            //if less than it, lowestHumidity updates
            //lowestHumidityInFle()
        } 
        return f;
    }
    public void testLowestHumidityInManyFiles(){
        //get file name of lowestHumidity
        String fileName = lowestHumidityInManyFiles(); 
        System.out.println("The lowest humidity in files is " + fileName);
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        
        //print someinformation of lowestRecord
        CSVRecord lowestRecord = lowestHumidityInFile(parser);
        System.out.println("The lowest humidity is " + lowestRecord.get("Humidity")
        + "at " + lowestRecord.get("DateUTC"));
        
        //get all the humidity data of that day with DataUTC
        parser = fr.getCSVParser();
        for (CSVRecord current : parser) {
            System.out.println(current.get("DateUTC") + ": " + current.get("Humidity"));
        }
        System.out.println();
    }
      public double averageTemperatureInFile(CSVParser parser) {
        double averageTemperature = 0.0;
        int numberOfLines = 0;
        for (CSVRecord currentRow : parser) {
            double currentRowTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            averageTemperature = averageTemperature + currentRowTemp;
            numberOfLines += 1;
        }
        averageTemperature = averageTemperature / numberOfLines;
        return averageTemperature;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avgTemp);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double result = 0.0;
        int numberOfRecordedTemps = 0;
        for (CSVRecord currentRow : parser) {
            if (!currentRow.get("Humidity").equals("N/A") && Integer.parseInt(currentRow.get("Humidity")) >= value) {
                double currentRowTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                result += currentRowTemp;
                numberOfRecordedTemps += 1;
            }
        }
        result = result / numberOfRecordedTemps;
        return result;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, value);
        if (Double.isNaN(avgTemp)) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        }
    }
}
