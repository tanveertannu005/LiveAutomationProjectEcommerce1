package utilities;

import java.util.Date;

public class CommonUtilities {

	 public static String generateBrandNewEmail() {
	        Date date = new Date();
	        return "user" + date.getTime() + "@gmail.com";
	    }

}
