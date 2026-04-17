package pages;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicEncryptionKey;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.asm.Advice.Return;
import pages.root.RootPage;
import utilities.ElementUtilities;



public class HeaderOptions extends RootPage {

	WebDriver driver;
	ElementUtilities elementUtilities;
	
	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver=driver;
		elementUtilities=new ElementUtilities(driver);
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement MyAccountDropMenu;
	
	@FindBy(linkText = "Register")
	private WebElement RegisterOption; 
	
	@FindBy(linkText = "Login")
	private WebElement Loginoption;
	
	@FindBy(xpath = "//i[@class='fa fa-phone']")
	private WebElement PhoneIcon;
	
	@FindBy(xpath = "//i[@class='fa fa-heart']")
	private WebElement HeartIconOptions;
	
	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md' and contains(text(), 'Wish List')]")
	private WebElement WishlistHeaderOption;
	
	@FindBy(xpath = "//i[@class='fa fa-shopping-cart']")
	private WebElement ShopingCartHeaderIcon;
	
	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement ShoppingCartHeaderOption;
	
	@FindBy(xpath = "//i[@class='fa fa-share']")
	private WebElement CheckoutHeaderIcon;
	
	@FindBy(xpath = "//span[text()='Checkout']")
	private WebElement CheckoutOption;
	
	@FindBy(xpath = "//div[@id='logo']//a")
	private WebElement Logo;
	
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement SearchButton;
	
	
	
	public SearchPage ClickOnSearchButton() {
		SearchButton.click();
		return new SearchPage(driver);
	}
	
	public HomePage SelectLogo() {
		Logo.click();
		return new HomePage(driver);
	}
	
	public ShoppingCartPage SelectCheckOutOption() {
		CheckoutOption.click();
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage SelectCheckoutHeaderIcon() {
		CheckoutHeaderIcon.click();
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage SelectShoppingCartOption() {
		ShoppingCartHeaderOption.click();
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage selectShopingCartIcon() {
		ShopingCartHeaderIcon.click();
		return new ShoppingCartPage(driver);
	}
	
	public LoginPage SelectWishListOption() {
		WishlistHeaderOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage SelectHeartIconOption() {
		HeartIconOptions.click();
		return new LoginPage(driver);
	}
	
	public ContactUsPage SelectPhoneIcon() {
		PhoneIcon.click();
		return new ContactUsPage(driver);
	}
	
	public LoginPage SelectLoginOption() {
		Loginoption.click();
		return new LoginPage(driver);
	}
	
	
public void ClickOnMyAccountDropMenu() {
		MyAccountDropMenu.click();
	}		
		public RegisterPage SelectOnRegisterOption() {
			RegisterOption.click();
			return new RegisterPage(driver);
			
	
		
	}
		
}
