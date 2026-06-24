package defaultTestPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import configPackage.UniversalProperties;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void EnterLoginCredentials()
	{
		MainTestFlow.LoginProcedure.info("Initializing Login Procedure");
		
		String username = UniversalProperties.GetProperty("loginUsername");
		String password = UniversalProperties.GetProperty("loginPassword");
		
		if(username == "default" && password == "default")
		{
			MainTestFlow.LoginProcedure.fail("Username and password not available. Aborting Login Procedure");
			
			return;
		}
		
		else
		{
			MainTestFlow.LoginProcedure.pass("Username and password retrieved successfully");
		}
		
		try
		{
			WebElement UsernameField = driver.findElement(By.name("username"));
			WebElement PasswordField = driver.findElement(By.name("password"));
			WebElement submitButton = driver.findElement(By.className("oxd-button"));
			
			UsernameField.sendKeys(username);
			PasswordField.sendKeys(password);
			submitButton.click();
			
			MainTestFlow.LoginProcedure.pass("Logged in with credentials");
			
		}
		catch(Exception e)
		{
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
	}

}
