package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_013 {
	@Test
	public void VerifyallthefieldsintheRegisterAccountpagehavetheproperplaceholders() {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String baseurl="https://tutorialsninja.com/demo/";
		driver.get(baseurl);
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		String expectedFirstNamePlaceholder="First Name";
		String expectedLastNamePlaceholder="Last Name";
		String expectedEmailPlaceholder="E-Mail";
		String expectedTelePhonePlaceholder="Telephone";
		String expectedPasswordPlaceholder="Password";
		String expectedPasswordConfirmmPlaceholder="Password Confirm";
		
		Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("placeholder"), expectedFirstNamePlaceholder);
		Assert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("placeholder"), expectedLastNamePlaceholder);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("placeholder"), expectedEmailPlaceholder);
		
		Assert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("placeholder"), expectedTelePhonePlaceholder);
		Assert.assertEquals(driver.findElement(By.id("input-password")).getAttribute("placeholder"), expectedPasswordPlaceholder);
		Assert.assertEquals(driver.findElement(By.id("input-confirm")).getAttribute("placeholder"), expectedPasswordConfirmmPlaceholder);
	}
}
