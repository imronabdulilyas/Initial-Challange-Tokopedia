package imronTest.TokopediaProject;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pageObjects.LoginPageObjects;
import utils.Functions;
import utils.Variables;
import pageObjects.CartObjects;
import pageObjects.CheckOutCompleteObjects;
import pageObjects.CheckOutStepOneObjects;
import pageObjects.CheckOutStepTwoObjects;
import pageObjects.InventoryItemObjects;
import pageObjects.InventoryPageObjects;

/**
 * Hello i'm Imron!
 *
 */
public class BaseTest extends Functions {
	
	WebDriver driver = null;
	ExtentTest test;
	ExtentReports extent;

	String projectPath = System.getProperty("user.dir");	
    String captureDate = projectPath+"\\captures\\" +formattedDate();
		
	@BeforeTest
	@Parameters("browserName")
	public void setUp(String browserName) {
		// Create new folder to save test captures
		new File(projectPath+"/captures/"+formattedDate()).mkdirs();
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/chrome/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath+"/drivers/firefox/geckodriver.exe");
			driver = new FirefoxDriver();
		}
				
		driver.manage().window().maximize();
		driver.get(Variables.url);
				
		// start reporters
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(projectPath+"/reports/"+formattedDate()+" - Reports.html"); 
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);          
        test = extent.createTest("Test Automations", " By Imron Abdul Ilyas");
	}
	
	@AfterMethod
	public void afterMyMethod(ITestResult result)  throws Exception {
		String methodName = result.getMethod().getMethodName();
		Functions.takeSnapShot(driver,  captureDate+ "/" + methodName+ ".png");
        
		if(result.getStatus()==ITestResult.FAILURE) {
			test.fail(methodName + " Failed");
		}else {
			test.pass(methodName + " Pass");	
		}
		extent.flush();
	}
	
	@Test(priority = 0)
	public void loginToSite() {
		// Login to site
		LoginPageObjects.login(driver);
	}
	
	@Test(priority = 1)
	public void verifyLoggedIn() {
		// Verify that you are logged in to the application
		LoginPageObjects.afterLogin(driver);
	}
	
	@Test(priority = 2)
	public void sortTheProductByHighestPrice() {
		// Sort the product by the highest price
		InventoryPageObjects.sortProductByHighestPrice(driver);
	}
	
	@Test(priority = 3)
	public void verifySortingResultByHighestPrice() {
		// Verify the result to match with your query
		InventoryPageObjects.verifySortingByHighestPrice(driver);
	}
	
	@Test(priority = 4)
	public void selectAndVerifyFirstResult() {
		// get detail item first result 
		String itemBefore = InventoryPageObjects.getFirstResult(driver);		
		// select item first result
		InventoryPageObjects.selectFirstResult(driver);		
		// verify item details first result
		String itemAfter = InventoryItemObjects.getDetailItem(driver);	
		if(itemAfter.equals(itemBefore)) {
			Assert.assertTrue(true);
		}else {
			Assert.fail();
		}
	}
	
	@Test(priority = 5)
	public void buySelectedProductAndVerify() {
		// Get product from inventory page
		String itemBefore = InventoryItemObjects.getDetailItem(driver);
		// Buy selected product
		InventoryItemObjects.buySelectedProduct(driver);
		// Get Product from cart page
		String itemAfter = CartObjects.getDetailItem(driver);		
		// Verify selected product
		Assert.assertTrue(itemAfter.contains(itemBefore));
	}
	
	@Test(priority = 6)
	public void checkOutAndEnterRequired() {
		// Click Checkout button
		CartObjects.checkOut(driver);
		// Enter the required details then continue
		CheckOutStepOneObjects.inputFieldsToCheckOut(driver);
	}
		
	@Test(priority = 8)
	public void verifyTheOrderStatus() {
		// Verify the order then finish
		CheckOutStepTwoObjects.verifyOrder(driver);
		// Verify order last status
		CheckOutCompleteObjects.checkOrderCompleted(driver);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
