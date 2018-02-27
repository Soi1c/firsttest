package homework.yandexRu;

import homework.MainTests;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import yandexRU.MainPage;

public class ChangeLanguage extends MainTests {


    private static MainPage MainPage;

    @BeforeClass
    public void beforeClass(){
        MainPage = PageFactory.initElements(driver, MainPage.class);
    }

    @Test
    public void changeLanguageOfYandexSearchToEnglish() {
        driver.get("https://yandex.ru/");
        MainPage.setLanguageFromSettings("English");
        MainPage.goToLanguageSettings();
        Assert.assertEquals(MainPage.getLocalesSelectButtonText(), "English");
    }
}
