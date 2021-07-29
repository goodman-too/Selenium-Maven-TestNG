package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import properties.PARAMETERS;


public class Driver {

    private static WebDriver driver;

    private static void initChromeDriver() {
        System.setProperty("webdriver.chrome.driver", PARAMETERS.DRIVER_PATH);
    }

    public static WebDriver getChromeDriver() {
        initChromeDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
