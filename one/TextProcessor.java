/**
 * Regex Codes: [ |\t|"](the )|(an )|(a ), (Mina Harker)|(Mrs. Harker), [^.?,;]*(Transylvania)[^.?,;]*, to (?<=\bto\s)(\w+), (?!Godalming|Helsing)(\b(\w+ing)\b)
 */
/**
* TextProcessor takes a txt file and allows the user to input any regex code to receive the returns from a regex
* expression. The line number of the return is noted as well as the amount of returns.
* @author Jacob Morris Aaron Bone
* @version 1.0
* Compiler Project 3
* CS322 - Compiler Construction
* Fall 2021
*/

package one;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class TextProcessor {
	
	static Scanner reader = new Scanner(System.in);
	static String input;
	static int intInput;
	static File targetFile;
	static Scanner fileReader;
	static Pattern pattern;
	static Matcher matcher;
	static String book;
	static int count = 0;
	static String patternInput;
	static int lineN;
	
	/**
	 * Empty default constructor
	 */
	public TextProcessor(){
		
	}

	public static void main(String[] args) {
		
		try {
			System.out.println("Input a text file before searching for patterns (do not forget .txt):");
			input = reader.nextLine();//take user input for file name			
			targetFile = new File(input);
			System.out.println("What pattern would you like to use?");
			patternInput = reader.nextLine();//take user input for regex pattern
			pattern = Pattern.compile(patternInput, Pattern.CASE_INSENSITIVE);//put regex pattern into pattern object. Flag set to ignore case of letters
			reader.close();
			fileReader = new Scanner(targetFile, "UTF-8");
			lineN = 1;//begin line number counter
			
			/*
			 * Begin loop to run through entire file line by line
			 */
			while(fileReader.hasNextLine()) {
				book = (fileReader.nextLine() + " ");
				matcher = pattern.matcher(book);
				
				/*
				 * Begin loop to search line until all patterns found, if any
				 */
				while(matcher.find()) {
					count++;//add 1 for every matching pattern found
					System.out.println("Line Number " + lineN + ": " + matcher.group());
				}
				
				lineN++;//add 1 to count number of lines
			}
			
			System.out.println("Number of matches: " + count);//printout total matches
			System.out.println("Pattern used: " + patternInput);//printout pattern used
			fileReader.close();
		}
		
		catch(FileNotFoundException e) {
			System.out.println("You messed up, stupid");
			e.printStackTrace();
		}
	}
}
