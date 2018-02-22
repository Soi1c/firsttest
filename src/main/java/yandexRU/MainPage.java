package yandexRU;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By settingsButton = By.cssSelector("a[data-statlog=\"head.settings\"]");
    private By otherSettings = By.cssSelector("a[data-statlog=\"head.settings.other\"]");
    private By languageSettings = By.cssSelector("a[data-statlog=\"tabs.lang\"]");
    private By localesSelectButton = By.cssSelector("div[class=\"option__content\"] > div > button > span[class=\"button__text\"]");

    public String localesSelectButtonText() {
        return driver.findElement(localesSelectButton).getText();
    }


    private void goToOtherSettings() {
        driver.findElement(settingsButton).click();
        driver.findElement(otherSettings).click();
    }

    public void goToLanguageSettings() {
        goToOtherSettings();
        driver.findElement(languageSettings).click();
    }

    public void setLanguageFromSettings(String language) {
        goToLanguageSettings();
        driver.findElement(By.cssSelector("div[class=\"option__content\"] > div > button ")).click();
        driver.findElement(By.xpath("//span[@class=\"select__text\" and text() = \"".concat("English").concat("\"]"))).click();
        driver.findElement(By.cssSelector("button[type = \"submit\"]")).click();
    }
}
