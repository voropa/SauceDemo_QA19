package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ItemDetailsPage;

public class ItemDetailsTest extends BaseTest {
    final static String PRODUCT_NAME = "Sauce Labs Fleece Jacket";

    ItemDetailsPage itemDetailsPage;

    @BeforeClass (alwaysRun = true)
    public void initialise() {
        itemDetailsPage = new ItemDetailsPage(driver);
    }

    @Test(groups = {"Smoke"})
    public void verifyItemNameAndPriceOnDetailsPage() {
        boolean isPageOpened = loginPage.setUserName(USERNAME)
                .setPassword(PASSWORD)
                .clickLoginButton().isPageOpened();
        Assert.assertTrue(isPageOpened, "Products page is not opened");

        isPageOpened = productsPage.openItemByName(PRODUCT_NAME).isPageOpened();
        Assert.assertTrue(isPageOpened, "ItemDetails page  page is not opened");

        // Assert.assertEquals(itemName, PRODUCT_NAME);
        String productPrice =  itemDetailsPage.clickAddToCartButton()
                .clickBackToProductsButton()
                .getProductPrice(PRODUCT_NAME);
        Assert.assertEquals(productPrice, "39");

        // productsPage.waitForProductsPageHeaderPresent();
        itemDetailsPage.getItemName();
        productsPage.openItemByName(PRODUCT_NAME);
        Assert.assertEquals(itemDetailsPage.getItemName(), PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
    }

    @Test(groups = {"Smoke"})
    public void testN1() {

    }
    @Test
    public void testN2() {

    }
}
