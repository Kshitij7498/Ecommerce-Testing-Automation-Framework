package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CheckoutTests extends BaseTest {
    @BeforeMethod
    public void signInAddItemAndStartCheckout() {
        new LoginPage(driver).loginAs("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addProductByName("Sauce Labs Backpack");
        inventory.openCart();
        new CartPage(driver).clickCheckout();
    }

    @Test
    public void missingFirstNameShowsValidationError() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterCustomerDetails("", "Tester", "440001");
        checkout.clickContinue();
        Assert.assertTrue(checkout.getErrorMessage().toLowerCase().contains("first name"));
    }

    @Test
    public void validCheckoutCompletesOrder() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterCustomerDetails("Test", "User", "440001");
        checkout.clickContinue();
        checkout.clickFinish();
        Assert.assertEquals(checkout.getCompleteHeader(), "Thank you for your order!");
    }
}
