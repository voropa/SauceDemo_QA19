package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AllureUtils;

import java.util.List;

public class LoginPageFactory extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    @FindBy(css = "list elements locator")
    private List<WebElement> listOfElements;

    @Override
    public boolean isPageOpened() {
        return loginButton.isDisplayed();
    }

    public LoginPageFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    @Step("Setting username = {userName}")
    public LoginPageFactory setUserName(String userName) {
        usernameInput.sendKeys(userName);
        AllureUtils.attachScreenshot(driver);
        return this;
    }

    @Step("Setting password = {password}")
    public LoginPageFactory setPassword(String password) {
        passwordInput.sendKeys(password);
        AllureUtils.attachScreenshot(driver);
        return this;
    }

    @Step("Clicking Login button")
    public ProductsPage clickLoginButton() {
        loginButton.click();
        AllureUtils.attachScreenshot(driver);
        return new ProductsPage(driver);
    }
}
