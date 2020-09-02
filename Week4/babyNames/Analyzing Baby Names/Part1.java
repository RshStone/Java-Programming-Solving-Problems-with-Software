
/**
 * Write a description of Part1 here.
 * 
 * @Shaoqing (your name) 
 * @29/08/2020 (a version number or a date)
 */
import java.io.*;
import org.apache.commons.csv.*;
import edu.duke.*;
public class Part1 {
    public void totalParticularBirths(FileResource fr, String name){
        int totalNumberOfGirls = 0;
        int totalNumberOfBoys = 0;
        int totalNumber = 0;
        CSVParser parser = fr.getCSVParser(false);
        //get into every row of CSVRecord
        for(CSVRecord currentRow: parser){
            //get the name of currentRow
            String currentName = currentRow.get(0);
            //compare it with name
            if(currentName.equals(name)){
                String gender = currentRow.get(1);
                if(gender.equals("F")){
                    totalNumberOfGirls = Integer.parseInt(currentRow.get(2));
                }
                else{
                    totalNumberOfBoys = Integer.parseInt(currentRow.get(2));
                }
            }
            
        }
        totalNumber = totalNumberOfGirls + totalNumberOfBoys;
        System.out.println("The number of the girl name " + name 
        + " is " + totalNumberOfGirls);
        System.out.println("The number of the boy name " + name 
        + " is " + totalNumberOfBoys);
        System.out.println("The number of the total name " + name 
        + " is " + totalNumber);
    }
    
    public void totalBirths(FileResource fr){
        int totalNumberOfGirls = 0;
        int totalNumberOfBoys = 0;
        int totalNumber = 0;
        int numOfGirlsName = 0;
        int numOfBoysName = 0;
        CSVParser parser = fr.getCSVParser(false);
        //get into every row of CSVRecord
        for(CSVRecord currentRow: parser){
            String gender = currentRow.get(1);
            if(gender.equals("F")){
                totalNumberOfGirls += Integer.parseInt(currentRow.get(2));
                numOfGirlsName++;
            }
            else{
                totalNumberOfBoys += Integer.parseInt(currentRow.get(2));
                numOfBoysName++;
            }
            
        }
        totalNumber = totalNumberOfGirls + totalNumberOfBoys;
        System.out.println("The number of the girl" + " is " + totalNumberOfGirls);
        System.out.println("The number of the boy" + " is " + totalNumberOfBoys);
        System.out.println("The number of the total name"+ " is " + totalNumber);
        System.out.println("The number of the boy name " 
        + " is " + numOfBoysName);
        System.out.println("The number of the girl name "
        + " is " + numOfGirlsName);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testTotalParticularBirths(){
        FileResource fr = new FileResource();
        //totalParticularBirths(fr, "Emily");
        totalParticularBirths(fr, "Frank");
    }
    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource();
        int rank = 0;
        for(CSVRecord currentRow: fr.getCSVParser(false)){
            if(currentRow.get(1).equals(gender)){
                rank++;
                if(currentRow.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    public int getRank2(int year, String name, String gender, FileResource fr) {
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank(){
        //int rank = getRank(2012, "Mason", "M");
        //System.out.println(rank);
        int rank = getRank(1960, "Emily", "F");
        System.out.println(rank);
    }
    public String getName(int year, int rank, String gender){
        //two if statement should have orders, the first is to judge 
        //gender and at this base, we make a second judgement 
        FileResource fr = new FileResource();
        int currentRank = 0;
        String name = "";
        for(CSVRecord currentRow: fr.getCSVParser(false)){
            if(currentRow.get(1).equals(gender)){
                currentRank++;
                if(currentRank == rank){
                name = currentRow.get(0);
                return name;
            }
            }
        }
        return "NO NAME";
    }
    public void testGetName(){
        //String name = getName(1982, 450, "M");
        //System.out.println(name);
        String name = getName(1980, 350, "F");
        System.out.println(name);
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        //find the rank of the current name
        int rank = getRank(year, name, gender);
        String newName = getName(year, rank, gender);
        if(!newName.equals("NO NAME")){
            System.out.println(name + "born in " + year + " would be " + newName + " in " + newYear);
        }
        else{
            System.out.println(newName);
        }
        //open another file and find the rank of the the same popular name
    }
    public void testWhatIsNameInYear(){
        //whatIsNameInYear("Isabella", 2012, 2014, "F");
        whatIsNameInYear("Susan", 1972, 2014, "F");
        //whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    public int yearOfHighestRank(String name, String gender){
        //open many files
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int highestYear = 0;
        for(File fr: dr.selectedFiles()){
            FileResource f = new FileResource(fr);
            String fileName = fr.getName();
            String strYear = fileName.substring(3,7);
            int year = Integer.parseInt(strYear);
            int currentRank = getRank2(year, name, gender,f);
            if(currentRank != -1){
                if(highestRank == 0){
                    highestRank = currentRank;
                    highestYear = year;
                }
                if(highestRank > currentRank){
                    highestRank = currentRank;
                    highestYear = year;
                }
            }
        }
        return highestYear;
    }
    public void testYearOfHighestRank(){
        //int highestRank = yearOfHighestRank("Mason", "M");
        //System.out.println("The highestRank is " + highestRank);
        int highestRank = yearOfHighestRank("Mich", "M");
        System.out.println("The highestRank is " + highestRank);
    }
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int count = 0;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            String strYear = fileName.substring(3,7);
            int year = Integer.parseInt(strYear);
            int currentRank = getRank2(year, name, gender,fr);
            if(currentRank != -1){
                count++;
                totalRank += currentRank;
            }
        }
        if(count == 0)return -1;
        double averageRank = (double)totalRank/count;
        return averageRank;
    }
    
    public void testGetAverageRank(){
        //double averageRank = getAverageRank("Mason", "M");
        //double averageRank = getAverageRank("Jacob", "M");
        //System.out.println("The getAverageRank is " + averageRank);
        double averageRank = getAverageRank("Robert", "M");
        System.out.println("The getAverageRank is " + averageRank);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource();
        int nameRank = getRank(year, name, gender);
        int rank = 0;
        int totalBirths = 0;
        for(CSVRecord currentRow: fr.getCSVParser(false)){
            String currentGender = currentRow.get(1);
            if(currentGender.equals(gender)){
                rank++;
                if(rank < nameRank){
                    int birth = Integer.parseInt(currentRow.get(2));
                    totalBirths += birth;
 
                }
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int total = getTotalBirthsRankedHigher(1990, "Emily", "F");
        System.out.println(total);
        //int total = getTotalBirthsRankedHigher(1990, "Drew", "M");
        //System.out.println(total);
    }
}
