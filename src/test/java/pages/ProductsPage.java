package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends HomePage {

    private final By addToCartButton = By.cssSelector("button[id^=add-to-cart]");
    private final By productLink = By.cssSelector("a[id$=_link]");
    private final String productContainerLocator
            = "//div[@class='inventory_item_name' and text()='%s']/ancestor::div[@class='inventory_item']";

    private final By productsPageHeader = By.id("header_container");

    @Override
    public boolean isPageOpened() {
        return isProductsPageHeaderDisplayed();
    }

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductsPageHeaderDisplayed() {
        return driver.findElement(productsPageHeader).isDisplayed();
    }

    public String getProductDescription(String productsName) {
        // return products description text
        return "";
    }

    public String getProductPrice(String productsName) {
        // return products price text
        return "";
    }

    public ItemDetailsPage openItemByName(String productsName) {
        WebElement productContainer = getProductContainerByName(productsName);
        productContainer.findElement(productLink).click();
        return new ItemDetailsPage(driver);
    }

    public void clickAddToCartButton(String productsName) {
        WebElement productContainer = getProductContainerByName(productsName);
        productContainer.findElement(addToCartButton).click();
    }


    private WebElement getProductContainerByName(String productsName) {
        return driver.findElement(
                By.xpath(
                        String.format(productContainerLocator, productsName)
                )
        );
    }
}
