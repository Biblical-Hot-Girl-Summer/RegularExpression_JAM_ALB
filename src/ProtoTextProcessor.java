import java.util.*;
import java.util.regex.*;
import java.io.*;


public class ProtoTextProcessor {

    //Universal scanner
    public static Scanner scan = new Scanner(System.in);

    public void printFromFile(String filename){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public String storeFromFile(String filename){
        String str = "";
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                /*
                String data = myReader.nextLine();
                System.out.println(data);
                 */
                str = str + myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        /*
        String pat = ("\\b[aA]\\b");
        Pattern pattern3 = Pattern.compile(pat);
         */
        return str;
    }
    public int patternFinding(String filename, String command){
        int count = 0;

        Pattern pattern = Pattern.compile(command);
        Matcher matcher;
        boolean matchFound;
        String data;

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                data = myReader.nextLine();
                matcher = pattern.matcher(data);
                matchFound = matcher.find();

                if(matchFound) {
                    System.out.println(data);
                    count++;
                    //System.out.println("Match found");
                } else {
                    //System.out.println("Match not found");
                }

                //
                //Lese den neste linja og sjekke for mønster!
                //

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        println("\nThe number of matches was: " + count);
        return count;
    }
    public int patternFinding2(String checker, String command){
        int count = 0;

        Pattern pattern = Pattern.compile(command);
        Matcher matcher = pattern.matcher(checker);
        boolean matchFound = matcher.find();
        if(matchFound) {
            System.out.println("Match found");
        } else {
            System.out.println("Match not found");
        }


        return count;
    }
    //searchInFile
    /*  public void searchInFile(String filename, String command){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String pat = ("\\b[aA]\\b");
        Pattern pattern3 = Pattern.compile(pat);

    }


     */

    public int instanceFinder(String filename, String command){
        int count = 0;

        Pattern pattern = Pattern.compile(command);
        Matcher matcher;
        boolean phraseFound;
        boolean wordFound;
        boolean skip;
        String data;
        String line;

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                skip = false;

                matcher = pattern.matcher(line);
                phraseFound = matcher.find();
                if (phraseFound) {
                    System.out.println(line);
                    skip = true;
                    count++;
                }
                Scanner scanWord = new Scanner(line);
                while(scanWord.hasNext()){
                    data = scanWord.next();
                    matcher = pattern.matcher(data);
                    wordFound = matcher.find();

                    if (wordFound) {
                        if(!skip) {
                            System.out.println(line);
                            count++;
                        }else {
                            skip = false;
                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        println("\nThe number of matches was: " + count);
        return count;
    }
    public int instanceFindermk2(String filename, String command){
        int count = 0;

        Pattern pattern = Pattern.compile(command);
        Matcher matcher;
        String line;

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                matcher = pattern.matcher(line);
                while(matcher.find()){
                    System.out.println(line);
                    count++;
                }
                //println();

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        println("\nThe number of matches was: " + count);
        return count;
    }
    public int instanceFindermk3(String filename, String command){
        int count = 0;

        Pattern pattern = Pattern.compile(command);
        Matcher matcher;
        String line;
        int lineNum = 0;

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                lineNum++;
                line = myReader.nextLine();
                matcher = pattern.matcher(line);
                //println("Line number " + lineNum);
                while(matcher.find()){

                    /*
                    println(matcher.start());
                    println(matcher.end());
                    */
                    println(line.substring(matcher.start(),matcher.end()));

                    count++;
                }
                //println();

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        println("\nThe number of matches was: " + count);
        return count;
    }

    public void hoved(){

        print("Please type the file name: ");
        String filename = "C:\\Users\\Bruker\\IdeaProjects\\ByteCodeTester\\src\\" + scan.nextLine() + ".txt";
        print("You chose this file: ");
        println(filename);
        println();

        println("Now, please type in a command.");
        println("Here are 5 RegEx examples: ");
        println("-----------------------------------------------------------");
        println("\\b[aA]\\b|\\b[Aa]n\\b|\\b[Tt]he\\b");
        println("(\\b[Mm]rs\\.|\\b[Mm]ina)\\sHarker\\b");
        //println(".*[Tt]ransylvania.*\\.{1}");
        println("\\w*Transylvania\\w*");
        println("\\b[Tt]o\\s+\\w+");
        println("\\w+(?<![Gg]odalm|[Hh]els)ing\\b");
        println("-----------------------------------------------------------");
        print("Please type the command here: ");
        String command = scan.nextLine();
        println();

        instanceFindermk2(filename, command);
        instanceFindermk3(filename, command);
        println("\n\nDone!");

    }

    public static void main(String[]args) {
        ProtoTextProcessor p = new ProtoTextProcessor();

        String EnkelTekst = "C:\\Users\\Bruker\\IdeaProjects\\ByteCodeTester\\src\\EnkelTekstFil.txt";
        String DraculaSample = "C:\\Users\\Bruker\\IdeaProjects\\ByteCodeTester\\src\\DraculaSample.txt";


        String patExample = ("\\b[aA]\\b");
        String pat1 = ("\\b[aA]\\b|\\b[Aa]n\\b|\\b[Tt]he\\b");
        String pat2 = "(\\b[Mm]rs\\.|\\b[Mm]ina)\\sHarker\\b";
        String pat3 = (".*[Tt]ransylvania.*\\.{1}");
        String pat3mk2 = "\\w*Transylvania\\w*";
        String pat3mk3 = "(?<=^|\\s)[A-Z][^!?.]*(Transylvania\\s*)[^!?.]*(?=\\.|\\!|\\?)";
        String pat4 = "\\b[Tt]o\\s+\\w+";
        String pat5 = "\\w+(?<![Gg]odalm|[Hh]els)ing\\b";
        //p.patternFinding(filename, pat);

        //p.instanceFinder(filename, pat5);
        //p.instanceFinderMark2(EnkelTekst, pat3mk2);
        //p.instanceFinderMark2(DraculaSample, patExample);
        //p.instanceFindermk3(EnkelTekst, pat3mk2);

        //Hovedmetoden
        p.hoved();

        /*
        String s = "abcde";
        String l = s.substring(2, s.length());
        p.println(l);
         */



        //Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
        //Pattern pattern2 = Pattern.compile("\b[aA]\b");
        //Pattern pattern2 = Pattern.compile(" A ", Pattern.CASE_INSENSITIVE);
        //Matcher matcher = pattern.matcher("She isnt Helsing either Kelsing. Transylvania.");
        /*
        String pat = (".*[Tt]ransylvania.*\\.{1}");
        Pattern pattern3 = Pattern.compile(pat);
        Matcher matcher = pattern3.matcher("She isnt Helsing either Kelsing. Transylvania.");
        boolean matchFound = matcher.find();
        if(matchFound) {
            System.out.println("Match found");
        } else {
            System.out.println("Match not found");
        }
         */





    }


    //Printer methods
    public void print(Object o){
        System.out.print(o);
    }
    public void println(Object o){
        System.out.println(o);
    }
    public void print(){
        System.out.print("");
    }
    public void println(){
        System.out.println("");
    }

}

//Regex example
/*
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher("Visit W3Schools!");
    boolean matchFound = matcher.find();
    if(matchFound) {
      System.out.println("Match found");
    } else {
      System.out.println("Match not found");
    }
  }
}
// Outputs Match found

         */


//Read from file
/*

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
  public static void main(String[] args) {
    try {
      File myObj = new File("filename.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
 */
