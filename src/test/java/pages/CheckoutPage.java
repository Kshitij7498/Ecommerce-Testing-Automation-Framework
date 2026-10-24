
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.cssSelector(".complete-header");
    private final By pageTitle = By.cssSelector(".title");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );
    }

    
    public void enterCustomerDetails(String first, String last, String zip) {
    enterAndVerify(firstName, first);
    enterAndVerify(lastName, last);
    enterAndVerify(postalCode, zip);
    }

    
    private void enterAndVerify(By locator, String value) {
        WebDriverWait fieldWait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement field = fieldWait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        for (int attempt = 1; attempt <= 3; attempt++) {
            field = fieldWait.until(
                    ExpectedConditions.elementToBeClickable(locator)
            );

            field.click();
            field.clear();

            if (!value.isEmpty()) {
                field.sendKeys(value);
            }

            try {
                fieldWait.until(d -> {
                    WebElement current = d.findElement(locator);
                    String actual = current.getAttribute("value");
                    return value.equals(actual);
                });

                return;
            } catch (org.openqa.selenium.TimeoutException e) {
                System.out.println(
                        "Input retry " + attempt
                        + " for " + locator
                        + ". Expected [" + value
                        + "], actual ["
                        + driver.findElement(locator).getAttribute("value")
                        + "]"
                );
            }
        }

        String actual = driver.findElement(locator).getAttribute("value");

        throw new AssertionError(
                "Could not enter expected value into " + locator
                + ". Expected [" + value
                + "] but found [" + actual + "] after 3 attempts."
        );
    }

    public void clickContinue() {
    wait.until(
            ExpectedConditions.elementToBeClickable(continueButton)
    ).click();

    try {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d ->
                        d.getCurrentUrl().contains(
                                "/checkout-step-two.html"
                        )
                        || !d.findElements(errorMessage).isEmpty()
                );
    } catch (org.openqa.selenium.TimeoutException e) {
        System.out.println(
                "Checkout URL after Continue: " + driver.getCurrentUrl()
        );
        System.out.println(
                "Checkout page title: " + driver.getTitle()
        );
        System.out.println(
                "Validation elements found: "
                        + driver.findElements(errorMessage).size()
        );

        throw new AssertionError(
                "Continue did not navigate to checkout overview "
                        + "or expose a validation message.",
                e
        );
    }
    }

    private String fieldValue(By locator) {
        if (driver.findElements(locator).isEmpty()) {
            return "<field missing>";
        }

        return driver.findElement(locator).getAttribute("value");
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }

    public void clickFinish() {
        wait.until(
                ExpectedConditions.urlContains("/checkout-step-two.html")
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("/checkout-complete.html")
        );
    }

    public String getCompleteHeader() {
        wait.until(
                ExpectedConditions.urlContains("/checkout-complete.html")
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(completeHeader)
        ).getText();
    }
}
