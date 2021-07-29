package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import properties.PARAMETERS;
import utils.Driver;
import utils.LoginPage;
import utils.StaticDataProvider;


public class LoginTests {

    WebDriver driver;
    LoginPage lp;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getChromeDriver();
        lp = new LoginPage(driver);
    }

    @Test
    public void shouldLogin() {
        lp.login(PARAMETERS.LOGIN, PARAMETERS.PASSWORD);

        String assertText = driver.findElement(By.xpath("//div[@class='text-center']/h1")).getText();
        Assert.assertEquals(assertText, "Welcome to Address Book");
    }

    @Test(dataProvider = "wrongLogin", dataProviderClass = StaticDataProvider.class)
    public void shouldNotLogin(String login, String password) {
        lp.login(login, password);

        String assertText = driver.findElement(By.cssSelector("div[data-test='notice']")).getText();
        Assert.assertEquals(assertText, "Bad email or password.");
    }

    @Test
    public void shouldSignOut() {
        //Log in
        lp.login(PARAMETERS.LOGIN, PARAMETERS.PASSWORD);

        //Sign out
        driver.findElement(By.xpath("//a[@data-test='sign-out']")).click();

        //Assert
        String assertText = driver.findElement(By.xpath("//a[@data-test='sign-in']")).getText();
        Assert.assertEquals(assertText, "Sign in");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
