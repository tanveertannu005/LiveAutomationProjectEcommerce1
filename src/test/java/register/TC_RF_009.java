package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.CommonUtilities;

public class TC_RF_009 {
	
	@Test
	public void VerifyRegisteringanAccountbyprovidingtheexistingaccountdetails() {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		String baseurl="https://tutorialsninja.com/demo/";
		driver.get(baseurl);
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
		
        driver.findElement(By.name("firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys("amotoori1@gmail.com");

        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("1234567890");
        driver.findElement(By.id("input-confirm")).sendKeys("1234567890");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        
        String WarningMessage="Warning: E-Mail Address is already registered!";
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]")).getText(), WarningMessage);
        
        
        
		
	}
}
