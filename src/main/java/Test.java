import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Test {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\IdeaProjects\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        final String website = "https://demo.applitools.com/app.html";
        final String av = "https://av.by/";
        final String cata = "https://catalog.onliner.by/";
        final String testWeb = "http://a.testaddressbook.com/sign_in";

        driver.get(testWeb);







//        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(10))
//        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='element-search autosuggest-search-activator']/input"))));
//
//        String prop = element.getAttribute("isConnected");
//
//        System.out.println(prop);
//
//        element.sendKeys("I'm searching nothing!");

//        WebElement marksList = driver.findElement(By.name("brand_id[]"));
//        WebElement option = driver.findElement(By.xpath("//*[@id=\"presearch_form\"]/div/div[1]/select/option[5]"));
//        WebElement button = driver.findElement(By.id("submit_presearch"));
//        Select markSelect = new Select(marksList);
//        markSelect.selectByVisibleText("Audi");
//        button.click();

//        WebElement search = driver.findElement(By.className("fast-search__input"));
//
//        search.sendKeys("XiaomiXiaomiXiaomiXiaomiXiaomiXiaomiXiaomiXiaomiXiaomiXiaomiXiaomiXiaomiXiaomiXiaomi", Keys.ENTER);

    }
}
