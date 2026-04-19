package utilities;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtilities {

	WebDriver driver;

	public ElementUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public void ClickOnElement(WebElement element) {
	    WaitForElementToBeClickable(element);
	    element.click();
	}

	public String getElementText(WebElement element) {
		String elementText = "";
		if (IsElementDisplayedOnPage(element))
		{
			elementText = element.getText();
		}
		return elementText;
	}

	public boolean IsElementDisplayedOnPage(WebElement element) {
	    try {
	        return element.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	

	public boolean IsElementDisplayed(WebElement element) {
		boolean b = false;
		try {
			b = element.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	public String getelementDomAttribute(WebElement element, String attributeName) {
		return element.getDomAttribute(attributeName);

	}

	public String getElementDomProperty(WebElement element, String attributeName) {
		return element.getDomProperty(attributeName);
	}

	public boolean IsElementIsSelected(WebElement element) {
		boolean b = false;
		if (IsElementDisplayedOnPage(element)) {
			b = element.isSelected();
		}
		return b;
	}
	
	public String getElementCssvalue(WebElement element, String cssPropertyName) {
		String value="";
		value=element.getCssValue(cssPropertyName);
		return value;
	}
	
	public void clearTextFromelement(WebElement element) {
		if (IsElementDisplayedOnPage(element) && element.isEnabled()) {
			element.clear();
		}
	}

	public void enterTextIntoElement(WebElement element, String text) {
		if(IsElementDisplayedOnPage(element) && element.isEnabled()) {
			clearTextFromelement(element);
			element.sendKeys(text);
		}
	}
	
	public void WaitForElementToBeVisible(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void WaitForElementToBeClickable(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
}
