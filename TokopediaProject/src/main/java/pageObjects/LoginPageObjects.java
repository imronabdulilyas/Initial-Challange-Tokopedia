package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import elements.LoginElements;
import utils.Variables;

public class LoginPageObjects extends LoginElements {
	
	public static void login(WebDriver driver) {
		driver.findElement(userName).sendKeys(Variables.username);
		driver.findElement(password).sendKeys(Variables.password);
		driver.findElement(btnLogin).click();
	}
	
	public static void afterLogin(WebDriver driver) {
		String expectedUrl= driver.getCurrentUrl(); 
		Assert.assertEquals(expectedUrl, "https://www.saucedemo.com/inventory.html"); 
	}

}
