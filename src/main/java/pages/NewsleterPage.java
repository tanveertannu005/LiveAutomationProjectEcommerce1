

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class NewsleterPage extends RootPage {
	WebDriver driver;
	public NewsleterPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	@FindBy(linkText = "Newsletter")
	private WebElement newsletterBreadCrumb;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement YesNewsletterOption;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement NoNewsletterOption;
	
	
	
	public boolean NonewsletteroptionisInSelectedState() {
	return	elementUtilities.IsElementIsSelected(NoNewsletterOption);
		
	}
	
	public boolean YesnewsletteroptionisInSelectedState() {
	return	elementUtilities.IsElementIsSelected(YesNewsletterOption);
		
	}
	
	public boolean DidweNavigateToNewsletterOption() {
		return elementUtilities.IsElementDisplayedOnPage(NoNewsletterOption);
	
		
	}
}
