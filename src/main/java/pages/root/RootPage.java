package pages.root;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.AccountSuccessPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RightColumOptions;
import utilities.ElementUtilities;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RootPage {

	WebDriver driver;
	
	public ElementUtilities elementUtilities;

	public RootPage(WebDriver driver) {
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement PageHeading;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[contains(@href,'common/home')]")
	private WebElement HomeBreadCrumb;

	@FindBy(linkText = "login page")
	private WebElement AccountBreadCrumb;

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

}
