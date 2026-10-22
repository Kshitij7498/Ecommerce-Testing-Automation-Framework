package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) { this.driver = driver; }

    public LoginPage enterUsername(String value) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(value);
        return this;
    }
    public LoginPage enterPassword(String value) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(value);
        return this;
    }
    public void clickLogin() { driver.findElement(loginButton).click(); }
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
    public void loginAs(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}
