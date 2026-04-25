package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;

public class Search extends Base {
	WebDriver driver;

	@BeforeMethod
	public void Setup() {
		driver = openBrowserAndApplicationURL();
		headeroptions = new HeaderOptions(driver);

	}

	@Test(priority = 1)
	public void VerifySearchingWithExistingProduct() {

		headeroptions.enterProductintoSearchBoxField(prop.getProperty("existingProduct"));
		searchPage = headeroptions.ClickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNaviagteToSearchResults());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
	}

	@Test(priority = 2)
	public void VerifySearchingWithNOnExistingProduct() {

		headeroptions.enterProductintoSearchBoxField(prop.getProperty("NonexistingProduct"));
		searchPage = headeroptions.ClickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNaviagteToSearchResults());
		Assert.assertFalse(searchPage.isProductDisplayedInSearchResults());
		Assert.assertTrue(searchPage.isNoProductMessageDisplayed());

	}
}