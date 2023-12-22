package week.first;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopMenu extends BasePage {

    public TopMenu(WebDriver driver){

        super(driver);
    }

    By logOutButton = By.id("react-burger-menu-btn");


    public void clickTopMenu(){

        driver.findElement(logOutButton).click();
    }

    public int getCartItemCount() {
        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        String itemCountText = cartIcon.findElement(By.className("shopping_cart_link")).getText();

        try {
            return Integer.parseInt(itemCountText);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
