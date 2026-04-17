package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class Privacypolicypage extends RootPage {
	WebDriver driver;
	
	public Privacypolicypage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver,this);
		
		
	} 

}
