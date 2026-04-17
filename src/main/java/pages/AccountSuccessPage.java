package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AccountSuccessPage extends RootPage {
		WebDriver driver;
		
		public AccountSuccessPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		
		@FindBy(linkText = "Logout")
		private WebElement LogoutOption;
		
		@FindBy(id = "content")
		private WebElement accountsuccesspagecontent;
		
		@FindBy(xpath = "//a[text()='Continue']")
		private WebElement ContinueSuccessPage;
		
		@FindBy(xpath = "//div[@id='content']/h1")
		private WebElement accountCreatedHeading;
		
		
		
		public String getAccountCreatedHeading() {
		    return accountCreatedHeading.getText();
		}

		
		public MyAccountPage ContinuePage() {
			ContinueSuccessPage.click();
			return new MyAccountPage(driver);
		}
		
		public String  getContent() {
			return accountsuccesspagecontent.getText();
		}
		
		public boolean isUserLoggedIn() {
			return LogoutOption.isDisplayed();
		}
}
