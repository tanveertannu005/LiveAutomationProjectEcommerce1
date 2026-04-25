package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class LogoutPage  extends RootPage{
	WebDriver driver;
	
	public LogoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Logout']")
	private WebElement logoutbreadCrumb;
	
	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement ContinueButton;
	
	public HomePage clickOnContinueButton() {
		elementUtilities.ClickOnElement(ContinueButton);
		return new HomePage(driver);
	}
	
	public boolean didWeNavigateToAccountLogoutPage() {
	return	elementUtilities.IsElementDisplayed(logoutbreadCrumb);
	}
	
	
	public LoginPage getLoginPage() {
		return new LoginPage(driver);
	}
}
