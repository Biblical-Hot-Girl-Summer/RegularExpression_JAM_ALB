/**
* ExtractText simply takes a file input from the user (via a string input of the directory) and converts a pdf
* file into a txt file by extracting all text elements to save in a new file
* @author Jacob Morris Aaron Bone
* @version 1.0
* Compiler Project 3
* CS322 - Compiler Construction
* Fall 2021
*/

package two;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtractText {

	static PDFParser parser;
	static PDFTextStripper pdfStripper;
	static PDDocument pdDoc;
	static COSDocument cosDoc;

	static String text;
	static String filePath;
	static File file;
	static Scanner reader;
    
	public static void main(String[] args) {
		reader = new Scanner(System.in);
		System.out.println("What pdf file would you like to convert into a text file? (Do not forget .pdf)");
		filePath = reader.nextLine();//take user input for file name
		
		try {
			file = new File(filePath);
	        parser = new PDFParser(new RandomAccessFile(file, "r"));
	        parser.parse();
	        cosDoc = parser.getDocument();
	        pdfStripper = new PDFTextStripper();
	        pdDoc = new PDDocument(cosDoc);
	        pdDoc.getNumberOfPages();
	        pdfStripper.setStartPage(0);
	        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
	        text = pdfStripper.getText(pdDoc);//grabs text from pdf and saves to a string
		}
		
		catch(IOException e) {
			System.out.println("You failed");
			e.printStackTrace();
		}
		
        System.out.println(text);//print out the extracted text
        
        try {
        	Files.writeString(Paths.get(".\\SchoolSchedule.txt"), text);// save string to a prenamed file called SchoolSchedule.txt
        }
        
        catch(IOException e) {
        	System.out.println("You failed spectacularly");
        	e.printStackTrace();
        }

	}




    
}
