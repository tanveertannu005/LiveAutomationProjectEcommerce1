package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import base.Base;
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.ProductComparisonPage;
import pages.ProductDisplayPage;
import pages.SearchPage;
import utilities.CommonUtilities;

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
	public void VerifySearchingWithNonExistingProduct() {

		headeroptions.enterProductintoSearchBoxField(prop.getProperty("NonexistingProduct"));
		searchPage = headeroptions.ClickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNaviagteToSearchResults());
		Assert.assertEquals(searchPage.GetNoProductMessage(), "There is no product that matches the search criteria.");

	}

	@Test(priority = 3)
	public void verifySearchingOfProductWithoutProvindgAnyProductName() {
		searchPage = headeroptions.ClickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNaviagteToSearchResults());
		Assert.assertEquals(searchPage.GetNoProductMessage(), "There is no product that matches the search criteria.");

	}

	@Test(priority = 4)
	public void verifyProductSearchAfterLogin() {
		loginPage = headeroptions.navigateToLoginPage();
		myaccountpage = loginPage.loginIntoApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
		headeroptions = myaccountpage.getHeaderoptions();
		headeroptions.enterProductintoSearchBoxField(prop.getProperty("existingProduct"));
		searchPage = headeroptions.ClickOnSearchButton();

		Assert.assertTrue(searchPage.didWeNaviagteToSearchResults());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());

	}

	@Test(priority = 5)
	public void verifyProductSearchResultingMultipleProducts() {

		headeroptions.enterProductintoSearchBoxField(prop.getProperty("existingProductTwo"));
		searchPage = headeroptions.ClickOnSearchButton();

		Assert.assertTrue(searchPage.didWeNaviagteToSearchResults());
		Assert.assertTrue(searchPage.getProductsCount() > 0);

	}

	@Test(priority = 6)
	public void verifySearchfunctionalityFieldPlaceHolder() {
		Assert.assertEquals(headeroptions.getSearchBoxFieldPlaceHolderText(), "Search");
		searchPage = headeroptions.ClickOnSearchButton();

		Assert.assertEquals(searchPage.getSearchCriteriaFieldPlaceHolderText(), "Keywords");
	}

	@Test(priority = 7)
	public void verifySearchFunctionalityUsingSearchCrtiteriaField() {
		searchPage = headeroptions.ClickOnSearchButton();
		searchPage.enterProductIntoSearchCriteriaField(prop.getProperty("existingProduct"));
		searchPage.ClickOnSearchButton();
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
	}

	@Test(priority = 8)
	public void verifySearchusingTextProductDesciption() {
		searchPage = headeroptions.ClickOnSearchButton();
		searchPage.enterTextInProductDescriptionIntoSearchCriteriaField(prop.getProperty("textInProductDescription"));
		searchPage.SelectSearchInProductInDescription();
		searchPage.ClickOnSearchButton();
		Assert.assertTrue(searchPage.isProductHavingTextInItsDescriptionDisplayedInSearchResults());
	}

	@Test(priority = 9)
	public void verifySearchBySelectingTheCategory() {
		searchPage = headeroptions.ClickOnSearchButton();
		searchPage.enterProductIntoSearchCriteriaField(prop.getProperty("existingProductThree"));
		searchPage.SelectOptionFromCategoryDropDownField(
				CommonUtilities.ConvertToInteger(prop.getProperty("correctCategoryIndex")));
		searchPage.ClickOnSearchButton();
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		searchPage.SelectOptionFromCategoryDropDownField(
				CommonUtilities.ConvertToInteger(prop.getProperty("wrongCategoryIndex")));
		searchPage.ClickOnSearchButton();
		Assert.assertEquals(searchPage.GetNoProductMessage(), "There is no product that matches the search criteria.");

	}

	@Test(priority = 10)
	public void verifySearchSelectingSearchToSubCategories() {
		searchPage = headeroptions.ClickOnSearchButton();
		searchPage.enterProductIntoSearchCriteriaField(prop.getProperty("existingProductThree"));
		searchPage.SelectOptionFromCategoryDropDownField(prop.getProperty("SuperCategory"));
		searchPage.ClickOnSearchButton();
		Assert.assertEquals(searchPage.GetNoProductMessage(), "There is no product that matches the search criteria.");
		searchPage.SelectToSearchInSubCategory();
		searchPage.ClickOnSearchButton();
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
	}

	@Test(priority = 11, enabled = false)
	public void verifyListAndGridViewsInSearchResultsHavingOnePage() {
		headeroptions.enterProductintoSearchBoxField(prop.getProperty("existingProductThree"));
		searchPage = headeroptions.ClickOnSearchButton();
		searchPage.SelectListOption();
		Assert.assertTrue(searchPage.getProductsCount() == 1);
		searchPage.clickOnAddToCartOption();
		String expectedMessage = "Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your shopping cart!";
		Assert.assertTrue(searchPage.getpageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnAddToWishListOption();
		expectedMessage = "You must login or create an account to save " + prop.getProperty("existingProductThree")
				+ " to your wish list!";
		Assert.assertTrue(searchPage.getpageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnCompareThisProductOption();
		expectedMessage = " Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your product comparison!";
		Assert.assertTrue(searchPage.getpageLevelSuccessMessage().contains(expectedMessage));
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		refreshPage(searchPage.getDriver());
		searchPage.selectGridOption();

		Assert.assertTrue(searchPage.getProductsCount() == 1);

		searchPage.clickOnAddToCartOption();
		expectedMessage = "You must login or create an account to save " + prop.getProperty("existingProductThree")
				+ " to your wish list!";
		Assert.assertEquals(searchPage.getpageLevelSuccessMessage(), expectedMessage);
		refreshPage(searchPage.getDriver());
		searchPage.clickOnAddToWishListOption();
		expectedMessage = " You must login or create an account to save " + prop.getProperty("existingProductThree")
				+ " to your wish list!";
		Assert.assertTrue(searchPage.getpageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnCompareThisProductOption();
		expectedMessage = " Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your product comparison!";
		Assert.assertEquals(searchPage.getpageLevelSuccessMessage(), expectedMessage);
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());

	}

	@Test(priority = 11)
	public void verifyListAndGridViewsInSearchResultsHavingOnePag() {

		headeroptions.enterProductintoSearchBoxField(prop.getProperty("existingProductThree"));
		searchPage = headeroptions.ClickOnSearchButton();

		// LIST VIEW
		searchPage.SelectListOption();
		Assert.assertEquals(searchPage.getProductsCount(), 1);

		// Add to Cart
		searchPage.clickOnAddToCartOption();
		String actualMessage = searchPage.getpageLevelSuccessMessage();

		Assert.assertTrue(actualMessage.contains("Success"));
		Assert.assertTrue(actualMessage.contains(prop.getProperty("existingProductThree")));

		refreshPage(searchPage.getDriver());

		// Add to Wishlist
		searchPage.clickOnAddToWishListOption();
		actualMessage = searchPage.getpageLevelSuccessMessage();

		Assert.assertTrue(actualMessage.contains("You must login or create an account"));
		Assert.assertTrue(actualMessage.contains(prop.getProperty("existingProductThree")));

		refreshPage(searchPage.getDriver());

		// Compare Product
		searchPage.clickOnCompareThisProductOption();
		actualMessage = searchPage.getpageLevelSuccessMessage();

		Assert.assertTrue(actualMessage.contains("Success"));
		Assert.assertTrue(actualMessage.contains(prop.getProperty("existingProductThree")));

		// Product Navigation
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());

		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());

		refreshPage(searchPage.getDriver());

		// GRID VIEW
		searchPage.selectGridOption();
		Assert.assertEquals(searchPage.getProductsCount(), 1);

		// Add to Cart
		searchPage.clickOnAddToCartOption();
		actualMessage = searchPage.getpageLevelSuccessMessage();

		Assert.assertTrue(actualMessage.contains("Success"));
		Assert.assertTrue(actualMessage.contains(prop.getProperty("existingProductThree")));

		refreshPage(searchPage.getDriver());

		// Add to Wishlist
		searchPage.clickOnAddToWishListOption();
		actualMessage = searchPage.getpageLevelSuccessMessage();

		Assert.assertTrue(actualMessage.contains("You must login or create an account"));
		Assert.assertTrue(actualMessage.contains(prop.getProperty("existingProductThree")));

		refreshPage(searchPage.getDriver());

		// Compare Product
		searchPage.clickOnCompareThisProductOption();
		actualMessage = searchPage.getpageLevelSuccessMessage();

		Assert.assertTrue(actualMessage.contains("Success"));
		Assert.assertTrue(actualMessage.contains(prop.getProperty("existingProductThree")));

		// Product Navigation again
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());

		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
	}

	@Test(priority = 12)
	public void verifyListAndGridViewsWhenMultipleProductsDisplayed() {
		headeroptions.enterProductintoSearchBoxField(prop.getProperty("existingProductTwo"));
		searchPage = headeroptions.ClickOnSearchButton();
		Assert.assertTrue(searchPage.getProductsCount() > 1);
		searchPage.SelectListOption();
		Assert.assertTrue(searchPage.getProductsCount() > 1);
		searchPage.selectGridOption();
		Assert.assertTrue(searchPage.getProductsCount() > 1);

	}

	@Test(priority = 13)
	public void verifyNavigatingToProductComparisonPageFromSearchResultsPage() {
		headeroptions.enterProductintoSearchBoxField(prop.getProperty("existingProductThree"));
		searchPage = headeroptions.ClickOnSearchButton();
		productComparisonPage = searchPage.selectProductCompareOption();
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());

	}

	@Test(priority = 14)
	public void verifyAllSortingInSearchresultsPage() {
		headeroptions.enterProductintoSearchBoxField(prop.getProperty("existingProductTwo"));
		searchPage = headeroptions.ClickOnSearchButton();
		searchPage.selectSortOptionInDropDownField("Default");
		Assert.assertTrue(searchPage.didProductGotDisplayedInAscendingOrder());

		searchPage.selectSortOptionInDropDownField("Name (A - Z)");
		Assert.assertTrue(searchPage.didProductGotDisplayedInAscendingOrder());

		searchPage.selectSortOptionInDropDownField("Name (Z - A)");
		Assert.assertTrue(searchPage.didProductGotDisplayedInAscendingOrder());

		searchPage.selectSortOptionInDropDownField("Price (Low > High)");
		Assert.assertTrue(searchPage.didProductGotDisplayedInAscendingOrder());

		searchPage.selectSortOptionInDropDownField("Price (High > Low)");
		Assert.assertTrue(searchPage.didProductGotDisplayedInAscendingOrder());

		searchPage.selectSortOptionInDropDownField("Rating (Highest)");
		Assert.assertTrue(searchPage.didProductGotDisplayedInAscendingOrder());

		searchPage.selectSortOptionInDropDownField("Rating (Lowest)");
		Assert.assertTrue(searchPage.didProductGotDisplayedInAscendingOrder());

		searchPage.selectSortOptionInDropDownField("Model (A - Z)");
		Assert.assertTrue(searchPage.didProductGotDisplayedInAscendingOrder());

		searchPage.selectSortOptionInDropDownField("Model (Z - A)");
		Assert.assertTrue(searchPage.didProductGotDisplayedInAscendingOrder());

	}

	// Test will fail defect In the app refer Test Case doc For Clarification
	@Test(priority = 15)
	public void how () {
		headeroptions.enterProductintoSearchBoxField(prop.getProperty("existingProductTwo"));
		searchPage = headeroptions.ClickOnSearchButton();
		String productLimitone = "20";
		searchPage.selectShowOptionInShowDropDownField("productLimitone");
		Assert.assertTrue(searchPage.getProductsCount() == Integer.parseInt(productLimitone));

		String productLimitTwo = "25";
		searchPage.selectShowOptionInShowDropDownField("productLimitTwo");
		Assert.assertTrue(searchPage.getProductsCount() == Integer.parseInt(productLimitTwo));

		String productLimitThree = "75";
		searchPage.selectShowOptionInShowDropDownField("productLimitThree");
		Assert.assertTrue(searchPage.getProductsCount() == Integer.parseInt(productLimitThree));

		String productLimitFour = "100";
		searchPage.selectShowOptionInShowDropDownField("productLimitFour");
		Assert.assertTrue(searchPage.getProductsCount() == Integer.parseInt(productLimitFour));

	}

	@Test(priority = 16)
	public void verifyDisplayingOfSearchFieldAndSearchButtononAllPagesOfTheApplication() {

		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateToPage(getBaseUrl() + prop.getProperty("contactUs"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateToPage(getBaseUrl() + prop.getProperty("RegisterPageUrl"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateToPage(getBaseUrl() + prop.getProperty("loginPageUrl"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateToPage(getBaseUrl() + prop.getProperty("forgottenPageUrl"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		registerpage = headeroptions.navigateToRegisterPage();
		String emailAddress = CommonUtilities.generateBrandNewEmail();
		accountsuccesspage = registerpage.registeringAnAccount(prop.getProperty("firstName"),
				prop.getProperty("lastName"), emailAddress, prop.getProperty("telephoneNumber"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));

		rightcolumoptions = accountsuccesspage.getRightColumnOptions();
		myaccountpage = rightcolumoptions.ClickOnMyAcccountOptionAfterLogin();

		myaccountpage.clickOnEdityourAccountInformation();

		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateBackInBrowser(headeroptions.getDriver());
		myaccountpage.ClickOnChangeYourPasswordlink();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateBackInBrowser(headeroptions.getDriver());

		addressBookEntriesPage = myaccountpage.clickOnModifyAddressBookoption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		addAddressPage = addressBookEntriesPage.clickOnNewAddreesButoon();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		addressBookEntriesPage = addAddressPage.enterMandatoryFieldAndAddAddress(prop.getProperty("firstName"),
				prop.getProperty("lastName"), prop.getProperty("companyName"), prop.getProperty("AddressField1"),
				prop.getProperty("AddressField2"), prop.getProperty("City"), prop.getProperty("PostCode"));

		editAddressPage = addressBookEntriesPage.clickOnEditButton();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		myaccountpage = editAddressPage.clickOnAccountBreadCrumb();
		mywishListPage = myaccountpage.clickOnModifyYourWishlistOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		searchPage = headeroptions.enterProductClickOnsearchButton(prop.getProperty("existingProduct"));

		productDisplayPage = searchPage.clickOnProductOneName();

		shoppingCartPage = productDisplayPage.clickonAddToCartAndSelectShoppingCartOption();

		checkOutPage = shoppingCartPage.clickOncheckOutOption();

		checkoutSuccessPage = checkOutPage.placeorder();
		refreshAndNavigateToPage(checkoutSuccessPage.getDriver(), getBaseUrl() + prop.getProperty("myAccountPage"));

		ordeHistoryPage = myaccountpage.clickOnViewYourOrderHistoryOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		orderInfromationPage = ordeHistoryPage.selectViewOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		productReturnsPage = orderInfromationPage.selectReturnOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		productReturnsPage.selectFirstReasonToReturn();
		productReturnsPage.clickOnSubmitButton();
		rightcolumoptions = productReturnsPage.getRightColumnOptions();

		rightcolumoptions.ClickOnMyAcccountOption();

		myaccountpage.clickOnDownloadsOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateBackInBrowser(headeroptions.getDriver());
		myaccountpage.clickOnRewardPointsOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateBackInBrowser(headeroptions.getDriver());

		productReturnsPage = myaccountpage.clickOnreturnRequestOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		returnInformationPage = productReturnsPage.clickOnViewOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		returnInformationPage.clickOnAccountBreadCrumb();

		myaccountpage.clickOnYourTransactionsPage();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateBackInBrowser(headeroptions.getDriver());
		myaccountpage.clickOnrecurringPaymentsPage();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateBackInBrowser(headeroptions.getDriver());

		affiliatePage = myaccountpage.clickOndityouraffiliateinformation();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		myaccountpage = affiliatePage.updateAffliateAccount(prop.getProperty("chequePayeeName"));
		myaccountpage.clickOnCustomAffiliateTrackingCodeOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateBackInBrowser(headeroptions.getDriver());
		myaccountpage.ClickonSubscribeunsubscribeNewsletterOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateBackInBrowser(headeroptions.getDriver());
		rightcolumoptions = myaccountpage.getRightColumnOptions();
		accountLogoutPage = rightcolumoptions.clickOnLogoutOption();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateToPage(getBaseUrl() + prop.getProperty("aboutUsPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateToPage(getBaseUrl() + prop.getProperty("DeliveryInformationPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("privacyPolicy"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
		navigateToPage(getBaseUrl() + prop.getProperty("TermsAndConditions"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("BrandsPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("sitemapPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("DesktopsCategoryPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("PCsubCategoryPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("MacsubCategoryPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("laptopsandNoteBooksCategoryPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("laptopsandNoteBooksCategoryPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("MacsSubcategorypage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("windowsSubcategoryPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("componentscategoryPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("miceAndTrackBallsSubcatergory"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("monitorsSubCategoryPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("testSubcategoryPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("specialOffersPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("BrandssPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("GiftCertificatePage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		navigateToPage(getBaseUrl() + prop.getProperty("affliateLoginPage"));
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

		searchPage = headeroptions.enterProductClickOnsearchButton(prop.getProperty("existingProduct"));

		productDisplayPage = searchPage.clickOnProductOneName();

		shoppingCartPage = productDisplayPage.clickonAddToCartAndSelectShoppingCartOption();

		guestCheckOutPage = shoppingCartPage.clickOncheckOutWithoutLogin();
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());

	}

	@Test(priority = 17)
	public void verifyNavigatingToPageFromSiteMapPage() {

		footerOptions = new FooterOptions(headeroptions.getDriver());
		siteMappage = footerOptions.SelectSiteMapPage();
		searchPage = siteMappage.clickOOnSearchOption();
		Assert.assertTrue(searchPage.didWeNaviagteToSearchResults());
		Assert.assertTrue(headeroptions.areSearchBoxFieldAndSearchButtonIsDisplayed());
	}

	@Test(priority = 18)
	public void verifyBreadCrumbOptionInSearchResultsPage() {
		searchPage = headeroptions.enterProductClickOnsearchButton(prop.getProperty("existingProduct"));
		searchPage = searchPage.clickOnBreadCrumb();
		Assert.assertTrue(searchPage.didWeNaviagteToSearchResults());
	}

	@Test(priority = 19)
	public void verifyUsingAllOptionsOnSearchResultsPageUsingKeyboardKeys() {

		headeroptions.enterProductClickOnsearchButton("");
		actions = ClickKeyBoardKeyMultipleTimes(headeroptions.getDriver(), Keys.TAB, 21);
		actions = typeTextIntoFieldsUsingActions(actions, prop.getProperty("existingProductOne"));
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ARROW_DOWN, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.SPACE, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 2);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);

		searchPage = new SearchPage(headeroptions.getDriver());
		searchPage.isProductDisplayedInSearchResults();
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 21);
		actions = typeTextIntoFieldsUsingActions(actions, prop.getProperty("textInProductDescription"));
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 3);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.SPACE, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());

		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 26);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);
		productComparisonPage = new ProductComparisonPage(searchPage.getDriver());
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
		navigateBackInBrowser(productComparisonPage.getDriver());
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ARROW_DOWN, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 30);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ARROW_DOWN, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 31);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);

		productDisplayPage = new ProductDisplayPage(searchPage.getDriver());
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productComparisonPage.getDriver());
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productComparisonPage.getDriver());
		
		
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsShoppingCartOptionDisplayedTheSuccessPage());
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IswishListOptionDisplayedTheSuccessPage());
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.TAB, 1);
		actions = ClickKeyBoardKeyMultipleTimes(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsProductComparisonOptionDisplayedTheSuccessPage());
		
	}
}