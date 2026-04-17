package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtilities {
		
	WebDriver driver;
	
	public ElementUtilities(WebDriver driver) {
		this.driver=driver;
	}
	public void ClickOnElement(WebElement element) {
		if(element.isDisplayed() && element.isEnabled()) {
			element.click();
		}
	}
}
