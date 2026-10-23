package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void validUserCanLogIn() {
        new LoginPage(driver).loginAs("standard_user", "secret_sauce");
        Assert.assertEquals(
         driver.getCurrentUrl().replaceAll("/$", ""),
         baseUrl.replaceAll("/$", "") + "/inventory.html");
    }

    @Test
    public void invalidPasswordShowsError() {
        LoginPage page = new LoginPage(driver);
        page.loginAs("standard_user", "wrong_password");
        Assert.assertTrue(page.getErrorMessage().toLowerCase().contains("username and password"));
    }

    @Test
    public void emptyCredentialsShowValidationError() {
        LoginPage page = new LoginPage(driver);
        page.clickLogin();
        Assert.assertTrue(page.getErrorMessage().toLowerCase().contains("username is required"));
    }
}
