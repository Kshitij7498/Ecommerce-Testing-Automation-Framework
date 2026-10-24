
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage =
            By.cssSelector("[data-test='error']");
    private final By finishButton = By.id("finish");
    private final By completeHeader =
            By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );
    }

    public void enterCustomerDetails(
            String first, String last, String zip
    ) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstName
                )
        ).sendKeys(first);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lastName
                )
        ).sendKeys(last);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        postalCode
                )
        ).sendKeys(zip);
    }

    public void clickContinue() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueButton
                )
        ).click();
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        errorMessage
                )
        ).getText();
    }

    public void clickFinish() {
        wait.until(
                ExpectedConditions.urlContains(
                        "/checkout-step-two.html"
                )
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        finishButton
                )
        ).click();
    }

    public String getCompleteHeader() {
        wait.until(
                ExpectedConditions.urlContains(
                        "/checkout-complete.html"
                )
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        completeHeader
                )
        ).getText();
    }
}
