import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddAddressTest {

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

//        driver.findElement(By.id("session_email")).submit();
    }

    @Test
    public void shouldAddAddress() {
//        //Go to creation page using buttons
//        driver.findElement(By.cssSelector("a[data-test='addresses']")).click();
//        driver.findElement(By.cssSelector("a[data-test='create']")).click();

        //Go to creation page using navigate method
        driver.navigate().to("http://a.testaddressbook.com/addresses/new");

        //Fill fields
        driver.findElement(By.id("address_first_name")).sendKeys("John");
        driver.findElement(By.id("address_last_name")).sendKeys("Kennedy");
        driver.findElement(By.id("address_street_address")).sendKeys("1234 Main Street");
        driver.findElement(By.id("address_secondary_address")).sendKeys("1234 Second Street");
        driver.findElement(By.id("address_city")).sendKeys("Columbus");
        driver.findElement(By.id("address_zip_code")).sendKeys("45011");
        driver.findElement(By.id("address_country_us")).click();
        driver.findElement(By.id("address_birthday")).sendKeys("11/30/2000");
        driver.findElement(By.id("address_color")).sendKeys("#2eff66");
        driver.findElement(By.id("address_age")).sendKeys("20");
        driver.findElement(By.id("address_website")).sendKeys("https://www.google.com/");
        driver.findElement(By.id("address_phone")).sendKeys("+3753311122233");
        driver.findElement(By.id("address_interest_climb")).click();
        driver.findElement(By.id("address_note")).sendKeys("A very large number of published documents contain text only.");
        driver.findElement(By.id("address_picture")).sendKeys(PARAMETERS.FILE_PATH);
        Select states = new Select(driver.findElement(By.id("address_state")));
        states.selectByVisibleText("Ohio");

        //Submit
        driver.findElement(By.cssSelector("input[data-test='submit']")).submit();

        //Asserts
        String firstNameText = driver.findElement(By.cssSelector("span[data-test='first_name']")).getText();
        Assert.assertEquals(firstNameText, "John");
        String lastNameText = driver.findElement(By.cssSelector("span[data-test='last_name']")).getText();
        Assert.assertEquals(lastNameText, "Kennedy");
        String streetAddressText = driver.findElement(By.cssSelector("span[data-test='street_address']")).getText();
        Assert.assertEquals(streetAddressText, "1234 Main Street");
        String secondaryAddressText = driver.findElement(By.cssSelector("span[data-test='secondary_address']")).getText();
        Assert.assertEquals(secondaryAddressText, "1234 Second Street");
        String cityText = driver.findElement(By.cssSelector("span[data-test='city']")).getText();
        Assert.assertEquals(cityText, "Columbus");
        String stateText = driver.findElement(By.cssSelector("span[data-test='state']")).getText();
        Assert.assertEquals(stateText, "OH");
        String zipcodeText = driver.findElement(By.cssSelector("span[data-test='zip_code']")).getText();
        Assert.assertEquals(zipcodeText, "45011");
        String countryText = driver.findElement(By.cssSelector("span[data-test='country']")).getText();
        Assert.assertEquals(countryText, "us");
        String birthdayText = driver.findElement(By.cssSelector("span[data-test='birthday']")).getText();
        Assert.assertEquals(birthdayText, "11/30/2000");
        String colorAttribute = driver.findElement(By.cssSelector("span[data-test='color']"))
                .getCssValue("background-color");
        String hexColorAttribute = Color.fromString(colorAttribute).asHex();
        Assert.assertEquals(hexColorAttribute, "#2eff66");
        String ageText = driver.findElement(By.cssSelector("span[data-test='age']")).getText();
        Assert.assertEquals(ageText, "20");
        String websiteText = driver.findElement(By.cssSelector("span[data-test='website']")).getText();
        Assert.assertEquals(websiteText, "https://www.google.com/");
        String phoneText = driver.findElement(By.cssSelector("span[data-test='phone']")).getText();
        Assert.assertEquals(phoneText, "+375331-112-2233");
        String climbText = driver.findElement(By.cssSelector("span[data-test='interest_climb']")).getText();
        Assert.assertEquals(climbText, "Yes");
        String danceText = driver.findElement(By.cssSelector("span[data-test='interest_dance']")).getText();
        Assert.assertEquals(danceText, "No");
        String readText = driver.findElement(By.cssSelector("span[data-test='interest_read']")).getText();
        Assert.assertEquals(readText, "No");
        String noteText = driver.findElement(By.cssSelector("span[data-test='note']")).getText();
        Assert.assertEquals(noteText, "A very large number of published documents contain text only.");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
