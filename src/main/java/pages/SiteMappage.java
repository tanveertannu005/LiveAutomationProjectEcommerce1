package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class SiteMappage extends RootPage{
		WebDriver driver;
		
		public SiteMappage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements( driver,this);
			
		}
		
		@FindBy(linkText = "Search")
		private WebElement searchOption;
		
		public SearchPage clickOOnSearchOption() {
			elementUtilities.ClickOnElement(searchOption);
			return new SearchPage(driver);
		}
}
