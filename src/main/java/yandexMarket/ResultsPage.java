package yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    private List<WebElement> getResultsSet() {
        return driver.findElements(resultLink);
    }

    //Возможные значения:12, 48
    public void setResultsQuantityOnPage(int quantity) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(quantitySelecter));
        actions.perform();
        driver.findElement(quantitySelecter).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class=\"select__text\" and text() = \"".concat("Показывать по ".concat(String.valueOf(quantity))
                .concat("\"]")))).click();
        Thread.sleep(2000);
    }

    public int getActualQuantityOfResultsOnPage() {
        return getResultsSet().size();
    }

    public boolean isPriceSortingUprising() {
        return uprisingPriceSortingIcon != null;
    }

    public boolean isPriceSortingDecreasing() { return decreasingPriceSortingIcon != null; }

    public void sortByPrice() {
        driver.findElement(sortByPrice).click();
    }

    public boolean isPriceSortingCorrect(List<Integer> listToCheckSorting, String sorting) throws Exception {
        Thread.sleep(2000);
        List<Integer> sortedList = listToCheckSorting;

        if (sorting.equals("uprising")) {
            Collections.sort(listToCheckSorting);
            return (listToCheckSorting == sortedList);
        }
        else if (sorting.equals("decreasing"))
        {
            Collections.sort(listToCheckSorting, Collections.reverseOrder());
            return (listToCheckSorting == sortedList);
        }
        else throw (new Exception("specify correct sorting"));
    }

    public List<Integer> getPricesOnSearchPage() throws InterruptedException {
        List<WebElement> resultsSet = getResultsSet();
        List<Integer> priceValues = new ArrayList<Integer>();
        WebElement explicitWaitForPrice = (new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.className("price"))));
        Thread.sleep(3000);
        for (int i = 0; i < resultsSet.size(); i++) {
            String price = resultsSet.get(i).findElement(By.cssSelector("div[class = \"price\"]")).getText();
            price = price.replaceAll("\\s","");
            int roublesSymbolPosition = price.indexOf("\u20BD");
            priceValues.add(Integer.parseInt(price.substring(0, roublesSymbolPosition)));
        }
        return priceValues;
    }
}