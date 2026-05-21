package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.io.FileHandler;
import utilities.CommonUtilities;
import utilities.ElementUtilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReports;
	ExtentTest	extentTest;
	WebDriver driver;

public void onStart(ITestContext context) {
	extentReports=	CommonUtilities.getExtentReport();
		
	}
	
	
	public void onTestStart(ITestResult result) {
		extentTest =extentReports.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+"	Test - Excecution Started");
		
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.pass(result.getName()+"	Test - got passed");
		extentTest.log(Status.PASS, result.getName()+"	Test - got passed");
		
	}

	public void onTestFailure(ITestResult result) {
		extentTest.fail(result.getName() + " Test - got failed");

		extentTest.fail(result.getName() + " Test - got failed");

	    try {

	        driver = (WebDriver) result.getTestClass()
	                .getRealClass()
	                .getDeclaredField("driver")
	                .get(result.getInstance());

	    } catch (Throwable t) {

	        t.printStackTrace();
	    }
	    
	String destScreenshotPath  =  new ElementUtilities(driver).captureScreenshotAndReturnPath(result.getName(), driver);

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    extentTest.addScreenCaptureFromPath(destScreenshotPath);
	    extentTest.log(Status.INFO, result.getThrowable());		
		
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.FAIL, result.getName()+"	Test - got Skipped");
	}


	public void onFinish(ITestContext context) {
		extentReports.flush();
	   File extentReportFile= new File(System.getProperty("user.dir")+"\\Reports\\ExtentReport.html");
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
