package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AccountLogoutPage  extends RootPage{
WebDriver driver;
	
	public AccountLogoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath = "//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement continueButton;
	
	public HomePage ClickOnContinueButton() {
		elementUtilities.ClickOnElement(continueButton);
		return new HomePage(driver);
	}
	
}
