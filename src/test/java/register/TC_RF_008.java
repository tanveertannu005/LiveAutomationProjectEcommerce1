package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.CommonUtilities;

public class TC_RF_008 {
	@Test
	public void VerifyRegisteringanAccountbyenteringdifferentpasswordsintoPasswordandPasswordConfirmfields() {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String baseurl="https://tutorialsninja.com/demo/";
		driver.get(baseurl);
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
		
        driver.findElement(By.name("firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("1234");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
			
	     String expectedWarninig="Password confirmation does not match password!";
	     Assert.assertEquals((driver.findElement(By.className("text-danger")).getText()),expectedWarninig );
	     
		driver.quit();
		
	}
}
