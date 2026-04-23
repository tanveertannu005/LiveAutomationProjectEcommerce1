package pages;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utilities.CommonUtilities;

public class LoginPage extends RootPage {
	WebDriver driver;
	
	public  LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	@FindBy(id = "input-email")
	private WebElement emailField;
	
	@FindBy(id = "input-password")
	private WebElement PasswordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadCrumb;
	
	
	@FindBy(linkText = "Forgotten Password")
	private WebElement ForgottenPaswordLink;
	
	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement ContinueSuccessPage;
	
	@FindBy(xpath = "(//div[@id='content']//h2)[1]")
	private WebElement firstHeading;
	
	@FindBy(xpath = "(//div[@id='content']//h2)[2]")
	private WebElement secondHeading;
	
	public String getFirstHeading() {
	return	elementUtilities.getElementText(firstHeading);
		
	}
	
	public String GetSecondHeading() {
	return	elementUtilities.getElementText(secondHeading);
	}
	
	public RegisterPage clickOnContinueButton() {
		elementUtilities.ClickOnElement(ContinueSuccessPage);
		return new RegisterPage(driver);
	}
	

	
	public LoginPage selectLoginBreadcrumboption() {
		elementUtilities.ClickOnElement(loginBreadCrumb);
		return new LoginPage(driver);
	}
	
	
	
	
	public String getPasswordFieldDomAttribute(String attributeName) {
	return	elementUtilities.getelementDomAttribute(PasswordField, attributeName);
	}
	
	public void CopyPasswordFromPasswordField() {
		elementUtilities.copyingTextUsingKeyboardKeys(driver);
	}
	
	public void pasteCopiedTextIntoEmailField() {
		elementUtilities.pasteTextIntoFieldUsingKeyboardkeys(emailField, driver);
		
				}
	
	
	
	public String GetEmailFieldPlaceholderText() {
		return elementUtilities.getelementDomAttribute(emailField, "placeholder");
}
	public String GetPasswordFieldPlaceholderText() {
		return elementUtilities.getelementDomAttribute(PasswordField, "placeholder");
}
	
	public String getPastedTextFromEmailField() {
	return	elementUtilities.getelementDomAttribute(emailField, "value");
	}
	
	
	
	public ForgottenPasswordPage ClickOnForgottenPasswordLink() {
		elementUtilities.ClickOnElement(ForgottenPaswordLink);
		return new ForgottenPasswordPage(driver);
	}
	
	public MyAccountPage ClickOnLoginButton() {
		elementUtilities.ClickOnElement(loginButton);
		return new MyAccountPage(driver);
	}
	
	public void enterPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(PasswordField, passwordText);
	}
	
	public void enterEmail(String emailText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
	}
	
	
	
	
	public boolean didWeNavigateToLogin() {
	return	elementUtilities.IsElementDisplayed(loginBreadCrumb);
	}
	
	public MyAccountPage loginIntoApplication(String emailText, String passwordText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
		elementUtilities.enterTextIntoElement(PasswordField, passwordText);
		elementUtilities.ClickOnElement(loginButton);
	
		return new MyAccountPage(driver);
	}
	
}
