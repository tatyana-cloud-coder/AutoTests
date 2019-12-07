package ru.Beru.Pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.Beru.Settings.WebDriverSettings;

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

    public void OpenShop() {
        driver.get("https://beru.ru/");
    }
    public void checkTextProfileButton(String s) {
        Assert.assertEquals(s,authorButton.getText());
        System.out.print("Ок");
    }
    @Step("Авторизация")
    public void doAuthorization() {
        authorButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")));
    }
    public void checkCity(String s) {
        String CityName = city.getText();
        Assert.assertEquals(CityName,s);
        System.out.println("Ок");
    }
    public void ChangeCity (String s) {
        city.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_2JDvXzYsUI")));    }
}
