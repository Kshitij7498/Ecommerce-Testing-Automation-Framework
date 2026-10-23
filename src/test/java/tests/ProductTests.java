package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

public class ProductTests extends BaseTest {
    private InventoryPage inventory;

    @BeforeMethod
    public void signIn() {
        new LoginPage(driver).loginAs("standard_user", "secret_sauce");
        inventory = new InventoryPage(driver);
    }

    @Test
    public void inventoryPageDisplaysProducts() {
        Assert.assertEquals(inventory.getTitle(), "Products");
        Assert.assertTrue(inventory.getProductCount() > 0, "Expected at least one product");
    }

    @Test
    public void nameAscendingSortPlacesAFirst() {
        inventory.sortBy("az");

        Assert.assertEquals(
        inventory.getFirstProductName(),
        "Sauce Labs Backpack",
        "Expected alphabetical sorting to place Sauce Labs Backpack first"
     );
    }

    @Test
    public void addingProductUpdatesCartBadge() {
        inventory.addProductByName("Sauce Labs Backpack");
        Assert.assertEquals(inventory.getCartCount(), 1);
    }
}
