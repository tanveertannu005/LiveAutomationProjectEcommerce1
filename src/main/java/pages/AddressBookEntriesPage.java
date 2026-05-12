package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AddressBookEntriesPage extends RootPage  {

	WebDriver driver;
	
	public  AddressBookEntriesPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
				
	
	}
	
	@FindBy(linkText = "Edit")
	private WebElement editButton;
	
	@FindBy(linkText = "New Address")
	private WebElement newAddressButton;
	
	public EditAddressPage clickOnEditButton() {
		elementUtilities.ClickOnElement(editButton);
		return new EditAddressPage(driver);
	}	
	
	
	public  AddAddressPage clickOnNewAddreesButoon() {
		elementUtilities.ClickOnElement(newAddressButton);
		return new AddAddressPage(driver);
	}
	
}
