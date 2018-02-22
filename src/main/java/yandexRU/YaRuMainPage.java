package yandexRU;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class YaRuMainPage {
    private final WebDriver driver;

    public YaRuMainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchField = By.id("text");

    public void searchText(String request) {
        driver.findElement(searchField).sendKeys(request, Keys.ENTER);
    }
}
