package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTests extends BaseTest {
    private InventoryPage inventory;

    @BeforeMethod
    public void signInAndAddItem() {
        new LoginPage(driver).loginAs("standard_user", "secret_sauce");
        inventory = new InventoryPage(driver);
        inventory.addProductByName("Sauce Labs Backpack");
        inventory.openCart();
    }

    @Test
    public void cartShowsAddedItem() {
        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.getTitle(), "Your Cart");
        Assert.assertEquals(cart.getItemCount(), 1);
    }

    @Test
    public void removingItemEmptiesCart() {
        CartPage cart = new CartPage(driver);
        cart.removeFirstItem();
        Assert.assertEquals(cart.getItemCount(), 0);
    }
}
