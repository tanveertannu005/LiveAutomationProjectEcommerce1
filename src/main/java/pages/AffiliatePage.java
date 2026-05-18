package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AffiliatePage extends RootPage {
	WebDriver driver;

	public AffiliatePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-company")
	private WebElement companyfield;

	@FindBy(id = "input-website")
	private WebElement websiteField;

	@FindBy(id = "input-cheque")
	private WebElement chequePayeeNameField;

	@FindBy(name = "agree")
	private WebElement agrementOption;

	@FindBy(xpath = "//*[@value='Continue']")
	private WebElement continueButton;

	public MyAccountPage updateAffliateAccount(String chequePayeeNameFieldText) {
		enterChequePayeeName(chequePayeeNameFieldText);
		selectAgrement();
		return clickOncontinueButton();
	}

	public void selectAgrement() {
		elementUtilities.ClickOnElement(agrementOption);
	}

	public MyAccountPage clickOncontinueButton() {
		elementUtilities.ClickOnElement(continueButton);
		return new MyAccountPage(driver);
	}

	public void enterChequePayeeName(String chequePayeeNameFieldText) {
		elementUtilities.enterTextIntoElement(chequePayeeNameField, chequePayeeNameFieldText);
	}

	public void enterWebsite(String websiteText) {
		elementUtilities.enterTextIntoElement(websiteField, websiteText);
	}

	public void enterCompany(String companyText) {
		elementUtilities.enterTextIntoElement(companyfield, companyText);
	}

}