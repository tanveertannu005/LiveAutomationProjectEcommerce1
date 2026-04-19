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
	
	@FindBy(xpath = "//footer//a[text()='Wish List']")
	private WebElement WishlistPage;
	
	@FindBy(xpath = "//footer//a[text()='Newsletter']")
	private WebElement newsletterpage;
	
	
	public LoginPage SelectNewsletterPage() {
		elementUtilities.ClickOnElement(newsletterpage);
		return new LoginPage(driver);
	}
	
	
	
	public LoginPage SelectWishListPage() {
		elementUtilities.ClickOnElement(WishlistPage);
		return new LoginPage(driver);
	}
	
	
	public LoginPage SelectorderHistoryPage() {
		elementUtilities.ClickOnElement(orderHistoryPage);
		return new LoginPage(driver);
	}
	
	
	public LoginPage SelectMyaccountPage() {
		elementUtilities.ClickOnElement(MyaccountPage);
		return new LoginPage(driver);
	}
	
	public SpecialOffersPage SelectSpecialOffersPage() {
		elementUtilities.ClickOnElement(SpecialOffersPage);
		return new SpecialOffersPage(driver);
	}
	
	
	
	public AffiliatePrograPage SelectaffiliateOption() {
		elementUtilities.ClickOnElement(affiliatePage);
		return new AffiliatePrograPage(driver);
	}
	
	public GiftCertificatesPage SelectGiftCertificatesOption() {
		elementUtilities.ClickOnElement(GiftCertificatesPage);
		return new GiftCertificatesPage(driver);
	}
	
	
	public BrandsPage SelectBrandsPageOption() {
		elementUtilities.ClickOnElement(brandspage);
		return new BrandsPage(driver);
	}
	
	public SiteMappage SelectSiteMapPage() {
		elementUtilities.ClickOnElement(sitemapPage);
		return new SiteMappage(driver);
	}
	
	public ProductReturnsPage SelectProductReturnsPage() {
		elementUtilities.ClickOnElement(ReturnsPage);
		return new ProductReturnsPage(driver);
	}
	
	public ContactUsPage SelectcontactUsPage() {
		elementUtilities.ClickOnElement(contactUsPage);
		return new ContactUsPage(driver);
	}
	
	public TermsandConditionsPage SelectTermsConditionsOption(){
		elementUtilities.ClickOnElement(TermsAndConditionsOption);
		return new TermsandConditionsPage(driver);
	}
	
	
	public Privacypolicypage SelectPrivacyPolicyOption() {
		elementUtilities.ClickOnElement(privacypolicyoption);
		return new Privacypolicypage(driver);
	}
	
	public DeliveryInformationPage SelectDeliveryInformationOpton() {
		elementUtilities.ClickOnElement(DeliveryInformationOption);
		return new DeliveryInformationPage(driver);
	}
	
	public AboutUspage SelectAboutUsoption() {
		elementUtilities.ClickOnElement(aboutusOption);
		return new AboutUspage(driver);
		
	}
}
