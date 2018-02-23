package homework.yandexMarket;

import homework.MainTests;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import yandexMarket.MainPage;
import yandexMarket.ResultsPage;

public class ResultsSorting extends MainTests {

    private static yandexMarket.MainPage MainPage;
    private static yandexMarket.ResultsPage ResultsPage;

    @BeforeClass
    public void beforeClass(){
        MainPage = PageFactory.initElements(driver, yandexMarket.MainPage.class);
        ResultsPage = PageFactory.initElements(driver, yandexMarket.ResultsPage.class);
    }

    @Test
    public void getQuantityOfResultsOnSearchPage() {
        driver.get("https://market.yandex.ru/");
        MainPage.searchText("ручка");
        ResultsPage.setResultsQuantityOnPage(12);
        Assert.assertEquals(ResultsPage.actualQuantityOfResultsOnPage(), 12);
    }

    @Test
    public void uprisingSorting() throws Exception {
        driver.get("https://market.yandex.ru/");
        MainPage.searchText("ручка");
        ResultsPage.sortByPrice();
        Assert.assertTrue(ResultsPage.isPriceSortingUprising());
        Assert.assertTrue(ResultsPage.isPriceSortingCorrect("uprising"));
    }

    @Test
    public void decreasingSorting() throws Exception {
        driver.get("https://market.yandex.ru/");
        MainPage.searchText("ручка");
        ResultsPage.sortByPrice();
        ResultsPage.sortByPrice();
        Assert.assertTrue(ResultsPage.isPriceSortingDecreasing());
        Assert.assertTrue(ResultsPage.isPriceSortingCorrect("decreasing"));
    }
}
