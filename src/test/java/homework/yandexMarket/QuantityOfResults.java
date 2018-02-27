package homework.yandexMarket;

import homework.MainTests;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import yandexMarket.MainPage;
import yandexMarket.ResultsPage;

public class QuantityOfResults extends MainTests {

    private static yandexMarket.MainPage MainPage;
    private static yandexMarket.ResultsPage ResultsPage;

    @BeforeClass
    public void beforeClass(){
        MainPage = PageFactory.initElements(driver, MainPage.class);
        ResultsPage = PageFactory.initElements(driver, ResultsPage.class);
    }

    @Test
    public void getQuantityOfResultsOnSearchPage() {
        driver.get("https://market.yandex.ru/");
        MainPage.searchText("ручка");
        ResultsPage.setResultsQuantityOnPage(12);
        Assert.assertEquals(ResultsPage.getActualQuantityOfResultsOnPage(), 12);
    }
}
