package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AllureUtils;

@Epic("Epic 1")
@Feature("Feature1")
public class LoginTests extends BaseTest {

    @Test(description = "Positive Login test", groups = {"Smoke", "Login"})
    @Description("Positive login test description. sdjfsdhfhsfdshfdshf. sdjfhjsdfhjsdjfsjfd")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://google.com/fgdfg/dfgdfg/dfgdfg/dfg")
    @Story("Story 1")
    @TmsLink("1")
    public void positiveLoginTest() {
        System.out.println(System.getProperty("browser"));
        loginPage.setUserName("wqeqwe");
        loginPage.setPassword(PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isProductsPageHeaderDisplayed());
    }

    @Test(description = "Negative Login test", groups = {"Regression", "Negative", "Login"}, dataProvider = "negativeLoginTestData")
    @Description("Negative login test description")
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        loginPage.setUserName(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(),
                expectedErrorMessage);
    }

    @DataProvider
    public Object[][] negativeLoginTestData() {
        return new Object[][]{
                {"T-shgirt", "sdfsdfsdfdfsdfdsfdsfds", "29$"},
                {USERNAME, "", "Epic sadface: Password is required"},
                {"locked_out_user", PASSWORD, "Epic sadface: Sorry, this user has been locked out."},
                {"", "", "Epic sadface: Username is required"},
        };
    }
}
