package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtilities {

	WebDriver driver;
	Actions actions;
	Select select;

	public ElementUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public Actions getActions(WebDriver driver) {
		Actions actions = new Actions(driver);
		return actions;
	}

	public void copyingTextUsingKeyboardKeys(WebDriver driver) {
		actions = getActions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).keyDown(Keys.CONTROL).sendKeys("c").build()
				.perform();

	}

	public void pasteTextIntoFieldUsingKeyboardkeys(WebElement element, WebDriver driver) {
		actions = getActions(driver);
		actions.click(element).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
	}

	
	public void clickEitherOfTheseElements(WebElement elementone,WebElement elementTwo ) {
		if(IsElementDisplayedOnWithoutexception(elementone)) {
			elementone.click();
			}else  {
				elementTwo.click();
			}
		
	}
	
	
	
	
	public void ClickOnElement(WebElement element) {
		WaitForElementToBeClickable(element);
		element.click();
	}
	
	public void waitForElement(WebElement element,int seconds) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public boolean waitAndCheckForElementDisplayedStatus(WebElement element,int seconds) {
		boolean b=false;
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			b=false;
		} catch (Exception e) {
			b=false;
		}
		return b;
		
	}
	
	
	public void waitForElementAndClick(WebElement element,int seconds) {
		waitForElement(element,seconds);
		ClickOnElement(element);
	}
	

	public String getElementText(WebElement element) {
		String elementText = "";
		if (IsElementDisplayedOnPage(element)) {
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

	public boolean IsElementDisplayedOnWithoutexception(WebElement element) {
		boolean b=false;
		try {
			
		b=element.isDisplayed();	
		} catch (NoSuchElementException e) {
			b=false;
		}
		return b;
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
		String value = "";
		value = element.getCssValue(cssPropertyName);
		return value;
	}

	public void clearTextFromelement(WebElement element) {
		if (IsElementDisplayedOnPage(element) && element.isEnabled()) {
			element.clear();
		}
	}

	public void enterTextIntoElement(WebElement element, String text) {
		if (IsElementDisplayedOnPage(element) && element.isEnabled()) {
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

	public int getElementsCount(List<WebElement> elements) {

		int count = 0;

		try {
			count = elements.size();
		} catch (NoSuchElementException e) {
			count = 0;
		}
		return count;

	}

	// Here we Done Typecasting Study This explore this very Important
	public void selectOptionDropdownFieldUsingIndex(WebElement element, int optionIndex) {
		if (IsElementDisplayedOnPage(element) && element.isEnabled()) {
			element.click();
			Select select = new Select(element);
			select.selectByIndex(optionIndex);

		}

	}
	
	public void selectOptionDropdownFieldUsingVisibleText(WebElement element, String optionText) {
		if (IsElementDisplayedOnPage(element) && element.isEnabled()) {
			element.click();
			Select select = new Select(element);
			select.selectByVisibleText(optionText);

		}
	}

	public List<String> getTextofElements(List<WebElement> items) {
		List<String>itemsNames = new ArrayList<String>();
		for(WebElement item:items) {
			getElementText(item);
			itemsNames.add(getElementText(item));
		}
		return itemsNames;
	}
	
	public String captureScreenshotAndReturnPath(String testName, WebDriver driver) {

	    TakesScreenshot ts = (TakesScreenshot) driver;

	    File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);

	    String destScreenshotPath = System.getProperty("user.dir")
	            + "\\Screenshots\\"
	            + testName
	            + ".png";

	    try {

	        FileHandler.copy(srcScreenshot, new File(destScreenshotPath));

	    } catch (IOException e) {

	        e.printStackTrace();
	    }

	    return destScreenshotPath;
	}
	
}
