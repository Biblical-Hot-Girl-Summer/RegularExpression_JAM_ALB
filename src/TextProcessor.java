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
	
	
	public TextProcessor(){
		
	}

	public static void main(String[] args) {
		
		try {
			System.out.println("Input a text file before searching for patterns (do not forget .txt):");
			input = reader.nextLine();
			
			targetFile = new File(input);
			//whatAreThePatterns();
			//pickAPattern();
			System.out.println("What pattern would you like to use?");
			patternInput = reader.nextLine();
			
			pattern = Pattern.compile(patternInput, /*Pattern.MULTILINE |*/ Pattern.CASE_INSENSITIVE);
			reader.close();
			fileReader = new Scanner(targetFile, "UTF-8");
			lineN = 1;
			while(fileReader.hasNextLine()) {
				
				book = (fileReader.nextLine() + " ");
				//System.out.println(book);
				
				matcher = pattern.matcher(book /*"An a ahhh words something an a */);
				//System.out.println(matcher.find());
				while(matcher.find()) {
					count++;
					System.out.println("Line Number " + lineN + ": " + matcher.group());
					//System.out.println(book);
				}
				lineN++;
				
			}
			System.out.println("Number of matches: " + count);
			System.out.println("Pattern used: " + patternInput);
			fileReader.close();
			/*if(pattern != null) {
				matcher = pattern.matcher(book /*"hi Transylvania hello"*//*);
				//System.out.println(matcher.find());
				while(matcher.find() == true) {
					count++;
					System.out.println(matcher.group());
				}
				System.out.println(count);
				System.out.println(patternInput);
			}
			else {
				System.out.println("it's super null");
			}*/
			
			
		}
		catch(FileNotFoundException e) {
			System.out.println("You messed up, stupid");
			e.printStackTrace();
		}
		

		
		

	}
	/*
	public static void pickAPattern() {
		System.out.println("Which pattern would you like to use? Select a number.");
		intInput = reader.nextInt();
		
		switch(intInput) {
		case 1:
			patternInput = "[ |\\t|\"](the )|(an )|(a )";
			break;
		case 2:
			patternInput = "([ |\\t|\"]|[])(Mina Harker)|(Mrs. Harker)";
			break;
		case 3:
			patternInput = "[^.]*(Transylvania)[^.]*";
			break;
		case 4:
			patternInput = "to (?<=\\bto\\s)(\\w+)";
			break;
		case 5:
			patternInput = "(?!Godalming|Helsing)(\\b(\\w+ing)\\b)";
			break;
		default:
			System.out.println("Incorrect input, try again you fool.");
			pickAPattern();
			break;
		}
		
	}*/
	
	/*public static void whatAreThePatterns() {
		System.out.println("The following available patterns are:");
		System.out.println("1. Find the articles 'a', 'an', or 'the'");
		System.out.println("2. Find the phrases 'Mina Harker' or 'Mrs. Harker'");
		System.out.println("3. Find the phrases containing 'Transylvania'");
		System.out.println("4. Find the infinitive phrases including the word 'to' such as 'to hide'");
		System.out.println("5. Find the words ending in -ing except Godalming and Helsing");
	}*/
}
