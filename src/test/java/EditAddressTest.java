import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class EditAddressTest {

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
    public void shouldEditAddress() {
        //Go to edit page
        driver.navigate().to("http://a.testaddressbook.com/addresses");
        driver.findElement(By.xpath("//table[@class='table']/tbody/tr[last()]/td[last()-1]/a")).click();

        //Update fields
        WebElement firstNameField = driver.findElement(By.id("address_first_name"));
        firstNameField.clear();
        firstNameField.sendKeys("Harry");

        WebElement lastNameField = driver.findElement(By.id("address_last_name"));
        lastNameField.clear();
        lastNameField.sendKeys("Kane");

        WebElement streetAddressField = driver.findElement(By.id("address_street_address"));
        streetAddressField.clear();
        streetAddressField.sendKeys("1111 Ball Street");

        //Submit
        driver.findElement(By.cssSelector("input[data-test='submit']")).click();

        //Asserts
        String firstNameText = driver.findElement(By.cssSelector("span[data-test='first_name']")).getText();
        Assert.assertEquals(firstNameText, "Harry");
        String lastNameText = driver.findElement(By.cssSelector("span[data-test='last_name']")).getText();
        Assert.assertEquals(lastNameText, "Kane");
        String streetAddressText = driver.findElement(By.cssSelector("span[data-test='street_address']")).getText();
        Assert.assertEquals(streetAddressText, "1111 Ball Street");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
