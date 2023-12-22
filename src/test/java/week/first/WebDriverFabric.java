package week.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class WebDriverFabric {

    private static final String URL = "https://www.saucedemo.com/";

    public static WebDriver startBrowser(String browser){

        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")){

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("safari")){

           WebDriverManager.safaridriver().setup();
            driver =  new SafariDriver();

        } else {

            throw new RuntimeException("NO SUCH BROWSER!");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get(URL);

        return driver;
    }

    public static  void closeBrowser(WebDriver driver){

        if (driver != null) {

            driver.quit();
        }
    }


}
