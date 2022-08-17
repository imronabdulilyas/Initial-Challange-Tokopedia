package pageObjects;

import org.openqa.selenium.WebDriver;

import elements.InventoryItemElements;

public class InventoryItemObjects extends InventoryItemElements {
	
	public static void buySelectedProduct(WebDriver driver) {
		driver.findElement(btnAddToCart).click();
		driver.findElement(btnShoppingCart).click();
	}
	
	public static String getDetailItem(WebDriver driver) {
		String nameDetail = driver.findElement(inventoryDetailName2).getText();
		String priceDetail = driver.findElement(inventoryDetailPrice2).getText();
		String namePriceDetail = nameDetail+priceDetail;		
		return namePriceDetail;
	}
		
}
