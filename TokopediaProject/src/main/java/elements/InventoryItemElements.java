package elements;

import org.openqa.selenium.By;

public class InventoryItemElements {
	
	public static By btnAddToCart = By.xpath("//*[contains(text(), 'Add to cart')]");
	public static By btnShoppingCart = By.className("shopping_cart_link");
	public static By inventoryDetailName2 = By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[1]");
	public static By inventoryDetailDesc2 = By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[2]");
	public static By inventoryDetailPrice2 = By.xpath("/html/body/div/div/div/div[2]/div/div/div[2]/div[3]");

}
