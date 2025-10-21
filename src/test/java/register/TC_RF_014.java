package register;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class TC_RF_014 {
	WebDriver driver;
	@AfterMethod
	public void teardown() {
	   driver.quit();
	}
	@Test
	public void VerifyallthemandatoryfieldsintheRegisterAccountpagearemarkedwithredcolorastricksymbol() {
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String baseurl = "https://tutorialsninja.com/demo/";
		driver.get(baseurl);

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		String expectedContent = "\"* \"";
		String expectedcolor = "rgb(255, 0, 0)";

		WebElement FirstNameLabel1 = driver.findElement(By.cssSelector("label[for='input-firstname']"));
		WebElement LastNameLabel = driver.findElement(By.cssSelector("label[for='input-lastname']"));
		WebElement Email = driver.findElement(By.cssSelector("label[for='input-email']"));
		WebElement Telephone = driver.findElement(By.cssSelector("label[for='input-telephone']"));
		WebElement Password = driver.findElement(By.cssSelector("label[for='input-password']"));
		WebElement PasswordConfirm = driver.findElement(By.cssSelector("label[for='input-confirm']"));

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		String firstNameLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
				FirstNameLabel1);
		Assert.assertEquals(firstNameLabelContent, expectedContent);

		String firstNameLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", FirstNameLabel1);

		Assert.assertEquals(firstNameLabelColor, expectedcolor);

		String lastNameLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", LastNameLabel);

		String LastNameLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", LastNameLabel);
		Assert.assertEquals(LastNameLabelColor, expectedcolor);

		String EmailContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", Email);
		Assert.assertEquals(EmailContent, expectedContent);

		String EmailColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", Email);

		Assert.assertEquals(EmailColor, expectedcolor);

		String TelephoneContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", Telephone);
		Assert.assertEquals(TelephoneContent, expectedContent);

		String TelephoneColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", Telephone);

		Assert.assertEquals(TelephoneColor, expectedcolor);

		String PasswordContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", Password);
		Assert.assertEquals(firstNameLabelContent, PasswordContent);

		String PasswordColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", Telephone);

		Assert.assertEquals(PasswordColor, expectedcolor);

		String PasswordConfirmContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
				PasswordConfirm);
		Assert.assertEquals(PasswordConfirmContent, expectedContent);

		String PasswordCofirmColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", Telephone);

		Assert.assertEquals(PasswordCofirmColor, expectedcolor);

	}
}
