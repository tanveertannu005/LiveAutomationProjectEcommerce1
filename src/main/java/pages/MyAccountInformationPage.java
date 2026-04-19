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
		    return	elementUtilities.getelementDomAttribute(Telephonefield, attributeName);
		        
		    }
		    
		    
		    public String getEmailDomAttribute(String attributeName) {
		    	return	elementUtilities.getelementDomAttribute(EmailField, attributeName);
		        
		    }
		    
		    
		    public String getFirstnameDomAttribute(String attributeName) {
		    return	elementUtilities.getelementDomAttribute(firstNameField, attributeName);
		      
		    }
		    
		    
		    public String getlastnameDomAttribute(String attributeName) {
		    	return	elementUtilities.getelementDomAttribute(lastNameField, attributeName);
		        
		    }
		    
		    public String getEmailnDomproperty(String propertyName) {
		    return	elementUtilities.getElementDomProperty(EmailField, propertyName);
			      
			    }
		}

