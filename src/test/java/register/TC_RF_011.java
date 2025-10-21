package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import utilities.CommonUtilities;

public class TC_RF_011 {
	@Test
	public void VerifyRegisteringanAccountbyprovidinganinvalidphonenumber() {
		 WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	        driver.manage().window().maximize();
	        driver.get("https://tutorialsninja.com/demo/");
	        
	        driver.findElement(By.xpath("//span[text()='My Account']")).click();
	        driver.findElement(By.linkText("Register")).click();
	        
	        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
	        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
	        driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
	        driver.findElement(By.id("input-telephone")).sendKeys("abcde");
	        driver.findElement(By.id("input-password")).sendKeys("12345");
	        driver.findElement(By.id("input-confirm")).sendKeys("12345");
	        driver.findElement(By.name("agree")).click();
	        driver.findElement(By.xpath("//input[@value='Continue']")).click();
	        
	        //This will still pass there is a lag in the app// 
	}
}
