package register;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.Assert;

import utilities.CommonUtilities;

public class TC_RF_012 {
	@Test
	public void VerifyRegisteringanAccountbyusingtheKeyboardkeys() {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		String baseurl="https://tutorialsninja.com/demo/";
		driver.get(baseurl);
		
		Actions actions=new Actions(driver);
		
		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB)
		.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(2)).sendKeys(Keys.ENTER).build().perform();
		
		for(int i=1; i<=23; i++) {
			
			actions.sendKeys(Keys.TAB).perform();
		}
		
		actions.sendKeys("Arun").sendKeys(Keys.TAB).sendKeys("Mottori").sendKeys(Keys.TAB)
		.sendKeys(CommonUtilities.generateBrandNewEmail())
		.sendKeys(Keys.TAB).sendKeys("1234567898").sendKeys(Keys.TAB)
		.sendKeys("12345").sendKeys(Keys.TAB)
		.sendKeys("12345").sendKeys(Keys.TAB)
		.sendKeys(Keys.ARROW_LEFT) .sendKeys(Keys.TAB).sendKeys(Keys.TAB)
		.sendKeys(Keys.SPACE).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Success")).isDisplayed());
		driver.quit();
	}
}
