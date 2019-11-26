package ru.Beru.Pages;

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
  private WebElement authorButton;




    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void OpenShop() {
        driver.get("https://beru.ru/");
    }
    public void checkTextProfileButton(String s) {
        authorButton = driver.findElement(By.className("pFhTbV17qj"));
        Assert.assertEquals(s,authorButton.getText());
        System.out.print("Ок");
    }
    public void doAuthorization() {
        authorButton.click();
        //определяем, сколько времени мы будем ждать с0траницу
        WebDriverWait wait = new WebDriverWait(driver, 100000);
        //к которой мы привзались с помощью формы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")));
    }

}
