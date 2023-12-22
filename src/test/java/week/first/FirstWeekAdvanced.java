package week.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Instant;


public class FirstWeekAdvanced {
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    WebDriver driver = null;
//    @Parameters({"safari"})
//    @BeforeMethod
//    public void setup(String browser){
//        WebDriverManager.safaridriver().setup();
//        driver = new SafariDriver();
//        driver.get("http://localhost:9010");
//
//        driver = WebDriverFabric.startBrowser(browser);
//    }

    @Test
    public void thirdTest(){
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
        // driver.get("http://localhost:9010");
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeOnUsernameFieldXPath(USERNAME);
        loginPage.typeOnPasswordFieldCSS(PASSWORD);
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageTitleIsCorrect(5, HomePage.PAGE_TITLE);
        homePage.getCurrentUrl();

        TopMenu topMenu = new TopMenu(driver);

        // add product to basket
        homePage.addProductToCart("Sauce Labs Backpack");

        // finish shopping
        homePage.finishPurchase();

         // check if success
         Assert.assertTrue(homePage.isOrderCompleted(), "Order was not completed successfully.");

         homePage.clickBacHome();

        // check reset in basket
        int cartItemCountAfterCheckout = topMenu.getCartItemCount();
        Assert.assertEquals(cartItemCountAfterCheckout, 0,
                "Number of items in the cart is not reset after completing the purchase.");

        homePage.clickLogoutButton();

        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button is not visible after completing the purchase.");
    }

    @AfterMethod
    public void teardown(){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.quitBrowser();
    }
}






