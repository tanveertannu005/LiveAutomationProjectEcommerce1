package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class OrderInfromationPage extends RootPage {
	WebDriver driver;
	
	public OrderInfromationPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
		
	}

	@FindBy(xpath = "//a[@*='Return']")
	private WebElement returnOption;
	
	public ProductReturnsPage selectReturnOption() {
		elementUtilities.ClickOnElement(returnOption);
		return new ProductReturnsPage(driver);
	}
	
	
}
