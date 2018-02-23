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
    private By decreasingPriceSortingIcon = By.cssSelector("div[class*=sorter_sort_desc]");

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

    public boolean isPriceSortingDecreasing() { return decreasingPriceSortingIcon != null; }

    public void sortByPrice() {
        driver.findElement(sortByPrice).click();
    }

    public boolean isPriceSortingCorrect(String sorting) throws Exception {
/*        List<WebElement> resultsSet = resultsSet();
        List<Integer> pricesBySorting = new ArrayList<Integer>();
        for (int i = 0; i < resultsSet.size(); i++) {
            WebElement roublesSymbolPosition1 = resultsSet.get(i);
        String roublesSymbolPosition2 = roublesSymbolPosition1.findElement(By.cssSelector("span[class=\"price\"]")).getText();
        int roublesSymbolPosition3 = roublesSymbolPosition2.indexOf("&nbsp;");
            pricesBySorting.add(Integer.parseInt(resultsSet.get(i).findElement(By.cssSelector("span[class=\"price\"]"))
                    .getText().substring(0, roublesSymbolPosition3)));
        }*/
        List<WebElement> resultsSet = resultsSet();
        List<Integer> priceValues = new ArrayList<Integer>();
        for (int i = 0; i < resultsSet.size(); i++) {
            String price = resultsSet.get(i).findElement(By.className("price")).getText();
            int roublesSymbolPosition = price.indexOf("\u20BD");
            priceValues.add(Integer.parseInt(price.substring(0, roublesSymbolPosition - 1)));
        }
        List<Integer> sortedList = priceValues;

        if (sorting.equals("uprising")) {

            Collections.sort(priceValues);

            return (priceValues == sortedList);
        } else if (sorting.equals("decreasing")) {
            Collections.sort(priceValues, Collections.reverseOrder());

            return (priceValues == sortedList);
        } else throw (new Exception("specify correct sorting"));
    }
}