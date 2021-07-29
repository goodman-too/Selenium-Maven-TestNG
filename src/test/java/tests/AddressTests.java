package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

import properties.PARAMETERS;
import utils.Driver;
import utils.LoginPage;


public class AddressTests {

    WebDriver driver = Driver.getChromeDriver();

    @BeforeTest
    public void setUp() {
        LoginPage lp = new LoginPage(driver);
        lp.login(PARAMETERS.LOGIN, PARAMETERS.PASSWORD);
    }

    @Test
    public void addAddressTest() {
        //Go to creation page using buttons
        driver.findElement(By.cssSelector("a[data-test='addresses']")).click();
        driver.findElement(By.cssSelector("a[data-test='create']")).click();

//        //Go to creation page using navigate method
//        driver.navigate().to("http://a.testaddressbook.com/addresses/new");

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
//        driver.findElement(By.id("address_picture")).sendKeys(PARAMETERS.FILE_PATH);
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

    @Test
    public void editAddressTest() {
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

    @Test
    public void removeAddressTest() {
        //Add address to remove
        driver.navigate().to("http://a.testaddressbook.com/addresses/new");

        driver.findElement(By.id("address_first_name")).sendKeys("Name");
        driver.findElement(By.id("address_last_name")).sendKeys("Last");
        driver.findElement(By.id("address_street_address")).sendKeys("1111 Default Street");
        driver.findElement(By.id("address_zip_code")).sendKeys("01234");
        driver.findElement(By.id("address_city")).sendKeys("Minsk");
        driver.findElement(By.cssSelector("input[data-test='submit']")).click();

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
    public void tearDown() {
        driver.quit();
    }
}
