package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//
//
//
import java.time.Duration;
import java.util.List;

import static junit.framework.Assert.assertTrue;

public class SimpleWebTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void initWebDriver(){
        driver=new ChromeDriver();
        wait= new WebDriverWait(driver, Duration.ofSeconds(30));

    }
    @Test
    public void searchDuckDuckGo(){
        //load the page
        driver.get("https://duckduckgo.com/");

        //Enter search phrase
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("giant panda");
        //Click search button

        WebElement searchButton = driver.findElement(By.id("search_button"));
        searchButton.click();

        //Wait for results to appear
        wait.until(ExpectedConditions.titleContains("giant panda"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.results_links_deep a.result__a")));


        //Make sure each result contains the word "panda"
        List<WebElement> resultLinks= driver.findElements(By.cssSelector("div.results_links_deep a.result_a"));
        for (WebElement link : resultLinks){
            assertTrue(link.getText().matches("(?i).*panda.*"));
        }
    }
    @AfterEach
    public void quitWebDriver(){
        driver.quit();
    }
}
