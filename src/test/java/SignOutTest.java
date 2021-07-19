import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignOutTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", PARAMETERS.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://a.testaddressbook.com/sign_in");

        driver.findElement(By.id("session_email")).sendKeys(PARAMETERS.LOGIN);
        driver.findElement(By.id("session_password")).sendKeys(PARAMETERS.PASSWORD);
        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();
    }

    @Test
    public void shouldSignOut() {
        //Sign out
        driver.findElement(By.xpath("//a[@data-test='sign-out']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.xpath("//a[@data-test='sign-in']")).getText(), "Sign in");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
