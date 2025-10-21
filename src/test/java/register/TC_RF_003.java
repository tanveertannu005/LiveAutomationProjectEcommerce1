package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.CommonUtilities;

import java.util.Date;

public class TC_RF_003 {
	@Test	
	public void  VerifyRegisteringanAccountbyprovidingallthefields() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		String baseurl="https://tutorialsninja.com/demo/";
		driver.get(baseurl);
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.name("firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("motu");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
		 // ✅ Assert logout link
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
        
        // ✅ Corrected heading locator
        String expectedHeading = "Your Account Has Been Created!";
        String actualHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals(actualHeading, expectedHeading);
        
        // ✅ Check details text
        String actualProperDetailsOne = "Congratulations! Your new account has been successfully created!";
        String actualProperDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String actualProperDetailsThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String actualProperDetailsFour = "contact us";
        
        String expectedProperDetails = driver.findElement(By.id("content")).getText();
        
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsOne));
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsTwo));
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsThree));
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsFour));
        
        driver.findElement(By.xpath("//a[text()='Continue']")).click();
        
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
        
        driver.quit();
	}
	
	
}
