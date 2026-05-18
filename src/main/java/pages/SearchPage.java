package pages;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utilities.CommonUtilities;

public class SearchPage extends RootPage {
	WebDriver driver;

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(linkText = "HP LP3065")
	private WebElement existingProductOne;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	private WebElement SearchPageBreadCrumb;

	@FindBy(xpath = "//p[text()='There is no product that matches the search criteria.']")
	private WebElement noProductMessage;

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> productThumbNail;
	
	@FindBy(id = "input-search")
	private WebElement searchCriteriaField;
	
	@FindBy(id="button-search")
	private WebElement SearchButton;
	
	@FindBy(id="description")
	private WebElement searchInproductDescriptionField;
	
	@FindBy(linkText = "iMac")
	private WebElement existingProductThree;
	
	@FindBy(name = "category_id")
	private WebElement CategoriesDropDownField;
	
	@FindBy(name = "sub_category")
	private WebElement searchInSubCategoriesOption;
	
	@FindBy(id = "list-view")
	private WebElement listOption;
	
	@FindBy(id="grid-view")
	private WebElement gridOption;

	@FindBy(xpath = "//div[@class='product-thumb']//span[text()='Add to Cart']")
	private WebElement addToCartOption;
	
	@FindBy(xpath = "//button[@*='Add to Wish List']")
	private WebElement addToWishListOption;
	
	@FindBy(xpath = "//button[@*='Compare this Product']")
	private WebElement compareThisProductOption;
	
	@FindBy(xpath = "(//div[@class='product-thumb']//img)[1]")
	private WebElement productOneImage;
	
	@FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[1]")
	private WebElement productOneName;
	
	@FindBy(id = "compare-total")
	private WebElement productCompareOption;
	
	@FindBy(id = "input-sort")
	private WebElement sortDropDownfield;
	
	@FindBy(xpath = "//div[@class='product-thumb']//h4/a")
	private List<WebElement> sortedProducts;
	
	@FindBy(xpath =    "//*[@id=\"input-limit\"]")
	private WebElement ShowDropDownField;
	
	public boolean didProductGotDisplayedInAscendingOrder() {
	 return CommonUtilities.areItemsInListAreInAscendingorde(elementUtilities.getTextofElements(sortedProducts));
	}
	
	public void selectSortOptionInDropDownField(String optionText) {
		elementUtilities.selectOptionDropdownFieldUsingVisibleText(sortDropDownfield, optionText);
	}
	
	public void selectShowOptionInShowDropDownField(String optionText) {
		elementUtilities.selectOptionDropdownFieldUsingVisibleText(ShowDropDownField, optionText);
	}
	
	public ProductComparisonPage selectProductCompareOption() {
		elementUtilities.ClickOnElement(productCompareOption);
		return new ProductComparisonPage(driver);
	}
	
	public void selectGridOption() {
		elementUtilities.ClickOnElement(gridOption);
		
	}
	
	
	public ProductDisplayPage clickOnProductOneName() {
		elementUtilities.ClickOnElement(productOneName);
		return new ProductDisplayPage(driver);
	}
	
	
	public ProductDisplayPage clickOnProductOneImage() {
		elementUtilities.ClickOnElement(productOneImage);
		return new ProductDisplayPage(driver);
	}
	
	public void clickOnCompareThisProductOption() {
		elementUtilities.ClickOnElement(compareThisProductOption);
	}
	
	
	public void clickOnAddToWishListOption() {
		elementUtilities.ClickOnElement(addToWishListOption);
	}
	
	public void clickOnAddToCartOption() {
		elementUtilities.ClickOnElement(addToCartOption);
		
	}
	
	public void SelectListOption() {
		elementUtilities.ClickOnElement(listOption);
	}
	
	public void SelectToSearchInSubCategory() {
		elementUtilities.ClickOnElement(searchInSubCategoriesOption);
	} 
	
	public void SelectOptionFromCategoryDropDownField(int optionIndex) {
		elementUtilities.selectOptionDropdownFieldUsingIndex(CategoriesDropDownField, optionIndex);
	}
	
	public void SelectOptionFromCategoryDropDownField(String optionText) {
		elementUtilities.selectOptionDropdownFieldUsingVisibleText(CategoriesDropDownField, optionText);
	}
	
	
	
	public void SelectSearchInProductInDescription() {
		elementUtilities.ClickOnElement(searchInproductDescriptionField);
	}
	
	
	public void enterTextInProductDescriptionIntoSearchCriteriaField(String textInProductDescription ) {
		elementUtilities.enterTextIntoElement(searchCriteriaField, textInProductDescription);
	}
	
	public void ClickOnSearchButton() {
		elementUtilities.ClickOnElement(SearchButton);
	}
	
	public void enterProductIntoSearchCriteriaField(String productName) {
		elementUtilities.enterTextIntoElement(searchCriteriaField, productName);
	}
	
	public String getSearchCriteriaFieldPlaceHolderText() {
	return	elementUtilities.getelementDomAttribute(searchCriteriaField, "placeholder");
	}

	public int getProductsCount() {
		return elementUtilities.getElementsCount(productThumbNail);
	}

	public String GetNoProductMessage() {
		return elementUtilities.getElementText(noProductMessage);
	}

	public boolean isProductFromCorrectCategoryDisplayedInSearchResults() {
		return elementUtilities.IsElementDisplayed(existingProductThree);
	}
	
	
	public boolean isProductDisplayedInSearchResults() {
		return elementUtilities.IsElementDisplayed(existingProductOne);
	}
	
	public boolean isProductHavingTextInItsDescriptionDisplayedInSearchResults() {
		return elementUtilities.IsElementDisplayed(existingProductThree);
	}

	public boolean didWeNaviagteToSearchResults() {
		return elementUtilities.IsElementDisplayed(SearchPageBreadCrumb);
	}

	public SearchPage clickOnBreadCrumb() {
		elementUtilities.ClickOnElement(SearchPageBreadCrumb);
		return new SearchPage(driver);
	}
}
