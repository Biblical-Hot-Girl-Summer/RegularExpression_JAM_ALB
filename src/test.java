import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	//static Pattern pattern;
	static Matcher matcher;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//pattern.compile("Hello");
		//matcher = pattern.matcher("Hello world and Hellsing. Ahh. An. Something. the The the");
		//while(matcher.find()) {
			//count++;
		//	System.out.println(matcher.group());
		//}
		
		//Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
        //Matcher matcher = pattern.matcher("She isnt Helsing either Kelsing. Transylvania.");
        String pat = ("\\b[aA]\\b");
        Pattern pattern3 = Pattern.compile(pat);
        Matcher matcher = pattern3.matcher(" a She isnt Helsing nor Kelsing. Transylvania.");
        boolean matchFound = matcher.find();
        if(matchFound) {
            System.out.println("Match found");
        } else {
            System.out.println("Match not found");
        }
         

	}

}
