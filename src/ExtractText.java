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
		System.out.println("What file would you like to convert into a text file?");
		filePath = reader.nextLine();
		

		try {
			file = new File(filePath);
	        parser = new PDFParser(new RandomAccessFile(file, "r")); // update for PDFBox V 2.0

	        parser.parse();
	        cosDoc = parser.getDocument();
	        pdfStripper = new PDFTextStripper();
	        pdDoc = new PDDocument(cosDoc);
	        pdDoc.getNumberOfPages();
	        pdfStripper.setStartPage(0);
	        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
	        text = pdfStripper.getText(pdDoc);
		}
		catch(IOException e) {
			System.out.println("You failed");
		}
		
        System.out.println(text);
        //d:\\programs\\compiler-construction-workspace-RegularExpression_JAM_ALB
        try {
        	Files.writeString(Paths.get(".\\SchoolSchedule.txt"), text);
        }
        catch(IOException e) {
        	System.out.println("You failed spectacularly");
        	e.printStackTrace();
        }

	}




    
}
