package week.first;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        super(driver);
    }

    public static final String PAGE_TITLE = "Swag Labs";

    By logOutButton = By.id("logout_sidebar_link");
    public void clickLogoutButton (){
//        // driver.findElement(logOutButton).click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        // WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(logOutButton));
//        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
//        logoutButton.click();

        WebElement menu = driver.findElement((By.id("react-burger-menu-btn")));
        menu.click();

        WebElement logoutButton = driver.findElement(logOutButton);
        logoutButton.click();
    }

    public void clickBacHome() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement backButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("back-to-products")));
        backButton.click();
    }


    public void addProductToCart(String productName) {
        WebElement product = driver.findElement(By.xpath("//div[text()='" + productName + "']"));
        product.click();

        WebElement addToCartButton = driver.findElement(By.xpath("//button[text()='Add to cart']"));
        addToCartButton.click();
    }

    public void finishPurchase() {
        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        cartIcon.click();

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        WebElement firstNameField = driver.findElement(By.id("first-name"));
        firstNameField.sendKeys("Jelisaveta");

        WebElement lastNameField = driver.findElement(By.id("last-name"));
        lastNameField.sendKeys("Gavrilovic");

        WebElement zipCodeField = driver.findElement(By.id("postal-code"));
        zipCodeField.sendKeys("18300");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue")));
        continueButton.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

//        WebElement finishButton = driver.findElement();
        finishButton.click();
    }

    public boolean isOrderCompleted() {
        try {
            WebElement confirmationMessage = driver.findElement(By.xpath("//span[contains(text(), 'Checkout: Complete!')]"));
            return confirmationMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
