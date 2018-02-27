package yandexRU;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage {
    private By resultLink = By.className("serp-item");

    private final WebDriver driver;
    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    private List<WebElement> getResultsSet() {
        return driver.findElements(resultLink);
    }

    public String textOfLinkFromResultPage(int numberOfLink) {
        return getResultsSet().get(numberOfLink - 1).getText();
    }
}
