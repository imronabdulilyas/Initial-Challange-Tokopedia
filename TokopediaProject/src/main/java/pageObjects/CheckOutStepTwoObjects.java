package pageObjects;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import elements.CheckOutStepTwoElements;

public class CheckOutStepTwoObjects extends CheckOutStepTwoElements{

	public static void verifyOrder(WebDriver driver) {		
		// Get list of orders
		List<String> values = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(inventoryItemPrice);
	    for (WebElement element : elements) {
	    	values.add(element.getText().replace("$", ""));		    	
	    }	    
	    // Convert string to double
	    List<Double> numbers = new ArrayList<Double>(values.size());
	    for(int i = 0; i<values.size();i++) {
	    	numbers.add(Double.parseDouble(values.get(i)));
	    }	    
	    // Final price checking for Item Total and Total
	    Assert.assertTrue(verifyItemTotal(numbers).equals(getItemTotal(driver)));
	    Assert.assertTrue(verifyTotal(numbers, driver).equals(getTotal(driver)));	    
	    // Click finish button
	    driver.findElement(btnFinish).click();   
	}
	
	// Sum up values of items
	public static Double verifyItemTotal(List<Double> numbers) {
		Double itemTotal = 0.0;		
		for(int i = 0; i<numbers.size();i++) {
			itemTotal = itemTotal + numbers.get(i);
	    }
		return itemTotal;
	}
	
	// Sum up value of items plus tax
	public static String verifyTotal(List<Double> numbers, WebDriver driver) {
		Double finalTotal = 0.00;
		DecimalFormat df = new DecimalFormat("0.00");
		finalTotal = verifyItemTotal(numbers) + getTax(driver);
		return df.format(finalTotal);
	}
	
	// Get Tax text to double
	public static Double getTax(WebDriver driver) {
		String tax = driver.findElement(summaryTax).getText().replace("Tax: $", "");
		Double taxValue = Double.valueOf(tax);		
		Double valueTax = 0.0;
		valueTax = taxValue;
		return valueTax;			
	}
	
	// Get Total Item text to double
	public static Double getItemTotal(WebDriver driver) {
		String itemTotal = driver.findElement(summarySubTotal).getText().replace("Item total: $", "");
		Double itemTotalValue = Double.valueOf(itemTotal);		
		Double valueItemTotal = 0.0;
		valueItemTotal = itemTotalValue;
		return valueItemTotal;			
	}
	
	// Get Total text
	public static String getTotal(WebDriver driver) {
		String total = driver.findElement(summaryTotal).getText().replace("Total: $", "");
		return total;			
	}

}
