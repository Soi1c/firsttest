package yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultsPage {
    private By resultLink = By.cssSelector("div[class*=\"n-snippet-cell2\"][data-bem*=\"type\\\":\\\"offer\\\"\"]");
    private By sortByPrice = By.cssSelector("div[data-bem*=\"\\\"id\\\":\\\"aprice\\\"\"]");
    private By quantitySelecter = By.cssSelector("button[role = \"listbox\"]");
    private By uprisingPriceSortingIcon = By.cssSelector("div[class*=sorter_sort_asc]");
    private By decreasingPriceSortingIcon = By.cssSelector("div[class*=sorter_sort_desc]");

    private final WebDriver driver;
    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    private List<WebElement> resultsSet() {
        return driver.findElements(resultLink);
    }

    //Возможные значения:12, 24
    public void setResultsQuantityOnPage(int quantity) {
        driver.findElement(quantitySelecter).click();
        driver.findElement(By.xpath("//span[@class=\"select__text\" and text() = \"".concat("Показывать по ".concat(String.valueOf(quantity))
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
        Thread.sleep(2000);
        List<WebElement> resultsSet = resultsSet();
        List<Integer> priceValues = new ArrayList<Integer>();
        WebElement explicitWaitForPrice = (new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.className("price"))));
        for (int i = 0; i < resultsSet.size(); i++) {
            String price = resultsSet.get(i).findElement(By.className("price")).getText();
            price = price.replaceAll("\\s","");
            int roublesSymbolPosition = price.indexOf("\u20BD");
            priceValues.add(Integer.parseInt(price.substring(0, roublesSymbolPosition)));
        }
        List<Integer> sortedList = priceValues;

        if (sorting.equals("uprising")) {
            Collections.sort(priceValues);
            return (priceValues == sortedList);
        }
        else if (sorting.equals("decreasing"))
        {
            Collections.sort(priceValues, Collections.reverseOrder());
            return (priceValues == sortedList);
        }
        else throw (new Exception("specify correct sorting"));
    }
}