package ru.Beru.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class HomePage  {
  WebDriver driver;
  private WebDriverWait wait;

  public HomePage(WebDriver driver) {
      PageFactory.initElements(driver, this);
      this.driver = driver;
      wait = new WebDriverWait(driver, 10);
    }

    @FindBy(css = "[class=\"pFhTbV17qj\"]")
    private WebElement authorButton;

    @FindBy(xpath = "//span[contains(@data-auto,'region-form-opener')]//span[2]")
    private WebElement city;


    @FindBy(css = "[class=\"_1r1GkezLi0\"]")
    private WebElement beru;

    @FindBy(css = "[class=\"_3odNv2Dw2n\"]")
    private WebElement authB;

    @FindBy(xpath = "//span[contains(@data-auto,'region-form-opener')]//span[2]")
    private WebElement buttonCity;

    @FindBy(css = "[class=\"_1U2ErCeoqP\"]")
    private WebElement popupCity;

    @FindBy(xpath = "//div[contains(@data-auto,'region-popup')] //input[contains(@class,'_2JDvXzYsUI')]")
    private WebElement fieldCity;

    @FindBy(id = "react-autowhatever-region")
    private WebElement listboxCities;

    @FindBy(css = "[class=\"_4qhIn2-ESi Pjv3h3YbYr THqSbzx07u\"]")
    private WebElement buttonOk;

    @FindBy(css = "[class=\"_1FEpprw_Km\"]")
    private WebElement buttonMyProfile;

    @FindBy(css = "[href=\"/my/settings?track=menu\"]")
    private WebElement buttonSettings;

    @FindBy(css = "[class=\"_3RM4_n5whA\"]")
    private WebElement buttonCatalog;

    @FindBy(css = "[class=\"_3JUsAgML4w\"]")
    private WebElement catalog;

    @FindBy(css = "a[title=\"Электрические зубные щетки\"]")
    private WebElement buttonElectricalToothbrushes;

    @FindBy(css = "a[title=\"Красота и гигиена\"]")
    private WebElement buttonBeautyAndHygiene;

    private By firstCityList = By.className("_229JDbp_Z8");
    private By authPage = By.id("passp-field-login");
    private By settingsPage = By.className("_38iDpDiSsi");
    private By beautyPage = By.cssSelector("a[title=\"Красота и гигиена\"]");
    private By brushesPage = By.className("ZsTILNLaud");


    public void openShop() {
        driver.get("https://beru.ru/");
    }

    @Step("Проверка мой профиль")
    public void checkText(String s) {
        Assert.assertEquals(s,authorButton.getText());
        System.out.print("Ок");
    }

    @Step("Авторизация")
    public void doAuthorization() {
        authorButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")));
    }

    @Step("Проверка города")
    public void checkCity(String s) {
        String CityName = city.getText();
        Assert.assertEquals(CityName,s);
        System.out.println("Ок");
    }

    @Step("Смена города")
    public void changeCity(String cityName) {
        buttonCity.click();
        wait.until(ExpectedConditions.visibilityOf(popupCity));
        fieldCity.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);

        for (int i = 0; i < cityName.length(); ++i) {
            fieldCity.sendKeys(Character.toString(cityName.charAt(i)));
            wait.withTimeout(50, TimeUnit.MILLISECONDS);
        }

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(listboxCities));
        WebElement firstCity = listboxCities.findElement(firstCityList);
        wait.until(ExpectedConditions.textToBePresentInElement(firstCity, cityName));
        Assert.assertEquals(cityName, firstCity.getText());
        firstCity.click();
        wait.until(ExpectedConditions.visibilityOf(buttonOk));
        buttonOk.click();
        wait.until(ExpectedConditions.textToBePresentInElement(buttonCity, cityName));
    }

    @Step("Открыть найстройки")
    public void openSettings() {
        authB.click();
        wait.until(ExpectedConditions.visibilityOf(buttonSettings));
        buttonSettings.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(settingsPage));
    }

    @Step("Открыть электрические зубные щетки")
    public void openElectricalToothbrushesPage() {
        buttonCatalog.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(beautyPage));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(beautyPage)).build().perform();
        buttonElectricalToothbrushes.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(brushesPage));
    }

}
