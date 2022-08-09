package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.LoginPageFactory;
import pages.ProductsPage;
import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPageFactory loginPage;
    protected ProductsPage productsPage;

    protected final static String USERNAME = "standard_user";
    protected final static String PASSWORD = "secret_sauce";

    @BeforeClass (alwaysRun = true, description = "Initialize driver")
    public void setUp(ITestContext testContext) throws Exception {
        String browserName = System.getProperty("browser", "chrome");
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--headless");

        } else if (browserName.equals("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        } else {
            throw new Exception("Undefined browser type");
        }
        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPageFactory(driver);
        productsPage = new ProductsPage(driver);
        testContext.setAttribute("driver", driver);
    }

    @BeforeMethod(alwaysRun = true, description = "Navigate")
    public void navigate() {
        driver.get("https://www.saucedemo.com");
    }


    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
    }

    @AfterClass (alwaysRun = true, description = "Close driver")
    public void tearDown() {
        driver.quit();
    }
}
