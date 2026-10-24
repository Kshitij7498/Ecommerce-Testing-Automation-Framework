
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By title = By.cssSelector(".title");
    private final By cartItems = By.cssSelector(".cart_item");
    private final By removeButton =
            By.cssSelector(".cart_item button[data-test^='remove-']");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(title)
        ).getText();
    }

    public int getItemCount() {
        wait.until(ExpectedConditions.urlContains("/cart.html"));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".cart_list")
                )
        );

        return driver.findElements(cartItems).size();
    }

    public void removeFirstItem() {
        wait.until(ExpectedConditions.urlContains("/cart.html"));

        wait.until(
                ExpectedConditions.elementToBeClickable(removeButton)
        ).click();

        wait.until(
                ExpectedConditions.numberOfElementsToBe(cartItems, 0)
        );
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.urlContains("/cart.html"));

        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("/checkout-step-one.html")
        );
    }
}
