
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumConfig {

    private WebDriver driver;

    public SeleniumConfig() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    static {
        System.setProperty("webdriver.chrome.driver", "C:/webdriver/chromedriver.exe");
    }

    protected WebDriver getDriver(){
        return driver;
    }

}
