package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class PasswordChangePage extends RootPage{
		WebDriver driver;
		
		public PasswordChangePage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver,this);
			
		}
		
		@FindBy(id = "input-password")
		private WebElement passwordField;
		
		@FindBy(id = "input-confirm")
		private WebElement passwordConfirmField;
		
		@FindBy(xpath = "//input[@value='Continue']")
		private WebElement ContinuteButton;
		
		public MyAccountPage clickOnContinueButton() {
			elementUtilities.ClickOnElement(ContinuteButton);
			return new MyAccountPage(driver);
		}
		
		
		public void enterNewPasswordIntoPasswordConfirmField(String newPassword) {
			elementUtilities.enterTextIntoElement(passwordConfirmField, newPassword);
		}
		
		public void enterNewPasswordIntoPasswordField(String newPassword) 
		{
			elementUtilities.enterTextIntoElement(passwordField, newPassword);
		}
		
		
}
