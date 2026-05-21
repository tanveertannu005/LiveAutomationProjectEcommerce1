package utilities;

import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

import jakarta.mail.Message;





public class CommonUtilities {
	
	public static final int MIN_TIME=3;
	public static final int AVERAGE_TIME=10;
	public static final int MAX_TIME=30;
	

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
	 
	 public static int ConvertToInteger(String text) {
		 return Integer.parseInt(text);
		 
	 }
	 
	 public static boolean areItemsInListAreInAscendingorde(List<String> list) {
		 List<String> sortedList=list;
		 Collections.sort(sortedList);
		 return list.equals(sortedList);
	 }
	 
	 public static void waitForSeconds(int milliseconds) {
		 try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public static Object[][] getTestData(MyXLSReader xls_received, String testName, String sheetName) {

			MyXLSReader xls = xls_received;

			String testCaseName = testName;

			String testDataSheet = sheetName;

			int testStartRowNumber = 1;

			while (!(xls.getCellData(testDataSheet, 1, testStartRowNumber).equals(testCaseName))) {

				testStartRowNumber++;

			}

			int columnStartRowNumber = testStartRowNumber + 1;
			int dataStartRowNumber = testStartRowNumber + 2;

			int rows = 0;
			while (!(xls.getCellData(testDataSheet, 1, dataStartRowNumber + rows).equals(""))) {

				rows++;

			}

			// Total number of columns in the required test
			int columns = 1;

			while (!(xls.getCellData(testDataSheet, columns, columnStartRowNumber).equals(""))) {

				columns++;

			}

			Object[][] obj = new Object[rows][1];

			HashMap<String, String> map = null;

			// Reading the data in the test
			for (int i = 0, row = dataStartRowNumber; row < dataStartRowNumber + rows; row++, i++) {

				map = new HashMap<String, String>();

				for (@SuppressWarnings("unused")
				int j = 0, column = 1; column < columns; column++, j++) {

					String key = xls.getCellData(testDataSheet, column, columnStartRowNumber);

					String value = xls.getCellData(testDataSheet, column, row);

					map.put(key, value);

				}

				obj[i][0] = map;

			}

			return obj;

		}

	 public static ExtentReports getExtentReport() {

			ExtentReports extentReport = new ExtentReports();

			File extentReportFile = new File(System.getProperty("user.dir") + "\\Reports\\ExtentReport.html");

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
			ExtentSparkReporterConfig sparkConfig = sparkReporter.config();
			sparkConfig.setReportName("Tutorials Ninja Test Automation Results");
			sparkConfig.setDocumentTitle("TN Results");

			extentReport.attachReporter(sparkReporter);
			extentReport.setSystemInfo("OS", System.getProperty("os.name"));
			extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
			extentReport.setSystemInfo("Username", System.getProperty("user.name"));
			extentReport.setSystemInfo("Selenium WebDriver Version", "4.35.0");

			return extentReport;

		}

}




