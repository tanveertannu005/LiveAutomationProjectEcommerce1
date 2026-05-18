package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utilities.CommonUtilities;

public class ProductDisplayPage extends RootPage {
		WebDriver driver;
		
	public 	ProductDisplayPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "button-cart")
	private WebElement addToCartButton;
	
	@FindBy(xpath =  "//a[text()='shopping cart']")
	private WebElement shoppingCartOption;
	
	@FindBy(xpath = "//a[text()='wish list']")
	private WebElement wishlisOption;
	
	@FindBy(xpath = "//a[text()='product comparison']")
	private WebElement productComparisonOption;
	
	public boolean didWeNavigateToProductDisplayPage() {
	return	elementUtilities.IsElementDisplayed(addToCartButton);
	}
	
	public ShoppingCartPage clickonAddToCartAndSelectShoppingCartOption() {
		 clickOnAddToCartButton();
		 selectShoppingCartOptionTheSuccessPage();
		 return new ShoppingCartPage(driver);
	}
	
	public void clickOnAddToCartButton() {
		elementUtilities.ClickOnElement(addToCartButton);
		
	}
	
	public ShoppingCartPage selectShoppingCartOptionTheSuccessPage() {
		elementUtilities.waitForElementAndClick(shoppingCartOption,CommonUtilities.AVERAGE_TIME);
		return new ShoppingCartPage(driver);
	}
	public boolean  IsShoppingCartOptionDisplayedTheSuccessPage() {
		return	elementUtilities.waitAndCheckForElementDisplayedStatus(shoppingCartOption,CommonUtilities.AVERAGE_TIME );
			
	}

	public boolean  IswishListOptionDisplayedTheSuccessPage() {
		return	elementUtilities.waitAndCheckForElementDisplayedStatus(wishlisOption,CommonUtilities.AVERAGE_TIME );
			
	}
	
	public boolean  IsProductComparisonOptionDisplayedTheSuccessPage() {
		return	elementUtilities.waitAndCheckForElementDisplayedStatus(productComparisonOption,CommonUtilities.AVERAGE_TIME );
			
	}
}



