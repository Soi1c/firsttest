package yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultsPage {
    private final WebDriver driver;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By resultLink = By.cssSelector("div[class*=\"n-snippet-cell2\"][data-bem*=\"type\\\":\\\"offer\\\"\"]");
    private By sortByPrice = By.cssSelector("div[data-bem*=\"\\\"id\\\":\\\"aprice\\\"\"]");
    private By quantitySelecter = By.cssSelector("button[role = \"listbox\"]");
    private By uprisingPriceSortingIcon = By.cssSelector("div[class*=sorter_sort_asc]");

    private List<WebElement> resultsSet() {
        return driver.findElements(resultLink);
    }

    //Возможные значения:12, 24
    public void setResultsQuantityOnPage(int quantity) {
        driver.findElement(quantitySelecter).click();
        driver.findElement(By.xpath("//span[@class=\"button__text\" and text() = \"".concat("Показывать по ".concat(String.valueOf(quantity))
                .concat("\"]")))).click();
    }

    public int actualQuantityOfResultsOnPage() {
        return resultsSet().size();
    }

    public boolean isPriceSortingUprising() {
        return uprisingPriceSortingIcon != null;
    }

    public void sortByPrice() {
        driver.findElement(sortByPrice).click();
    }

    public boolean isPriceSortingCorrect(String sorting) {
        List<WebElement> resultsSet = resultsSet();
        List<Integer> pricesBySorting = new ArrayList<Integer>();
        for (int i = 0; i < resultsSet.size(); i++) {
            int roublesSymbolPosition = resultsSet.get(i).findElement(By.cssSelector("By.cssSelector(\"div[class = \\\"n-snippet-cell2__main-price\\\"] > a > div\""))
                    .getText().indexOf("\u20BD") - 1;
            pricesBySorting.add(Integer.parseInt(resultsSet.get(i).findElement(By.cssSelector("By.cssSelector(\"div[class = \\\"n-snippet-cell2__main-price\\\"] > a > div\""))
                    .getText().substring(0, roublesSymbolPosition)));
        }
        List<Integer> sortedList = pricesBySorting;

        Collections.sort(pricesBySorting);

        return (pricesBySorting == sortedList);
    }

}