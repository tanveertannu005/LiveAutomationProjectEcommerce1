package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountInformationPage extends RootPage {
		WebDriver driver;
		
		public MyAccountInformationPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		  @FindBy(id = "input-firstname")
		    private WebElement firstNameField;
		    
		    
		   
		    @FindBy(id = "input-lastname")
		    private WebElement lastNameField;
		    
		    @FindBy(id = "input-email")
		    private WebElement EmailField;
		    
		    @FindBy(id = "input-telephone")
		    private WebElement Telephonefield;
		    
		    public String getTelePhoneDomAttribute(String attributeName) {
		        return Telephonefield.getDomAttribute(attributeName);
		    }
		    
		    
		    public String getEmailDomAttribute(String attributeName) {
		        return EmailField.getDomAttribute(attributeName);
		    }
		    
		    
		    public String getFirstnameDomAttribute(String attributeName) {
		        return firstNameField.getDomAttribute(attributeName);
		    }
		    
		    
		    public String getlastnameDomAttribute(String attributeName) {
		       return lastNameField.getDomAttribute(attributeName);
		    }
		    
		    public String getEmailnDomproperty(String propertyName) {
			       return EmailField.getDomProperty(propertyName);
			    }
		}

