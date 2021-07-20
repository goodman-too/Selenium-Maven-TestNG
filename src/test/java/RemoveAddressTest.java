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
        //Driver info
        System.setProperty("webdriver.chrome.driver", PARAMETERS.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //Open page
        driver.get("http://a.testaddressbook.com/sign_in");

        //Log In
        driver.findElement(By.id("session_email")).sendKeys(PARAMETERS.LOGIN);
        driver.findElement(By.id("session_password")).sendKeys(PARAMETERS.PASSWORD);
        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();

        //Add address to remove
        driver.findElement(By.cssSelector("a[data-test='addresses']")).click();
        driver.findElement(By.cssSelector("a[data-test='create']")).click();
        driver.findElement(By.id("address_first_name")).sendKeys("Name");
        driver.findElement(By.id("address_last_name")).sendKeys("Last");
        driver.findElement(By.id("address_street_address")).sendKeys("1111 Default Street");
        driver.findElement(By.id("address_zip_code")).sendKeys("01234");
        driver.findElement(By.id("address_city")).sendKeys("Minsk");
        driver.findElement(By.cssSelector("input[data-test='submit']")).click();
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
