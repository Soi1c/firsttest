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
        driver.findElement(By.cssSelector("a[data-statlog=\"head.settings\"]")).click();
        driver.findElement(By.cssSelector("a[data-statlog=\"head.settings.other\"]")).click();
        driver.findElement(By.cssSelector("a[data-statlog=\"tabs.lang\"]")).click();
        driver.findElement(By.cssSelector("div[class=\"option__content\"] > div > button ")).click();
        driver.findElement(By.xpath("//span[@class=\"select__text\" and text() = \"English\"]")).click();
        driver.findElement(By.cssSelector("button[type = \"submit\"]")).click();
        driver.findElement(By.cssSelector("a[data-statlog=\"head.settings\"]")).click();
        driver.findElement(By.cssSelector("a[data-statlog=\"head.settings.other\"]")).click();
        driver.findElement(By.cssSelector("a[data-statlog=\"tabs.lang\"]")).click();
        String localesSelectButtonText = driver.findElement(By.cssSelector("div[class=\"option__content\"] > div > button > span[class=\"button__text\"]")).getText();

        Assert.assertEquals(localesSelectButtonText, "English");
    }
}
