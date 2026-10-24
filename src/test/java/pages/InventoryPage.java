
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By title = By.cssSelector(".title");
    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By sortDropdown =
            By.cssSelector("[data-test='product-sort-container']");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartLink = By.cssSelector(".shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );
    }

    public String getTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(title)
        ).getText();
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }

    public void sortBy(String option) {
        new Select(
                wait.until(
                        ExpectedConditions.elementToBeClickable(sortDropdown)
                )
        ).selectByValue(option);
    }

    public String getFirstProductName() {
        return driver.findElement(
                By.cssSelector(".inventory_item_name")
        ).getText();
    }

    public void addProductByName(String productName) {
        String safe = productName.replace("'", "\\'");

        By addButton = By.xpath(
                "//div[@class='inventory_item']" +
                "[.//div[normalize-space()='" + safe + "']]" +
                "//button[contains(@class,'btn_inventory')]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        ).click();
    }

    public int getCartCount() {
        return driver.findElements(cartBadge).isEmpty()
                ? 0
                : Integer.parseInt(
                        driver.findElement(cartBadge).getText()
                );
    }

    public void openCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(cartLink)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("/cart.html")
        );

        wait.until(
                ExpectedConditions.textToBe(title, "Your Cart")
        );
    }
}
