import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddAddressTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", CONSTANTS.DRIVER_PATH);
        driver = new ChromeDriver();

        driver.get("http://a.testaddressbook.com/sign_in");

        driver.findElement(By.id("session_email")).sendKeys(CONSTANTS.LOGIN);
        driver.findElement(By.id("session_password")).sendKeys(CONSTANTS.PASSWORD);
        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();
    }

    @Test
    public void addAddressTest(){
//        driver.findElement(By.cssSelector("a[data-test='addresses']")).click();
//        driver.findElement(By.cssSelector("a[data-test='create']")).click();
        driver.navigate().to("http://a.testaddressbook.com/addresses/new");
        //
        driver.findElement(By.id("address_first_name")).sendKeys("TEST");
        driver.findElement(By.id("address_last_name")).sendKeys("TEST");
        driver.findElement(By.id("address_street_address")).sendKeys("TEST");
        driver.findElement(By.id("address_secondary_address")).sendKeys("TEST");
        driver.findElement(By.id("address_city")).sendKeys("TEST");
        driver.findElement(By.id("address_zip_code")).sendKeys("TEST");

        Select states = new Select(driver.findElement(By.id("address_state")));
        states.selectByVisibleText("Ohio");


    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
