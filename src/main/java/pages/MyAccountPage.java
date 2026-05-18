package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountPage extends RootPage {
		WebDriver driver;
		
		public  MyAccountPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		@FindBy(linkText = "Subscribe / unsubscribe to newsletter")
		private WebElement SubscribeUnsubscribenewsletter;
		
		public  NewsleterPage ClickonSubscribeunsubscribeNewsletterOption() {
			elementUtilities.ClickOnElement(SubscribeUnsubscribenewsletter);
			return new NewsleterPage(driver);
		}
		@FindBy(linkText = "Edit your account information")
		private WebElement edityourAccoutInformation;
		
		@FindBy(linkText =      "Change your password")
		private WebElement changeYourPasswordLink;
		
		@FindBy(xpath = "//a[text()='Continue']")
		private WebElement ContinueSuccessPage;
		
		@FindBy(linkText = "Modify your address book entries")
		private WebElement modifyaddressBookEntries;
		
		@FindBy(linkText = "Modify your wish list")
		private WebElement modifyYourWishListOption;
		
		@FindBy(linkText = "View your order history")
		private WebElement orderHistoryOption;
		
		@FindBy(linkText = "Downloads")
		private WebElement downloadsOption;
		
		@FindBy(linkText = "Your Reward Points")
		private WebElement rewardPointsoption;
		
		@FindBy(linkText = "View your return requests")
		private WebElement viewYourReturnrequest; 
		
		@FindBy(linkText = "Your Transactions")
		private WebElement Yourtransactions;
		
		@FindBy(linkText = "Recurring payments")
		private WebElement recurringPayments;
		
		@FindBy(linkText = "Register for an affiliate account")
		private WebElement Registerforanaffiliateaccount;
		
		@FindBy(linkText = "Edit your affiliate information")
		private WebElement Edityouraffiliateinformation;
		
		@FindBy(linkText = "Custom Affiliate Tracking Code")
		private WebElement CustomAffiliateTrackingCode;
		
	
		
		public AffiliateTrackingpage clickOnCustomAffiliateTrackingCodeOption() {
			elementUtilities.ClickOnElement(CustomAffiliateTrackingCode);
			return new AffiliateTrackingpage(driver);
			
		}
		
		public AffiliatePage clickOndityouraffiliateinformation() {
			elementUtilities.clickEitherOfTheseElements(Registerforanaffiliateaccount,Edityouraffiliateinformation);
			return new AffiliatePage(driver);
		}
		
		public AffiliatePage clickonRegisterforanaffiliateaccountoption() {
			elementUtilities.ClickOnElement(Registerforanaffiliateaccount);
			return new AffiliatePage(driver);
		}
		
		public RecurringPaymentspage clickOnrecurringPaymentsPage() {
			elementUtilities.ClickOnElement(recurringPayments);
			return new RecurringPaymentspage(driver);
		}
		
		public YourTransactionsPage clickOnYourTransactionsPage() {
			elementUtilities.ClickOnElement(Yourtransactions);
			return new YourTransactionsPage(driver);
		}
		
		public ProductReturnsPage clickOnreturnRequestOption() {
			elementUtilities.ClickOnElement(viewYourReturnrequest);
			return new ProductReturnsPage(driver);
		}
		
		public RewardPointsPage clickOnRewardPointsOption() {
			elementUtilities.ClickOnElement(rewardPointsoption);
			return new RewardPointsPage(driver);
		}
		
		public Downloadspage clickOnDownloadsOption() {
			elementUtilities.ClickOnElement(downloadsOption);
			return new Downloadspage(driver);
		}
		
		public OrderHistoryPage clickOnViewYourOrderHistoryOption() {
			elementUtilities.ClickOnElement(orderHistoryOption);
			return new OrderHistoryPage(driver);
		}
		
		public MywishListPage clickOnModifyYourWishlistOption() {
			elementUtilities.ClickOnElement(modifyYourWishListOption);
			return new MywishListPage(driver);
		}		
		public AddressBookEntriesPage clickOnModifyAddressBookoption() {
			elementUtilities.ClickOnElement(modifyaddressBookEntries);
			return new AddressBookEntriesPage(driver);
		}
		
		
		public boolean didwenavigateToMyAccountPage() {
			return	elementUtilities.IsElementDisplayed(edityourAccoutInformation);
			
			 
		}
		public MyAccountPage ContinuePage() {
			elementUtilities.ClickOnElement(ContinueSuccessPage);	
			return new MyAccountPage(driver);
		}
		
		public MyAccountInformationPage clickOnEdityourAccountInformation() {
			elementUtilities.ClickOnElement(edityourAccoutInformation);
			return new MyAccountInformationPage(driver);
		}


		public HeaderOptions getHeaderoptions() {
			
			return new HeaderOptions(driver);
		}
		
		
		
		public  PasswordChangePage ClickOnChangeYourPasswordlink() {
			elementUtilities.ClickOnElement(changeYourPasswordLink);
			return new PasswordChangePage(driver);
		}
}
