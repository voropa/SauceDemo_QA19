package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemDetailsPage extends HomePage {
    private final By addToCartButton
            = By.cssSelector("button[id^=add-to-cart]");
    private final By backToProductsButton = By.id("back-to-products");
    private final By itemName = By.cssSelector(".inventory_details_name");

    @Override
    public boolean isPageOpened() {
        return driver.findElement(addToCartButton).isDisplayed();
    }

    public ItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ItemDetailsPage clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
        return this;
    }

    public ProductsPage clickBackToProductsButton() {
        driver.findElement(backToProductsButton).click();
        return new ProductsPage(driver);
    }

    public String getItemName() {
        return driver.findElement(itemName).getText();
    }

    public String getItemDescription() {
        return "";
    }

    public String getItemPrice() {
        return "";
    }

}
