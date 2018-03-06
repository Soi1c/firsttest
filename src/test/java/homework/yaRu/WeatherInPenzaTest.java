package homework.yaRu;

import org.openqa.selenium.support.PageFactory;
import homework.MainTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import yandexRU.YaRuMainPage;
import yandexRU.ResultsPage;

public class WeatherInPenzaTest extends MainTests {

    private static YaRuMainPage yaRuMainPage;
    private static ResultsPage resultsPage;

    @BeforeClass
    public void beforeClass(){
        yaRuMainPage = PageFactory.initElements(driver, YaRuMainPage.class);
        resultsPage = PageFactory.initElements(driver, ResultsPage.class);
    }

    @Test
    public void checkWeatherInPenzaByFirstLinkTest() {
        driver.get("https://ya.ru/");
        yaRuMainPage.searchText("погода пенза");
        Assert.assertTrue(resultsPage.textOfLinkFromResultPage(1).contains("Погода в Пензе"));
    }
}
