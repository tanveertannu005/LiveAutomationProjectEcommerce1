package experiement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
			
			ChromeDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			String baseurl="https://omayo.blogspot.com/";
			driver.get(baseurl);
			
			boolean b=driver.findElement(By.id("dte")).isSelected();
			System.out.println(b);
			
			driver.quit();
			
			
	}

}
