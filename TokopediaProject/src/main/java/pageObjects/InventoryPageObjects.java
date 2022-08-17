package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import elements.InventoryPageElement;

public class InventoryPageObjects extends InventoryPageElement{
	
	public static void sortProductByHighestPrice(WebDriver driver) {
		Select drpSort = new Select(driver.findElement(drpPrice));
		drpSort.selectByVisibleText("Price (high to low)");
	}
	
	public static void verifySortingByHighestPrice(WebDriver driver) {
		// Get string of price
		List<String> values = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(inventoryItemPrice);
	    for (WebElement element : elements) {
	    	values.add(element.getText().replace("$", ""));		    	
	    }
	    // Convert string of array to double of array
	    List<Double> numbers = new ArrayList<Double>(values.size());
	    for(int i = 0; i<values.size();i++) {
	    	numbers.add(Double.parseDouble(values.get(i)));
	    }
	    // Check sorting    
	    List<Double> numbersTemp = new ArrayList<Double>(numbers);	    
	    Collections.sort(numbersTemp, Collections.reverseOrder());
	    // Verify sorting
	    Assert.assertTrue(numbersTemp.equals(numbers));	    
 
	}
	
	public static String getFirstResult(WebDriver driver) {
		// Get name and price from first result
		List<WebElement> elementName = driver.findElements(inventoryItemName);
		List<WebElement> elementPrice = driver.findElements(inventoryItemPrice);
		String name = elementName.get(0).getText();
		String price = elementPrice.get(0).getText();
		String namePrice = name + price;

		return namePrice;		
	}
	
	public static void selectFirstResult(WebDriver driver) {
		// Select the first result
		List<WebElement> elementName = driver.findElements(inventoryItemName);
		elementName.get(0).click();		
	}
		

}
