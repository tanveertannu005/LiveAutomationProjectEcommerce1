package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AddAddressPage extends RootPage {
	WebDriver driver;

	public AddAddressPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-company")
	private WebElement CompanyField;

	@FindBy(id = "input-address-1")
	private WebElement Addressfield1;

	@FindBy(id = "input-address-2")
	private WebElement Addressfield2;

	@FindBy(id = "input-city")
	private WebElement City;

	@FindBy(id = "input-postcode")
	private WebElement postcodefield;

	@FindBy(id = "input-zone")
	private WebElement regionDropDownField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	public AddressBookEntriesPage enterMandatoryFieldAndAddAddress(String firstNameText, String LastNameText,
			String companyText, String address1Text, String address2Text, String cityText, String postCodetext) {
		enterFirstName(firstNameText);
		enterLastName(LastNameText);
		enterCompanyfield(companyText);
		enterAddressField1(address1Text);
		enterAddressField2(address2Text);
		enterCityName(cityText);
		enterPostCode(postCodetext);
		selectAnyoptionFromRegionDropdownField();
		return clickOnContinueButton();
	}

	public AddressBookEntriesPage clickOnContinueButton() {
		elementUtilities.ClickOnElement(continueButton);
		return new AddressBookEntriesPage(driver);
	}

	public void selectAnyoptionFromRegionDropdownField() {
		elementUtilities.selectOptionDropdownFieldUsingIndex(regionDropDownField, 3);
	}

	public void enterPostCode(String postCodetext) {
		elementUtilities.enterTextIntoElement(postcodefield, postCodetext);
	}

	public void enterCityName(String cityText) {
		elementUtilities.enterTextIntoElement(City, cityText);
	}

	public void enterAddressField2(String address2Text) {
		elementUtilities.enterTextIntoElement(Addressfield2, address2Text);
	}

	public void enterAddressField1(String address1Text) {
		elementUtilities.enterTextIntoElement(Addressfield1, address1Text);
	}

	public void enterCompanyfield(String companyText) {
		elementUtilities.enterTextIntoElement(CompanyField, companyText);
	}

	public void enterFirstName(String firstNameText) {
		elementUtilities.enterTextIntoElement(firstNameField, firstNameText);
	}

	public void enterLastName(String LastNameText) {
		elementUtilities.enterTextIntoElement(lastNameField, LastNameText);
	}

}
