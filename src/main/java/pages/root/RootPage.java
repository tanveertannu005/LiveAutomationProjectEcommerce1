package pages.root;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.AccountLogoutPage;
import pages.AccountSuccessPage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.MyAccountPage;
import pages.RightColumOptions;
import utilities.ElementUtilities;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RootPage {

	WebDriver driver;
	
	public ElementUtilities elementUtilities;
	public Properties prop;

	public RootPage(WebDriver driver) {
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement pageLevelWarning;
	
	@FindBy(xpath ="//div[@class='alert alert-success alert-dismissible']" )
	private WebElement getPageLevelSuccessMessage;
	
	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement PageHeading;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[contains(@href,'common/home')]")
	private WebElement HomeBreadCrumb;

	@FindBy(linkText = "login page")
	private WebElement AccountBreadCrumb;
	
	public String getpageLevelSuccessMessage() {
		return	elementUtilities.getElementText(getPageLevelSuccessMessage);
			
		}
	
	public String getpageLevelWarning() {
		return	elementUtilities.getElementText(pageLevelWarning);
			
		}
	

	public LoginPage SelectAccountBreadCrumbWithoutLogin() {
		elementUtilities.ClickOnElement(AccountBreadCrumb);
		return new LoginPage(driver);
	}

	public HomePage SelectHomeBreadCrumbOption() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(HomeBreadCrumb));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", HomeBreadCrumb);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", HomeBreadCrumb);

		return new HomePage(driver);
	}

	
	public String GetPageHeading() {
		return elementUtilities.getElementText(PageHeading);
		
	}

	public WebDriver getDriver() {
		return driver;
	}

	public RightColumOptions getRightColumnOptions() {
		return new RightColumOptions(driver);
	}

	public AccountSuccessPage getAccountSuccessPage() {
		return new AccountSuccessPage(driver);
	}
	
	public AccountLogoutPage  getAccountLogoutPage() {
		return new AccountLogoutPage(driver);
	}

	public HeaderOptions getHeaderoptions() {
		
		return new HeaderOptions(driver);
	}
}
