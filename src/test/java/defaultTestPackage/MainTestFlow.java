package defaultTestPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import configPackage.UniversalProperties;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTestFlow {

	WebDriver driver;
	
	public ExtentReports extent;
	public static ExtentTest generalFlow;
	public static ExtentTest LoginProcedure;
	
	
	@BeforeTest
	public void SetupTest()
	{
		SetupExtentReports();
		SetupBrowserDrivers();
	}
	
	@Test
	public void StartTest()
	{
		SetupTests();
		
		String targetURL = UniversalProperties.GetProperty("targetURL");
		
		generalFlow.info("received target URL: " + targetURL);
		
		generalFlow.info("Opening target URL");
		
		driver.get(targetURL);
		
		generalFlow.info("Target URL opened");
		
		WebDriverWait wait_1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement submitButton = wait_1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-button")));
			
		LoginPage loginPage = new LoginPage(driver);
		
		if(submitButton == null)
		{
			generalFlow.fail("Button not loaded. Test aborted.");
			return;
		}
		else
		{
			generalFlow.pass("Button found. Proceeding with tests.");
		}
		
		loginPage.EnterLoginCredentials();
	}
	
	@AfterTest
	public void EndTest()
	{
		
		if(extent != null)
		{
			extent.flush();
		}
		
		//driver.close();
		
		driver.quit();
	}
	
	private void SetupExtentReports()
	{
		ExtentSparkReporter spark = new ExtentSparkReporter("TestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}
	
	private void SetupTests()
	{
		generalFlow = extent.createTest("General Flow");
		LoginProcedure = extent.createTest("Login Procedure");
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
