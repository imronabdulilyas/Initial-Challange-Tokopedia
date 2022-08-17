package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import elements.CheckOutCompleteElements;

public class CheckOutCompleteObjects extends CheckOutCompleteElements {
	
	public static void checkOrderCompleted(WebDriver driver) {
		// Verify complete order status
		String completed = driver.findElement(title).getText();
		Assert.assertTrue(completed.equalsIgnoreCase("Checkout: Complete!"));
	}

}
