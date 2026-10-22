package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;
    public CheckoutPage(WebDriver driver) { this.driver = driver; }
    public void enterCustomerDetails(String first, String last, String zip) {
        driver.findElement(By.id("first-name")).sendKeys(first);
        driver.findElement(By.id("last-name")).sendKeys(last);
        driver.findElement(By.id("postal-code")).sendKeys(zip);
    }
    public void clickContinue() { driver.findElement(By.id("continue")).click(); }
    public String getErrorMessage() { return driver.findElement(By.cssSelector("[data-test='error']")).getText(); }
    public void clickFinish() { driver.findElement(By.id("finish")).click(); }
    public String getCompleteHeader() { return driver.findElement(By.cssSelector(".complete-header")).getText(); }
}
