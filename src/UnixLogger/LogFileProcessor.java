/**
* LogFileProcessor takes a csv file and sets up two hashmaps of IPs and Usernames. It then allows the user to
* print out a result which may be a default result of the size of the hashmaps and numbers of lines read or
* include the contents of the hashmaps.
* @author Jacob Morris Aaron Bone
* @version 1.0
* Compiler Project 3
* CS322 - Compiler Construction
* Fall 2021
*/
package UnixLogger;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFileProcessor {

	static LogFileProcessor processor;
	static HashMap<String, Integer> IPHash = new HashMap<String, Integer>();
	static HashMap<String, Integer> UserHash = new HashMap<String, Integer>();
	static File targetFile;
	static String input;
	static Scanner reader = new Scanner(System.in);
	static int intInput;
	static int lineN;
	static Pattern IPPattern;
	static Pattern UserPattern;
	static Scanner fileReader;
	static String txtFile;
	static Matcher matcher;
	static String match;
	
	/*
	 * Empty constuctor
	 */
	public LogFileProcessor() {
		
	}
	
	/*
	 * Constructor takes in a string for a file name and an int for a flag and defines the regex patterns used
	 * to find IPs and Usernames before preceding to utilize them and giving the user the choosen results
	 */
	public LogFileProcessor(String fileName, int flag) {
		targetFile = new File(fileName);
		IPPattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d");
		UserPattern = Pattern.compile("user\\s\\w*");
		IPSearch();//search log file for IPs
		UserSearch();//search log file for Usernames
		pickAnOption(flag);//takes user input and gives back the results
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("Input a text file before searching for patterns (do not forget .csv):");
		input = reader.nextLine();//takes user input for file name
		whatAreTheOptions();//display what options user can choose and takes user input
		processor = new LogFileProcessor(input, intInput);//creates processor object which takes in the user inputs and ultimately returns what the user choose
		
	}
	
	/*
	 * whatAreTheOptions mostly just displays the options for the user to choose. But also takes the time to
	 * take in the users input for the options.
	 */
	public static void whatAreTheOptions() {
		System.out.println("The following available options are:");
		System.out.println("0. Get the default output");
		System.out.println("1. Get a list of IP addresses and the defualt output");
		System.out.println("2. Get a list of Usernames and the default output");
		System.out.println("Which option would you like to use? Select a number.");
		intInput = reader.nextInt();
		
	}
	
	/*
	 * pickAnOption takes in the flag option choosesn in whatAreTheOptions and goes forward with executing the
	 * wishes of the user and returning the desired outputs
	 */
	public static void pickAnOption(int i) {		
		
		switch(i) {
		case 0:
			System.out.println("Arg[1] == " + intInput );
			defaultOutput();//returns lines counted and size of hashmaps
			break;
		case 1:
			System.out.println("Arg[1] == " + intInput );
			IPContents();//returns contents of IPHash
			defaultOutput();//returns lines counted and size of hashmaps
			break;
		case 2:
			System.out.println("Arg[1] == " + intInput );
			UserContents();//returns contents of UserHash
			defaultOutput();//returns lines counted and size of hashmaps
			break;
		default:
			System.out.println("Arg[1] == " + intInput );
			defaultOutput();//returns lines counted and size of hashmaps
			break;
		}
		
	}
	
	/*
	 * getIPHashmapSize returns the size of IPHash
	 */
	
	public static int getIPHashmapSize() {
		int i = 0;
		i = IPHash.size();
		return i;
	}
	
	/*
	 * getUserHashmapSize returns the size of UserHash
	 */
	
	public static int getUserHashmapSize() {
		int i = 0;
		i = UserHash.size();
		return i;
	}
	
	/*
	 * return number of lines and size of the hashmaps
	 */
	
	public static void defaultOutput() {	
		System.out.println(lineN + " lines in the log file were parsed");
		System.out.println("There are " + getIPHashmapSize() + " unique IP addresses in the log");
		System.out.println("There are " + getUserHashmapSize() + " unique users in the log");
	}
	
	/*
	 * return contents of IPHash
	 */
	
	public static void IPContents() {
		for(String i : IPHash.keySet()) {
			System.out.println(i + ": " + IPHash.get(i));
		}
	}
	
	/*
	 * return contents of UserHash
	 */
	
	public static void UserContents() {
		for(String i : UserHash.keySet()) {
			System.out.println(i + ": " + UserHash.get(i));
		}
	}
	
	/*
	 * look within log file for IPs to store in hashmap with a number representing how many times an IP appears
	 */
	public static void IPSearch() {
		try {
			fileReader = new Scanner(targetFile, "UTF-8");
			lineN = 0;//prepare line number counter to see how many lines are in file
			/*
			 * prepares loop to look through entire file line by line
			 */
			while(fileReader.hasNextLine()) {
				lineN++;
				txtFile = (fileReader.nextLine() + " ");				
				matcher = IPPattern.matcher(txtFile);
				/*
				 * looks through line to find matching patterns
				 */
				while(matcher.find()) {
					match = matcher.group();
					if(IPHash.containsKey(match)) {//if IP is already found just replace value in hashmap with +1 more
						IPHash.put(match, IPHash.get(match) + 1);
					}
					else {//if IP is found for first time put in hashmap with value 1
						IPHash.put(match, 1);
					}
				}
			}					
		}
		
		catch(FileNotFoundException e) {
			System.out.println("You messed up, stupid");
			e.printStackTrace();
		}
	}
	
	/*
	 * look within log file for usernames to store in hashmap with a number representing how many times a username appears
	 */
	public static void UserSearch() {
		try {
			fileReader = new Scanner(targetFile, "UTF-8");
			/*
			 * prepares loop to look through entire file line by line
			 */
			while(fileReader.hasNextLine()) {
				txtFile = (fileReader.nextLine() + " ");				
				matcher = UserPattern.matcher(txtFile);
				/*
				 * looks through line to find matching patterns
				 */
				while(matcher.find()) {
					match = matcher.group();
					if(UserHash.containsKey(match)) {//if username is already found just replace value in hashmap with +1 more
						UserHash.put(match, UserHash.get(match) + 1);
					}
					else {//if username is found for first time put in hashmap with value 1
						UserHash.put(match, 1);
					}					
				}
			}					
		}
		
		catch(FileNotFoundException e) {
			System.out.println("You messed up");
			e.printStackTrace();
		}
		
	}

}
