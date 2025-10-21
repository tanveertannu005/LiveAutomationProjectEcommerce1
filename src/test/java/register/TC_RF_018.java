package register;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_018 {

    @Test
    public void VerifyHeightWidthNumberOfCharacters() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String baseurl = "https://tutorialsninja.com/demo/";
        driver.get(baseurl);
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        // ------------------ Height & Width Verification ------------------
        WebElement firstNameField = driver.findElement(By.id("input-firstname"));
        String actualHeight = firstNameField.getCssValue("height");
        String actualWidth = firstNameField.getCssValue("width");

        Assert.assertEquals(actualHeight, "34px", "Height mismatch!");
        Assert.assertEquals(actualWidth, "701.25px", "Width mismatch!");

        // ------------------ 1️⃣ Empty field -> Expect warning ------------------
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String expectedWarning = "First Name must be between 1 and 32 characters!";
        String actualWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
        Assert.assertEquals(actualWarning, expectedWarning, "Warning message mismatch for empty field!");

        // ------------------ 2️⃣ One character -> VALID ------------------
        firstNameField.clear();
        firstNameField.sendKeys("a");
        driver.findElement(By.id("input-lastname")).sendKeys("test");
        driver.findElement(By.id("input-email")).sendKeys("test" + System.currentTimeMillis() + "@gmail.com");
        driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
        driver.findElement(By.id("input-password")).sendKeys("abc123");
        driver.findElement(By.id("input-confirm")).sendKeys("abc123");
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        boolean warningExists = driver.findElements(By.xpath("//input[@id='input-firstname']/following-sibling::div")).size() > 0;
        Assert.assertFalse(warningExists, "⚠️ Warning displayed for valid single-character first name!");

        // Go back to Register page for next validation
        driver.navigate().back();
        driver.findElement(By.linkText("Register")).click();

        // ------------------ 3️⃣ More than 32 characters -> INVALID ------------------
        String longName = "abcdefghijklmnopqrstuvwxyzabcdefg"; // 33 chars
        WebElement longFirstNameField = driver.findElement(By.id("input-firstname"));
        longFirstNameField.sendKeys(longName);

        driver.findElement(By.id("input-lastname")).sendKeys("test");
        driver.findElement(By.id("input-email")).sendKeys("test" + System.currentTimeMillis() + "@gmail.com");
        driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
        driver.findElement(By.id("input-password")).sendKeys("abc123");
        driver.findElement(By.id("input-confirm")).sendKeys("abc123");
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // Now, warning should exist
        boolean longNameWarningExists = driver.findElements(By.xpath("//input[@id='input-firstname']/following-sibling::div")).size() > 0;
        Assert.assertTrue(longNameWarningExists, "⚠️ Expected warning for long first name not displayed!");

        driver.quit();
    }
}
