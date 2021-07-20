import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RemoveAddressTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", PARAMETERS.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("http://a.testaddressbook.com/sign_in");

        driver.findElement(By.id("session_email")).sendKeys(PARAMETERS.LOGIN);
        driver.findElement(By.id("session_password")).sendKeys(PARAMETERS.PASSWORD);
        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();
    }

    @Test
    public void shouldRemoveAddress() {
        //Go to addresses page
        driver.navigate().to("http://a.testaddressbook.com/addresses");

        //Data for assert
        List<WebElement> trs = driver.findElements(By.xpath("//table[@class='table']/tbody/tr"));
        int numberOfAddresses = trs.size();

        //Delete last address
        driver.findElement(By.xpath("//table[@class='table']/tbody/tr[last()]/td[last()]/a")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        //Assert
        trs = driver.findElements(By.xpath("//table[@class='table']/tbody/tr"));
        Assert.assertEquals(trs.size(), numberOfAddresses - 1);
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
