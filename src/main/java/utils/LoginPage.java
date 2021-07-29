package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    By loginLocator = By.id("session_email");
    By passwordLocator = By.id("session_password");
    By submitButtonLocator = By.cssSelector("input[class='btn btn-primary']");

    private final WebDriver driver;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String login, String password) {
        driver.get("http://a.testaddressbook.com/sign_in");

        driver.findElement(loginLocator).sendKeys(login);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(submitButtonLocator).click();
    }
}
