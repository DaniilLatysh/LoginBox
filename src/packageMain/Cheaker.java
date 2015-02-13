package packageMain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cheaker {

	public static boolean name(String text) {
		Pattern patternMail = Pattern.compile("[a-zA-Z]{1}([a-zA-Z0-9._-]){1,2}");
		Matcher matcherMail = patternMail.matcher(text);
		  while (matcherMail.find()) {    	
		      matcherMail.group();
		      return true;
		    }
		return false;
		      
	}

	public static boolean pass(String text) {
		Pattern patternPass = Pattern.compile("[a-zA-Z0-9]{4}");
		Matcher matcherPass = patternPass.matcher(text);
		 while (matcherPass.find()) {
		      matcherPass.group();
		      return true;
		    }
		return false;
	}
	
}
