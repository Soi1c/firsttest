package homework.yaRu;

import homework.MainTests;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeatherInPenza extends MainTests {

    @Test
    public void weatherInPenzaFirstLink() {
        driver.get("https://ya.ru/");
        driver.findElement(By.id("text")).sendKeys("погода пенза", Keys.ENTER);
        WebElement firstLink =  driver.findElements(By.className("serp-item")).get(0);
        Assert.assertEquals(firstLink.findElement(By.cssSelector(".link")).getText().contains("Погода в Пензе"), true);
    }
}
