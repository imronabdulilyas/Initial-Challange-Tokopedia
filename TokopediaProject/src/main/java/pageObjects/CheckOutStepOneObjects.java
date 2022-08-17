package pageObjects;

import org.openqa.selenium.WebDriver;

import elements.CheckOutStepOneElements;
import utils.Variables;

public class CheckOutStepOneObjects extends CheckOutStepOneElements{
	
	public static void inputFieldsToCheckOut(WebDriver driver) {
		driver.findElement(fieldFirstName).sendKeys(Variables.firstName);
		driver.findElement(fieldLasName).sendKeys(Variables.lastName);
		driver.findElement(fieldPostalCode).sendKeys(Variables.postalCode);
		driver.findElement(btnContinue).click();
	}

}
