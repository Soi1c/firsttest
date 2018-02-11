package homework.h1.autotests.yaRu;

import homework.h1.autotests.MainTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeLanguage extends MainTests {

    @Test
    public void changeLanguageOnYaRu() {
        driver.get("https://yandex.ru/");
        //Настройка языка
        driver.findElement(By.cssSelector("a[data-statlog=\"head.settings\"]")).click();
        driver.findElement(By.cssSelector("a[data-statlog=\"head.settings.other\"]")).click();
        driver.findElement(By.cssSelector("a[data-statlog=\"tabs.lang\"]")).click();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver)
                    .executeScript("$('select[name=intl]').val('en').closest('form').submit()");
        }

        //Проверка языка
        driver.findElement(By.cssSelector("a[data-statlog=\"head.settings\"]")).click();
        driver.findElement(By.cssSelector("a[data-statlog=\"head.settings.other\"]")).click();
        driver.findElement(By.cssSelector("a[data-statlog=\"tabs.lang\"]")).click();
        String s = driver.findElement(By.cssSelector("div[class=\"option__content\"] > div > button > span[class=\"button__text\"]")).getText();

        Assert.assertEquals(s, "English");
    }
}
