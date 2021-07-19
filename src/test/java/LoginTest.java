import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", PARAMETERS.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void shouldLogin() {
        driver.get("http://a.testaddressbook.com/sign_in");

        driver.findElement(By.id("session_email")).sendKeys(PARAMETERS.LOGIN);
        driver.findElement(By.id("session_password")).sendKeys(PARAMETERS.PASSWORD);
        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();

        String welcomeText = driver.findElement(By.xpath("//div[@class='text-center']/h1")).getText();
        Assert.assertEquals(welcomeText, "Welcome to Address Book");
    }

    @Test
    public void shouldNotLogin() {
        driver.get("http://a.testaddressbook.com/sign_in");

        driver.findElement(By.id("session_email")).sendKeys("wwwww@wwwww.www");
        driver.findElement(By.id("session_password")).sendKeys("asd12eac");
        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();

        String noticeText = driver.findElement(By.cssSelector("div[data-test='notice']")).getText();
        Assert.assertEquals(noticeText, "Bad email or password.");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
