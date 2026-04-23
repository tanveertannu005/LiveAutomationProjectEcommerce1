package base;


import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import pages.AboutUspage;
import pages.AccountLogoutPage;
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
import pages.LogoutPage;
import pages.MyAccountInformationPage;
import pages.MyAccountPage;
import pages.NewsleterPage;
import pages.PasswordChangePage;
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

public class Base {

	WebDriver driver;
	public Properties prop;
	public String browserName;
	public HeaderOptions headeroptions;
	public RegisterPage registerpage;
	public AccountSuccessPage accountsuccesspage;
	public MyAccountPage myaccountpage;
	public NewsleterPage newsletterPage;
	public LoginPage loginPage;
	public RightColumOptions rightcolumoptions;
	public MyAccountInformationPage myaccountInformationPage;
	public ContactUsPage contactUsPage;
	public ShoppingCartPage shoppingCartPage;
	public HomePage homePage;
	public SearchPage searchPage;
	public ForgottenPasswordPage forgottenPasswordPage;
	public FooterOptions footerOptions;
	public AboutUspage aboutUspage;
	public DeliveryInformationPage deliveryInformationPage;
	public Privacypolicypage privacypolicypage;
	public TermsandConditionsPage termsandConditionsPage;
	public ProductReturnsPage productReturnsPage;
	public SiteMappage siteMappage;
	public BrandsPage brandsPage;
	public GiftCertificatesPage giftCertificatesPage;
	public AffiliatePrograPage affiliatePrograPage;
	public SpecialOffersPage specialOffersPage;
	public Actions actions;
	public LogoutPage logoutPage;
	public PasswordChangePage  passwordChangePage;
	public AccountLogoutPage accountLogoutPage;				

	public WebDriver openBrowserAndApplicationURL() {

		prop = CommonUtilities.loadPropertiesFiles();
		browserName = prop.getProperty("browserName");

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {

			String path = System.getProperty("user.dir") + "\\src\\test\\resources\\EdgeDriver\\msedgedriver.exe";
			System.setProperty("webdriver.edge.driver", path);
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("appURL"));

		return driver;
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void navigateBackInBrowser(WebDriver driver) {
		driver.navigate().back();

	}

	public void CloseBrowser(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}
	}

	public Actions getActions(WebDriver driver) {
		Actions actions = new Actions(driver);
		return actions;
	}

	public Actions ClickKeyBoardKeyMultipleTimes(Actions actions, Keys keyName, int noOfTimes) {

		for (int i = 1; i <= noOfTimes; i++) {
			actions.sendKeys(keyName).perform();
		}
		return actions;
	}

	public Actions typeTextIntoFieldsUsingActions(Actions actions, String text) {
		actions.sendKeys(text).perform();
		return actions;
	}
	
	public Properties swapPassword(Properties prop) {
		String oldPassword = prop.getProperty("validPasswordTwo");
		String newPassword = prop.getProperty("validPasswordThree");
		prop.setProperty("validPasswordTwo", newPassword);
		prop.setProperty("validPasswordThree", oldPassword);
		prop=	CommonUtilities.storePropertiesFile(prop);
		return prop;
	}
}
