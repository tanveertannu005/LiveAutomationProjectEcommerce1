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
		
		public boolean didwenavigateToMyAccountPage() {
			return	elementUtilities.IsElementDisplayed(edityourAccoutInformation);
			
			 
		}
		
		
		public MyAccountInformationPage clickOnEdityourAccountInformation() {
			elementUtilities.ClickOnElement(edityourAccoutInformation);
			return new MyAccountInformationPage(driver);
		}
}
