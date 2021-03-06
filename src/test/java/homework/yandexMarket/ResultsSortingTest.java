package homework.yandexMarket;

import homework.MainTests;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class ResultsSortingTest extends MainTests {

    private static yandexMarket.MainPage MainPage;
    private static yandexMarket.ResultsPage ResultsPage;

    @BeforeClass
    public void beforeClass(){
        MainPage = PageFactory.initElements(driver, yandexMarket.MainPage.class);
        ResultsPage = PageFactory.initElements(driver, yandexMarket.ResultsPage.class);
    }

    @Test
    public void uprisingSortingTest() throws Exception {
        driver.get("https://market.yandex.ru/");
        MainPage.searchText("ручка");
        ResultsPage.setResultsQuantityOnPage(12);
        ResultsPage.sortByPrice();
        Assert.assertTrue(ResultsPage.isPriceSortingUprising());
        Assert.assertTrue(ResultsPage.isPriceSortingCorrect(ResultsPage.getPricesOnSearchPage(),"uprising"));
    }

    @Test
    public void decreasingSortingTest() throws Exception {
        driver.get("https://market.yandex.ru/");
        MainPage.searchText("ручка");
        ResultsPage.setResultsQuantityOnPage(12);
        ResultsPage.sortByPrice();
        ResultsPage.sortByPrice();
        Assert.assertTrue(ResultsPage.isPriceSortingDecreasing());
        Assert.assertTrue(ResultsPage.isPriceSortingCorrect(ResultsPage.getPricesOnSearchPage(), "decreasing"));
    }
}
