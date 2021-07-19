import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", CONSTANTS.DRIVER_PATH);
        driver = new ChromeDriver();
    }

    @Test
    public void shouldLogin() {
        driver.get("http://a.testaddressbook.com/sign_in");

        driver.findElement(By.id("session_email")).sendKeys(CONSTANTS.LOGIN);
        driver.findElement(By.id("session_password")).sendKeys(CONSTANTS.PASSWORD);
        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();

        String name = driver.findElement(By.cssSelector("span.navbar-text")).getText();
        Assert.assertEquals(name, "o_p@mail.com");
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
