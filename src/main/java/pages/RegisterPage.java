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
		elementUtilities.ClickOnElement(LoginPageOption);
		return new LoginPage(driver);
	}
	
	public HeaderOptions getHeaderoptions() {
		return new HeaderOptions(driver);
	}
	
	public String getPasswordConfirmFieldDomAttribute(String attributeName) {
		return elementUtilities.getelementDomAttribute(PasswordConfirmField, attributeName);
		
	}
	
	
	public String getPasswordFieldDomAttribute(String attributeName) {
		return	elementUtilities.getelementDomAttribute(PasswordField, attributeName);
		 
	}
	
	public boolean  isPrivacyPolicFieldisSelected() {
		return  elementUtilities.IsElementIsSelected(privacyPolicyField);
		
		
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
		elementUtilities.clearTextFromelement(EmailField);
		
	}
	
	
	public boolean DidWeNavigateToRegisterPage() {
		return elementUtilities.IsElementDisplayed(registerPageBreadCrumb);
	
	}
	
	public RegisterPage SelectRegisterPageBreadCrumbOption() {
		elementUtilities.ClickOnElement(registerPageBreadCrumb);
		return new RegisterPage(driver);
	}
	
	public String getPasswordConfirmationWarning() {
		return elementUtilities.getElementText(passwordConfirmationWarning);
	 
	} 
	
	public String getpageLevelWarning() {
	return	elementUtilities.getElementText(pageLevelWarning);
		
	}
	
	public String GetPasswordWarning() {
		return elementUtilities.getElementText(PasswordWarning);
		
	}
	
	public String GetTelephoneWarning() {
	return	elementUtilities.getElementText(telephoneWarning);
	    
	}
	
	
	public String GetEmailWarninigMessage() {
	return	elementUtilities.getElementText(EmailWarning);
		
	}

	public String GetLastNameWarning() {
		return	elementUtilities.getElementText(lastNameWarning);
		
	}

	public String getFirstNameWarning() {
		return	elementUtilities.getElementText(firstNameWarning);
		
	}

	public void selectNewSeletterOption() {
		elementUtilities.ClickOnElement(isNewSeletterIsselected);
		
	}
	
	public void NoNewSeletterOptionOption() {
		elementUtilities.ClickOnElement(IsNewsletterIsNotSelected);
		 
	}

	public void enterFirstName(String FirstNameText) {
		elementUtilities.enterTextIntoElement(FirstNameField, FirstNameText);
		
	}

	public void enterLastName(String LastNameText) {
		elementUtilities.enterTextIntoElement(LastNameField, LastNameText);
		
	}

	public void EnterEmail(String Email) {
		elementUtilities.enterTextIntoElement(EmailField, Email);
		
	}

	public void EnterTelePhoneNumber(String Telephone) {
		elementUtilities.enterTextIntoElement(TelephoneField, Telephone);
		
	}

	public void enterPasswordField(String PasswordText) {
		elementUtilities.enterTextIntoElement(PasswordField, PasswordText);
		
	}

	public void enterPasswordConfirm(String ConfirmPassword) {
		elementUtilities.enterTextIntoElement(PasswordConfirmField, ConfirmPassword);
		
	}

	public void selectPrivacyPolicyField() {
		elementUtilities.ClickOnElement(privacyPolicyField);
		
	}

	public AccountSuccessPage clickOnContinueButton() {
		elementUtilities.ClickOnElement(ContinueButton);
		return new AccountSuccessPage(driver);

	}
	
	
	
}
