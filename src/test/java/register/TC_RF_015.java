package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.CommonUtilities;

public class TC_RF_015 {

	@Test
	public void VerifyTheDetailsThatAreProvidedWhileRegisteringAnAccountAreStoredInTheDatabase() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("http://localhost/opencart/");

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		String firstNameInputData = "Arun";
		driver.findElement(By.id("input-firstname")).sendKeys(firstNameInputData);

		String lastNameInputData = "Motoori";
		driver.findElement(By.id("input-lastname")).sendKeys(lastNameInputData);

		String emailInputData = CommonUtilities.generateBrandNewEmail();
		driver.findElement(By.id("input-email")).sendKeys(emailInputData);

		String passwordInputData = "12345";
		driver.findElement(By.id("input-password")).sendKeys(passwordInputData);

		driver.findElement(By.xpath("//*[@id=\"input-newsletter\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"form-register\"]/div/div/input")).click();

		driver.findElement(By.xpath("//button[text()='Continue']")).click();

		// --- Database Connection ---
		String JdbcUrl = "jdbc:mysql://localhost:3306/opencart_db";
		String DbUser = "root";
		String DbPassword = "";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String firstNameStoredInDatabase = null;
		String lastNameStoredInDatabase = null;
		String emailStoredInDatabase = null;

		try {
			connection = DriverManager.getConnection(JdbcUrl, DbUser, DbPassword);
			System.out.println("Connected to the database!");

			statement = connection.createStatement();

			// Fetch the most recent record based on descending customer_id
			String sql = "SELECT firstname, lastname, email FROM oc_customer ORDER BY customer_id DESC LIMIT 1";
			resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				firstNameStoredInDatabase = resultSet.getString("firstname");
				lastNameStoredInDatabase = resultSet.getString("lastname");
				emailStoredInDatabase = resultSet.getString("email");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// --- Validation ---
		Assert.assertEquals(firstNameStoredInDatabase, firstNameInputData, "First name mismatch in DB");
		Assert.assertEquals(lastNameStoredInDatabase, lastNameInputData, "Last name mismatch in DB");
		Assert.assertEquals(emailStoredInDatabase, emailInputData, "Email mismatch in DB");

		driver.quit();
	}
}
