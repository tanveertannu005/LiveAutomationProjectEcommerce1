package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import jakarta.mail.Message;

public class CommonUtilities {

	public static Properties loadPropertiesFiles() {
		Properties prop = new Properties();
		try {
			FileReader fr = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();

		}
	   return prop;
	}
	public static Properties storePropertiesFile(Properties prop) {
		
		try {
			FileWriter fw = new FileWriter(
					System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
			prop.store(fw, "Updated properties file");
		} catch (IOException e) {
			e.printStackTrace();

		}
		return prop;
	}

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
	 public static void takeScreenshot(WebDriver driver, String path) throws IOException {

		    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    File dest = new File(path);

		    FileUtils.copyFile(src, dest);
		}
	 
	 
}
