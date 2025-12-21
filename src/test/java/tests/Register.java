package tests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.FlagTerm;
import utilities.CommonUtilities;

public class Register {
	WebDriver driver;
	String browserName;
	Properties prop;

	@AfterMethod

	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeMethod
	public void setup() {
		
		prop=CommonUtilities.loadPropertiesFiles();
		
		browserName = prop.getProperty("browserName");

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("appURL"));

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@Test(priority = 1)
	public void verifyRegisteringWithMandatoryFields() {

		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
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

	}

	@Test(priority = 2)
	public void VerifyThankyouforconfirmationEmailonSuccesfullRegistration() throws Exception {

		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		String emailtext = CommonUtilities.generateBrandNewEmail();
		driver.findElement(By.id("input-email")).sendKeys(emailtext);
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		// ===== Email Verification =====
		String host = "emailtext";
		String username = "yourEmail@gmail.com"; // ✅ replace with your Gmail
		String appPassword = "yourAppPassword"; // ✅ Gmail App Password (not normal pwd)

		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", "993");
		properties.put("mail.imap.ssl.enable", "true");

		Session emailSession = Session.getDefaultInstance(properties);
		Store store = emailSession.getStore("imaps");
		store.connect(host, username, appPassword);

		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		// Fetch unread mails
		Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

		boolean found = false;

		for (int i = messages.length - 1; i >= 0; i--) {
			Message message = messages[i];

			/*
			 * if (message.getSubject() != null &&
			 * message.getSubject().contains(expectedSubject)) { found = true;
			 * 
			 * // Assertions
			 * Assert.assertTrue(message.getSubject().contains(expectedSubject),
			 * "Subject mismatch!");
			 * Assert.assertTrue(message.getFrom()[0].toString().contains(expectedFromEmail)
			 * , "Sender mismatch!");
			 * 
			 * String actualEmailBody = getTextFromMessage(message);
			 * Assert.assertTrue(actualEmailBody.contains(expectedBodyContent),
			 * "Body content mismatch!");
			 * 
			 * // Extract link safely if (actualEmailBody.contains("600\">")) { String[] ar
			 * = actualEmailBody.split("600\">"); if (ar.length > 1) { String linkPart =
			 * ar[1]; String[] arr = linkPart.split("</a>"); link = arr[0].trim();
			 * System.out.println("✅ Extracted link: " + link);
			 */
		}
		/*
		 * } break; } }
		 */
		if (!found) {
			System.out.println("❌ No confirmation email found.");
		}

		inbox.close(false);
		store.close();

		// close browser at end
	}

	@Test(priority = 3)
	public void VerifyRegisteringanAccountbyprovidingallthefields() {

		driver.findElement(By.name("firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
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

	}

	@Test(priority = 4)
	public void verifywarningMessagesOfMandatoryFieldsInRegisterAccountPage() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//input[@Value='Continue']")).click();

		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String TelePhonewarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String PricacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";

		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(),
				expectedFirstNameWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(),
				expectedLastNameWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(),
				expectedEmailWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(),
				TelePhonewarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				expectedPasswordWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(),
				PricacyPolicyWarning);

	}

	@Test(priority = 5)
	public void verifyRegsiterPageWhenYesNewstellerIsSelected() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Newsletter")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).isSelected());

	}

	@Test(priority = 6)
	public void verifyRegsiterPageWhenNoNewstellerIsSelected() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Newsletter")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).isSelected());

	}

	@Test(priority = 7)
	public void VerifydifferentwaysofnavigatingtoRegisterAccountpage() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Register']"))
				.isDisplayed());

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.linkText("Continue")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Register']"))
				.isDisplayed());

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Register']"))
				.isDisplayed());

	}

	@Test(priority = 8)
	public void VerifyRegisteringanAccountbyenteringdifferentpasswordsintoPasswordandPasswordConfirmfields() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.name("firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("mismatchingPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String expectedWarninig = "Password confirmation does not match password!";
		Assert.assertEquals((driver.findElement(By.className("text-danger")).getText()), expectedWarninig);

	}

	@Test(priority = 9)
	public void VerifyRegisteringanAccountbyprovidingtheexistingaccountdetails() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.name("firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("existingEmail"));

		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String WarningMessage = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]")).getText(),
				WarningMessage);

	}

	@Test(priority = 10)
	public void VerifyRegisteringanAccountByProvidinganinvalidEmailAddressintotheEMailField()
			throws IOException, InterruptedException {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		
		driver.findElement(By.name("firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("InvalidEmail"));

		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		if (browserName.equals("chrome") || browserName.equals("edge")) 
		{
			String expectedwarningmessageOne = "Please include an '@' in the email address. 'amotoori1' is missing an '@'.";
			String actualwarningmessageOne = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
			Assert.assertEquals(actualwarningmessageOne, expectedwarningmessageOne);
		} else if (browserName.equals("firefox")) {
			String expectedwarningmessageOne = "Please enter an email address.";
			String actualwarningmessageOne = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
			Assert.assertEquals(actualwarningmessageOne, expectedwarningmessageOne);
		}

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("InvalidEmailTwo"));
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		if (browserName.equals("chrome") || browserName.equals("edge")) {
			String expectedwarningmessageTwo = "Please enter a part following '@'. 'amotoori@' is incomplete.";
			String actualwarningmessageTwo = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
			Assert.assertEquals(actualwarningmessageTwo, expectedwarningmessageTwo);
		} else if (browserName.equals("firefox")) {
			String expectedwarningmessageOne = "Please enter an email address.";
			String actualwarningmessageOne = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
			Assert.assertEquals(actualwarningmessageOne, expectedwarningmessageOne);
		}
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("InvalidEmailThree"));
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String expectedwarningmessageThree = "E-Mail Address does not appear to be valid!";
		String actualwarningmessageThree = driver.findElement(By.xpath("//*[@id=\"account\"]/div[4]/div/div"))
				.getText();
		Assert.assertEquals(actualwarningmessageThree, expectedwarningmessageThree);

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("InvalidEmailFour"));
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		if (browserName.equals("chrome") || browserName.equals("edge")) {
			String expectedwarningmessageFour = "'.' is used at a wrong position in 'gmail.'.";
			String actualwarningmessageFour = driver.findElement(By.id("input-email"))
					.getAttribute("validationMessage");

			Assert.assertEquals(actualwarningmessageFour, expectedwarningmessageFour);

			Thread.sleep(3000);
		} else if (browserName.equals("firefox")) {
			String expectedwarningmessageOne = "Please enter an email address.";
			String actualwarningmessageOne = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
			Assert.assertEquals(actualwarningmessageOne, expectedwarningmessageOne);
		}
	}

	@Test(priority = 11)
	public void VerifyRegisteringanAccountbyprovidinganinvalidphonenumber() {

		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("InvalidTelephone"));
		
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String expectedwarningmessage = "Telephone number entered is invalid!";
		boolean b = false;
		try {
			String actualWarningMessage = driver.findElement(By.xpath("//div[@class='text-danger']")).getText();
			if (actualWarningMessage.equals(expectedwarningmessage)) {
				b = true;
			}
		} catch (NoSuchElementException e) {
			b = false;
		}
		Assert.assertTrue(b);
		Assert.assertFalse(driver.findElement(By.xpath("//ul[@class='breadcumb']//a[text()='success']")).isDisplayed());
	}

	@Test(priority = 12)
	public void VerifyRegisteringanAccountbyusingtheKeyboardkeys() {

		Actions actions = new Actions(driver);

		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(2)).sendKeys(Keys.ENTER).build().perform();

		for (int i = 1; i <= 23; i++) {

			actions.sendKeys(Keys.TAB).perform();
		}

		actions.sendKeys(prop.getProperty("firstName")).sendKeys(Keys.TAB).sendKeys(prop.getProperty("lastName")).sendKeys(Keys.TAB)
				.sendKeys(CommonUtilities.generateBrandNewEmail()).sendKeys(Keys.TAB).sendKeys(prop.getProperty("telephoneNumber"))
				.sendKeys(Keys.TAB).sendKeys(prop.getProperty("validPassword")).sendKeys(Keys.TAB).sendKeys("validPassword").sendKeys(Keys.TAB)
				.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.SPACE).sendKeys(Keys.TAB)
				.sendKeys(Keys.ENTER).build().perform();

		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Success")).isDisplayed());

	}

	@Test(priority = 13)
	public void VerifyallthefieldsintheRegisterAccountpagehavetheproperplaceholders() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		String expectedFirstNamePlaceholder = "First Name";
		String expectedLastNamePlaceholder = "Last Name";
		String expectedEmailPlaceholder = "E-Mail";
		String expectedTelePhonePlaceholder = "Telephone";
		String expectedPasswordPlaceholder = "Password";
		String expectedPasswordConfirmmPlaceholder = "Password Confirm";

		Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("placeholder"),
				expectedFirstNamePlaceholder);
		Assert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("placeholder"),
				expectedLastNamePlaceholder);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("placeholder"),
				expectedEmailPlaceholder);

		Assert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("placeholder"),
				expectedTelePhonePlaceholder);
		Assert.assertEquals(driver.findElement(By.id("input-password")).getAttribute("placeholder"),
				expectedPasswordPlaceholder);
		Assert.assertEquals(driver.findElement(By.id("input-confirm")).getAttribute("placeholder"),
				expectedPasswordConfirmmPlaceholder);
	}

	@Test(priority = 14)
	public void VerifyallthemandatoryfieldsintheRegisterAccountpagearemarkedwithredcolorastricksymbol() {

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

	@Test(priority = 15)
	public void VerifyTheDetailsThatAreProvidedWhileRegisteringAnAccountAreStoredInTheDatabase() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		String firstNameInputData = prop.getProperty("firstName");
		driver.findElement(By.id("input-firstname")).sendKeys(firstNameInputData);

		String lastNameInputData = prop.getProperty("lastName");
		driver.findElement(By.id("input-lastname")).sendKeys(lastNameInputData);

		String emailInputData = CommonUtilities.generateBrandNewEmail();
		driver.findElement(By.id("input-email")).sendKeys(emailInputData);

		String passwordInputData = prop.getProperty("validPassword");
		driver.findElement(By.id("input-password")).sendKeys(passwordInputData);

		driver.findElement(By.xpath("//*[@id=\"input-newsletter\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"form-register\"]/div/div/input")).click();

		driver.findElement(By.xpath("//button[text()='Continue']")).click();

		// --- Database Connection ---
		String JdbcUrl = "jdbc:mysql://localhost:3306/opencart_db";
		String DbUser = "root";
		String DbPassword = "";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String firstNameStoredInDatabase = null;
		String lastNameStoredInDatabase = null;
		String emailStoredInDatabase = null;

		try {
			connection = DriverManager.getConnection(JdbcUrl, DbUser, DbPassword);
			System.out.println("Connected to the database!");

			statement = connection.createStatement();

			// Fetch the most recent record based on descending customer_id
			String sql = "SELECT firstname, lastname, email FROM oc_customer ORDER BY customer_id DESC LIMIT 1";
			resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				firstNameStoredInDatabase = resultSet.getString("firstname");
				lastNameStoredInDatabase = resultSet.getString("lastname");
				emailStoredInDatabase = resultSet.getString("email");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// --- Validation ---
		Assert.assertEquals(firstNameStoredInDatabase, firstNameInputData, "First name mismatch in DB");
		Assert.assertEquals(lastNameStoredInDatabase, lastNameInputData, "Last name mismatch in DB");
		Assert.assertEquals(emailStoredInDatabase, emailInputData, "Email mismatch in DB");

	}

	@Test(priority = 16)
	public void VerifywhethertheMandatoryfieldsintheRegisterAccountpagearenotacceptingonlyspaces() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.name("firstname")).sendKeys("    ");
		driver.findElement(By.id("input-lastname")).sendKeys("    ");
		driver.findElement(By.id("input-email")).sendKeys("      ");
		driver.findElement(By.id("input-telephone")).sendKeys("     ");
		driver.findElement(By.id("input-password")).sendKeys("    ");
		driver.findElement(By.id("input-confirm")).sendKeys("    ");

		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		if (browserName.equals("chrome") || browserName.equals("edge")) {
			String ExpectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
			String ExpectedLastNameWarningMessage = "Last Name must be between 1 and 32 characters!";
			String ExpectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
			String ExpectedTelephoneWarningMessage = "Telephone Number is not a valid!";

			Assert.assertEquals(
					driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(),
					ExpectedFirstNameWarningMessage);
			Assert.assertEquals(
					driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(),
					ExpectedLastNameWarningMessage);
			Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::div")).getText(),
					ExpectedEmailWarningMessage);
			Assert.assertEquals(
					driver.findElement(By.xpath("//input[@id='telephone']/following-sibling::div")).getText(),
					ExpectedTelephoneWarningMessage);
		} else if (browserName.equals("firefox")) {

			String expectedwarningmessageOne = "Please enter an email address.";
			String actualwarningmessageOne = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
			Assert.assertEquals(actualwarningmessageOne, expectedwarningmessageOne);

		}

	}

	@Test(priority = 17, dataProvider = "passwordSupplier")
	public void VerifywhetherthePasswordfieldsintheRegisterAccountpagearefollowingPasswordComplexityStandards(
			String PasswordText) {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.name("firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys("PasswordText");
		driver.findElement(By.id("input-confirm")).sendKeys("PasswordText");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String ExpectedWarningMessage = "Enter password which matches password complexity";

		boolean b = false;
		try {
			String actualWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div"))
					.getText();
			if (actualWarning.equals(ExpectedWarningMessage)) {
				b = true;
			}

		} catch (NoSuchElementException e) {
			b = false;

		}

		Assert.assertTrue(b);

	}

	@DataProvider(name = "passwordSupplier")
	public Object[][] supplypassword() {

		Object[][] data = { { "12345" }, { "abcdefgh" }, { "abcd1234" }, { "abcd123$" }, { "abcd123$#" } };

		return data;
	}

	@Test(priority = 18)
	public void VerifyHeightWidthNumberOfCharacters() {

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
		String actualWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div"))
				.getText();
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

		boolean warningExists = driver.findElements(By.xpath("//input[@id='input-firstname']/following-sibling::div"))
				.size() > 0;
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
		boolean longNameWarningExists = driver
				.findElements(By.xpath("//input[@id='input-firstname']/following-sibling::div")).size() > 0;
		Assert.assertTrue(longNameWarningExists, "⚠️ Expected warning for long first name not displayed!");

	}

	@Test(priority = 19)
	public void VerifyRegisterAccountByLeadingAndTralingSpaces() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		SoftAssert softassert = new SoftAssert();

		String firstname = "     "+prop.getProperty("firstName")+  " ";
		driver.findElement(By.id("input-firstname")).sendKeys(firstname);

		String lastname = "     "+prop.getProperty("lastName")+  " ";
		driver.findElement(By.id("input-lastname")).sendKeys(lastname);

		String emailText = "           " + CommonUtilities.generateBrandNewEmail() + "     ";

		driver.findElement(By.id("input-email")).sendKeys(emailText);

		String Telephone = "   "+prop.getProperty("Telephone")+"     ";
		driver.findElement(By.id("input-telephone")).sendKeys(Telephone);

		String Password = "   "+prop.getProperty("validPassword")+"     ";
		driver.findElement(By.id("input-password")).sendKeys(Password);

		String passwordConfirm = "   "+prop.getProperty("validPassword")+"     ";
		driver.findElement(By.id("input-confirm")).sendKeys(passwordConfirm);

		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@Value='Continue']")).click();

		if (browserName.equals("chrome") || browserName.equals("edge")) {
			driver.findElement(By.xpath("//a[@class='btn btn-primary'][text()='Continue']")).click();

			driver.findElement(By.linkText("Edit your account information")).click();

			String ActualFirsName = driver.findElement(By.id("input-firstname")).getAttribute("value");
			softassert.assertEquals(ActualFirsName, firstname.trim());

			String ActualLastName = driver.findElement(By.id("input-firstname")).getAttribute("value");
			softassert.assertEquals(ActualLastName, lastname.trim());

			String ActualEmail = driver.findElement(By.id("input-email")).getAttribute("value");
			softassert.assertEquals(ActualEmail, emailText.trim());

			String ActualTelephone = driver.findElement(By.id("input-telephone")).getAttribute("value");
			softassert.assertEquals(ActualTelephone, Telephone.trim());

			softassert.assertAll();
		} else if (browserName.equals("firefox")) {
			String expectedwarningmessageOne = "Please enter an email address.";
			String actualwarningmessageOne = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
			Assert.assertEquals(actualwarningmessageOne, expectedwarningmessageOne);
		}
	}

	@Test(priority = 20)
	public void VerifywhetherthePrivacyPolicycheckboxoptionisnotselectedbydefault() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		/*
		 * driver.findElement(By.id("input-firstname")).sendKeys("Sam wilson");
		 * driver.findElement(By.id("input-lastname")).sendKeys("wilson");
		 * driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.
		 * generateBrandNewEmail());
		 * 
		 * driver.findElement(By.id("input-telephone")).sendKeys("8897578786");
		 * driver.findElement(By.id("input-password")).sendKeys("1234");
		 * driver.findElement(By.id("input-confirm")).sendKeys("1234");
		 * 
		 * driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click
		 * ();
		 */

		Assert.assertFalse(driver.findElement(By.name("agree")).isSelected());

	}

	@Test(priority = 21)
	public void VerifyRegisteringtheAccountwithoutselectingthePrivacyPolicycheckboxoption() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());

		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));

		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@Value='Continue']")).click();

		String expectedprivacypolicyWarninigMessage = "Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(),
				expectedprivacypolicyWarninigMessage);

	}

	@Test(priority = 22)
	public void verifypasswordandpasswordconfirmtoggleishiddenstate() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		Assert.assertEquals(driver.findElement(By.id("input-password")).getDomAttribute("type"), "password");
		Assert.assertEquals(driver.findElement(By.id("input-confirm")).getDomAttribute("type"), "password");

	}

	@Test(priority = 23)
	public void verifynavigatingtotheotherpagesusingoptionslinks() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.xpath("//i[@class='fa fa-phone']")).click();
		Assert.assertEquals(driver.getTitle(), "Contact Us");
		driver.navigate().back();

		driver.findElement(By.xpath("//i[@class='fa fa-heart']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md' and contains(text(), 'Wish List')]"))
				.click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']")).click();
		Assert.assertEquals(driver.getTitle(), "Shopping Cart");
		driver.navigate().back();

		driver.findElement(By.xpath("//span[text()='Shopping Cart']")).click();
		Assert.assertEquals(driver.getTitle(), "Shopping Cart");
		driver.navigate().back();

		driver.findElement(By.xpath("//i[@class='fa fa-share']")).click();
		Assert.assertEquals(driver.getTitle(), "Shopping Cart");
		driver.navigate().back();

		driver.findElement(By.linkText("Qafox.com")).click();
		Assert.assertEquals(driver.getTitle(), "Your Store");
		driver.navigate().back();

		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertEquals(driver.getTitle(), "Search");
		driver.navigate().back();

		/*
		 * driver.findElement(By.xpath("//i[@class='fa fa-home']")).click();
		 * Assert.assertEquals(driver.getTitle(), "Your Store");
		 * driver.navigate().back();
		 */

		driver.findElement(By.linkText("Account")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.linkText("login page")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.linkText("Login")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.linkText("Register")).click();
		Assert.assertEquals(driver.getTitle(), "Register Account");

		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Forgotten Password']")).click();
		Assert.assertEquals(driver.getTitle(), "Forgot Your Password?");
		driver.navigate().back();

		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Address Book']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Wish List']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Order History']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Downloads']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='About Us']"));
		Assert.assertEquals(driver.getTitle(), "Register Account");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Delivery Information']")).click();
		Assert.assertEquals(driver.getTitle(), "Delivery Information");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Privacy Policy']")).click();
		Assert.assertEquals(driver.getTitle(), "Privacy Policy");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Privacy Policy']")).click();
		Assert.assertEquals(driver.getTitle(), "Privacy Policy");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Terms & Conditions']")).click();
		Assert.assertEquals(driver.getTitle(), "Terms & Conditions");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Contact Us']")).click();
		Assert.assertEquals(driver.getTitle(), "Contact Us");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Returns']")).click();
		Assert.assertEquals(driver.getTitle(), "Product Returns");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Site Map']")).click();
		Assert.assertEquals(driver.getTitle(), "Site Map");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Brands']")).click();
		Assert.assertEquals(driver.getTitle(), "Find Your Favorite Brand");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Gift Certificates']")).click();
		Assert.assertEquals(driver.getTitle(), "Purchase a Gift Certificate");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Affiliate']")).click();
		Assert.assertEquals(driver.getTitle(), "Affiliate Program");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Specials']")).click();
		Assert.assertEquals(driver.getTitle(), "Special Offers");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Order History']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Wish List']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

		driver.findElement(By.xpath("//footer//a[text()='Newsletter']")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();

	}

	@Test(priority = 24)
	public void verifywhenuserwillnotpasswordconfirm() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));

		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

		String ExpectedWarningMessage = "Password confirmation does not match password!";

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger']")).getText(),
				ExpectedWarningMessage);

	}

	@Test(priority = 25)
	public void VerifytheBreadcrumbPageHeadingPageURLPageTitleofRegisterAccountPage() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		String ExpectedTitle = "Register Account";
		Assert.assertEquals(driver.getTitle(), ExpectedTitle);

		String ExpectedUrl = "https://tutorialsninja.com/demo/index.php?route=account/register";
		Assert.assertEquals(driver.getCurrentUrl(), ExpectedUrl);

		Assert.assertTrue(
				driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h1")).getText(), "Register Account");
		driver.quit();

	}

	@Test(priority = 26)
	public void verifyUIofRegisterAccountPage() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		CommonUtilities.scrennshots(driver, System.getProperty("user.dir") + "/Screenshots/actualRegisterPageUI.png");
		
			Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "/Screenshots/actualRegisterPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedRegisterPageUI.png"));
			
				
		
	}

	@Test(priority = 27)

	public void VerifyRegisterAccountfunctionalityinallthesupportedenvironments() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));

		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Logout']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		Assert.assertEquals(driver.getTitle(), "My Account");

		

	}

	
}
