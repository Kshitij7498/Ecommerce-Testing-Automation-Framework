package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    public CartPage(WebDriver driver) { this.driver = driver; }
    public String getTitle() { return driver.findElement(By.cssSelector(".title")).getText(); }
    public int getItemCount() { return driver.findElements(By.cssSelector(".cart_item")).size(); }
    public void removeFirstItem() { driver.findElement(By.cssSelector(".cart_item button")).click(); }
    public void clickCheckout() { driver.findElement(By.id("checkout")).click(); }
}
