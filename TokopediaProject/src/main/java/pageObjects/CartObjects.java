package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import elements.CartElements;

public class CartObjects extends CartElements {

	public static void checkOut(WebDriver driver) {
		// Klik button checkout
		driver.findElement(btnCheckout).click();
	}
	
	public static String getDetailItem2(WebDriver driver) {
		// Get list of string product name
		List<String> name = new ArrayList<String>();
		List<WebElement> elementsName = driver.findElements(inventoryItemName);		
	    for (WebElement element : elementsName) {
	    	name.add(element.getText());
	    }	    
		return name.toString();
	}
	
	public static String getDetailItem(WebDriver driver) {
		
		List<String> name = new ArrayList<String>();			
		List<WebElement> elementsNameList = driver.findElements(inventoryItemName);	
		
		for(int i=0; i<elementsNameList.size(); i++) {	
			List<WebElement> elementsNameList2 = driver.findElements(inventoryItemName);	
			List<WebElement> elementsPriceList2 = driver.findElements(inventoryItemPrice);	
			String nameList = elementsNameList2.get(i).getText();
			String priceList = elementsPriceList2.get(i).getText();
			name.add(nameList + priceList);
			}	
		return name.toString();		
	}
	

}
