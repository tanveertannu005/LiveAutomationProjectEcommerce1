package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountLogoutPage;
import pages.HeaderOptions;
import utilities.CommonUtilities;

public class Logout extends Base {
	WebDriver driver;

	@BeforeMethod
	public void Setup() {
		driver = openBrowserAndApplicationURL();
		headeroptions = new HeaderOptions(driver);

	}

	

	@Test(priority = 1)
	public void VerifyLoggingoutUsingMyAccountLogoutOption() {
		loginPage = headeroptions.navigateToLoginPage();
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		headeroptions = myaccountpage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		accountLogoutPage = headeroptions.SelectLogoutoption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		headeroptions = accountLogoutPage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		Assert.assertTrue(headeroptions.isLoginOptionMyAccountDropMenuDisplayed());
		accountLogoutPage = headeroptions.getAccountLogoutPage();
		homePage = accountLogoutPage.ClickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

	@Test(priority = 2)
	public void verifyLoggingOutUsingrightColumOptions() {
		loginPage = headeroptions.navigateToLoginPage();
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		rightcolumoptions = myaccountpage.getRightColumnOptions();
		accountLogoutPage = rightcolumoptions.clickOnLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());

		headeroptions = accountLogoutPage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		Assert.assertTrue(headeroptions.isLoginOptionMyAccountDropMenuDisplayed());
		accountLogoutPage = headeroptions.getAccountLogoutPage();
		homePage = accountLogoutPage.ClickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

	@Test(priority = 3)
	public void verifyLoggingoutAndBrowsingBack() {
		loginPage = headeroptions.navigateToLoginPage();
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		headeroptions = myaccountpage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		accountLogoutPage = headeroptions.SelectLogoutoption();
		navigateBackInBrowser(driver);
		refreshPage(driver);
		headeroptions = accountLogoutPage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		Assert.assertTrue(headeroptions.isLoginOptionMyAccountDropMenuDisplayed());

	}

	@Test(priority = 4)
	public void NoLogoutOptionBeforeLoginInMyAccountdropMenu() {
		headeroptions.ClickOnMyAccountDropMenu();
		Assert.assertFalse(headeroptions.isLogoutOptionUnderMyaccountDropMenuisDisplayed());
	}

	@Test(priority = 5)
	public void verifynoLogoutOptionBeforeLoginInrightColumoptions() {
		headeroptions.ClickOnMyAccountDropMenu();
		registerpage = headeroptions.SelectOnRegisterOption();
		rightcolumoptions = registerpage.getRightColumnOptions();
		Assert.assertFalse(rightcolumoptions.isLogoutOptionUnderMyaccountDropMenuisDisplayed());
	}

	@Test(priority = 6)
	public void verifyLoginImmediatelyAfterLogout() {
		loginPage = headeroptions.navigateToLoginPage();
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		headeroptions = myaccountpage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		accountLogoutPage = headeroptions.SelectLogoutoption();
		headeroptions.ClickOnMyAccountDropMenu();
		loginPage = headeroptions.navigateToLoginPage();
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(myaccountpage.didwenavigateToMyAccountPage());

	}

	@Test(priority = 7)
	public void verifyBreadCrumbTitleUrlHeadingOfAccountLogoutPage() {
		loginPage = headeroptions.navigateToLoginPage();
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		headeroptions = myaccountpage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		accountLogoutPage = headeroptions.SelectLogoutoption();

		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Account Logout");
		Assert.assertEquals(getPageUrl(accountLogoutPage.getDriver()), prop.getProperty("logOutPageUrl"));
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		Assert.assertEquals(accountLogoutPage.GetPageHeading(), "Account Logout");
	}

	@Test(priority = 8)
	public void verfiyLogoutUI() throws IOException {
		loginPage = headeroptions.navigateToLoginPage();
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		headeroptions = myaccountpage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();

		if (browserName.equalsIgnoreCase("chrome")) {

			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualChromeLogoutUI.png");

			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualChromeLogoutUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedChromeLogoutUI.png"));

		} else if (browserName.equalsIgnoreCase("firefox")) {

			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutUI.png");

			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLogoutUI.png"));

		} else if (browserName.equalsIgnoreCase("edge")) {

			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutUI.png");

			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLogoutUI.png"));
		}
	}
	
	@Test(priority = 9)
	public void VerfiyLogoutInAllsupportedEnvironments() {
		loginPage = headeroptions.navigateToLoginPage();
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		headeroptions = myaccountpage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		accountLogoutPage = headeroptions.SelectLogoutoption();
		
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		headeroptions = accountLogoutPage.getHeaderoptions();
		headeroptions.ClickOnMyAccountDropMenu();
		Assert.assertTrue(headeroptions.isLoginOptionMyAccountDropMenuDisplayed());
		accountLogoutPage = headeroptions.getAccountLogoutPage();
		homePage = accountLogoutPage.ClickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

}
