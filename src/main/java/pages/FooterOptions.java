package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class FooterOptions extends RootPage {
	WebDriver driver;
	
	public FooterOptions(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	@FindBy(xpath = "//footer//a[text()='About Us']")
	private WebElement aboutusOption;
	
	@FindBy(xpath = "//footer//a[text()='Delivery Information']")
	private WebElement DeliveryInformationOption;
	
	@FindBy(xpath = "//footer//a[text()='Privacy Policy']")
	private WebElement privacypolicyoption;
	
	@FindBy(xpath = "//footer//a[text()='Terms & Conditions']")
	private WebElement TermsAndConditionsOption;
	
	@FindBy(xpath = "//footer//a[text()='Contact Us']")
	private WebElement contactUsPage;
	
	@FindBy(xpath = "//footer//a[text()='Returns']")
	private WebElement ReturnsPage;
	
	@FindBy(xpath = "//footer//a[text()='Site Map']")
	private WebElement sitemapPage;
	
	@FindBy(xpath = "//footer//a[text()='Brands']")
	private WebElement brandspage;
	
	@FindBy(xpath = "//footer//a[text()='Gift Certificates']")
	private WebElement GiftCertificatesPage;
	
	@FindBy(xpath = "//footer//a[text()='Affiliate']")
	private WebElement affiliatePage;
	
	@FindBy(xpath = "//footer//a[text()='Specials']")
	private WebElement SpecialOffersPage;
	
	
	@FindBy(xpath = "//footer//a[text()='My Account']")
	private WebElement MyaccountPage;
	
	@FindBy(xpath = "//footer//a[text()='Order History']")
	private WebElement orderHistoryPage;
	
	@FindBy(xpath = "//footer//a[text()='Wish List0']")
	private WebElement WishlistPage;
	
	@FindBy(xpath = "//footer//a[text()='Newsletter']")
	private WebElement newsletterpage;
	
	
	public LoginPage SelectNewsletterPage() {
		newsletterpage.click();
		return new LoginPage(driver);
	}
	
	
	
	public LoginPage SelectWishListPage() {
		WishlistPage.click();
		return new LoginPage(driver);
	}
	
	
	public LoginPage SelectorderHistoryPage() {
		orderHistoryPage.click();
		return new LoginPage(driver);
	}
	
	
	public LoginPage SelectMyaccountPage() {
		MyaccountPage.click();
		return new LoginPage(driver);
	}
	
	public SpecialOffersPage SelectSpecialOffersPage() {
		SpecialOffersPage.click();
		return new SpecialOffersPage(driver);
	}
	
	
	
	public AffiliatePrograPage SelectaffiliateOption() {
		affiliatePage.click();
		return new AffiliatePrograPage(driver);
	}
	
	public GiftCertificatesPage SelectGiftCertificatesOption() {
		GiftCertificatesPage.click();
		return new GiftCertificatesPage(driver);
	}
	
	
	public BrandsPage SelectBrandsPageOption() {
		brandspage.click();
		return new BrandsPage(driver);
	}
	
	public SiteMappage SelectSiteMapPage() {
		sitemapPage.click();
		return new SiteMappage(driver);
	}
	
	public ProductReturnsPage SelectProductReturnsPage() {
		ReturnsPage.click();
		return new ProductReturnsPage(driver);
	}
	
	public ContactUsPage SelectcontactUsPage() {
		contactUsPage.click();
		return new ContactUsPage(driver);
	}
	
	public TermsandConditionsPage SelectTermsConditionsOption(){
		TermsAndConditionsOption.click();
		return new TermsandConditionsPage(driver);
	}
	
	
	public Privacypolicypage SelectPrivacyPolicyOption() {
		privacypolicyoption.click();
		return new Privacypolicypage(driver);
	}
	
	public DeliveryInformationPage SelectDeliveryInformationOpton() {
		DeliveryInformationOption.click();
		return new DeliveryInformationPage(driver);
	}
	
	public AboutUspage SelectAboutUsoption() {
		aboutusOption.click();
		return new AboutUspage(driver);
		
	}
}
