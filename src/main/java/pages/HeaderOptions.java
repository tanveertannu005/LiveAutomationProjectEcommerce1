package pages;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicEncryptionKey;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.asm.Advice.Return;
import pages.root.RootPage;
import utilities.ElementUtilities;



public class HeaderOptions extends RootPage {

	WebDriver driver;
	
	
	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement MyAccountDropMenu;
	
	@FindBy(xpath =   "//a[text()='Register']")
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
	
	
	@FindBy(linkText = "Logout")
	private WebElement Logoutoption;
	
	public LogoutPage SelectLogoutoption() {
		elementUtilities.ClickOnElement(Logoutoption);
		return new LogoutPage(driver);
	}
	
	public SearchPage ClickOnSearchButton() {
		elementUtilities.ClickOnElement(SearchButton);;
		return new SearchPage(driver);
	}
	
	public HomePage SelectLogo() {
		elementUtilities.ClickOnElement(Logo);
		return new HomePage(driver);
	}
	
	public ShoppingCartPage SelectCheckOutOption() {
		elementUtilities.ClickOnElement(CheckoutHeaderIcon);
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage SelectCheckoutHeaderIcon() {
		elementUtilities.ClickOnElement(CheckoutHeaderIcon);
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage SelectShoppingCartOption() {
		elementUtilities.ClickOnElement(ShopingCartHeaderIcon);
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage selectShopingCartIcon() {
		elementUtilities.ClickOnElement(ShopingCartHeaderIcon);
		return new ShoppingCartPage(driver);
	}
	
	public LoginPage SelectWishListOption() {
		elementUtilities.ClickOnElement(WishlistHeaderOption);
		return new LoginPage(driver);
	}
	
	public LoginPage SelectHeartIconOption() {
		elementUtilities.ClickOnElement(HeartIconOptions);
		return new LoginPage(driver);
	}
	
	public ContactUsPage SelectPhoneIcon() {
		elementUtilities.ClickOnElement(PhoneIcon);
		return new ContactUsPage(driver);
	}
	
	public LoginPage SelectLoginOption() {
		elementUtilities.ClickOnElement(Loginoption);
		return new LoginPage(driver);
	}
	public void ClickOnMyAccountDropMenu() {
		elementUtilities.ClickOnElement(MyAccountDropMenu);
			
		}		
			public RegisterPage SelectOnRegisterOption() {
				elementUtilities.ClickOnElement(RegisterOption);
				return new RegisterPage(driver);
				
		
			
		}
	
			public LoginPage navigateToLoginPage() {
				 ClickOnMyAccountDropMenu();
				return  SelectLoginOption();
			}
		
}
