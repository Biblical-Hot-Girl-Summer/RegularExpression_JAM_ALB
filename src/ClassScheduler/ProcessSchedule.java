/**
* ProcessSchedule allows the user, after inputing a file directory, to choose between three predefined regex
* patterns in which the first two will save to a file and print out the results at the same time. The last
* pattern will just print the class codes and the number of unique classes within.
* @author Jacob Morris Aaron Bone
* @version 1.0
* Compiler Project 3
* CS322 - Compiler Construction
* Fall 2021
*/

package ClassScheduler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessSchedule {
	
	static Scanner reader = new Scanner(System.in);
	static String input;
	static int intInput;
	static File targetFile;
	static Scanner fileReader;
	static Pattern pattern;
	static Matcher matcher;
	static String txtFile;
	static int count;
	static String patternInput;
	static int lineN;
	static String resultString = "";//just making sure string doesn't hold a null
	static String previousString = "";//just making sure string doesn't hold a null

	public static void main(String[] args) {	
		
		System.out.println("Input a text file before searching for patterns (do not forget .txt):");
		input = reader.nextLine();//take user input for file name
		targetFile = new File(input);
		whatAreThePatterns();//give user options for patterns
		pickAPattern();//take user input for pattern option and runs them
		reader.close();
		System.out.println("Pattern used: " + patternInput);//display chosen pattern
		fileReader.close();

	}
	
	/*
	 * pickAPattern takes user input and defines a regex pattern and calls the appropriate method to utilize
	 * defined pattern
	 */
	
	public static void pickAPattern() {
		
		System.out.println("Which pattern would you like to use? Select a number.");
		intInput = reader.nextInt();
		switch(intInput) {
		case 1:
			patternInput = "[A-Z]+(-)+(\\d{3}|(\\d{3}[A-Z]))+(-)+(\\d{2}|([A-Z]))+(?:(?!\\d).)* +(\\d)";
			option1();//find course names and subjects
			break;
		case 2:
			patternInput = "[A-Z]+(-)+(\\d{3}|(\\d{3}[A-Z]))+(-)+(\\d{2}|([A-Z]))+(?:(?!\\d).)* +(\\d)+( Open| CLOSED)+ (\\d{2}|\\d{1})+ (\\d{2}|\\d{1})";
			option2();//find course availabilities
			break;
		case 3:
			patternInput = "[A-Z]+(?=(-)+(\\d{3}|(\\d{3}[A-Z]))+(-)+(\\d{2}|([A-Z])))";
			option3();//count number of unique classes
			break;
		default:
			System.out.println("Incorrect input, try again you fool.");
			pickAPattern();//call itself again to get a correct user input
			break;
		}
	}
	
	/*
	 * whatAreThePatterns merely shows what options the user can input for the pickAPattern()
	 */
	
	public static void whatAreThePatterns() {
		System.out.println("The following available patterns are:");
		System.out.println("1. Find the Course names and subjects and save to CourseNamesAndSubjects.txt");
		System.out.println("2. Find the status of a course's availability and save to CourseAvailibilites.txt");
		System.out.println("3. Count the number of unique classes per program code");
	}
	
	/*
	 * option1 uses regex pattern to find course names and subjects and then return the results in console
	 * and save the information in CourseNamesAndSubject.txt
	 */
	
	public static void option1() {
		
		try {
			pattern = Pattern.compile(patternInput, Pattern.CASE_INSENSITIVE);//takes in pattern and sets flag to ignore cases of letters
			fileReader = new Scanner(targetFile, "UTF-8");
			lineN = 1;//prepares linenumber count
			
			/*
			 * prepares loop to read every line of txt file
			 */
			while(fileReader.hasNextLine()) {	
				txtFile = (fileReader.nextLine() + " ");
				matcher = pattern.matcher(txtFile);
				
				/*
				 * prepares loop to look in the line for all matching patterns and save to string
				 */
				while(matcher.find()) {
					System.out.println("Line Number " + lineN + ": " + matcher.group());
					resultString += (matcher.group() + "\n");//adds matching phrases to string to ultimately be saved to txt file
				}
				
				lineN++;//count up for line numbers
			}	
		}
		
		catch(FileNotFoundException e) {
			System.out.println("You messed up, stupid");
			e.printStackTrace();
		}
		
		saveToFile("CourseNamesAndSubjects", resultString);//creates txt file
	}
	
	/*
	 * option2 uses regex pattern to find course availabilities and print result in console and save to
	 * CourseAvailibilites.txt
	 */
	public static void option2() {
		
		try {
			pattern = Pattern.compile(patternInput, Pattern.CASE_INSENSITIVE);//takes in pattern and sets flag to ignore cases of letters
			fileReader = new Scanner(targetFile, "UTF-8");
			lineN = 1;//prepares line number count
			
			/*
			 * prepares loop to read every line of txt file
			 */
			while(fileReader.hasNextLine()) {			
				txtFile = (fileReader.nextLine() + " ");				
				matcher = pattern.matcher(txtFile);
				
				/*
				 * prepares loop to look in the line for all matching patterns and save to string
				 */
				while(matcher.find()) {
					System.out.println("Line Number " + lineN + ": " + matcher.group());
					resultString += (matcher.group() + "\n");//adds matching phrases to string to ultimately be saved to txt file
				}
				
				lineN++;//count up for line numbers	
			}		
		}
		
		catch(FileNotFoundException e) {
			System.out.println("You messed up, stupid");
			e.printStackTrace();
		}
		
		saveToFile("CourseAvailibilities", resultString);//creates txt file
	}
	
	/*
	 * option3 takes a regex code to find a pattern of unique courses, but doesn't actually save to any file.
	 * Results are printed on console with a number next to every course code 
	 */
	
	public static void option3() {
		try {
			pattern = Pattern.compile(patternInput, Pattern.CASE_INSENSITIVE);//takes in pattern and sets flag to ignore cases of letters			
			fileReader = new Scanner(targetFile, "UTF-8");
			lineN = 1;//prepares line number count
			
			/*
			 * Prepares loop to read through file line by line
			 */
			while(fileReader.hasNextLine()) {			
				txtFile = (fileReader.nextLine() + " ");
				String match;	
				matcher = pattern.matcher(txtFile);
				
				/*
				 * Prepares loop to read through line and find matching phrase
				 */
				while(matcher.find()) {
					match = matcher.group();
					if(match.equals(previousString)) {//if the class code is the same as the class code from last line, increase count
						count++;
					}
					else if(match.equals("")) {//if it's the first line, set count to 1 and set up class code for future comparisons
						count=1;
						previousString = match;
					}
					else {//if last line had different class code, set previous string to current class code and restart count to 1. Print result of last count
						System.out.println(previousString + ": " + count);
						previousString = match;
						count = 1;
					}
				}
			}
			
			System.out.println(previousString + ": " + count);//print result of final classcode count
		}
		
		catch(FileNotFoundException e) {
			System.out.println("You messed up, you goober");
			e.printStackTrace();
		}	
	}
	
	/*
	 * saveToFile saves string to a txt file
	 */
	public static void saveToFile(String fileName, String text) {
		
		try {
        	Files.writeString(Paths.get(".\\" + fileName + ".txt"), text);
        }
		
        catch(IOException e) {
        	System.out.println("You failed spectacularly");
        	e.printStackTrace();
        }
	}
}
