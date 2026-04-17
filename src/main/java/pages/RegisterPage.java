package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utilities.CommonUtilities;

public class RegisterPage extends RootPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement isNewSeletterIsselected;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement IsNewsletterIsNotSelected;

	@FindBy(id = "input-firstname")
	private WebElement FirstNameField;

	@FindBy(id = "input-lastname")
	private WebElement LastNameField;

	@FindBy(id = "input-email")
	private WebElement EmailField;

	@FindBy(id = "input-telephone")
	private WebElement TelephoneField;

	@FindBy(id = "input-password")
	private WebElement PasswordField;

	@FindBy(id = "input-confirm")
	private WebElement PasswordConfirmField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement ContinueButton;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement EmailWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement PasswordWarning;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement pageLevelWarning;
	
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement registerPageBreadCrumb;
	
	@FindBy(className = "text-danger")
	private WebElement passwordConfirmationWarning;
	
	@FindBy(css = "label[for='input-firstname']")
	private WebElement firstNamefieldlabel;
	
	@FindBy(css = "label[for='input-lastname']")
	private WebElement LastNamefieldlabel;
	
	@FindBy(css = "label[for='input-email']")
	private WebElement emailfieldlabel;
	
	@FindBy(css = "label[for='input-telephone']")
	private WebElement Telephonefieldlabel;
	
	@FindBy(css = "label[for='input-password']")
	private WebElement passwordfieldlabel;
	
	@FindBy(css="label[for='input-confirm']")
	private WebElement passwordConfirmFieldLabel;
	
	@FindBy(linkText = "login page")
	private WebElement LoginPageOption;
	
	public LoginPage SelectLoginPageOption() {
		LoginPageOption.click();
		return new LoginPage(driver);
	}
	
	public HeaderOptions getHeaderoptions() {
		return new HeaderOptions(driver);
	}
	
	public String getPasswordConfirmFieldDomAttribute(String attributeName) {
		return PasswordConfirmField.getDomAttribute(attributeName);
	}
	
	
	public String getPasswordFieldDomAttribute(String attributeName) {
		return PasswordField.getDomAttribute(attributeName);
	}
	
	public boolean  isPrivacyPolicFieldisSelected() {
		return   privacyPolicyField.isSelected();
	}
	
	public  WebElement getFirstNameFieldElement() {
		return FirstNameField;
	}
	
	public  WebElement getLastNameFieldElement() {
		return LastNameField;
	}
	
	public  WebElement getEmailFieldElement() {
		return EmailField;
	}
	
	public  WebElement TelephoneFieldElement() {
		return TelephoneField;
	}

	public  WebElement GetPasswordFieldElement() {
		return PasswordField;
	}
	public  WebElement GetPasswordConfirmFieldElement() {
		return PasswordConfirmField;
	}
	public  WebElement GetContinueButtonElement() {
		return ContinueButton ;
	}
	public WebElement getPasswordConfirmFieldlabelelement() {
	    return passwordConfirmFieldLabel;
	}
	
	public WebElement getPasswordFieldlabelelement() {
		return passwordfieldlabel;
	}
	
	public WebElement getTelephoneFieldlabelelement() {
		return Telephonefieldlabel;
	}
	
	
	public WebElement getEmailFieldlabelelement() {
		return emailfieldlabel;
	}
	
	public WebElement getLastnameLabelFieldelement() {
		return LastNamefieldlabel;
	}
	
	public WebElement getFirstnameLabelFieldelement() {
		return firstNamefieldlabel;
	}
	
	public String GetFirstNameFieldPlaceholderText() {
			return FirstNameField.getDomAttribute("placeholder");
	}
	public String GetLastNameFieldPlaceholderText() {
		return LastNameField.getDomAttribute("placeholder");
}
	public String GetEmailPlaceholderText() {
		return EmailField.getDomAttribute("placeholder");
}
	public String GetTelephonePlaceholderText() {
		return TelephoneField.getDomAttribute("placeholder");
}
	public String GetPasswordPlaceholderText() {
		return PasswordField.getDomAttribute("placeholder");
}
	public String GetPasswordConfirmPlaceholderText() {
		return PasswordConfirmField.getDomAttribute("placeholder");
}
	
	public String GetEmailValidationMessage() {
		return EmailField.getDomProperty("validationMessage");
	}
	
	public void ClearemailField() {
		EmailField.clear();
	}
	
	
	public boolean DidWeNavigateToRegisterPage() {
	return	registerPageBreadCrumb.isDisplayed();
	}
	
	public RegisterPage SelectRegisterPageBreadCrumbOption() {
		registerPageBreadCrumb.click();
		return new RegisterPage(driver);
	}
	
	public String getPasswordConfirmationWarning() {
	  return	passwordConfirmationWarning.getText();
	} 
	
	public String getpageLevelWarning() {
		return pageLevelWarning.getText();
	}
	
	public String GetPasswordWarning() {
		return PasswordWarning.getText();
	}
	
	public String GetTelephoneWarning() {
	    return telephoneWarning.getText();
	}
	
	
	public String GetEmailWarninigMessage() {
		return EmailWarning.getText();
	}

	public String GetLastNameWarning() {
		return lastNameWarning.getText();
	}

	public String getFirstNameWarning() {
		return firstNameWarning.getText();
	}

	public void selectNewSeletterOption() {
		isNewSeletterIsselected.click();
	}
	
	public void NoNewSeletterOptionOption() {
		 IsNewsletterIsNotSelected.click();
	}

	public void enterFirstName(String FirstNameText) {
		FirstNameField.sendKeys(FirstNameText);
	}

	public void enterLastName(String LastNameText) {
		LastNameField.sendKeys(LastNameText);
	}

	public void EnterEmail(String Email) {
		EmailField.sendKeys(Email);
	}

	public void EnterTelePhoneNumber(String Telephone) {
		TelephoneField.sendKeys(Telephone);

	}

	public void enterPasswordField(String PasswordText) {
		PasswordField.sendKeys(PasswordText);
	}

	public void enterPasswordConfirm(String ConfirmPassword) {
		PasswordConfirmField.sendKeys(ConfirmPassword);
	}

	public void selectPrivacyPolicyField() {
		privacyPolicyField.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		ContinueButton.click();
		return new AccountSuccessPage(driver);

	}
	
	
	
}
