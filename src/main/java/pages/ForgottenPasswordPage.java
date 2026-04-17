package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.root.RootPage;

public class ForgottenPasswordPage extends RootPage {
		WebDriver driver;
		
		public ForgottenPasswordPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver,this);
		} 
}
