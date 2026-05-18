package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ProductReturnsPage extends RootPage {
	
	WebDriver driver;
	
	public ProductReturnsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}

	@FindBy(xpath = "//a[@*='View']")
	private WebElement viewOption;
	
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
	private WebElement accountBreadcrumb;
	
	@FindBy(xpath = "(//input[@name='return_reason_id'])[1]")
	private WebElement ReasonForReturnfirstOption;
	
	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement SubmitButton;
	
	public void clickOnSubmitButton() {
		elementUtilities.ClickOnElement(SubmitButton);
		
	}
	
	public void selectFirstReasonToReturn() {
		elementUtilities.ClickOnElement(ReasonForReturnfirstOption);
	}
	
	public MyAccountPage clickOnAccountBreadCrumb() {
		elementUtilities.ClickOnElement(accountBreadcrumb);
		return new MyAccountPage(driver);
	}
	
	
	
	public ReturnInformationPage clickOnViewOption() {
		elementUtilities.ClickOnElement(viewOption);
		return new ReturnInformationPage(driver);
	}
	
	
}
