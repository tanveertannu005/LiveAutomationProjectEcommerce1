package experiement;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo5 {
	
	public static void main(String[] args) {
		
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://omayo.blogspot.com/");
		
	String str= 	driver.findElement(By.id("rotb")).getDomAttribute("readonly");
	System.out.println(str);
	driver.quit();
	
	}
	
	

}
