package model.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EGNValidator {
	

	public boolean containsTenDigits(String str) {
		  Pattern patt = Pattern.compile(".*?[0-9]{10}.*");
		  Matcher m = patt.matcher(str);
		  return m.matches();
	}

}
