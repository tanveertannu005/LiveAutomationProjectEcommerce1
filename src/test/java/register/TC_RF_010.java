package register;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.io.FileHandler;

public class TC_RF_010 {
	@Test
	public void VerifyRegisteringanAccountByProvidinganinvalidEmailAddressintotheEMailField() throws IOException, InterruptedException{
			
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		String baseurl="https://tutorialsninja.com/demo/";
		driver.get(baseurl);
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.name("firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys("amotoori1");
        
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("1234567890");
        driver.findElement(By.id("input-confirm")).sendKeys("1234567890");
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        
       
       
       String expectedwarningmessageOne="Please include an '@' in the email address. 'amotoori1' is missing an '@'.";
       String actualwarningmessageOne = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
       Assert.assertEquals(actualwarningmessageOne, expectedwarningmessageOne);
       
       driver.findElement(By.id("input-email")).clear();
       driver.findElement(By.id("input-email")).sendKeys("amotoori@");
       driver.findElement(By.xpath("//input[@value='Continue']")).click();
       
       
       String expectedwarningmessageTwo="Please enter a part following '@'. 'amotoori@' is incomplete.";
       String actualwarningmessageTwo = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
       Assert.assertEquals(actualwarningmessageTwo, expectedwarningmessageTwo);
       
       
       driver.findElement(By.id("input-email")).clear();
       driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail");
       driver.findElement(By.xpath("//input[@value='Continue']")).click();
       
       String expectedwarningmessageThree="E-Mail Address does not appear to be valid!";
       String actualwarningmessageThree = driver.findElement(By.xpath("//*[@id=\"account\"]/div[4]/div/div")).getText();
       Assert.assertEquals(actualwarningmessageThree, expectedwarningmessageThree);
       
       driver.findElement(By.id("input-email")).clear();
       driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail.");
       driver.findElement(By.xpath("//input[@value='Continue']")).click();
       
       String expectedwarningmessageFour="'.' is used at a wrong position in 'gmail.'.";
       String actualwarningmessageFour = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
      
       Assert.assertEquals(actualwarningmessageFour, expectedwarningmessageFour);
       
       Thread.sleep(5000);
       
       //driver.quit();
	}
}
