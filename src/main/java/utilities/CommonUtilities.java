package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.mail.Message;

import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class CommonUtilities {

	 public static String generateBrandNewEmail() {
	        Date date = new Date();
	        return "user" + date.getTime() + "@gmail.com";
	    }

	 public static void scrennshots(WebDriver driver, String ScreenshotPath) {
	    	TakesScreenshot ts = (TakesScreenshot) driver;
	        File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
	       
	        try {
	            FileHandler.copy(srcScreenshot,
	                new File(System.getProperty("ScreenshotPath")));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public static boolean compareTwoScreenshots(String string, String string2) {
		// TODO Auto-generated method stub
		return false;
	}

	 private static String getTextFromMessage(Message message) throws Exception {
	        String result = "";
	        if (message.isMimeType("text/plain")) {
	            result = message.getContent().toString();
	        } else if (message.isMimeType("text/html")) {
	            String html = (String) message.getContent();
	            result = Jsoup.parse(html).text();
	        }
	        return result;
	    }
}
