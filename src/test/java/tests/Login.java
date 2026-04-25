package tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountLogoutPage;
import pages.AccountSuccessPage;
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.LogoutPage;
import pages.MyAccountPage;
import pages.PasswordChangePage;
import pages.RightColumOptions;
import utilities.CommonUtilities;
import utilities.ElementUtilities;

public class Login extends Base {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = openBrowserAndApplicationURL();
		headeroptions = new HeaderOptions(driver);
		headeroptions.ClickOnMyAccountDropMenu();
		loginPage = headeroptions.SelectLoginOption();

	}

	
	@Test(priority = 1)
	public void VerifyLoggingIntoApplicationUsingValidCredentials() {

		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		myaccountpage = loginPage.ClickOnLoginButton();
		Assert.assertTrue(myaccountpage.didwenavigateToMyAccountPage());

	}

	@Test(priority = 2)
	public void VerifyloggingintotheApplicationusinginvalidcredential() {
		loginPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("mismatchingPassword"));
		loginPage.ClickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getpageLevelWarning(), expectedWarning);
	}

	@Test(priority = 3)
	public void VerifyloggingintotheApplicationusinginvalidemailaddressandvalidPassword() {
		loginPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.ClickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getpageLevelWarning(), expectedWarning);
	}

	@Test(priority = 4)
	public void VerifyloggingintotheApplicationusingvalidemailaddressandinvalidPassword() {

		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("mismatchingPassword"));
		loginPage.ClickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getpageLevelWarning(), expectedWarning);

	}

	@Test(priority = 5)
	public void VerifyloggingintotheApplicationwithoutprovidinganycredentials() {
		loginPage.ClickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getpageLevelWarning(), expectedWarning);
	}

	@Test(priority = 6)
	public void VerifyForgottenPasswordlinkisavailableintheLoginpageandisworking() {
		forgottenPasswordPage = loginPage.ClickOnForgottenPasswordLink();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgotPasswordPage());
	}

	@Test(priority = 7)
	public void VerifyloggingintotheApplicationusingkeyboardkeys() {
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 23);
		actions = typeTextIntoFieldsUsingActions(actions, prop.getProperty("validEmail"));
		actions = ClickKeyBoardKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextIntoFieldsUsingActions(actions, prop.getProperty("validPassword"));
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 2);
		actions = ClickKeyBoardKeyMultipleTimes(actions, Keys.ENTER, 1);

		myaccountpage = new MyAccountPage(driver);
		Assert.assertTrue(myaccountpage.didwenavigateToMyAccountPage());

	}

	@Test(priority = 8)
	public void VerifyEMailAddressandPasswordtextfieldsintheLoginpagehavetheplaceholdertext() {
		Assert.assertEquals(loginPage.GetEmailFieldPlaceholderText(), "E-Mail Address");
		Assert.assertEquals(loginPage.GetPasswordFieldPlaceholderText(), "Password");
	}

	@Test(priority = 9)
	public void VerifyBrowsingBackAfterLogin() {
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		navigateBackInBrowser(myaccountpage.getDriver());
		refreshPage(myaccountpage.getDriver());
		Assert.assertTrue(myaccountpage.didwenavigateToMyAccountPage());
	}

	@Test(priority = 10)
	public void VerifyBrowsingBackAfterLogout() {
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		headeroptions = myaccountpage.getHeaderoptions();
		accountLogoutPage = headeroptions.SelectLogoutoption();
		navigateBackInBrowser(logoutPage.getDriver());
		refreshPage(logoutPage.getDriver());
		loginPage = logoutPage.getLoginPage();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

	}

	@Test(priority = 11)
	public void verifyLoggingIntoApplicationUsingInactiveCredentials() {
		loginPage.loginIntoApplication(prop.getProperty("inactiveEmail", browserName),
				prop.getProperty("validPassword"));
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getpageLevelWarning(), expectedWarning);
	}

	@Test(priority = 12)
	public void VerifyNumberOfUnsuccessfullLoginAttempts() {
		String invalidEmail = CommonUtilities.generateBrandNewEmail();
		loginPage.loginIntoApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		loginPage.loginIntoApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		loginPage.loginIntoApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		loginPage.loginIntoApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		loginPage.loginIntoApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		loginPage.loginIntoApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		String expectedWarning = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
		Assert.assertEquals(loginPage.getpageLevelWarning(), expectedWarning);
	}

	@Test(priority = 13)
	public void verifyLoginPasswordFieldforVisibility() {
		Assert.assertEquals(loginPage.getPasswordFieldDomAttribute("type"), "password");

	}

	@Test(priority = 14)
	public void verifyCopyingofTextEnterIntoPasswordField() {
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.CopyPasswordFromPasswordField();
		loginPage.pasteCopiedTextIntoEmailField();
		loginPage.ClickOnLoginButton();
		Assert.assertNotEquals(loginPage.getPastedTextFromEmailField(), prop.getProperty("validPassword"));

	}

	// Defect In The application for this test case please refer TestCase Doc
	@Test(priority = 15)
	public void verifyPasswordIsnotVisibleInPageSource() {
		loginPage.enterPassword(prop.getProperty("randomPasword"));
		Assert.assertFalse(getPageSource(loginPage.getDriver()).contains(prop.getProperty("randomPasword")));
		loginPage.ClickOnLoginButton();
		Assert.assertFalse(getPageSource(loginPage.getDriver()).contains(prop.getProperty("randomPasword")));

	}

	// Test Case 16 is right facing timeout issues will fix it//Fixed it Date
	// 22/04/2026// very important Testcase here we are using set property and using
	// store properties using swap passwords here

	// Need to fix the test case
	@Test(priority = 17)
	public void verifyLoginPageNavigations() {

		headeroptions = loginPage.getHeaderoptions();

		contactUsPage = headeroptions.SelectPhoneIcon();
		Assert.assertEquals(getPageTitle(driver), "Contact Us");
		navigateBackInBrowser(driver);

		loginPage = headeroptions.SelectHeartIconOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = headeroptions.SelectWishListOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		shoppingCartPage = headeroptions.SelectShoppingCartOption();
		Assert.assertEquals(getPageTitle(driver), "Shopping Cart");
		navigateBackInBrowser(driver);

		shoppingCartPage = headeroptions.SelectCheckoutHeaderIcon();
		Assert.assertEquals(getPageTitle(driver), "Shopping Cart");
		navigateBackInBrowser(driver);

		shoppingCartPage = headeroptions.SelectCheckOutOption();
		Assert.assertEquals(getPageTitle(driver), "Shopping Cart");
		navigateBackInBrowser(driver);

		homePage = headeroptions.SelectLogo();
		Assert.assertEquals(getPageTitle(driver), "Your Store");
		navigateBackInBrowser(driver);

		searchPage = headeroptions.ClickOnSearchButton();
		Assert.assertEquals(getPageTitle(driver), "Search");
		navigateBackInBrowser(driver);

		homePage = headeroptions.SelectHomeBreadCrumbOption();
		Assert.assertEquals(getPageTitle(driver), "Your Store");
		navigateBackInBrowser(driver);

		loginPage = headeroptions.SelectAccountBreadCrumbWithoutLogin();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = loginPage.selectLoginBreadcrumboption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		registerpage = loginPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(driver), "Register Account");
		navigateBackInBrowser(driver);

		forgottenPasswordPage = loginPage.ClickOnForgottenPasswordLink();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgotPasswordPage());
		navigateBackInBrowser(driver);

		rightcolumoptions = new RightColumOptions(driver);

		loginPage = rightcolumoptions.ClickOnLoginOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		forgottenPasswordPage = rightcolumoptions.ClickOnForgottenPasswordOption();
		Assert.assertEquals(getPageTitle(driver), "Forgot Your Password?");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnMyAcccountOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnAddressBookOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnWishListOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnOrderHistoryOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnDownloadsOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnRecurringPaymentsOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnRewardspointOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnReturnsOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnTransactionsOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = rightcolumoptions.ClickOnNewsletterOption();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		footerOptions = new FooterOptions(driver);

		aboutUspage = footerOptions.SelectAboutUsoption();
		Assert.assertEquals(getPageTitle(driver), "About Us");
		navigateBackInBrowser(driver);

		deliveryInformationPage = footerOptions.SelectDeliveryInformationOpton();
		Assert.assertEquals(getPageTitle(driver), "Delivery Information");
		navigateBackInBrowser(driver);

		privacypolicypage = footerOptions.SelectPrivacyPolicyOption();
		Assert.assertEquals(getPageTitle(driver), "Privacy Policy");
		navigateBackInBrowser(driver);

		termsandConditionsPage = footerOptions.SelectTermsConditionsOption();
		Assert.assertEquals(getPageTitle(driver), "Terms & Conditions");
		navigateBackInBrowser(driver);

		contactUsPage = footerOptions.SelectcontactUsPage();
		Assert.assertEquals(getPageTitle(driver), "Contact Us");
		navigateBackInBrowser(driver);

		productReturnsPage = footerOptions.SelectProductReturnsPage();
		Assert.assertEquals(getPageTitle(driver), "Product Returns");
		navigateBackInBrowser(driver);

		siteMappage = footerOptions.SelectSiteMapPage();
		Assert.assertEquals(getPageTitle(driver), "Site Map");
		navigateBackInBrowser(driver);

		brandsPage = footerOptions.SelectBrandsPageOption();
		Assert.assertEquals(getPageTitle(driver), "Find Your Favorite Brand");
		navigateBackInBrowser(driver);

		giftCertificatesPage = footerOptions.SelectGiftCertificatesOption();
		Assert.assertEquals(getPageTitle(driver), "Purchase a Gift Certificate");
		navigateBackInBrowser(driver);

		affiliatePrograPage = footerOptions.SelectaffiliateOption();
		Assert.assertEquals(getPageTitle(driver), "Affiliate Program");
		navigateBackInBrowser(driver);

		specialOffersPage = footerOptions.SelectSpecialOffersPage();
		Assert.assertEquals(getPageTitle(driver), "Special Offers");
		navigateBackInBrowser(driver);

		loginPage = footerOptions.SelectMyaccountPage();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = footerOptions.SelectorderHistoryPage();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = footerOptions.SelectWishListPage();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
		navigateBackInBrowser(driver);

		loginPage = footerOptions.SelectNewsletterPage();
		Assert.assertEquals(getPageTitle(driver), "Account Login");
	}

	@Test(priority = 18)
	public void verifyDiffrentwaysOfnavigatingTologinPage() {
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		rightcolumoptions = loginPage.getRightColumnOptions();
		rightcolumoptions.ClickOnLoginOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		registerpage = loginPage.clickOnContinueButton();
		loginPage = registerpage.SelectLoginPageOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

	}

	@Test(priority = 19)
	public void VerifytheBreadcrumbPageHeadingPageURLPageTitleofRegisterLoginPage() {

		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		Assert.assertEquals(getPageUrl(loginPage.getDriver()), prop.getProperty("loginPageUrl"));
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		Assert.assertEquals(loginPage.getFirstHeading(), "New Customer");
		Assert.assertEquals(loginPage.GetSecondHeading(), "Returning Customer");
	}
	@Test(priority = 20)
	public void verifyLoginPageUi() {
		CommonUtilities.scrennshots(driver, System.getProperty("user.dir") + "/Screenshots/actualLoginPageUI.png");

		Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
				System.getProperty("user.dir") + "/Screenshots/actualLoginPageUI.png",
				System.getProperty("user.dir") + "\\Screenshots\\expectedRegisterPageUI.png"));
	}
	@Test(priority = 21)

	public void VerifyLogininallthesupportedenvironments() {
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		myaccountpage = loginPage.ClickOnLoginButton();
		Assert.assertTrue(myaccountpage.didwenavigateToMyAccountPage());

	}

}