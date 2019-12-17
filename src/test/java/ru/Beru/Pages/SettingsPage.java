package ru.Beru.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SettingsPage {
    WebDriver driver;
    WebDriverWait wait;

    public SettingsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(xpath = "//span[contains(@data-auto,'region-form-opener')]//span[2]")
    private WebElement city;

    @FindBy(xpath = "//span[contains(@data-auto,'region')]//span[1]//span[1]")
    private WebElement citySettings;

    @Step("Сверяем города")
    public void checkCityCorrect() {
        Assert.assertEquals(city.getText(), citySettings.getText());
    }
}
