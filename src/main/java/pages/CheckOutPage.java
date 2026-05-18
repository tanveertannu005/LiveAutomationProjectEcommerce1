package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utilities.CommonUtilities;

public class CheckOutPage extends RootPage {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	@FindBy(id = "button-payment-address")
	private WebElement BiilingDetailscontinueButton;
	
	@FindBy(id = "button-shipping-address")
	private WebElement DeliveryDetailscontinueButton;
	
	@FindBy(id = "button-shipping-method")
	private WebElement DeliverMethodcontinueButton;
	
	@FindBy(name = "agree")
	private WebElement termsAndcoditionOption;
	
	@FindBy(id = "button-payment-method")
	private WebElement paymentMethodContinueButton;
	
	@FindBy(id = "button-confirm")
	private WebElement confirmOrderButton;
	
	public CheckoutSuccessPage placeorder() {
		clickOnBiilingDetailscontinueButton();
		clickOndeliveryDetailsContinueButton();
		clickOnDeliveryMethodContinueButton();
		selectTermsAndConditions();
		clickOnPaymentMethodContinueButton();
		clickOnConfirmOrderButton();
		return new CheckoutSuccessPage(driver);
	}
	
	
	public CheckoutSuccessPage clickOnConfirmOrderButton() {
		elementUtilities.waitForElementAndClick(confirmOrderButton, CommonUtilities.AVERAGE_TIME);
		return new CheckoutSuccessPage(driver);	
		}
	
	public void clickOnPaymentMethodContinueButton() {
		elementUtilities.ClickOnElement(paymentMethodContinueButton);
	}
	
	public void selectTermsAndConditions() {
		elementUtilities.waitForElementAndClick(termsAndcoditionOption, CommonUtilities.AVERAGE_TIME);
	}
	
	public void clickOnDeliveryMethodContinueButton() {
		elementUtilities.waitForElementAndClick(DeliverMethodcontinueButton, CommonUtilities.AVERAGE_TIME);
	}
	
	public void clickOndeliveryDetailsContinueButton() {
		elementUtilities.waitForElementAndClick(DeliveryDetailscontinueButton, CommonUtilities.AVERAGE_TIME);
	}

	public void clickOnBiilingDetailscontinueButton() {
		elementUtilities.ClickOnElement(BiilingDetailscontinueButton);
	}

	
	
}
