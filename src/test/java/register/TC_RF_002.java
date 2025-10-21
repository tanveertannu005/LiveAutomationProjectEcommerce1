package register;

import java.time.Duration;
import java.util.Properties;
import javax.mail.*;
import javax.mail.search.FlagTerm;

import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_002 {
    
    @Test
    public void VerifyThankyouforconfirmationEmailonSuccesfullRegistration() throws Exception {
        
        // ===== Selenium Part =====
        WebDriver driver = new ChromeDriver();
        String baseurl = "https://www.amazon.in/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(baseurl);
        driver.manage().window().maximize();
        
        driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
        driver.findElement(By.id("ap_email_login")).sendKeys("tanveertannu05@gmail.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.linkText("Forgot password?")).click();
        
        // ===== Email Verification =====
        String host = "imap.gmail.com"; 
        String username = "yourEmail@gmail.com";      // ✅ replace with your Gmail
        String appPassword = "yourAppPassword";       // ✅ Gmail App Password (not normal pwd)
        
        String expectedSubject = "Amazon password assistance"; 
        String expectedFromEmail = "account-update@amazon.in"; 
        String expectedBodyContent = "To authenticate, please use the following link"; 
        String link = "";
        
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");
        
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore("imaps");
        store.connect(host, username, appPassword);
        
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        
        // Fetch unread mails
        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        
        boolean found = false;
        
        for (int i = messages.length - 1; i >= 0; i--) {
            Message message = messages[i];
            
            if (message.getSubject() != null && message.getSubject().contains(expectedSubject)) {
                found = true;
                
                // Assertions
                Assert.assertTrue(message.getSubject().contains(expectedSubject), "Subject mismatch!");
                Assert.assertTrue(message.getFrom()[0].toString().contains(expectedFromEmail), "Sender mismatch!");
                
                String actualEmailBody = getTextFromMessage(message);
                Assert.assertTrue(actualEmailBody.contains(expectedBodyContent), "Body content mismatch!");
                
                // Extract link safely
                if (actualEmailBody.contains("600\">")) {
                    String[] ar = actualEmailBody.split("600\">");
                    if (ar.length > 1) {
                        String linkPart = ar[1];
                        String[] arr = linkPart.split("</a>");
                        link = arr[0].trim();
                        System.out.println("✅ Extracted link: " + link);
                    }
                }
                break;
            }
        }
        
        if (!found) {
            System.out.println("❌ No confirmation email found.");
        }
        
        inbox.close(false);
        store.close();
        
        driver.quit(); // close browser at end
    }
    
    // Utility method to read email body
    private String getTextFromMessage(Message message) throws Exception {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            String html = (String) message.getContent();
            result = Jsoup.parse(html).text();
        }
        return result;
    }
}
