package week.first;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    
    public LoginPage(WebDriver driver){
        
        super(driver);
        
    }

    private By usernameFieldID  = By.id("username");

    private By usernameFieldName =  By.name("username");

    private By usernameFieldXpath = By.xpath("//input[@type='text']");

    private By passwordFieldCSS = By.cssSelector("input[type='password']");

    private By loginButtonCSS = By.cssSelector("input[value='Login']");

    public void typeOnUsernameFieldName(String username){

        WebElement usernameField =
                driver.findElement(usernameFieldName);

        usernameField.sendKeys(username);
    }

    public void typeOnUsernameFieldID(String username){

        WebElement usernameField =
                driver.findElement(usernameFieldID);

        usernameField.sendKeys(username);
    }

    public void typeOnUsernameFieldXPath(String username){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFieldXpath));

        usernameField.sendKeys(username);
    }

    public void typeOnPasswordFieldCSS(String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldCSS));

        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonCSS));

        loginButton.click();
    }

    public boolean isLoginButtonVisible(){
        return driver.findElement(loginButtonCSS).isDisplayed();
    }
}
