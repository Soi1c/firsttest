package yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private By searchField = By.id("header-search");

    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchText(String request) {
        driver.findElement(searchField).sendKeys(request, Keys.ENTER);
    }

}

