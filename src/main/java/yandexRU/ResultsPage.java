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

    private List<WebElement> resultsSet() {
        return driver.findElements(resultLink);
    }

    private WebElement linkFromResultsPage(int numberOfLink) {
        return resultsSet().get(numberOfLink - 1);
    }

    public String textOfLinkFromResultPage(int numberOfLink) {
        return resultsSet().get(numberOfLink - 1).getText();
    }
}
