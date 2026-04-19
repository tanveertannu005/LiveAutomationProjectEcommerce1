package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class RightColumOptions extends RootPage {
	WebDriver driver;
	
	public RightColumOptions(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath  = "//aside//a[text()='Logout']")
	private WebElement logoutOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Register']")
	private WebElement registerOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Login']")
	private WebElement LoginOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Forgotten Password']")
	private WebElement forGottenPasswordOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='My Account']")
	private WebElement MyAccountOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Address Book']")
	private WebElement AddressBookOption;
	
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Wish List']")
	private WebElement WishListOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Order History']")
	private WebElement OrderHistoryOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Downloads']")
	private WebElement DownloadsOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Recurring payments']")
	private WebElement RecurringPaymentsOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Reward Points']")
	private WebElement RewardPointsOption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Returns']")
	private WebElement Returnsoption;
	
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Transactions']")
	private WebElement Transactionsoption;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Newsletter']")
	private WebElement Newsletteroption;
	
	
	public LoginPage ClickOnNewsletterOption() {
		elementUtilities.ClickOnElement(Newsletteroption);
		return new LoginPage(driver);
	}
	
	public LoginPage ClickOnTransactionsOption() {
		elementUtilities.ClickOnElement(Transactionsoption);
		return new LoginPage(driver);
	}
	
	
	public LoginPage ClickOnReturnsOption() {
		elementUtilities.ClickOnElement(Returnsoption);
		return new LoginPage(driver);
	}
	
	public LoginPage ClickOnRewardspointOption() {
		elementUtilities.ClickOnElement(RewardPointsOption);
		return new LoginPage(driver);
	}
	
	public LoginPage ClickOnRecurringPaymentsOption() {
		elementUtilities.ClickOnElement(RecurringPaymentsOption);
		return new LoginPage(driver);
	}
	
	public LoginPage ClickOnDownloadsOption() {
		elementUtilities.ClickOnElement(DownloadsOption);
		return new LoginPage(driver);
	}
	
	public LoginPage ClickOnOrderHistoryOption() {
		elementUtilities.ClickOnElement(OrderHistoryOption);
		return new LoginPage(driver);
	}
	
	public LoginPage ClickOnWishListOption() {
		elementUtilities.ClickOnElement(WishListOption);
		 return new LoginPage(driver);
	}

	public LoginPage ClickOnAddressBookOption() {
		elementUtilities.ClickOnElement(AddressBookOption);
		return new LoginPage(driver);
	}
	
	public LoginPage ClickOnMyAcccountOption() {
		elementUtilities.ClickOnElement(MyAccountOption);
		return new LoginPage(driver);
	}
	
	
	
	public  ForgottenPasswordPage ClickOnForgottenPasswordOption() {
		elementUtilities.ClickOnElement(forGottenPasswordOption);
		return new ForgottenPasswordPage(driver);
	}
	
	
	public LoginPage ClickOnLoginOption() {
		elementUtilities.ClickOnElement(LoginOption);
		return new LoginPage(driver);
	}
	
	public boolean DidWeGetLoggedIn() {
		return elementUtilities.IsElementDisplayed(logoutOption);
		
	}
	
	
	public RegisterPage ClickOnRegisterOption() {
		elementUtilities.ClickOnElement(registerOption);
		return new RegisterPage(driver);
	}
}
