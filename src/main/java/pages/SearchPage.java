package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class SearchPage  extends RootPage{
	WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(linkText = "HP LP3065")
	private WebElement existingProductOne;
	
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	private WebElement SearchPageBreadCrumb;
	
	@FindBy(xpath = "//p[text()='There is no product that matches the search criteria.']")
	private WebElement searchPageNoproductWarninig;
	
	public boolean isNoProductMessageDisplayed() {
	return	elementUtilities.IsElementDisplayed(searchPageNoproductWarninig);
	}
	
	public boolean isProductDisplayedInSearchResults() {
		return elementUtilities.IsElementDisplayed(existingProductOne);
	}
	
	public boolean didWeNaviagteToSearchResults() {
	return	elementUtilities.IsElementDisplayed(SearchPageBreadCrumb);
	}
	
}
