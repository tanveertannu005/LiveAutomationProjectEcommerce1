package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.CommonUtilities;

public class TC_RF_017 {
	WebDriver driver;

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(dataProvider = "passwordSupplier")
	public void VerifywhetherthePasswordfieldsintheRegisterAccountpagearefollowingPasswordComplexityStandards(
			String PasswordText) {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String baseurl = "https://tutorialsninja.com/demo/";
		driver.get(baseurl);

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.name("firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("motu");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys("PasswordText");
		driver.findElement(By.id("input-confirm")).sendKeys("PasswordText");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String ExpectedWarningMessage = "Enter password which matches password complexity";

		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				ExpectedWarningMessage);

	}

	@DataProvider(name = "passwordSupplier")
	public Object[][] supplypassword() {

		Object[][] data = 
					{ { "12345" }, 
					{ "abcdefgh" }, 
					{ "abcd1234" }, 
					{ "abcd123$" }, 
					{ "abcd123$#" } };

		return data;
	}

}
