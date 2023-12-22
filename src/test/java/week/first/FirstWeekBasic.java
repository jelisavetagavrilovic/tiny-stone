package week.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class FirstWeekBasic {
    @Test
    public void firstTestSuccess(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        WebElement usernameFieldID = driver.findElement(By.id("user-name"));
        usernameFieldID.sendKeys("standard_user");

        WebElement passwordFieldID = driver.findElement(By.id("password"));
        passwordFieldID.sendKeys("secret_sauce");

        WebElement loginButtonID = driver.findElement(By.id("login-button"));
        loginButtonID.click();

        // successful login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        driver.quit();
    }

    @Test
    public void firstTestFail(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        WebElement usernameFieldID = driver.findElement(By.id("user-name"));
        usernameFieldID.sendKeys("locked_out_user");

        WebElement passwordFieldID = driver.findElement(By.id("password"));
        passwordFieldID.sendKeys("secret_sauce");

        WebElement loginButtonID = driver.findElement(By.id("login-button"));
        loginButtonID.click();

        //unsuccessful login
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("inventory.html"));
        } catch (TimeoutException e) {
            WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test=error]"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed for unsuccessful login");
        }
        driver.quit();
    }

    @Test
    public void secondTestSuccess(){
        String USERNAME = "standard_user";
        String PASSWORD = "secret_sauce";

        WebDriverManager.safaridriver().setup();
        WebDriver driver = new SafariDriver();
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeOnUsernameFieldXPath(USERNAME);
        loginPage.typeOnPasswordFieldCSS(PASSWORD);
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageTitleIsCorrect(5, HomePage.PAGE_TITLE);
        homePage.getCurrentUrl();

        homePage.clickLogoutButton();

        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button is not visible after completing the purchase.");
        driver.close();
    }

    @Test
    public void secondTestFail(){
        String USERNAME = "locked_out_user";
        String PASSWORD = "secret_sauce";

        WebDriverManager.safaridriver().setup();
        WebDriver driver = new SafariDriver();
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeOnUsernameFieldXPath(USERNAME);
        loginPage.typeOnPasswordFieldCSS(PASSWORD);
        loginPage.clickOnLoginButton();

        //unsuccessful login
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("inventory.html"));
        } catch (TimeoutException e) {
            WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test=error]"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed for unsuccessful login");
        }
        driver.quit();
    }
}