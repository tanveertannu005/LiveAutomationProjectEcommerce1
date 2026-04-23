package tests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
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

import base.Base;
import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.FlagTerm;
import pages.AboutUspage;
import pages.AccountSuccessPage;
import pages.AffiliatePrograPage;
import pages.BrandsPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.FooterOptions;
import pages.ForgottenPasswordPage;
import pages.GiftCertificatesPage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountInformationPage;
import pages.MyAccountPage;
import pages.NewsleterPage;
import pages.Privacypolicypage;
import pages.ProductReturnsPage;
import pages.RegisterPage;
import pages.RightColumOptions;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.SiteMappage;
import pages.SpecialOffersPage;
import pages.TermsandConditionsPage;
import utilities.CommonUtilities;
import utilities.ElementUtilities;


public class Register extends Base {
	WebDriver driver;
	
	
	@AfterMethod

	public void teardown() {
		CloseBrowser(driver);
	}

	@BeforeMethod
	public void setup() {

		
		driver = openBrowserAndApplicationURL();
		headeroptions = new HeaderOptions(driver);
		headeroptions.ClickOnMyAccountDropMenu();
		registerpage = headeroptions.SelectOnRegisterOption();
	}

	@Test(priority = 1)
	public void verifyRegisteringWithMandatoryFields() {

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage = registerpage.clickOnContinueButton();

		Assert.assertTrue(accountsuccesspage.isUserLoggedIn());

		String expectedHeading = "Your Account Has Been Created!";
		String actualHeading = accountsuccesspage.getAccountCreatedHeading();
		Assert.assertEquals(actualHeading, expectedHeading);

		String actualProperDetailsOne = "Congratulations! Your new account has been successfully created!";
		String actualProperDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String actualProperDetailsThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String actualProperDetailsFour = "contact us";

		String expectedProperDetails = accountsuccesspage.getContent();

		Assert.assertTrue(accountsuccesspage.getContent().contains(actualProperDetailsOne));
		Assert.assertTrue(accountsuccesspage.getContent().contains(actualProperDetailsTwo));
		Assert.assertTrue(accountsuccesspage.getContent().contains(actualProperDetailsThree));
		Assert.assertTrue(accountsuccesspage.getContent().contains(actualProperDetailsFour));

		myaccountpage = accountsuccesspage.ContinuePage();
		Assert.assertTrue(myaccountpage.didwenavigateToMyAccountPage());

	}

	@Test(priority = 2)
	public void VerifyThankyouforconfirmationEmailonSuccesfullRegistration() throws Exception {

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.selectPrivacyPolicyField();

		accountsuccesspage = registerpage.clickOnContinueButton();

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

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage = registerpage.clickOnContinueButton();

		// ✅ Assert logout link
		Assert.assertTrue(accountsuccesspage.isUserLoggedIn());

		String expectedHeading = "Your Account Has Been Created!";
		String actualHeading = accountsuccesspage.getAccountCreatedHeading();
		Assert.assertEquals(actualHeading, expectedHeading);

		String actualProperDetailsOne = "Congratulations! Your new account has been successfully created!";
		String actualProperDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String actualProperDetailsThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String actualProperDetailsFour = "contact us";

		String expectedProperDetails = accountsuccesspage.getContent();

		Assert.assertTrue(accountsuccesspage.getContent().contains(actualProperDetailsOne));
		Assert.assertTrue(accountsuccesspage.getContent().contains(actualProperDetailsTwo));
		Assert.assertTrue(accountsuccesspage.getContent().contains(actualProperDetailsThree));
		Assert.assertTrue(accountsuccesspage.getContent().contains(actualProperDetailsFour));

		myaccountpage = accountsuccesspage.ContinuePage();
		Assert.assertTrue(myaccountpage.didwenavigateToMyAccountPage());

	}

	@Test(priority = 4)
	public void verifywarningMessagesOfMandatoryFieldsInRegisterAccountPage() {

		registerpage.clickOnContinueButton();

		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String TelePhonewarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String PricacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";

		Assert.assertEquals(registerpage.getFirstNameWarning(), expectedFirstNameWarning);
		Assert.assertEquals(registerpage.GetLastNameWarning(), expectedLastNameWarning);
		Assert.assertEquals(registerpage.GetEmailWarninigMessage(), expectedEmailWarning);
		Assert.assertEquals(registerpage.GetTelephoneWarning(), TelePhonewarning);
		Assert.assertEquals(registerpage.GetPasswordWarning(), expectedPasswordWarning);
		Assert.assertEquals(registerpage.getpageLevelWarning(), PricacyPolicyWarning);

	}

	@Test(priority = 5)
	public void verifyRegsiterPageWhenYesNewstellerIsSelected() {

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage = registerpage.clickOnContinueButton();
		
		myaccountpage = accountsuccesspage.ContinuePage();
		newsletterPage = myaccountpage.ClickonSubscribeunsubscribeNewsletterOption();

		Assert.assertTrue(newsletterPage.DidweNavigateToNewsletterOption());
		Assert.assertTrue(newsletterPage.DidweNavigateToNewsletterOption());

	}

	@Test(priority = 6)
	public void verifyRegsiterPageWhenNoNewstellerIsSelected() {

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.NoNewSeletterOptionOption();
		registerpage.selectPrivacyPolicyField();

		accountsuccesspage = registerpage.clickOnContinueButton();
		myaccountpage = accountsuccesspage.ContinuePage();
		newsletterPage = myaccountpage.ClickonSubscribeunsubscribeNewsletterOption();

		Assert.assertTrue(newsletterPage.DidweNavigateToNewsletterOption());
		Assert.assertTrue(newsletterPage.NonewsletteroptionisInSelectedState());

	}

	@Test(priority = 7)
	public void VerifydifferentwaysofnavigatingtoRegisterAccountpage() {

		Assert.assertTrue(registerpage.DidWeNavigateToRegisterPage());
		headeroptions = registerpage.getHeaderoptions();

		headeroptions.ClickOnMyAccountDropMenu();
		loginPage = headeroptions.SelectLoginOption();
		myaccountpage = loginPage.ClickOnLoginButton();

		Assert.assertTrue(registerpage.DidWeNavigateToRegisterPage());
		headeroptions = registerpage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		loginPage = headeroptions.SelectLoginOption();
		rightcolumoptions = loginPage.getRightColumnOptions();

		registerpage = rightcolumoptions.ClickOnRegisterOption();

		Assert.assertTrue(registerpage.DidWeNavigateToRegisterPage());

	}

	@Test(priority = 8)
	public void VerifyRegisteringanAccountbyenteringdifferentpasswordsintoPasswordandPasswordConfirmfields() {

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("mismatchingPassword"));
		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();

		String expectedWarninig = "Password confirmation does not match password!";
		Assert.assertEquals(registerpage.getPasswordConfirmationWarning(), expectedWarninig);

	}

	@Test(priority = 9)
	public void VerifyRegisteringanAccountbyprovidingtheexistingaccountdetails() {

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(prop.getProperty("existingEmail"));

		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();

		String WarningMessage = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(registerpage.getpageLevelWarning(), WarningMessage);

	}

	@Test(priority = 10)
	public void VerifyRegisteringanAccountByProvidinganinvalidEmailAddressintotheEMailField()
			throws IOException, InterruptedException {

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));

		// 🔹 Invalid Email One (Missing @)
		registerpage.EnterEmail(prop.getProperty("InvalidEmailOne"));
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {

			String actualWarningMessageOne = registerpage.GetEmailValidationMessage();
			Assert.assertTrue(actualWarningMessageOne.contains("missing an '@'"),
					"Validation message mismatch for InvalidEmailOne");

		} else if (browserName.equalsIgnoreCase("firefox")) {

			String expectedWarningMessageOne = "Please enter an email address.";
			Assert.assertEquals(registerpage.GetEmailValidationMessage(), expectedWarningMessageOne);
		}

		// 🔹 Invalid Email Two (Incomplete after @)
		registerpage.ClearemailField();
		registerpage.EnterEmail(prop.getProperty("InvalidEmailTwo"));
		registerpage.clickOnContinueButton();

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {

			String actualWarningMessageTwo = registerpage.GetEmailValidationMessage();
			Assert.assertTrue(actualWarningMessageTwo.contains("incomplete"),
					"Validation message mismatch for InvalidEmailTwo");

		} else if (browserName.equalsIgnoreCase("firefox")) {

			String expectedWarningMessageTwo = "Please enter an email address.";
			Assert.assertEquals(registerpage.GetEmailValidationMessage(), expectedWarningMessageTwo);
		}

		// 🔹 Invalid Email Three (Application Level Validation)
		registerpage.ClearemailField();
		registerpage.EnterEmail(prop.getProperty("InvalidEmailThree"));
		registerpage.clickOnContinueButton();

		String expectedWarningMessageThree = "E-Mail Address does not appear to be valid!";
		String actualWarningMessageThree = registerpage.GetEmailWarninigMessage();

		Assert.assertEquals(actualWarningMessageThree, expectedWarningMessageThree);

		// 🔹 Invalid Email Four (Wrong dot position)
		registerpage.ClearemailField();
		registerpage.EnterEmail(prop.getProperty("InvalidEmailFour"));
		registerpage.clickOnContinueButton();

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {

			String actualWarningMessageFour = registerpage.GetEmailValidationMessage();
			Assert.assertTrue(actualWarningMessageFour.contains("wrong position"),
					"Validation message mismatch for InvalidEmailFour");

		} else if (browserName.equalsIgnoreCase("firefox")) {

			String expectedWarningMessageFour = "Please enter an email address.";
			Assert.assertEquals(registerpage.GetEmailValidationMessage(), expectedWarningMessageFour);
		}
	}

	@Test(priority = 11)
	public void VerifyRegisteringanAccountbyprovidinganinvalidphonenumber() {
		registerpage.enterFirstName((prop.getProperty("firstName")));
		registerpage.enterLastName((prop.getProperty("lastName")));
		registerpage.EnterEmail((CommonUtilities.generateBrandNewEmail()));
		registerpage.EnterTelePhoneNumber((prop.getProperty("InvalidTelephone")));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage = registerpage.clickOnContinueButton();

		String expectedwarningmessage = "Telephone number entered is invalid!";
		boolean b = false;
		try {

			if (registerpage.GetTelephoneWarning().equals(expectedwarningmessage)) {
				b = true;
			}
		} catch (NoSuchElementException e) {
			b = false;
		}
		Assert.assertTrue(b);
		Assert.assertFalse(myaccountpage.didwenavigateToMyAccountPage());
	}

	@Test(priority = 12)
	public void VerifyRegisteringanAccountbyusingtheKeyboardkeys() {

		Actions actions = new Actions(driver);

		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(2)).sendKeys(Keys.ENTER).build().perform();
		
		for (int i = 1; i <= 23; i++) {

			actions.sendKeys(Keys.TAB).perform();
		}

		actions.sendKeys(prop.getProperty("firstName")).sendKeys(Keys.TAB).sendKeys(prop.getProperty("lastName"))
				.sendKeys(Keys.TAB).sendKeys(CommonUtilities.generateBrandNewEmail()).sendKeys(Keys.TAB)
				.sendKeys(prop.getProperty("telephoneNumber")).sendKeys(Keys.TAB)
				.sendKeys(prop.getProperty("validPassword")).sendKeys(Keys.TAB).sendKeys("validPassword")
				.sendKeys(Keys.TAB).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.SPACE)
				.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();

		rightcolumoptions = registerpage.getRightColumnOptions();

		Assert.assertTrue(rightcolumoptions.DidWeGetLoggedIn());
		accountsuccesspage = rightcolumoptions.getAccountSuccessPage();

		Assert.assertTrue(accountsuccesspage.isUserLoggedIn());

	}

	@Test(priority = 13)
	public void VerifyallthefieldsintheRegisterAccountpagehavetheproperplaceholders() {

		Assert.assertEquals((registerpage.GetFirstNameFieldPlaceholderText()), "First Name");
		Assert.assertEquals(registerpage.GetLastNameFieldPlaceholderText(), "Last Name");
		Assert.assertEquals(registerpage.GetEmailPlaceholderText(), "E-Mail");

		Assert.assertEquals((registerpage.GetTelephonePlaceholderText()), "Telephone");
		Assert.assertEquals(registerpage.GetPasswordPlaceholderText(), "Password");
		Assert.assertEquals(registerpage.GetPasswordConfirmPlaceholderText(), "Password Confirm");
	}

	@Test(priority = 14)
	public void VerifyallthemandatoryfieldsintheRegisterAccountpagearemarkedwithredcolorastricksymbol() {

		String expectedContent = "\"* \"";
		String expectedColor = "rgb(255, 0, 0)";

		JavascriptExecutor js = (JavascriptExecutor) driver;

		List<WebElement> mandatoryLabels = Arrays.asList(registerpage.getFirstnameLabelFieldelement(),
				registerpage.getLastnameLabelFieldelement(), registerpage.getEmailFieldlabelelement(),
				registerpage.getTelephoneFieldlabelelement(), registerpage.getPasswordFieldlabelelement(),
				registerpage.getPasswordConfirmFieldlabelelement());

		for (WebElement label : mandatoryLabels) {

			String content = (String) js.executeScript(
					"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", label);

			Assert.assertEquals(content, expectedContent);

			String color = (String) js.executeScript(
					"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", label);

			Assert.assertEquals(color, expectedColor);
		}
	}

	@Test(priority = 15, enabled = false)
	public void VerifyTheDetailsThatAreProvidedWhileRegisteringAnAccountAreStoredInTheDatabase() {

		String firstNameInputData = prop.getProperty("firstName");
		registerpage.enterFirstName(firstNameInputData);

		String lastNameInputData = prop.getProperty("lastName");
		registerpage.enterLastName(lastNameInputData);

		String emailInputData = CommonUtilities.generateBrandNewEmail();
		registerpage.EnterEmail(emailInputData);

		String passwordInputData = prop.getProperty("validPassword");
		registerpage.enterPasswordField(passwordInputData);

		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();

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

		registerpage.enterFirstName("    ");
		registerpage.enterLastName("    ");
		registerpage.EnterEmail("      ");
		registerpage.EnterTelePhoneNumber("     ");
		registerpage.enterPasswordField("    ");
		registerpage.enterPasswordConfirm("    ");
		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {

			Assert.assertEquals(registerpage.getFirstNameWarning(), "First Name must be between 1 and 32 characters!");
			Assert.assertEquals(registerpage.GetLastNameWarning(), "Last Name must be between 1 and 32 characters!");
			Assert.assertEquals(registerpage.GetEmailWarninigMessage(), "E-Mail Address does not appear to be valid!");
			Assert.assertEquals(registerpage.GetTelephoneWarning(), "Telephone Number is not a valid!");
		} else if (browserName.equalsIgnoreCase("firefox")) {

			Assert.assertEquals(registerpage.GetEmailValidationMessage(), "Please enter an email address.");

		}

	}

	@Test(priority = 17, dataProvider = "passwordSupplier")
	public void VerifywhetherthePasswordfieldsintheRegisterAccountpagearefollowingPasswordComplexityStandards(
			String PasswordText) {

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(PasswordText);
		registerpage.enterPasswordConfirm(PasswordText);

		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();

		String ExpectedWarningMessage = "Enter password which matches password complexity";

		boolean b = false;
		try {
			String actualWarning = (registerpage.GetPasswordWarning());
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

	/*
	 * @Test(priority = 18) public void VerifyHeightWidthNumberOfCharacters() {
	 * 
	 * 
	 * // ------------------ Height & Width Verification ------------------
	 * WebElement firstNameField = registerpage.getFirstNameFieldElement(); String
	 * actualHeight = firstNameField.getCssValue("height"); String actualWidth =
	 * firstNameField.getCssValue("width");
	 * 
	 * Assert.assertEquals(actualHeight, "34px", "Height mismatch!");
	 * Assert.assertEquals(actualWidth, "701.25px", "Width mismatch!");
	 * 
	 * // ------------------ 1️⃣ Empty field -> Expect warning ------------------
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); String
	 * expectedWarning = "First Name must be between 1 and 32 characters!"; String
	 * actualWarning = driver.findElement(By.xpath(
	 * "//input[@id='input-firstname']/following-sibling::div")) .getText();
	 * Assert.assertEquals(actualWarning, expectedWarning,
	 * "Warning message mismatch for empty field!");
	 * 
	 * // ------------------ 2️⃣ One character -> VALID ------------------
	 * firstNameField.clear(); firstNameField.sendKeys("a");
	 * driver.findElement(By.id("input-lastname")).sendKeys("test");
	 * driver.findElement(By.id("input-email")).sendKeys("test" +
	 * System.currentTimeMillis() + "@gmail.com");
	 * driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
	 * driver.findElement(By.id("input-password")).sendKeys("abc123");
	 * driver.findElement(By.id("input-confirm")).sendKeys("abc123");
	 * driver.findElement(By.xpath("//input[@name='agree']")).click();
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click();
	 * 
	 * boolean warningExists = driver.findElements(By.xpath(
	 * "//input[@id='input-firstname']/following-sibling::div")) .size() > 0;
	 * Assert.assertFalse(warningExists,
	 * "⚠️ Warning displayed for valid single-character first name!");
	 * 
	 * // Go back to Register page for next validation driver.navigate().back();
	 * driver.findElement(By.linkText("Register")).click();
	 * 
	 * // ------------------ 3️⃣ More than 32 characters -> INVALID
	 * ------------------ String longName = "abcdefghijklmnopqrstuvwxyzabcdefg"; //
	 * 33 chars WebElement longFirstNameField =
	 * driver.findElement(By.id("input-firstname"));
	 * longFirstNameField.sendKeys(longName);
	 * 
	 * driver.findElement(By.id("input-lastname")).sendKeys("test");
	 * driver.findElement(By.id("input-email")).sendKeys("test" +
	 * System.currentTimeMillis() + "@gmail.com");
	 * driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
	 * driver.findElement(By.id("input-password")).sendKeys("abc123");
	 * driver.findElement(By.id("input-confirm")).sendKeys("abc123");
	 * driver.findElement(By.xpath("//input[@name='agree']")).click();
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click();
	 * 
	 * // Now, warning should exist boolean longNameWarningExists = driver
	 * .findElements(By.xpath(
	 * "//input[@id='input-firstname']/following-sibling::div")).size() > 0;
	 * Assert.assertTrue(longNameWarningExists,
	 * "⚠️ Expected warning for long first name not displayed!");
	 * 
	 * }
	 */
	@Test(priority = 19)
	public void VerifyRegisterAccountByLeadingAndTralingSpaces() {

		SoftAssert softassert = new SoftAssert();

		String firstname = prop.getProperty("firstName");
		String lastname = prop.getProperty("lastName");
		String emailText = CommonUtilities.generateBrandNewEmail();
		String Telephone = prop.getProperty("telephoneNumber");

		registerpage.enterFirstName("     " + firstname + " ");
		registerpage.enterLastName("     " + lastname + " ");
		registerpage.EnterEmail("           " + emailText + "     ");
		registerpage.EnterTelePhoneNumber("   " + Telephone + "     ");
		registerpage.enterPasswordField("   " + prop.getProperty("validPassword") + "     ");
		registerpage.enterPasswordConfirm("   " + prop.getProperty("validPassword") + "     ");

		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();

		accountsuccesspage = registerpage.clickOnContinueButton();

		if (browserName.equals("chrome") || browserName.equals("edge")) {

			myaccountpage = accountsuccesspage.ContinuePage();
			myaccountInformationPage = myaccountpage.clickOnEdityourAccountInformation();

			softassert.assertEquals(myaccountInformationPage.getFirstnameDomAttribute("value"), firstname.trim());

			softassert.assertEquals(myaccountInformationPage.getlastnameDomAttribute("value"), lastname.trim());

			softassert.assertEquals(myaccountInformationPage.getEmailDomAttribute("value"), emailText.trim());

			softassert.assertEquals(myaccountInformationPage.getTelePhoneDomAttribute("value"), Telephone.trim());

			softassert.assertAll();

		} else if (browserName.equals("firefox")) {

			String expectedwarningmessageOne = "Please enter an email address.";

			Assert.assertEquals(myaccountInformationPage.getEmailDomAttribute("validationMessage"),
					expectedwarningmessageOne);
		}
	}

	@Test(priority = 20)
	public void VerifywhetherthePrivacyPolicycheckboxoptionisnotselectedbydefault() {

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

		Assert.assertFalse(registerpage.isPrivacyPolicFieldisSelected());

	}

	@Test(priority = 21)
	public void VerifyRegisteringtheAccountwithoutselectingthePrivacyPolicycheckboxoption() {

		registerpage.enterFirstName(prop.getProperty("firstName"));

		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));

		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.selectNewSeletterOption();

		registerpage.clickOnContinueButton();

		Assert.assertEquals(registerpage.getpageLevelWarning(), "Warning: You must agree to the Privacy Policy!");

	}

	@Test(priority = 22)
	public void verifypasswordandpasswordconfirmtoggleishiddenstate() {

		Assert.assertEquals(registerpage.getPasswordFieldDomAttribute("type"), "password");
		Assert.assertEquals(registerpage.getPasswordConfirmFieldDomAttribute("type"), "password");

	}
	// Getting Assertion Error For fireFox 844 line
	@Test(priority = 23)
	public void verifynavigatingtotheotherpagesusingoptionslinks() {

		headeroptions = registerpage.getHeaderoptions();
		contactUsPage = headeroptions.SelectPhoneIcon();
		Assert.assertEquals(getPageTitle(headeroptions.getDriver()), "Contact Us");
		navigateBackInBrowser(headeroptions.getDriver());

		loginPage = headeroptions.SelectHeartIconOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = headeroptions.SelectWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		shoppingCartPage = headeroptions.SelectShoppingCartOption();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headeroptions.SelectCheckoutHeaderIcon();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headeroptions.SelectCheckoutHeaderIcon();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headeroptions.SelectCheckOutOption();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		homePage = headeroptions.SelectLogo();
		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		searchPage = headeroptions.ClickOnSearchButton();
		Assert.assertEquals(getPageTitle(searchPage.getDriver()), "Search");
		navigateBackInBrowser(searchPage.getDriver());

		homePage = headeroptions.SelectHomeBreadCrumbOption();
		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		loginPage = headeroptions.SelectAccountBreadCrumbWithoutLogin();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		registerpage = registerpage.SelectRegisterPageBreadCrumbOption();
		Assert.assertEquals(getPageTitle(registerpage.getDriver()), "Register Account");

		loginPage = registerpage.SelectLoginPageOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		rightcolumoptions = new RightColumOptions(driver);

		loginPage = rightcolumoptions.ClickOnLoginOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		forgottenPasswordPage = rightcolumoptions.ClickOnForgottenPasswordOption();
		Assert.assertEquals(getPageTitle(forgottenPasswordPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgottenPasswordPage.getDriver());

		loginPage = rightcolumoptions.ClickOnMyAcccountOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightcolumoptions.ClickOnAddressBookOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightcolumoptions.ClickOnWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightcolumoptions.ClickOnOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightcolumoptions.ClickOnDownloadsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightcolumoptions.ClickOnRecurringPaymentsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightcolumoptions.ClickOnRewardspointOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightcolumoptions.ClickOnReturnsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightcolumoptions.ClickOnTransactionsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightcolumoptions.ClickOnNewsletterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		footerOptions = new FooterOptions(driver);
		aboutUspage = footerOptions.SelectAboutUsoption();
		Assert.assertEquals(getPageTitle(aboutUspage.getDriver()), "About Us");
		navigateBackInBrowser(aboutUspage.getDriver());

		deliveryInformationPage = footerOptions.SelectDeliveryInformationOpton();
		Assert.assertEquals(getPageTitle(deliveryInformationPage.getDriver()), "Delivery Information");
		navigateBackInBrowser(deliveryInformationPage.getDriver());

		
		privacypolicypage = footerOptions.SelectPrivacyPolicyOption();
		Assert.assertEquals(getPageTitle(privacypolicypage.getDriver()), "Privacy Policy");
		navigateBackInBrowser(privacypolicypage.getDriver());

		termsandConditionsPage = footerOptions.SelectTermsConditionsOption();
		Assert.assertEquals(getPageTitle(termsandConditionsPage.getDriver()), "Terms & Conditions");
		navigateBackInBrowser(termsandConditionsPage.getDriver());

		contactUsPage = footerOptions.SelectcontactUsPage();
		Assert.assertEquals(getPageTitle(contactUsPage.getDriver()), "Contact Us");
		navigateBackInBrowser(contactUsPage.getDriver());

		productReturnsPage = footerOptions.SelectProductReturnsPage();
		Assert.assertEquals(getPageTitle(productReturnsPage.getDriver()), "Product Returns");
		navigateBackInBrowser(productReturnsPage.getDriver());
		

		siteMappage = footerOptions.SelectSiteMapPage();
		Assert.assertEquals(getPageTitle(siteMappage.getDriver()), "Site Map");
		navigateBackInBrowser(siteMappage.getDriver());
		
		
		 brandsPage = footerOptions.SelectBrandsPageOption();
		Assert.assertEquals(getPageTitle(brandsPage.getDriver()), "Find Your Favorite Brand");
		navigateBackInBrowser(brandsPage.getDriver());

		giftCertificatesPage = footerOptions.SelectGiftCertificatesOption();
		Assert.assertEquals(getPageTitle(giftCertificatesPage.getDriver()), "Purchase a Gift Certificate");
		navigateBackInBrowser(giftCertificatesPage.getDriver());

		affiliatePrograPage=footerOptions.SelectaffiliateOption();
		Assert.assertEquals(getPageTitle(affiliatePrograPage.getDriver()), "Affiliate Program");
		navigateBackInBrowser(affiliatePrograPage.getDriver());

		specialOffersPage=footerOptions.SelectSpecialOffersPage();
		Assert.assertEquals(getPageTitle(specialOffersPage.getDriver()), "Special Offers");
		navigateBackInBrowser(specialOffersPage.getDriver());

		loginPage=footerOptions.SelectMyaccountPage();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());
		
		loginPage=footerOptions.SelectorderHistoryPage();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());
		
		loginPage=footerOptions.SelectWishListPage();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		
		loginPage=footerOptions.SelectNewsletterPage();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		
		

	}

	@Test(priority = 24)
	public void verifywhenuserwillnotpasswordconfirm() {

		registerpage.enterFirstName(prop.getProperty("firstName"));
		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();

		Assert.assertEquals(registerpage.getPasswordConfirmationWarning(),
				"Password confirmation does not match password!");

	}

	@Test(priority = 25)
	public void VerifytheBreadcrumbPageHeadingPageURLPageTitleofRegisterAccountPage() {

		Assert.assertEquals(getPageTitle(registerpage.getDriver()), "Register Account");

		Assert.assertEquals(getPageUrl(registerpage.getDriver()), prop.getProperty("RegisterPageUrl"));

		Assert.assertTrue(registerpage.DidWeNavigateToRegisterPage());

		Assert.assertEquals(registerpage.GetPageHeading(), "Register Account");

	}

	@Test(priority = 26)
	public void verifyUIofRegisterAccountPage() {

		CommonUtilities.scrennshots(driver, System.getProperty("user.dir") + "/Screenshots/actualRegisterPageUI.png");

		Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
				System.getProperty("user.dir") + "/Screenshots/actualRegisterPageUI.png",
				System.getProperty("user.dir") + "\\Screenshots\\expectedRegisterPageUI.png"));

	}

	@Test(priority = 27)

	public void VerifyRegisterAccountfunctionalityinallthesupportedenvironments() {

		registerpage.enterFirstName(prop.getProperty("firstName"));

		registerpage.enterLastName(prop.getProperty("lastName"));
		registerpage.EnterEmail(CommonUtilities.generateBrandNewEmail());
		registerpage.EnterTelePhoneNumber(prop.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerpage.selectNewSeletterOption();
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage = registerpage.clickOnContinueButton();

		Assert.assertTrue(accountsuccesspage.isUserLoggedIn());

	}

}
