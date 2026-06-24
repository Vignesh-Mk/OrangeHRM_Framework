package defaultTestPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import configPackage.UniversalProperties;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTestFlow {

	WebDriver driver;
	
	@BeforeTest
	public void SetupTest()
	{
		SetupBrowserDrivers();
	}
	
	@Test
	public void StartTest()
	{
		String targetURL = UniversalProperties.GetProperty("targetURL");
		
		driver.get(targetURL);
	}
	
	@AfterTest
	public void EndTest()
	{
		
	}
	
	private void SetupBrowserDrivers()
	{
		String browser = UniversalProperties.GetProperty("browser");
		
		switch(browser)
		{
			case "default":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
				
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
				
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
		}
	}

}
